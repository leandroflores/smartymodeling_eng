package view.edit.panel.base.product;

import controller.view.edit.panel.base.product.ControllerPanelBaseProduct;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.structural.base.product.Product;
import view.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseProduct</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Product Base</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  07/10/2019
 * @see    controller.view.edit.panel.base.product.ControllerPanelBaseProduct
 * @see    model.structural.base.product.Product
 * @see    view.Panel
 */
public final class PanelBaseProduct extends Panel {
    private final ViewMenu viewMenu;
    private final Product product;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param product Product.
     */
    public PanelBaseProduct(ViewMenu viewMenu, Product product) {
        this.viewMenu   = viewMenu;
        this.product    = product;
        this.controller = new ControllerPanelBaseProduct(this);
        this.setSettings();
        this.addComponents();
        this.setValues();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(50, 50));
        this.setSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Name*: "),  this.getConstraints(1, 1, 0, 0));
        this.add(this.createTextField("nameTextField",  "", 10),  this.getConstraints(2, 1, 1, 0));
        
        this.add(this.createLabel("Version*: "), this.getConstraints(1, 1, 0, 1));
        this.add(this.createTextField("versionTextField", "", 10), this.getConstraints(2, 1, 1, 1));
        
        this.createTextArea("descriptionTextArea");
        this.add(this.createLabel("Description: "), this.getConstraints(1, 1, 0, 2));
        this.add(this.getDescriptionScrollPane(), this.getConstraints(2, 5, 1, 2));
    }
    
    /**
     * Method responsible for setting the Product Values.
     */
    public void setValues() {
        this.getNameTextField().setText(this.product.getName());
        this.getVersionTextField().setText(this.product.getVersion());
        this.getDescriptionTextArea().setText(this.product.getDescription());
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
    
    /**
     * Method responsible for returning the Product.
     * @return Product.
     */
    public Product getProduct() {
        return this.product;
    }
    
    /**
     * Method responsible for returning Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.textFields.get("nameTextField");
    }
    
    /**
     * Method responsible for returning Version Text Field.
     * @return Version Text Field.
     */
    public JTextField getVersionTextField() {
        return this.textFields.get("versionTextField");
    }
    
    /**
     * Method responsible for returning the Description Text Area.
     * @return Description Text Area.
     */
    public JTextArea getDescriptionTextArea() {
        return this.textAreas.get("descriptionTextArea");
    }
    
    /**
     * Method responsible for return the Description Scroll Pane.
     * @return Description Scroll Pane.
     */
    public JScrollPane getDescriptionScrollPane() {
        return this.scrollPanes.get("descriptionTextArea");
    }
}