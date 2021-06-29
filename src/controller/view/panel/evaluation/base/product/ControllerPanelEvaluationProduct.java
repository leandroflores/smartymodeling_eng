package controller.view.panel.evaluation.base.product;

import controller.view.panel.evaluation.ControllerPanelEvaluation;
import javax.script.ScriptException;
import view.modal.message.ViewError;
import view.panel.evaluation.base.product.PanelEvaluationProduct;

/**
 * <p>Class of Controller <b>ControllerPanelEvaluationProduct</b>.</p>
 * <p>Class responsible for controlling the <b>PanelEvaluationProduct</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-01
 * @see    controller.view.panel.evaluation.ControllerPanelEvaluation
 * @see    model.structural.base.product.Product
 * @see    view.panel.evaluation.base.product.PanelEvaluationProduct
 */
public class ControllerPanelEvaluationProduct extends ControllerPanelEvaluation {

    /**
     * Default constructor method of Class.
     * @param panel Panel Evaluation Product.
     */
    public ControllerPanelEvaluationProduct(PanelEvaluationProduct panel) {
        super(panel);
    }
    
    @Override
    public void update() {
        try {
            if (check())
                evaluate(getProduct(), getPanel().getOperationTextField().getText().trim());
        }catch (ScriptException exception) {
            new ViewError(getPanel().getView(), "Error to Apply Operation!").setVisible(true);
            getPanel().getOperationTextField().requestFocus();
        }
    }
    
    @Override
    public PanelEvaluationProduct getPanel() {
        return (PanelEvaluationProduct) panel;
    }
}