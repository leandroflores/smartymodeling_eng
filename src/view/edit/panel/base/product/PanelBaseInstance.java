package view.edit.panel.base.product;

import controller.view.edit.panel.base.product.ControllerPanelBaseInstance;
import java.awt.GridLayout;
import javax.swing.JTextField;
import model.structural.base.product.Instance;
import view.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseInstance</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Instance Base</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  07/10/2019
 * @see    controller.view.edit.panel.base.product.ControllerPanelBaseInstance
 * @see    model.structural.base.product.Instance
 * @see    view.Panel
 */
public final class PanelBaseInstance extends Panel {
    private final ViewMenu viewMenu;
    private final Instance instance;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param instance Instance.
     */
    public PanelBaseInstance(ViewMenu view, Instance instance) {
        this.viewMenu   = view;
        this.instance   = instance;
        this.controller = new ControllerPanelBaseInstance(this);
        this.setSettings();
        this.addComponents();
        this.setValues();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridLayout(3, 2));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Product: "));
        this.add(this.createTextFieldNoEditable("productTextField", "", 15));
        
        this.add(this.createLabel("Diagram: "));
        this.add(this.createTextFieldNoEditable("diagramTextField", "", 15));
        
        this.add(this.createLabel("Name*: "));
        this.add(this.createTextField("nameTextField", "", 15));
    }
    
    /**
     * Method responsible for setting the Instance Values.
     */
    public void setValues() {
        this.getProductTextField().setText(this.instance.getProduct().getName());
        this.getDiagramTextField().setText(this.instance.getDiagram().getName());
        this.getNameTextField().setText(this.instance.getName());
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
    
    /**
     * Method responsible for returning the Instance.
     * @return Instance.
     */
    public Instance getInstance() {
        return this.instance;
    }
    
    /**
     * Method responsible for returning the Product Text Field.
     * @return Product Text Field.
     */
    public JTextField getProductTextField() {
        return this.textFields.get("productTextField");
    }
    
    /**
     * Method responsible for returning the Diagram Text Field.
     * @return Diagram Text Field.
     */
    public JTextField getDiagramTextField() {
        return this.textFields.get("diagramTextField");
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.textFields.get("nameTextField");
    }
}