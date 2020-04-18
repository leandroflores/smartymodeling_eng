package view.delete.base.product;

import controller.view.delete.base.product.ControllerViewDeleteProduct;
import model.structural.base.product.Product;
import view.delete.base.ViewDelete;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewDeleteProduct</b>.</p>
 * <p>Class responsible for defining the <b>Product Delete View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-1
 * @see    controller.view.delete.base.product.ControllerViewDeleteProduct
 * @see    model.structural.base.product.Product
 * @see    view.delete.base.ViewDelete
 */
public final class ViewDeleteProduct extends ViewDelete {
    private final Product product;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param product Product.
     */
    public ViewDeleteProduct(PanelModeling panel, Product product) {
        super(panel);
        this.product    = product;
        this.controller = new ControllerViewDeleteProduct(this);
        this.title      = "Delete Product";
        this.initComponents();
        this.addComponents();
    }

    @Override
    public void addComponents() {
        super.addComponents(this.product.getName());
    }
    
    /**
     * Method responsible for returning the Product.
     * @return Product.
     */
    public Product getProduct() {
        return this.product;
    }
}