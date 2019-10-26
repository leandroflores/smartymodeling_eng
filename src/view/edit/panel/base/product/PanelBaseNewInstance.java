package view.edit.panel.base.product;

import controller.view.edit.panel.base.product.ControllerPanelBaseNewInstance;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.structural.base.product.Instance;
import view.Panel;
import view.new_.product.ViewNewInstance;

/**
 * <p>Class of View <b>PanelBaseNewInstance</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>New Instance Base</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  07/10/2019
 * @see    controller.view.edit.panel.base.product.ControllerPanelBaseNewInstance
 * @see    model.structural.base.product.Instance
 * @see    view.Panel
 */
public final class PanelBaseNewInstance extends Panel {
    private final ViewNewInstance viewNew;
    private final Instance instance;
    
    /**
     * Default constructor method of Class.
     * @param view View New Instance.
     * @param instance Instance.
     */
    public PanelBaseNewInstance(ViewNewInstance view, Instance instance) {
        this.viewNew    = view;
        this.instance   = instance;
        this.controller = new ControllerPanelBaseNewInstance(this);
        this.setSettings();
        this.addComponents();
        this.addFooter();
        this.setValues();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridLayout(4, 2));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Product*: "));
        this.add(this.createComboBox("productComboBox", this.viewNew.getViewMenu().getProject().getProductsList().toArray(), 15));
        this.getProductComboBox().setPreferredSize(new Dimension(325, 30));
        
        this.add(this.createLabel("Diagram*: "));
        this.add(this.createComboBox("diagramComboBox", this.viewNew.getViewMenu().getProject().getDiagramsList().toArray(), 15));
        this.getDiagramComboBox().setPreferredSize(new Dimension(325, 30));
        
        this.add(this.createLabel("Name*: "));
        this.add(this.createTextField("nameTextField", "", 15));
    }
    
    /**
     * Method responsible for setting the Instance Values.
     */
    public void setValues() {
        this.setProduct();
        this.setDiagram();
        this.getNameTextField().setText(this.instance.getName());
    }
    
    /**
     * Method responsible for setting the Instance Product.
     */
    private void setProduct() {
        if (this.instance.getProduct() != null)
            this.getProductComboBox().setSelectedItem(this.instance.getProduct());
    }
    
    /**
     * Method responsible for setting the Instance Diagram.
     */
    private void setDiagram() {
        if (this.instance.getDiagram() != null)
            this.getDiagramComboBox().setSelectedItem(this.instance.getDiagram());
    }
    
    /**
     * Method responsible for returning the View New Instance.
     * @return View New Instance.
     */
    public ViewNewInstance getViewNewInstance() {
        return this.viewNew;
    }
    
    /**
     * Method responsible for returning the Instance.
     * @return Instance.
     */
    public Instance getInstance() {
        return this.instance;
    }
    
    /**
     * Method responsible for returning the Product Combo Box.
     * @return Product Combo Box.
     */
    public JComboBox getProductComboBox() {
        return this.comboBoxes.get("productComboBox");
    }
    
    /**
     * Method responsible for returning the Diagram Combo Box.
     * @return Diagram Combo Box.
     */
    public JComboBox getDiagramComboBox() {
        return this.comboBoxes.get("diagramComboBox");
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.textFields.get("nameTextField");
    }
}