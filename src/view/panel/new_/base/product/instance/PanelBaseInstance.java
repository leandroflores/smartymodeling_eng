package view.panel.new_.base.product.instance;

import controller.view.panel.new_.base.product.instance.ControllerPanelBaseInstance;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.structural.base.product.Instance;
import view.panel.new_.base.product.PanelNewInstance;

/**
 * <p>Class of View <b>PanelBaseInstance</b>.</p> 
 * <p>Class responsible for defining a <b>New Instance Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-07
 * @see    controller.view.panel.new_.base.product.instance.ControllerPanelBaseInstance
 * @see    model.structural.base.product.Instance
 * @see    view.panel.new_.base.product.instance.PanelBase
 */
public final class PanelBaseInstance extends PanelBase {
    
    /**
     * Default constructor method of Class.
     * @param panel Panel New Instance.
     * @param instance Instance.
     */
    public PanelBaseInstance(PanelNewInstance panel, Instance instance) {
        super(panel, instance);
        this.controller = new ControllerPanelBaseInstance(this);
        this.setDefaultProperties();
        this.addComponents();
        this.addFooter();
        this.setValues();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridBagLayout());
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Product*: "), this.createConstraints(1, 1, 0, 0));
        this.add(this.createComboBox("productComboBox", this.getProject().getProductsList().toArray(), 15),    this.createConstraints(3, 1, 1, 0));
        
        this.add(this.createLabel("Diagram*: "), this.createConstraints(1, 1, 0, 1));
        this.add(this.createComboBox("diagramComboBox", this.getProject().getUMLDiagramsList().toArray(), 15), this.createConstraints(3, 1, 1, 1));
        
        this.add(this.createLabel("Name*: "),    this.createConstraints(1, 1, 0, 2));
        this.add(this.createTextField("nameTextField", "", 15), this.createConstraints(3, 1, 1, 2));
    }
    
    /**
     * Method responsible for adding the Panel Footer.
     */
    @Override
    public void addFooter() {
        this.add(this.getFooter(), this.createConstraints(4, 1, 0, 3));
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
     * Method responsible for returning the Product Combo Box.
     * @return Product Combo Box.
     */
    public JComboBox getProductComboBox() {
        return this.getComboBox("productComboBox");
    }
    
    /**
     * Method responsible for returning the Diagram Combo Box.
     * @return Diagram Combo Box.
     */
    public JComboBox getDiagramComboBox() {
        return this.getComboBox("diagramComboBox");
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.getTextField("nameTextField");
    }
}