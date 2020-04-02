package controller.view.evaluation;

import view.evaluation.ViewEvaluationProduct;

/**
 * <p>Class of Controller <b>ControllerViewEvaluationProduct</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewEvaluationProduct</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  01/04/2020
 * @see    controller.view.evaluation.ControllerViewEvaluation
 * @see    view.evaluation.ViewEvaluationProduct
 */
public class ControllerViewEvaluationProduct extends ControllerViewEvaluation {
    private final ViewEvaluationProduct viewEvaluationProduct;

    /**
     * Default constructor method of Class.
     * @param viewEvaluation View Evaluation Product.
     */
    public ControllerViewEvaluationProduct(ViewEvaluationProduct viewEvaluation) {
        super(viewEvaluation);
        this.viewEvaluationProduct = viewEvaluation;
    }
    
    @Override
    public void refresh() {
        this.viewEvaluationProduct.getPanelEvaluationProduct().getController().update();
    }

    @Override
    public void clear() {
        this.viewEvaluationProduct.getPanelEvaluationProduct().clear();
    }
}