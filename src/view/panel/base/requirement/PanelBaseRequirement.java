package view.panel.base.requirement;

import controller.view.panel.base.requirement.ControllerPanelBaseRequirement;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.controller.structural.base.requirement.ControllerRequirement;
import model.structural.base.requirement.Requirement;
import view.panel.base.PanelBase;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseRequirement</b>.</p> 
 * <p>Class responsible for defining a <b>Requirement Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-15
 * @see    controller.view.panel.base.requirement.ControllerPanelBaseRequirement
 * @see    model.structural.base.requirement.Requirement
 * @see    view.panel.base.PanelBase
 */
public final class PanelBaseRequirement extends PanelBase {
    private final Requirement requirement;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param requirement Requirement.
     */
    public PanelBaseRequirement(ViewMenu view, Requirement requirement) {
        super(view);
        this.requirement = requirement;
        this.controller  = new ControllerPanelBaseRequirement(this);
        this.setDefaultProperties();
        this.addComponents();
        this.getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridBagLayout());
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Id*: "), this.createConstraints(1, 1, 0, 0));
        this.add(this.createTextField("codeTextField", this.requirement.getCode(), 10),  this.createConstraints(4, 1, 1, 0));
        
        this.add(this.createLabel("Type*: "), this.createConstraints(1, 1, 0, 1));
        this.add(this.createComboBox("typeComboBox", ControllerRequirement.TYPES, 10, this.requirement.getType()), this.createConstraints(4, 1, 1, 1));
        
        this.add(this.createLabel("Name*: "), this.createConstraints(1, 1, 0, 2));
        this.add(this.createTextField("nameTextField", this.requirement.getName(), 15), this.createConstraints(4, 1, 1, 2));
        
        this.createTextArea("descriptionTextArea", this.requirement.getDescription());
        this.add(this.createLabel("Description*: "), this.createConstraints(1, 1, 0, 3));
        this.add(this.getDescriptionScrollPane(), this.createConstraints(4, 5, 1, 3));
    }
    
    /**
     * Method responsible for return the Requirement.
     * @return Requirement.
     */
    public Requirement getRequirement() {
        return this.requirement;
    }
    
    /**
     * Method responsible for returning the Code Text Field.
     * @return Code Text Field.
     */
    public JTextField getCodeTextField() {
        return this.getTextField("codeTextField");
    }
    
    /**
     * Method responsible for returning the Type Combo Box.
     * @return Type Combo Box.
     */
    public JComboBox getTypeComboBox() {
        return this.getComboBox("typeComboBox");
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.getTextField("nameTextField");
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
}