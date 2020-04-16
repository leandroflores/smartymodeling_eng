package view.edit.panel.base.classes;

import controller.view.edit.panel.base.classes.ControllerPanelBaseAttributeUML;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.AttributeUML;
import view.panel.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseAttributeUML</b>.</p> 
 * <p>Class responsible for defining a Base Panel for the <b>Attribute UML</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  05/06/2019
 * @see    controller.view.edit.panel.base.classes.ControllerPanelBaseAttributeUML
 * @see    model.structural.diagram.classes.base.AttributeUML
 * @see    view.panel.Panel
 */
public final class PanelBaseAttributeUML extends Panel {
    private final ViewMenu viewMenu;
    private final ClassDiagram diagram;
    private final AttributeUML attributeUML;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Class Diagram.
     * @param attributeUML Attribute UML.
     */
    public PanelBaseAttributeUML(ViewMenu viewMenu, ClassDiagram diagram, AttributeUML attributeUML) {
        this.viewMenu     = viewMenu;
        this.diagram      = diagram;
        this.attributeUML = attributeUML;
        this.controller   = new ControllerPanelBaseAttributeUML(this);
        this.setSettings();
        this.addComponents();
//        this.setValues();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridLayout(5, 2, 2, 5));
        this.setPreferredSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Visibility: "));
        this.add(this.createComboBox("visibilityComboBox", this.diagram.getVisibilities(), 30, this.attributeUML.getVisibility()));
        this.getVisibilityComboBox().setPreferredSize(new Dimension(325, 30));
        
        this.add(this.createLabel("Name*: "));
        this.add(this.createTextField("nameTextField", this.attributeUML.getName(), 25));
        
        this.add(this.createLabel("Type: "));
        this.add(this.createComboBox("typeComboBox", this.diagram.getProject().getTypesList().toArray(), 30, this.attributeUML.getTypeUML()));
        this.getTypeComboBox().setPreferredSize(new Dimension(325, 30));
        
        this.add(this.createLabel("Static: "));
        this.add(this.createCheckBox("staticCheckBox", "", this.attributeUML.isStatic()));
        
        this.add(this.createLabel("Final: "));
        this.add(this.createCheckBox("finalCheckBox",  "", this.attributeUML.isFinal()));
    }
    
    /**
     * Method responsible for setting the Attribute Values.
     */
    public void setValues() {
        this.getVisibilityComboBox().setSelectedItem(this.attributeUML.getVisibility());
        this.getNameTextField().setText(this.attributeUML.getName());
        this.getTypeComboBox().setSelectedItem(this.attributeUML.getTypeUML());
        this.getStaticCheckBox().setSelected(this.attributeUML.isStatic());
        this.getFinalCheckBox().setSelected(this.attributeUML.isFinal());
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
    
    /**
     * Method responsible for returning the Class Diagram.
     * @return Class Diagram.
     */
    public ClassDiagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Attribute UML.
     * @return Attribute UML.
     */
    public AttributeUML getAttributeUML() {
        return this.attributeUML;
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