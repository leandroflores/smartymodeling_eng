package controller.view.panel.evaluation.base;

import controller.view.panel.evaluation.ControllerPanelEvaluation;
import javax.script.ScriptException;
import model.structural.base.Diagram;
import model.structural.base.product.Product;
import view.panel.evaluation.base.PanelEvaluationProject;
import view.modal.message.ViewError;

/**
 * <p>Class of Controller <b>ControllerPanelEvaluationProject</b>.</p>
 * <p>Class responsible for controlling the <b>PanelEvaluationProject</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-03-30
 * @see    controller.view.panel.evaluation.ControllerPanelEvaluation
 * @see    model.structural.base.Project
 * @see    view.panel.evaluation.base.PanelEvaluationProject
 */
public class ControllerPanelEvaluationProject extends ControllerPanelEvaluation {

    /**
     * Default constructor method of Class.
     * @param panel Panel Evaluation Project.
     */
    public ControllerPanelEvaluationProject(PanelEvaluationProject panel) {
        super(panel);
    }
    
    @Override
    public void update() {
        Object target    = getPanel().getTargetComboBox().getSelectedItem();
        String operation = getPanel().getOperationTextField().getText().trim();
        try {
            if (check()) {
                if (target.toString().equalsIgnoreCase("Project"))
                    evaluate(getProject(), operation);
                else if (target instanceof Diagram)
                    evaluate(getDiagram(), operation);
                else if (target instanceof Product)
                    evaluate(getProduct(), operation);
            }
        }catch (ScriptException exception) {
            new ViewError(getPanel().getViewEvaluation(), "Error to Apply Operation!").setVisible(true);
            getPanel().getOperationTextField().requestFocus();
        }catch (Exception exception) {
            new ViewError(getPanel().getViewEvaluation(), "Invalid Operation Expression!").setVisible(true);
            getPanel().getOperationTextField().requestFocus();
        }
    }
    
    @Override
    public PanelEvaluationProject getPanel() {
        return (PanelEvaluationProject) panel;
    }
}