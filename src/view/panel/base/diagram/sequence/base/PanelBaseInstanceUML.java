package view.panel.base.diagram.sequence.base;

import controller.view.panel.base.diagram.sequence.base.ControllerPanelBaseInstanceUML;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.sequence.base.InstanceUML;
import view.panel.base.PanelBaseElement;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseInstanceUML</b>.</p>
 * <p>Class responsible for defining the <b>Instance UML Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-03
 * @see    controller.view.panel.base.diagram.sequence.base.ControllerPanelBaseInstanceUML
 * @see    model.structural.diagram.sequence.base.InstanceUML
 * @see    view.panel.base.PanelBaseElement
 */
public final class PanelBaseInstanceUML extends PanelBaseElement {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Sequence Diagram.
     * @param instance Instance UML.
     */
    public PanelBaseInstanceUML(ViewMenu view, SequenceDiagram diagram, InstanceUML instance) {
        super(view, diagram, instance);
        this.controller = new ControllerPanelBaseInstanceUML(this);
        this.setDefaultProperties();
        this.addComponents();
        this.setClassUML();
        this.getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridLayout(3, 2, 2, 5));
        this.setPreferredSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Name*: "));
        this.add(this.createTextField("nameTextField", this.getElement().getName(), 25));
        
        this.add(this.createLabel("Class: "));
        this.add(this.createComboBox("classComboBox", this.getProject().getElements("class").toArray(), 30));
        this.getClassComboBox().setPreferredSize(new Dimension(325, 30));
        
        this.add(this.createLabel("Mandatory: "));
        this.add(this.createCheckBox("mandatoryCheckBox", "", this.getElement().isMandatory()));
    }
    
    /**
     * Method responsible for setting the Class UML.
     */
    public void setClassUML() {
        if (this.getElement().getClassUML() != null)
            this.getClassComboBox().setSelectedItem(this.getElement().getClassUML());
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.getTextField("nameTextField");
    }
    
    /**
     * Method responsible for returning the Class Combo Box.
     * @return Class Combo Box.
     */
    public JComboBox getClassComboBox() {
        return this.getComboBox("classComboBox");
    }
    
    /**
     * Method responsible for returning the Mandatory Check Box.
     * @return Mandatory Check Box.
     */
    public JCheckBox getMandatoryCheckBox() {
        return this.getCheckBox("mandatoryCheckBox");
    }
    
    @Override
    public SequenceDiagram getDiagram() {
        return (SequenceDiagram) this.diagram;
    }
    
    @Override
    public InstanceUML getElement() {
        return (InstanceUML) this.element;
    }
}