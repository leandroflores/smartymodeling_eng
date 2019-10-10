package view.delete.product;

import controller.view.delete.product.ControllerViewDeleteProduct;
import model.structural.base.Project;
import model.structural.base.product.Product;
import view.ViewStyle;
import view.delete.ViewDelete;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewDeleteProduct</b>.</p>
 * <p>Class responsible for defining the <b>Product Delete View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  10/10/2019
 * @see    controller.view.delete.product.ControllerViewDeleteProduct
 * @see    model.structural.base.product.Product
 * @see    view.delete.ViewDelete
 */
public final class ViewDeleteProduct extends ViewDelete {
    private final Project project;
    private final Product product;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param product Product.
     */
    public ViewDeleteProduct(PanelModeling panel, Product product) {
        super(panel);
        this.project    = this.view.getProject();
        this.product    = product;
        this.controller = new ControllerViewDeleteProduct(this);
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.setTitle(ViewStyle.SYSTEM + "Delete Product");
        this.addComponents();
    }

    @Override
    public void addComponents() {
        super.addComponents(this.product.getName());
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return this.project;
    }
    
    /**
     * Method responsible for returning the Product.
     * @return Product.
     */
    public Product getProduct() {
        return this.product;
    }
}