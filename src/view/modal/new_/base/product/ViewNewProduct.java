package view.modal.new_.base.product;

import controller.view.modal.new_.base.product.ControllerViewNewProduct;
import java.awt.Dimension;
import javax.swing.JTabbedPane;
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
        this.product    = new Product();
        this.controller = new ControllerViewNewProduct(this);
        this.title      = "New Product";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(600, 445);
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.addPanelEditProduct();
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Edit Product.
     */
    private void addPanelEditProduct() {
        this.addPanel("panelEditProduct", new PanelEditProduct(this.view, this.product));
        this.getPanel("panelEditProduct").setPreferredSize(new Dimension(500, 325));
        this.add(this.getPanel("panelEditProduct"));
    }
    
    /**
     * Method responsible for returning the Panel Base Product.
     * @return Panel Base Product.
     */
    public PanelBaseProduct getPanelBaseProduct() {
        return ((PanelEditProduct) this.getPanel("panelEditProduct")).getPanelBaseProduct();
    }
    
    /**
     * Method responsible for returning the Product.
     * @return Product.
     */
    public Product getProduct() {
        return this.product;
    }
}