package controller.view.panel.evaluation.base;

import controller.view.panel.evaluation.ControllerPanelEvaluation;
import javax.script.ScriptException;
import model.structural.base.Diagram;
import model.structural.base.product.Product;
import view.panel.evaluation.base.PanelEvaluationProject;
import view.modal.message.ViewError;

/**
 * <p>Class of Controller <b>ControllerPanelEvaluationProject</b>.</p>
 * <p>Class responsible for controlling the <b>PanelEvaliationProject</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-03-30
 * @see    controller.view.panel.evaluation.ControllerPanelEvaluation
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
        Object target    = this.getPanel().getTargetComboBox().getSelectedItem();
        String operation = this.getPanel().getOperationTextField().getText().trim();
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
            new ViewError(this.getPanel().getViewEvaluation(), "Error to Apply Operation!").setVisible(true);
            this.getPanel().getOperationTextField().requestFocus();
        }
    }
    
    @Override
    public PanelEvaluationProject getPanel() {
        return (PanelEvaluationProject) this.panel;
    }
}