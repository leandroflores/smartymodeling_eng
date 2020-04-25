package view.panel.new_.base.product.instance;

import controller.view.panel.new_.base.product.instance.ControllerPanelBaseArtifacts;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import model.structural.base.product.Instance;
import view.panel.new_.base.product.PanelNewInstance;

/**
 * <p>Class of View <b>PanelBaseArtifacts</b>.</p> 
 * <p>Class responsible for defining a <b>Artifats Instance Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-09
 * @see    controller.view.panel.new_.base.product.instance.ControllerPanelBaseArtifacts
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
        this.controller = new ControllerPanelBaseArtifacts(this);
        this.setDefaultProperties();
        this.addHeader();
        this.addComponents();
        this.addFooter();
        this.setValues();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setPreferredSize(new Dimension(600, 350));
        this.setLayout(new GridBagLayout());
    }
    
    /**
     * Method responsible for adding the Header.
     */
    public void addHeader() {
        this.add(this.createLabel("Finishing the New Instance:"), this.createConstraints(6, 1, 0, 0));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Name*: "), this.createConstraints(2, 1, 0, 1));
        this.add(this.createTextFieldNoEditable("nameTextField", this.getInstance().getName(), 10), this.createConstraints(4, 1, 2, 1));
        
        this.add(this.createLabel("Artifacts: "), this.createConstraints(2, 1, 0, 2));
        this.add(this.createTextFieldNoEditable("elementsTextField", "", 10), this.createConstraints(4, 1, 2, 2));
        
        this.add(this.createLabel("Relationships: "), this.createConstraints(2, 1, 0, 3));
        this.add(this.createTextFieldNoEditable("associationsTextField", "", 10), this.createConstraints(4, 1, 2, 3));
    }
    
    @Override
    public void addFooter() {
        this.add(this.getFooter(), this.createConstraints(6, 1, 0, 4));
    }
    
    /**
     * Method responsible for setting the Values.
     */
    public void setValues() {
        this.getNameTextField().setText(this.getInstance().getName());
        this.getElementsTextField().setText(Integer.toString(this.getPanelNew().getElementsSize()));
        this.getAssociationsTextField().setText(Integer.toString(this.getPanelNew().getAssociationsSize()));
        
        this.getNameTextField().requestFocus();
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.getTextField("nameTextField");
    }
    
    /**
     * Method responsible for returning the Elements Field.
     * @return Elements Field.
     */
    public JTextField getElementsTextField() {
        return this.getTextField("elementsTextField");
    }
    
    /**
     * Method responsible for returning the Associations Field.
     * @return Associations Field.
     */
    public JTextField getAssociationsTextField() {
        return this.getTextField("associationsTextField");
    }
    
    @Override
    public ControllerPanelBaseArtifacts getController() {
        return (ControllerPanelBaseArtifacts) this.controller;
    }
}