package controller.view.edit.panel.base.evaluation;

import controller.view.panel.ControllerPanel;
import funct.evaluation.Evaluation;
import funct.evaluation.base.EvaluationDiagram;
import funct.evaluation.base.EvaluationProject;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.script.ScriptException;
import model.structural.base.Diagram;
import model.structural.base.Project;
import model.structural.base.evaluation.Metric;
import view.panel.base.evaluation.PanelBaseEvaluation;
import view.message.ViewError;

/**
 * <p>Class of Controller <b>ControllerPanelBaseEvaluation</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseEvaluation</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  23/10/2019
 * @see    controller.view.panel.ControllerPanel
 * @see    view.panel.base.evaluation.PanelBaseEvaluation
 */
public class ControllerPanelBaseEvaluation extends ControllerPanel {
    private final PanelBaseEvaluation panelBaseEvaluation;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Evaluation.
     */
    public ControllerPanelBaseEvaluation(PanelBaseEvaluation panel) {
        super(panel);
        this.panelBaseEvaluation = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.evaluate();
        if (this.panelBaseEvaluation.getBackButton().equals(event.getSource()))
            this.panelBaseEvaluation.getViewNewMeasure().removePanelBaseEvaluation();
        else if (this.panelBaseEvaluation.getNextButton().equals(event.getSource()))
            this.panelBaseEvaluation.getViewNewMeasure().getController().insert();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {}
    
    @Override
    public void keyReleased(KeyEvent event) {}
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    private Project getProject() {
        return this.panelBaseEvaluation.getProject();
    }
    
    /**
     * Method responsible for returning the Diagram selected.
     * @return Diagram selected.
     */
    private Diagram getDiagram() {
        return (Diagram) this.panelBaseEvaluation.getTargetComboBox().getSelectedItem();
    }
    
    /**
     * Method responsible for Evaluate the Target by Operation.
     */
    public void evaluate() {
        Object target = this.panelBaseEvaluation.getTargetComboBox().getSelectedItem();
        Metric metric = this.panelBaseEvaluation.getMeasure().getMetric();
        try {
            if (target.toString().equalsIgnoreCase("Project"))
                this.evaluate(this.getProject(), metric);
            else if (target instanceof Diagram)
                this.evaluate(this.getDiagram(), metric);
        }catch (ScriptException exception) {
            new ViewError(this.panelBaseEvaluation.getViewNewMeasure(), "Error to Apply Metric!").setVisible(true);
        }
    }
    
    /**
     * Method responsible for evaluate the Project by a Metric.
     * @param  project Project.
     * @param  metric Metric.
     * @throws ScriptException Exception to Evaluate the Project.
     */
    public void evaluate(Project project, Metric metric) throws ScriptException {
        Evaluation evaluation = new EvaluationProject(project);
        Double     finalValue = evaluation.getFinalValue(metric.getOperation());
        this.panelBaseEvaluation.getValueTextField().setText(Double.toString(finalValue));
        this.panelBaseEvaluation.updateDetails(evaluation.getObjects());
    }
    
    /**
     * Method responsible for evaluate the Diagram by a Metric.
     * @param  diagram Diagram.
     * @param  metric Metric.
     * @throws ScriptException Exception to Evaluate the Diagram.
     */
    public void evaluate(Diagram diagram, Metric metric) throws ScriptException {
        Evaluation evaluation = new EvaluationDiagram(diagram);
        Double     finalValue = evaluation.getFinalValue(metric.getOperation());
        this.panelBaseEvaluation.getValueTextField().setText(Double.toString(finalValue));
        this.panelBaseEvaluation.updateDetails(evaluation.getObjects());
    }
}