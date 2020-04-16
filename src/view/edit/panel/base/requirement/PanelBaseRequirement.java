package view.edit.panel.base.requirement;

import controller.view.edit.panel.base.requirement.ControllerPanelBaseRequirement;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.controller.structural.base.requirement.ControllerRequirement;
import model.structural.base.Project;
import model.structural.base.requirement.Requirement;
import view.panel.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseRequirement</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Requirement Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-15
 * @see    controller.view.edit.panel.base.requirement.ControllerPanelBaseRequirement
 * @see    model.structural.base.requirement.Requirement
 * @see    view.panel.Panel
 */
public final class PanelBaseRequirement extends Panel {
    private final ViewMenu viewMenu;
    private final Project  project;
    private final Requirement requirement;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param requirement Requirement.
     */
    public PanelBaseRequirement(ViewMenu viewMenu, Requirement requirement) {
        this.viewMenu    = viewMenu;
        this.project     = this.viewMenu.getProject();
        this.requirement = requirement;
        this.controller  = new ControllerPanelBaseRequirement(this);
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
        this.add(this.createLabel("Id*: "), this.createConstraints(1, 1, 0, 0));
        this.add(this.createTextField("idTextField", "", 10),  this.createConstraints(1, 1, 1, 0));
        this.add(this.createLabel("Type*: "), this.createConstraints(1, 1, 2, 0));
        this.add(this.createComboBox("typeComboBox", ControllerRequirement.TYPES, 10), this.createConstraints(2, 1, 3, 0));
        
        this.add(this.createLabel("Name*: "), this.createConstraints(1, 1, 0, 1));
        this.add(this.createTextField("nameTextField", "", 15), this.createConstraints(4, 1, 1, 1));
        
        this.createTextArea("descriptionTextArea");
        this.add(this.createLabel("Description*: "), this.createConstraints(1, 1, 0, 2));
        this.add(this.getDescriptionScrollPane(), this.createConstraints(4, 5, 1, 2));
    }
    
    /**
     * Method responsible for setting the Requirement Values.
     */
    public void setValues() {
        this.getCodeTextField().setText(this.requirement.getCode());
        this.getTypeComboBox().setSelectedItem(this.requirement.getType());
        this.getNameTextField().setText(this.requirement.getName());
        this.getDescriptionTextArea().setText(this.requirement.getDescription());
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
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