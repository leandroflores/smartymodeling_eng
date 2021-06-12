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
        refresh();
        super.actionPerformed(event);
    }
    
    @Override
    protected void return_() {
        getPanel().getPanelNew().removePanelBaseEvaluation();
    }
    
    @Override
    public boolean check() {
        return !getPanel().getValueTextField().getText().equals("");
    }
    
    /**
     * Method responsible for returning the Diagram selected.
     * @return Diagram selected.
     */
    private Diagram getDiagram() {
        return (Diagram) getPanel().getTargetComboBox().getSelectedItem();
    }
    
    @Override
    public void refresh() {
        super.refresh();
        Object target = getPanel().getTargetComboBox().getSelectedItem();
        Metric metric = getPanel().getMeasure().getMetric();
        try {
            if (target.toString().equalsIgnoreCase("Project"))
                evaluate(getProject(), metric);
            else if (target instanceof Diagram)
                evaluate(getDiagram(), metric);
        }catch (ScriptException exception) {
            new ViewError(getViewNew(), "Error to Apply Metric!").setVisible(true);
        }catch (Exception exception) {
            new ViewError(getViewNew(), "Error to Apply Metric!").setVisible(true);
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
        getPanel().getValueTextField().setText(Double.toString(evaluation.getFinalValue(metric.getOperation())));
        getPanel().updateDetails(evaluation.getResult());
    }
    
    /**
     * Method responsible for evaluate the Diagram by a Metric.
     * @param  diagram Diagram.
     * @param  metric Metric.
     * @throws ScriptException Exception to Evaluate the Diagram.
     */
    public void evaluate(Diagram diagram, Metric metric) throws ScriptException {
        EvaluationBase evaluation = new EvaluationDiagram(diagram);
        getPanel().getValueTextField().setText(Double.toString(evaluation.getFinalValue(metric.getOperation())));
        getPanel().updateDetails(evaluation.getResult());
    }
    
    @Override
    public void next() {}
    
    @Override
    protected void update() {
        getMeasure().setValue(getDouble(getPanel().getValueTextField()));
    }
    
    @Override
    public PanelBaseEvaluation getPanel() {
        return (PanelBaseEvaluation) panel;
    }
}