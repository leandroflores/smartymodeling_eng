package view.modal.edit.base.product;

import controller.view.modal.edit.base.product.ControllerViewEditProduct;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.product.Product;
import view.modal.edit.ViewEdit;
import view.panel.base.product.PanelBaseProduct;
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
        super(panel);
        this.product    = product;
        this.controller = new ControllerViewEditProduct(this);
        this.title      = "Edit Product Data";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(600, 350);
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(550, 225));
            this.addPanelBaseProduct();
        this.add(this.tabbedPane);
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Base Product.
     */
    private void addPanelBaseProduct() {
        this.addPanel("panelBaseProduct", new PanelBaseProduct(this.getViewMenu(), this.product));
        this.createScrollPane("scrollPanelBaseProduct",  this.getPanelBaseProduct());
        this.getScrollPanelBaseProduct().setViewportView(this.getPanelBaseProduct());
        this.tabbedPane.add("Product", this.getScrollPanelBaseProduct());
    }
    
    /**
     * Method responsible for returning the Panel Base Product.
     * @return Panel Base Product.
     */
    public PanelBaseProduct getPanelBaseProduct() {
        return (PanelBaseProduct) this.getPanel("panelBaseProduct");
    }
    
    /**
     * Method responsible for returning Panel Base Product.
     * @return Panel Base Product.
     */
    public JScrollPane getScrollPanelBaseProduct() {
        return this.getScrollPane("scrollPanelBaseProduct");
    }
    
    /**
     * Method responsible for returning the Product.
     * @return Product.
     */
    public Product getProduct() {
        return this.product;
    }
}