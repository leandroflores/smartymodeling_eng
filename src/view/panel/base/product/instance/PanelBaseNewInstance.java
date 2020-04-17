package view.panel.base.product.instance;

import controller.view.panel.base.product.instance.ControllerPanelBaseNewInstance;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.structural.base.product.Instance;
import view.new_.product.ViewNewInstance;

/**
 * <p>Class of View <b>PanelBaseNewInstance</b>.</p> 
 * <p>Class responsible for defining a <b>New Instance Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-07
 * @see    controller.view.panel.base.product.instance.ControllerPanelBaseNewInstance
 * @see    model.structural.base.product.Instance
 * @see    view.panel.base.product.instance.PanelBase
 */
public final class PanelBaseNewInstance extends PanelBase {
    private final Instance instance;
    
    /**
     * Default constructor method of Class.
     * @param view View New Instance.
     * @param instance Instance.
     */
    public PanelBaseNewInstance(ViewNewInstance view, Instance instance) {
        super(view);
        this.instance   = instance;
        this.controller = new ControllerPanelBaseNewInstance(this);
        this.setDefaultProperties();
        this.addComponents();
        this.addFooter();
        this.setValues();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridLayout(4, 2));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Product*: "));
        this.add(this.createComboBox("productComboBox", this.getProject().getProductsList().toArray(), 15));
        this.getProductComboBox().setPreferredSize(new Dimension(325, 30));
        
        this.add(this.createLabel("Diagram*: "));
        this.add(this.createComboBox("diagramComboBox", this.getProject().getUMLDiagramsList().toArray(), 15));
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
    
    @Override
    public Instance getInstance() {
        return this.instance;
    }
}