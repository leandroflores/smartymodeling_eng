package view.modal.evaluation.base.product;

import controller.view.modal.evaluation.base.product.ControllerViewEvaluationProduct;
import java.awt.Dimension;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import view.modal.evaluation.ViewEvaluation;
import view.panel.evaluation.base.product.PanelEvaluationProduct;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewEvaluationProduct</b>.</p>
 * <p>Class responsible for defining the <b>View Evaluation Product</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-01
 * @see    controller.view.evaluation.
 * @see    view.modal.evaluation.ViewEvaluation
 * @see    view.panel.evaluation.base.product.PanelEvaluationProduct
 */
public final class ViewEvaluationProduct extends ViewEvaluation {
    private PanelEvaluationProduct panelEvaluationProduct;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param project Project.
     */
    public ViewEvaluationProduct(ViewMenu view, Project project) {
        super(view);
        this.controller = new ControllerViewEvaluationProduct(this);
        this.title      = "Evaluation Product";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(600, 520);
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.addTabbedPane();
            this.addPanelEvaluationProduct();
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Tabbed Pane.
     */
    private void addTabbedPane() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(500, 400));
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Evaluation Product.
     */
    public void addPanelEvaluationProduct() {
        this.addPanel("panelEvaluationProduct", new PanelEvaluationProduct(this));
        this.tabbedPane.removeAll();
        this.createScrollPane("scrollPanelEvaluationProduct",  this.getPanelEvaluationProduct());
        this.getScrollPane("scrollPanelEvaluationProduct").setViewportView(this.getPanelEvaluationProduct());
        this.tabbedPane.add("Evaluation", this.getScrollPane("scrollPanelEvaluationProduct"));
    }
    
    /**
     * Method responsible for returning the Panel Evaluation Product.
     * @return Panel Evaluation Product.
     */
    public PanelEvaluationProduct getPanelEvaluationProduct() {
        return (PanelEvaluationProduct) this.getPanel("panelEvaluationProduct");
    }
}