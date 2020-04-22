package view.modal.new_.base.product;

import controller.view.modal.new_.base.product.ControllerViewNewProduct;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import model.structural.base.product.Product;
import view.panel.base.product.PanelBaseProduct;
import view.modal.new_.ViewNew;
import view.structural.ViewMenu;

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
     * @param project Project.
     */
    public ViewNewProduct(ViewMenu view, Project project) {
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
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(550, 325));
        
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
     * Method responsible for returning the Scroll Panel Base Product.
     * @return Scroll Panel Base Product.
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