package view.modal.edit.base.product;

import controller.view.modal.edit.base.product.ControllerViewEditProduct;
import java.awt.Dimension;
import model.structural.base.product.Product;
import view.modal.edit.ViewEdit;
import view.panel.base.product.PanelBaseProduct;
import view.panel.edit.base.product.PanelEditProduct;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditProduct</b>.</p>
 * <p>Class responsible for defining the <b>Product Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-18
 * @see    controller.view.modal.edit.base.product.ControllerViewEditProduct
 * @see    model.structural.base.product.Product
 * @see    view.modal.edit.ViewEdit
 */
public final class ViewEditProduct extends ViewEdit {
    private final Product product;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param product Product.
     */
    public ViewEditProduct(PanelModeling panel, Product product) {
        super(panel.getViewMenu());
        this.product    = product;
        this.controller = new ControllerViewEditProduct(this);
        this.title      = "Edit Product Data";
        initComponents();
    }
    
    @Override
    protected Dimension getViewDimension() {
        return new Dimension(600, 350);
    }
    
    @Override
    protected PanelEditProduct createPanelEdit() {
        return new PanelEditProduct(view, product);
    }
    
    @Override
    protected Dimension getPanelDimension() {
        return new Dimension(500, 225);
    }
    
    @Override
    public PanelEditProduct getPanelEdit() {
        return (PanelEditProduct) super.getPanelEdit();
    }
    
    /**
     * Method responsible for returning the Panel Base Product.
     * @return Panel Base Product.
     */
    public PanelBaseProduct getPanelBaseProduct() {
        return getPanelEdit().getPanelBaseProduct();
    }
    
    /**
     * Method responsible for returning the Product.
     * @return Product.
     */
    public Product getProduct() {
        return product;
    }
}