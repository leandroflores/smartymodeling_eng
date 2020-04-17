package view.panel.base.diagram.classes.base;

import controller.view.panel.base.diagram.classes.base.ControllerPanelBaseAttributeUML;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.AttributeUML;
import view.panel.base.PanelBaseElement;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseAttributeUML</b>.</p>
 * <p>Class responsible for defining a <b>Attribute UML Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-05
 * @see    controller.view.panel.base.diagram.classes.base.ControllerPanelBaseAttributeUML
 * @see    model.structural.diagram.classes.base.AttributeUML
 * @see    view.panel.base.PanelBaseElement
 */
public final class PanelBaseAttributeUML extends PanelBaseElement {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Class Diagram.
     * @param attribute Attribute UML.
     */
    public PanelBaseAttributeUML(ViewMenu view, ClassDiagram diagram, AttributeUML attribute) {
        super(view, diagram, attribute);
        this.controller  = new ControllerPanelBaseAttributeUML(this);
        this.setDefaultProperties();
        this.addComponents();
        this.getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridLayout(5, 2, 2, 5));
        this.setPreferredSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Visibility: "));
        this.add(this.createComboBox("visibilityComboBox", this.getDiagram().getVisibilities(), 30, this.getElement().getVisibility()));
        this.getVisibilityComboBox().setPreferredSize(new Dimension(325, 30));
        
        this.add(this.createLabel("Name*: "));
        this.add(this.createTextField("nameTextField", this.getElement().getName(), 25));
        
        this.add(this.createLabel("Type: "));
        this.add(this.createComboBox("typeComboBox", this.getProject().getTypesList().toArray(), 30, this.getElement().getTypeUML()));
        this.getTypeComboBox().setPreferredSize(new Dimension(325, 30));
        
        this.add(this.createLabel("Static: "));
        this.add(this.createCheckBox("staticCheckBox", "", this.getElement().isStatic()));
        
        this.add(this.createLabel("Final: "));
        this.add(this.createCheckBox("finalCheckBox",  "", this.getElement().isFinal()));
    }
    
    /**
     * Method responsible for setting the Attribute Values.
     */
    public void setValues() {
        this.getVisibilityComboBox().setSelectedItem(this.getElement().getVisibility());
        this.getNameTextField().setText(this.getElement().getName());
        this.getTypeComboBox().setSelectedItem(this.getElement().getTypeUML());
        this.getStaticCheckBox().setSelected(this.getElement().isStatic());
        this.getFinalCheckBox().setSelected(this.getElement().isFinal());
    }
    
    @Override
    public ClassDiagram getDiagram() {
        return (ClassDiagram) this.diagram;
    }
    
    @Override
    public AttributeUML getElement() {
        return (AttributeUML) this.element;
    }
    
    /**
     * Method responsible for returning the Visibility Combo Box.
     * @return Visibility Combo Box.
     */
    public JComboBox getVisibilityComboBox() {
        return this.getComboBox("visibilityComboBox");
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.getTextField("nameTextField");
    }
    
    /**
     * Method responsible for returning the Type Combo Box.
     * @return Type Combo Box.
     */
    public JComboBox getTypeComboBox() {
        return this.getComboBox("typeComboBox");
    }
    
    /**
     * Method responsible for returning the Static Check Box.
     * @return Static Check Box.
     */
    public JCheckBox getStaticCheckBox() {
        return this.getCheckBox("staticCheckBox");
    }
    
    /**
     * Method responsible for returning the Final Check Box.
     * @return Final Check Box.
     */
    public JCheckBox getFinalCheckBox() {
        return this.getCheckBox("finalCheckBox");
    }
}