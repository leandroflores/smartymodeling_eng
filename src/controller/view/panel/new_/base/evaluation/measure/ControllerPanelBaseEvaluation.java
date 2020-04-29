package controller.view.panel.new_.base.evaluation.measure;

import funct.evaluation.base.EvaluationBase;
import funct.evaluation.base.EvaluationDiagram;
import funct.evaluation.base.EvaluationProject;
import java.awt.event.ActionEvent;
import javax.script.ScriptException;
import model.structural.base.Diagram;
import model.structural.base.Project;
import model.structural.base.evaluation.Metric;
import view.panel.new_.base.evaluation.measure.PanelBaseEvaluation;
import view.modal.message.ViewError;

/**
 * <p>Class of Controller <b>ControllerPanelBaseEvaluation</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseEvaluation</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-23
 * @see    controller.view.panel.new_.base.evaluation.measure.ControllerPanelBase
 * @see    view.panel.new_.base.evaluation.measure.PanelBaseEvaluation
 */
public class ControllerPanelBaseEvaluation extends ControllerPanelBase {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Evaluation.
     */
    public ControllerPanelBaseEvaluation(PanelBaseEvaluation panel) {
        super(panel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.refresh();
        super.actionPerformed(event);
    }
    
    @Override
    protected void return_() {
        this.getPanel().getPanelNew().removePanelBaseEvaluation();
    }
    
    @Override
    public boolean check() {
        return !this.getPanel().getValueTextField().getText().equals("");
    }
    
    /**
     * Method responsible for returning the Diagram selected.
     * @return Diagram selected.
     */
    private Diagram getDiagram() {
        return (Diagram) this.getPanel().getTargetComboBox().getSelectedItem();
    }
    
    @Override
    public void refresh() {
        super.refresh();
        Object target = this.getPanel().getTargetComboBox().getSelectedItem();
        Metric metric = this.getPanel().getMeasure().getMetric();
        try {
            if (target.toString().equalsIgnoreCase("Project"))
                this.evaluate(this.getProject(), metric);
            else if (target instanceof Diagram)
                this.evaluate(this.getDiagram(), metric);
        }catch (ScriptException exception) {
            new ViewError(this.getViewNew(), "Error to Apply Metric!").setVisible(true);
        }catch (Exception exception) {
            new ViewError(this.getViewNew(), "Error to Apply Metric!").setVisible(true);
        }
    }
    
    /**
     * Method responsible for evaluate the Project by a Metric.
     * @param  project Project.
     * @param  metric Metric.
     * @throws ScriptException Exception to Evaluate the Project.
     */
    public void evaluate(Project project, Metric metric) throws ScriptException {
        EvaluationBase evaluation = new EvaluationProject(project);
        this.getPanel().getValueTextField().setText(Double.toString(evaluation.getFinalValue(metric.getOperation())));
        this.getPanel().updateDetails(evaluation.getResult());
    }
    
    /**
     * Method responsible for evaluate the Diagram by a Metric.
     * @param  diagram Diagram.
     * @param  metric Metric.
     * @throws ScriptException Exception to Evaluate the Diagram.
     */
    public void evaluate(Diagram diagram, Metric metric) throws ScriptException {
        EvaluationBase evaluation = new EvaluationDiagram(diagram);
        this.getPanel().getValueTextField().setText(Double.toString(evaluation.getFinalValue(metric.getOperation())));
        this.getPanel().updateDetails(evaluation.getResult());
    }
    
    @Override
    public void next() {}
    
    @Override
    protected void update() {
        this.getMeasure().setValue(this.getDouble(this.getPanel().getValueTextField()));
    }
    
    @Override
    public PanelBaseEvaluation getPanel() {
        return (PanelBaseEvaluation) this.panel;
    }
}