package view.panel.edit.base.product;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import model.structural.base.product.Product;
import view.edit.panel.base.product.PanelBaseProduct;
import view.panel.edit.PanelEdit;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditProduct</b>.</p> 
 * <p>Class responsible for defining a Panel for Edit the <b>Product</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  09/10/2019
 * @see    model.structural.base.product.Product
 * @see    view.panel.edit.PanelEdit
 */
public final class PanelEditProduct extends PanelEdit {
    private final Project project;
    private final Product product;
    private PanelBaseProduct panelBaseProduct;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param product Product.
     */
    public PanelEditProduct(ViewMenu viewMenu, Product product) {
        super(viewMenu);
        this.project = this.viewMenu.getProject();
        this.product  = product;
        this.setPreferredSize(new Dimension(200, 100));
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(100, 100));
        
        this.addPanelBaseProduct();
        
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Product.
     */
    private void addPanelBaseProduct() {
        this.panelBaseProduct = new PanelBaseProduct(this.viewMenu, this.product);
        this.createScrollPane("scrollPanelBaseProduct",  this.panelBaseProduct);
        this.getScrollPanelBaseProduct().setViewportView(this.panelBaseProduct);
        this.tabbedPane.add("Product", this.getScrollPanelBaseProduct());
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
    
    /**
     * Method responsible for returning the Scroll Panel Base Product.
     * @return Scroll Panel Base Product.
     */
    public JScrollPane getScrollPanelBaseProduct() {
        return this.scrollPanes.get("scrollPanelBaseProduct");
    }
}