package view.modal.export.base.product;

import controller.view.modal.export.base.product.ControllerViewExportProduct;
import java.awt.Dimension;
import javax.swing.JTabbedPane;
import model.structural.base.product.Product;
import view.modal.export.ViewExport;
import view.panel.export.base.product.PanelExportProduct;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewExportProduct</b>.</p>
 * <p>Class responsible for defining the <b>Export Product View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-05
 * @see    controller.view.modal.export.base.product.ControllerViewExportProduct
 * @see    model.structural.base.product.Product
 * @see    view.modal.export.ViewExport
 */
public final class ViewExportProduct extends ViewExport {
    private Product product;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     */
    public ViewExportProduct(ViewMenu viewMenu) {
        super(viewMenu);
        this.controller = new ControllerViewExportProduct(this);
        this.title      = "Export Product";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(600, 420);
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(550, 300));
            this.addPanelExportProduct();
        this.add(this.tabbedPane);
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Export Product.
     */
    private void addPanelExportProduct() {
        this.addPanel("panelExportProduct", new PanelExportProduct(this.getViewMenu()));
        this.createScrollPane("scrollPanelExportProduct",  this.getPanelExportProduct());
        this.getScrollPane("scrollPanelExportProduct").setViewportView(this.getPanelExportProduct());
        this.tabbedPane.add("Export Product", this.getScrollPane("scrollPanelExportProduct"));
    }
    
    /**
     * Method responsible for returning the Panel Export Product.
     * @return Panel Export Product.
     */
    public PanelExportProduct getPanelExportProduct() {
        return (PanelExportProduct) this.getPanel("panelExportProduct");
    }
    
    /**
     * Method responsible for returning the Product.
     * @return Product.
     */
    public Product getProduct() {
        return this.product;
    }

    /**
     * Method responsible for setting the Product.
     * @param product Product.
     */
    public void setProduct(Product product) {
        this.product = product;
    }
}