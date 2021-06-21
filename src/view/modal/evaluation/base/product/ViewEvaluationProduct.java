package view.modal.evaluation.base.product;

import controller.view.modal.evaluation.base.product.ControllerViewEvaluationProduct;
import model.structural.base.Project;
import view.modal.evaluation.ViewEvaluation;
import view.panel.evaluation.base.product.PanelEvaluationProduct;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewEvaluationProduct</b>.</p>
 * <p>Class responsible for defining the <b>View Evaluation Product</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-01
 * @see    controller.view.modal.evaluation.base.product.ControllerViewEvaluationProduct
 * @see    model.structural.base.product.Product
 * @see    view.modal.evaluation.ViewEvaluation
 */
public final class ViewEvaluationProduct extends ViewEvaluation {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param project Project.
     */
    public ViewEvaluationProduct(ViewMenu view, Project project) {
        super(view);
        controller = new ControllerViewEvaluationProduct(this);
        title      = "Evaluation Product";
        initComponents();
    }
    
    @Override
    protected PanelEvaluationProduct createPanelEvaluation() {
        return new PanelEvaluationProduct(this);
    }
    
    @Override
    public PanelEvaluationProduct getPanelEvaluation() {
        return (PanelEvaluationProduct) super.getPanelEvaluation();
    }
}