package view.panel.evaluation.base.product;

import controller.view.panel.evaluation.base.product.ControllerPanelEvaluationProduct;
import model.controller.structural.base.ControllerProject;
import view.modal.evaluation.base.product.ViewEvaluationProduct;
import view.panel.evaluation.PanelEvaluation;

/**
 * <p>Class of View <b>PanelEvaluationProduct</b>.</p>
 * <p>Class responsible for defining a <b>Product Evaluation Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-01
 * @see    controller.view.panel.evaluation.base.product.ControllerPanelEvaluationProduct
 * @see    view.panel.evaluation.PanelEvaluation
 * @see    view.modal.evaluation.base.product.ViewEvaluationProduct
 */
public final class PanelEvaluationProduct extends PanelEvaluation {
    
    /**
     * Default constructor method of Class.
     * @param view View Evaluation Product.
     */
    public PanelEvaluationProduct(ViewEvaluationProduct view) {
        super(view);
        controller = new ControllerPanelEvaluationProduct(this);
        setDefaultProperties();
        addComponents();
    }
    
    @Override
    protected Object[] getTargets() {
        return (Object[]) new ControllerProject(project).getProducts();
    }
}