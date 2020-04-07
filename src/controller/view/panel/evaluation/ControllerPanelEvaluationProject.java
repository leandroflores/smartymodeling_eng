package controller.view.panel.evaluation;

import javax.script.ScriptException;
import model.structural.base.Diagram;
import model.structural.base.product.Product;
import view.panel.evaluation.PanelEvaluationProject;
import view.message.ViewError;

/**
 * <p>Class of Controller <b>ControllerPanelEvaluationProject</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelEvaluationProject</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  30/03/2020
 * @see    controller.view.panel.evaluation.ControllerPanelEvaluation
 * @see    view.panel.evaluation.PanelEvaluationProject
 */
public class ControllerPanelEvaluationProject extends ControllerPanelEvaluation {

    /**
     * Default constructor method of Class.
     * @param panelEvaluation Panel Evaluation Project.
     */
    public ControllerPanelEvaluationProject(PanelEvaluationProject panelEvaluation) {
        super(panelEvaluation);
    }
    
    @Override
    public void update() {
        Object target    = this.panelEvaluation.getTargetComboBox().getSelectedItem();
        String operation = this.panelEvaluation.getOperationTextField().getText().trim();
        try {
            if (this.check()) {
                if (target.toString().equalsIgnoreCase("Project"))
                    this.evaluate(this.getProject(), operation);
                else if (target instanceof Diagram)
                    this.evaluate(this.getDiagram(), operation);
                else if (target instanceof Product)
                    this.evaluate(this.getProduct(), operation);
            }
        }catch (ScriptException exception) {
            new ViewError(this.panelEvaluation.getViewEvaluation(), "Error to Apply Operation!").setVisible(true);
            this.panelEvaluation.getOperationTextField().requestFocus();
        }
    }
}