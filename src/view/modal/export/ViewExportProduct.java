package view.modal.export;

import controller.view.modal.export.ControllerViewExportProduct;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.product.Product;
import view.panel.export.PanelExportProduct;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewExportProduct</b>.</p>
 * <p>Class responsible for defining the <b>Export Product View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  05/11/2019
 * @see    controller.view.modal.export.ControllerViewExportProduct
 * @see    model.structural.base.product.Product
 * @see    view.modal.export.ViewExport
 */
public final class ViewExportProduct extends ViewExport {
    private Product product;
    private PanelExportProduct panelExportProduct;
    
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
        this.panelExportProduct = new PanelExportProduct(this.getViewMenu());
        this.createScrollPane("scrollPanelExportProduct",  this.panelExportProduct);
        this.getScrollPanelExportProduct().setViewportView(this.panelExportProduct);
        this.tabbedPane.add("Export Product", this.getScrollPanelExportProduct());
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
    
    /**
     * Method responsible for returning the Panel Export Product.
     * @return Panel Export Product.
     */
    public PanelExportProduct getPanelExportProduct() {
        return this.panelExportProduct;
    }
    
    /**
     * Method responsible for returning the Panel Export Product.
     * @return Panel Export Product.
     */
    public JScrollPane getScrollPanelExportProduct() {
        return this.getScrollPane("scrollPanelExportProduct");
    }
}