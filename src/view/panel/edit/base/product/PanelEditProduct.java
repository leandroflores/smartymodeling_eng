package view.panel.edit.base.product;

import java.awt.Dimension;
import model.structural.base.product.Product;
import view.panel.base.product.PanelBaseProduct;
import view.panel.edit.PanelEdit;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditProduct</b>.</p>
 * <p>Class responsible for defining a <b>Product Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-09
 * @see    model.structural.base.product.Product
 * @see    view.panel.edit.PanelEdit
 */
public final class PanelEditProduct extends PanelEdit {
    private final Product product;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param product Product.
     */
    public PanelEditProduct(ViewMenu view, Product product) {
        super(view);
        this.product = product;
        this.setPreferredSize(new Dimension(200, 100));
        this.addComponents();
    }
    
    @Override
    protected void addPanels() {
        this.addPanelBaseProduct();
    }
    
    /**
     * Method responsible for adding the Panel Base Product.
     */
    private void addPanelBaseProduct() {
        this.addPanel("panelBaseProduct", new PanelBaseProduct(this.viewMenu, this.product));
        this.createScrollPane("scrollPanelBaseProduct",  this.getPanelBaseProduct());
        this.getScrollPane("scrollPanelBaseProduct").setViewportView(this.getPanelBaseProduct());
        this.tabbedPane.add("Product", this.getScrollPane("scrollPanelBaseProduct"));
    }
    
    /**
     * Method responsible for returning the Panel Base Product.
     * @return Panel Base Product.
     */
    public PanelBaseProduct getPanelBaseProduct() {
        return (PanelBaseProduct) this.getPanel("panelBaseProduct");
    }
    
    /**
     * Method responsible for returning the Product.
     * @return Product.
     */
    public Product getProduct() {
        return this.product;
    }
}