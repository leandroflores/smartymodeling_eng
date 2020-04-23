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
        this.setDefaultProperties();
        this.addComponents();
        this.getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(50, 50));
        this.setSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Name*: "),  this.createConstraints(1, 1, 0, 0));
        this.add(this.createTextField("nameTextField",  this.product.getName(), 10),  this.createConstraints(2, 1, 1, 0));
        
        this.add(this.createLabel("Version*: "), this.createConstraints(1, 1, 0, 1));
        this.add(this.createTextField("versionTextField", this.product.getVersion(), 10), this.createConstraints(2, 1, 1, 1));
        
        this.createTextArea("descriptionTextArea");
        this.getDescriptionTextArea().setText(this.product.getDescription());
        this.add(this.createLabel("Description: "), this.createConstraints(1, 1, 0, 2));
        this.add(this.getDescriptionScrollPane(), this.createConstraints(2, 5, 1, 2));
    }
    
    /**
     * Method responsible for returning Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.getTextField("nameTextField");
    }
    
    /**
     * Method responsible for returning Version Text Field.
     * @return Version Text Field.
     */
    public JTextField getVersionTextField() {
        return this.getTextField("versionTextField");
    }
    
    /**
     * Method responsible for returning the Description Text Area.
     * @return Description Text Area.
     */
    public JTextArea getDescriptionTextArea() {
        return this.getTextArea("descriptionTextArea");
    }
    
    /**
     * Method responsible for return the Description Scroll Pane.
     * @return Description Scroll Pane.
     */
    public JScrollPane getDescriptionScrollPane() {
        return this.getScrollPane("descriptionTextArea");
    }
    
    /**
     * Method responsible for returning the Product.
     * @return Product.
     */
    public Product getProduct() {
        return this.product;
    }
}