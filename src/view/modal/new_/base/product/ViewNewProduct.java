package view.modal.new_.base.product;

import controller.view.modal.new_.base.product.ControllerViewNewProduct;
import java.awt.Dimension;
import model.structural.base.product.Product;
import view.panel.base.product.PanelBaseProduct;
import view.modal.new_.ViewNew;
import view.main.structural.ViewMenu;
import view.panel.edit.base.product.PanelEditProduct;

/**
 * <p>Class of View <b>ViewNewProduct</b>.</p>
 * <p>Class responsible for defining the <b>New Product View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-08-09
 * @see    controller.view.modal.new_.base.product.ControllerViewNewProduct
 * @see    model.structural.base.product.Product
 * @see    view.modal.new_.ViewNew
 */
public final class ViewNewProduct extends ViewNew {
    private final Product product;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewNewProduct(ViewMenu view) {
        super(view);
        product    = new Product();
        controller = new ControllerViewNewProduct(this);
        title      = "New Product";
        initComponents();
    }
    
    @Override
    protected Dimension getViewDimension() {
        return new Dimension(600, 445);
    }
    
    @Override
    protected PanelEditProduct createPanelNew() {
        return new PanelEditProduct(view, product);
    }
    
    @Override
    protected Dimension getPanelDimension() {
        return new Dimension(500, 325);
    }
    
    @Override
    protected PanelEditProduct getPanelNew() {
        return (PanelEditProduct) super.getPanelNew();
    }
    
    /**
     * Method responsible for returning the Panel Base Product.
     * @return Panel Base Product.
     */
    public PanelBaseProduct getPanelBaseProduct() {
        return getPanelNew().getPanelBaseProduct();
    }
    
    /**
     * Method responsible for returning the Product.
     * @return Product.
     */
    public Product getProduct() {
        return product;
    }
}