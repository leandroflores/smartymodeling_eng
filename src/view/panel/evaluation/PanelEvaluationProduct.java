package view.panel.evaluation;

import controller.view.panel.evaluation.ControllerPanelEvaluationProduct;
import model.controller.structural.base.ControllerProject;
import view.modal.evaluation.ViewEvaluationProduct;

/**
 * <p>Class of View <b>PanelEvaluationProject</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Panel Evaluation Product</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  01/04/2020
 * @see    controller.view.panel.evaluation.ControllerPanelEvaluationProduct
 * @see    view.panel.evaluation.PanelEvaluation
 * @see    view.modal.evaluation.ViewEvaluationProduct
 */
public final class PanelEvaluationProduct extends PanelEvaluation {
    
    /**
     * Default constructor method of Class.
     * @param viewEvaluation View Evaluation Product.
     */
    public PanelEvaluationProduct(ViewEvaluationProduct viewEvaluation) {
        super(viewEvaluation);
        this.controller = new ControllerPanelEvaluationProduct(this);
        this.setSettings();
        this.addComponents();
    }
    
    @Override
    protected Object[] getTargets() {
        return new ControllerProject(this.project).getProducts();
    }
}