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
        controller = new ControllerPanelBaseInstanceUML(this);
        setDefaultProperties();
        addComponents();
        setClassUML();
        getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        setLayout(new GridLayout(3, 2, 2, 5));
        setPreferredSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Name*: "));
        add(createTextField("name", getElement().getName(), 25));
        
        add(createLabel("Class: "));
        add(createComboBox("class", getProject().getElements("class").toArray(), 30));
        getClassComboBox().setPreferredSize(new Dimension(325, 30));
        
        add(createLabel("Mandatory: "));
        add(createCheckBox("mandatory", "", getElement().isMandatory()));
    }
    
    /**
     * Method responsible for setting the Class UML.
     */
    public void setClassUML() {
        if (getElement().getClassUML() != null)
            getClassComboBox().setSelectedItem(getElement().getClassUML());
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return getTextField("name");
    }
    
    /**
     * Method responsible for returning the Class Combo Box.
     * @return Class Combo Box.
     */
    public JComboBox getClassComboBox() {
        return getComboBox("class");
    }
    
    /**
     * Method responsible for returning the Mandatory Check Box.
     * @return Mandatory Check Box.
     */
    public JCheckBox getMandatoryCheckBox() {
        return getCheckBox("mandatory");
    }
    
    @Override
    public SequenceDiagram getDiagram() {
        return (SequenceDiagram) diagram;
    }
    
    @Override
    public InstanceUML getElement() {
        return (InstanceUML) element;
    }
}