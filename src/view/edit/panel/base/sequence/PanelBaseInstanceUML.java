package view.edit.panel.base.sequence;

import controller.view.edit.panel.base.sequence.ControllerPanelBaseInstanceUML;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.sequence.base.InstanceUML;
import view.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseInstanceUML</b>.</p> 
 * <p>Class responsible for defining a Base Panel for the <b>Instance UML</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  03/10/2019
 * @see    controller.view.edit.panel.base.sequence.ControllerPanelBaseInstanceUML
 * @see    model.structural.diagram.sequence.base.InstanceUML
 * @see    view.Panel
 */
public final class PanelBaseInstanceUML extends Panel {
    private final ViewMenu viewMenu;
    private final SequenceDiagram diagram;
    private final InstanceUML instanceUML;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Sequence Diagram.
     * @param instanceUML Instance UML.
     */
    public PanelBaseInstanceUML(ViewMenu viewMenu, SequenceDiagram diagram, InstanceUML instanceUML) {
        this.viewMenu    = viewMenu;
        this.diagram     = diagram;
        this.instanceUML = instanceUML;
        this.controller   = new ControllerPanelBaseInstanceUML(this);
        this.setSettings();
        this.addComponents();
        this.setValues();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridLayout(3, 2, 2, 5));
        this.setPreferredSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Name*: "));
        this.add(this.createTextField("nameTextField", this.instanceUML.getName(), 25));
        
        this.add(this.createLabel("Class: "));
        this.add(this.createComboBox("classComboBox", this.diagram.getProject().getElements("class").toArray(), 30));
        this.getClassComboBox().setPreferredSize(new Dimension(325, 30));
        
        this.add(this.createLabel("Mandatory: "));
        this.add(this.createCheckBox("mandatoryCheckBox", "", this.instanceUML.isMandatory()));
    }
    
    /**
     * Method responsible for setting the Instance Values.
     */
    public void setValues() {
        this.getNameTextField().setText(this.instanceUML.getName());
        this.setClassUML();
        this.getMandatoryCheckBox().setSelected(this.instanceUML.isMandatory());
    }
    
    /**
     * Method responsible for setting the Class UML.
     */
    private void setClassUML() {
        if (this.instanceUML.getClassUML() != null)
            this.getClassComboBox().setSelectedItem(this.instanceUML.getClassUML());
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
    
    /**
     * Method responsible for returning the Sequence Diagram.
     * @return Sequence Diagram.
     */
    public SequenceDiagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Instance UML.
     * @return Instance UML.
     */
    public InstanceUML getInstanceUML() {
        return this.instanceUML;
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.textFields.get("nameTextField");
    }
    
    /**
     * Method responsible for returning the Class Combo Box.
     * @return Class Combo Box.
     */
    public JComboBox getClassComboBox() {
        return this.comboBoxes.get("classComboBox");
    }
    
    /**
     * Method responsible for returning the Mandatory Check Box.
     * @return Mandatory Check Box.
     */
    public JCheckBox getMandatoryCheckBox() {
        return this.checkBoxes.get("mandatoryCheckBox");
    }
}