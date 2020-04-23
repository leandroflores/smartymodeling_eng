package controller.view.modal.evaluation.base.product;

import controller.view.modal.evaluation.ControllerViewEvaluation;
import view.modal.evaluation.base.product.ViewEvaluationProduct;

/**
 * <p>Class of Controller <b>ControllerViewEvaluationProduct</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEvaluationProduct</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-01
 * @see    controller.view.modal.evaluation.ControllerViewEvaluation
 * @see    model.structural.base.product.Product
 * @see    view.modal.evaluation.base.product.ViewEvaluationProduct
 */
public class ControllerViewEvaluationProduct extends ControllerViewEvaluation {

    /**
     * Default constructor method of Class.
     * @param view View Evaluation Product.
     */
    public ControllerViewEvaluationProduct(ViewEvaluationProduct view) {
        super(view);
    }
    
    @Override
    public void refresh() {
        this.getView().getPanelEvaluationProduct().getController().update();
    }

    @Override
    public void clear() {
        this.getView().getPanelEvaluationProduct().clear();
    }
    
    @Override
    public ViewEvaluationProduct getView() {
        return (ViewEvaluationProduct) this.viewModal;
    }
}