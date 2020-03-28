package controller.view.edit.panel.base.evaluation;

import controller.view.ControllerPanel;
import funct.evaluation.base.EvaluationDiagram;
import funct.evaluation.base.EvaluationProject;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.script.ScriptException;
import model.structural.base.Diagram;
import model.structural.base.Project;
import model.structural.base.evaluation.Metric;
import view.edit.panel.base.evaluation.PanelBaseEvaluation;
import view.message.ViewError;

/**
 * <p>Class of Controller <b>ControllerPanelBaseEvaluation</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseEvaluation</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  23/10/2019
 * @see    controller.view.ControllerPanel
 * @see    view.edit.panel.base.evaluation.PanelBaseEvaluation
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
        this.update();
        if (this.panelBaseEvaluation.getBackButton().equals(event.getSource()))
            this.panelBaseEvaluation.getViewNewMeasure().removePanelBaseEvaluation();
        else if (this.panelBaseEvaluation.getNextButton().equals(event.getSource()))
            this.panelBaseEvaluation.getViewNewMeasure().getController().insert();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        this.update();
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
        this.update();
    }
    
    private void evaluate() {
        Object target = this.panelBaseEvaluation.getTargetComboBox().getSelectedItem();
        Metric metric = this.panelBaseEvaluation.getMeasure().getMetric();
        try {
            if (target.toString().equalsIgnoreCase("Project"))
                this.evaluate(this.panelBaseEvaluation.getProject().getEvaluation(), metric);
            else if (target instanceof Diagram)
                this.evaluate(((Diagram) target).getEvaluation(), metric);
        }catch (ScriptException exception) {
            new ViewError(this.panelBaseEvaluation.getViewNewMeasure(), "Error to Apply Metric!").setVisible(true);
        }
    }
    
    /**
     * Method responsible for evaluate the Project by a Metric.
     * @param  evaluation Evaluation Project.
     * @param  metric Metric.
     * @throws ScriptException 
     */
    private void evaluate(EvaluationProject evaluation, Metric metric) throws ScriptException {
        System.out.println("AA");
        Double value = evaluation.getFinalValue(metric.getOperation());
        this.panelBaseEvaluation.getValueTextField().setText(Double.toString(value));
        this.panelBaseEvaluation.updateDetails(evaluation.getObjects());
    }
    
    /**
     * Method responsible for evaluate the Diagram by a Metric.
     * @param  evaluation Evaluation Diagram.
     * @param  metric Metric.
     * @throws ScriptException 
     */
    private void evaluate(EvaluationDiagram evaluation, Metric metric) throws ScriptException {
        Double value = evaluation.getFinalValue(metric.getOperation());
        this.panelBaseEvaluation.getValueTextField().setText(Double.toString(value));
        this.panelBaseEvaluation.updateDetails(evaluation.getObjects());
    }
    
    /**
     * Method responsible for setting the Evaluation Values.
     */
    private void update() {
        this.evaluate();
//        this.panelBaseEvaluation.getViewMenu().getPanelModeling().updateDiagram(this.panelBaseEvaluation.getDiagram());
        this.panelBaseEvaluation.getViewMenu().getPanelProject().getPanelTree().updateUI(); 
        this.panelBaseEvaluation.getViewMenu().setSave(false);
    }
}