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
        controller = new ControllerPanelBaseInstance(this);
        setDefaultProperties();
        addComponents();
        addFooter();
        setValues();
    }
    
    @Override
    protected void setDefaultProperties() {
        setLayout(new GridBagLayout());
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Product*: "), createConstraints(1, 1, 0, 0));
        add(createComboBox("product", getProject().getProductsList().toArray(), 15), createConstraints(3, 1, 1, 0));
        
        add(createLabel("Diagram*: "), createConstraints(1, 1, 0, 1));
        add(createComboBox("diagram", getProject().getUMLDiagramsList().toArray(), 15), createConstraints(3, 1, 1, 1));
        
        add(createLabel("Name*: "),    createConstraints(1, 1, 0, 2));
        add(createTextField("name", "", 15), createConstraints(3, 1, 1, 2));
    }
    
    /**
     * Method responsible for setting the Instance Values.
     */
    public void setValues() {
        setProduct();
        setDiagram();
        getNameTextField().setText(getInstance().getName());
    }
    
    /**
     * Method responsible for setting the Instance Product.
     */
    private void setProduct() {
        if (getProduct() != null)
            getProductComboBox().setSelectedItem(getProduct());
    }
    
    /**
     * Method responsible for setting the Diagram.
     */
    private void setDiagram() {
        if (getDiagram() != null)
            getDiagramComboBox().setSelectedItem(getDiagram());
    }
    
    /**
     * Method responsible for adding the Panel Footer.
     */
    @Override
    public void addFooter() {
        add(getFooter(), createConstraints(4, 1, 0, 3));
        getReturnButton().setEnabled(false);
    }
    
    /**
     * Method responsible for returning the Product Combo Box.
     * @return Product Combo Box.
     */
    public JComboBox getProductComboBox() {
        return getComboBox("product");
    }
    
    /**
     * Method responsible for returning the Diagram Combo Box.
     * @return Diagram Combo Box.
     */
    public JComboBox getDiagramComboBox() {
        return getComboBox("diagram");
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return getTextField("name");
    }
}