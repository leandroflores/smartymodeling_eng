package view.panel.base.product;

import controller.view.panel.base.product.ControllerPanelBaseProduct;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.structural.base.product.Product;
import view.panel.base.PanelBase;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseProduct</b>.</p>
 * <p>Class responsible for defining a <b>Product Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-07
 * @see    controller.view.panel.base.product.ControllerPanelBaseProduct
 * @see    model.structural.base.product.Product
 * @see    view.panel.base.PanelBase
 */
public final class PanelBaseProduct extends PanelBase {
    private final Product product;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param product Product.
     */
    public PanelBaseProduct(ViewMenu view, Product product) {
        super(view);
        this.product    = product;
        this.controller = new ControllerPanelBaseProduct(this);
        setDefaultProperties();
        addComponents();
        getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(50, 50));
        setSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Name*: "), createConstraints(1, 1, 0, 0));
        add(createTextField("name", product.getName(), 10), createConstraints(2, 1, 1, 0));
        
        add(createLabel("Version*: "), createConstraints(1, 1, 0, 1));
        add(createTextField("version", product.getVersion(), 10), createConstraints(2, 1, 1, 1));
        
        createTextArea("description");
        getDescriptionTextArea().setText(product.getDescription());
        add(createLabel("Description: "), createConstraints(1, 1, 0, 2));
        add(getDescriptionScrollPane(), createConstraints(2, 5, 1, 2));
    }
    
    /**
     * Method responsible for returning Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return getTextField("name");
    }
    
    /**
     * Method responsible for returning Version Text Field.
     * @return Version Text Field.
     */
    public JTextField getVersionTextField() {
        return getTextField("version");
    }
    
    /**
     * Method responsible for returning the Description Text Area.
     * @return Description Text Area.
     */
    public JTextArea getDescriptionTextArea() {
        return getTextArea("description");
    }
    
    /**
     * Method responsible for return the Description Scroll Pane.
     * @return Description Scroll Pane.
     */
    public JScrollPane getDescriptionScrollPane() {
        return getScrollPane("description");
    }
    
    /**
     * Method responsible for returning the Product.
     * @return Product.
     */
    public Product getProduct() {
        return product;
    }
}