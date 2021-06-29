package view.panel.new_.base.product.instance;

import controller.view.panel.new_.base.product.instance.ControllerPanelBaseArtifacts;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.structural.base.product.Instance;
import view.panel.new_.base.product.PanelNewInstance;

/**
 * <p>Class of View <b>PanelBaseArtifacts</b>.</p> 
 * <p>Class responsible for defining a <b>Artifats Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-09
 * @see    controller.view.panel.new_.base.product.instance.ControllerPanelBaseArtifacts
 * @see    model.structural.base.product.Instance
 * @see    view.panel.new_.base.product.instance.PanelBase
 */
public final class PanelBaseArtifacts extends PanelBase {
    
    /**
     * Default constructor method of Class.
     * @param panel Panel New Instance.
     * @param instance Instance.
     */
    public PanelBaseArtifacts(PanelNewInstance panel, Instance instance) {
        super(panel, instance);
        controller = new ControllerPanelBaseArtifacts(this);
        setDefaultProperties();
        addHeader();
        addComponents();
        addFooter();
        setValues();
    }
    
    @Override
    protected void setDefaultProperties() {
        setLayout(new GridBagLayout());
    }
    
    /**
     * Method responsible for adding the Header.
     */
    public void addHeader() {
        add(createLabel("New Instance:"), createConstraints(6, 1, 0, 0));
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Name*: "), createConstraints(2, 1, 0, 1));
        add(createTextField("name", "", 10), createConstraints(4, 1, 2, 1));
        
        add(createLabel("Product*: "), createConstraints(2, 1, 0, 2));
        add(createComboBox("product", getProject().getProductsList().toArray(), 15), createConstraints(4, 1, 2, 2));
        
        add(createLabel("Diagram: "), createConstraints(2, 1, 0, 3));
        add(createTextFieldNoEditable("diagram", "", 10), createConstraints(4, 1, 2, 3));
        
        add(createLabel("Artifacts: "), createConstraints(2, 1, 0, 4));
        add(createTextFieldNoEditable("artifacts", "", 10), createConstraints(4, 1, 2, 4));
        
        add(createLabel("Relationships: "), createConstraints(2, 1, 0, 5));
        add(createTextFieldNoEditable("relationships", "", 10), createConstraints(4, 1, 2, 5));
    }
    
    @Override
    public void addFooter() {
        add(getFooter(), createConstraints(6, 1, 0, 6));
        getNextButton().setEnabled(false);
    }
    
    /**
     * Method responsible for setting the Values.
     */
    public void setValues() {
        getNameTextField().setText(getInstance().getName());
        getProductComboBox().setSelectedItem(getInstance().getProduct());
        getDiagramTextField().setText(getInstance().getDiagram().toString());
        getArtifactsTextField().setText(Integer.toString(getPanelNew().getElementsSize()));
        getRelationshipsTextField().setText(Integer.toString(getPanelNew().getAssociationsSize()));
        
        getNameTextField().requestFocus();
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return getTextField("name");
    }
    
    /**
     * Method responsible for returning the Product Combo Box.
     * @return Product Combo Box.
     */
    public JComboBox getProductComboBox() {
        return getComboBox("product");
    }
    
    /**
     * Method responsible for returning the Diagram Text Field.
     * @return Diagram Text Field.
     */
    public JTextField getDiagramTextField() {
        return getTextField("diagram");
    }
    
    /**
     * Method responsible for returning the Artifacts Field.
     * @return Artifacts Field.
     */
    public JTextField getArtifactsTextField() {
        return getTextField("artifacts");
    }
    
    /**
     * Method responsible for returning the Relationships Field.
     * @return Relationships Field.
     */
    public JTextField getRelationshipsTextField() {
        return getTextField("relationships");
    }
    
    @Override
    public ControllerPanelBaseArtifacts getController() {
        return (ControllerPanelBaseArtifacts) controller;
    }
}