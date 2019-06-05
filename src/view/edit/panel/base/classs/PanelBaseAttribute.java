package view.edit.panel.base.classs;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classs.base.AttributeUML;
import view.Panel;

/**
 * <p>Class of View <b>PanelBaseAttribute</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Attribute Base</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  05/06/2019
 * @see    view.Panel
 */
public final class PanelBaseAttribute extends Panel {
    private final ClassDiagram diagram;
    private final AttributeUML attribute;
    
    /**
     * Default constructor method of Class.
     * @param diagram Class Diagram.
     * @param attribute Attribute UML.
     */
    public PanelBaseAttribute(ClassDiagram diagram, AttributeUML attribute) {
        this.diagram   = diagram;
        this.attribute = attribute;
        this.setSettings();
        this.addComponents();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setPreferredSize(new Dimension(300, 100));
    }
    
    @Override
    protected void addComponents() {
        this.addLines(1);
        
        this.add(this.createLabel("Visibility: ", 120));
        this.add(this.createComboBox("visibilityComboBox", this.diagram.getVisibilities(), 30));
//        this.getVisibilityComboBox().setPreferredSize(new Dimension(400, 30));
        this.addLines(1);
        
        this.add(this.createLabel("Name*: ", 120));
        this.add(this.createTextField("nameTextField", "", 30));
        
        this.addLines(1);
        
        this.add(this.createLabel("Type: ", 120));
        this.add(this.createComboBox("typeComboBox", this.diagram.getProject().getTypesList().toArray(), 30));
        
        this.addLines(1);
        
        this.add(this.createCheckBox("staticCheckBox", "Static"));
        this.add(this.createLabel("", 5));
        this.add(this.createCheckBox("finalCheckBox",  "Final"));
        
        this.addLines(1);
    }
    
    /**
     * Method responsible for returning the Class Diagram.
     * @return Class Diagram.
     */
    public ClassDiagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Attribute.
     * @return Attribute.
     */
    public AttributeUML getAttribute() {
        return this.attribute;
    }
    
    /**
     * Method responsible for returning the Visibility Combo Box.
     * @return Visibility Combo Box.
     */
    public JComboBox getVisibilityComboBox() {
        return this.comboBoxes.get("visibilityComboBox");
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.textFields.get("nameTextField");
    }
    
    /**
     * Method responsible for returning the Type Combo Box.
     * @return Type Combo Box.
     */
    public JComboBox getTypeComboBox() {
        return this.comboBoxes.get("typeComboBox");
    }
    
    /**
     * Method responsible for returning the Static Check Box.
     * @return Static Check Box.
     */
    public JCheckBox getStaticCheckBox() {
        return this.checkBoxes.get("staticCheckBox");
    }
    
    /**
     * Method responsible for returning the Final Check Box.
     * @return Final Check Box.
     */
    public JCheckBox getFinalCheckBox() {
        return this.checkBoxes.get("finalCheckBox");
    }
}