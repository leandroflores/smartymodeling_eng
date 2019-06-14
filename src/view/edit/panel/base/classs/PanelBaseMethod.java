package view.edit.panel.base.classs;

import controller.view.edit.panel.base.classs.ControllerPanelBaseMethod;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classs.base.MethodUML;
import view.Panel;

/**
 * <p>Class of View <b>PanelBaseMethod</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Method Base</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  06/06/2019
 * @see    controller.view.edit.panel.base.classs.ControllerPanelBaseMethod
 * @see    model.structural.diagram.classs.base.MethodUML
 * @see    view.Panel
 */
public final class PanelBaseMethod extends Panel {
    private final ClassDiagram diagram;
    private final MethodUML    method;
    
    /**
     * Default constructor method of Class.
     * @param diagram Class Diagram.
     * @param method Method UML.
     */
    public PanelBaseMethod(ClassDiagram diagram, MethodUML method) {
        this.diagram    = diagram;
        this.method     = method;
        this.controller = new ControllerPanelBaseMethod(this);
        this.setSettings();
        this.addComponents();
        this.setValues();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setPreferredSize(new Dimension(360, 100));
    }
    
    @Override
    protected void addComponents() {
        this.addLines(1);
        
        this.add(this.createLabel("Visibility: ", 120));
        this.add(this.createComboBox("visibilityComboBox", this.diagram.getVisibilities(), 30, this.method.getVisibility()));
        this.getVisibilityComboBox().setPreferredSize(new Dimension(325, 30));
        this.addLines(1);
        
        this.add(this.createLabel("Name*: ", 120));
        this.add(this.createTextField("nameTextField", this.method.getName(), 25));
        
        this.addLines(1);
        
        this.add(this.createLabel("Constructor: ", 120));
        this.add(this.createCheckBox("constructorCheckBox", "", this.method.isConstructor()));
        this.add(this.createLabel("Return: ", 100));
        this.add(this.createComboBox("returnComboBox", this.diagram.getProject().getTypesList().toArray(), 30, this.method.getReturn()));
        this.getReturnComboBox().setPreferredSize(new Dimension(200, 30));
        
        this.addLines(1);
        
        this.add(this.createCheckBox("abstractCheckBox", "Abstract", this.method.isAbstract()));
        this.add(this.createLabel("", 5));
        this.add(this.createCheckBox("staticCheckBox",   "Static",   this.method.isStatic()));
        this.add(this.createLabel("", 5));
        this.add(this.createCheckBox("finalCheckBox",     "Final",   this.method.isFinal()));
        
        this.addLines(1);
    }
    
    /**
     * Method responsible for setting the Method Values.
     */
    public void setValues() {
        this.getVisibilityComboBox().setSelectedItem(this.method.getVisibility());
        this.getNameTextField().setText(this.method.getName());
        this.getConstructorCheckBox().setSelected(this.method.isConstructor());
        this.getReturnComboBox().setSelectedItem(this.method.getReturn());
        this.getReturnComboBox().setEnabled(!this.method.isConstructor());
        this.getAbstractCheckBox().setSelected(this.method.isAbstract());
        this.getStaticCheckBox().setSelected(this.method.isStatic());
        this.getFinalCheckBox().setSelected(this.method.isFinal());
    }
    
    /**
     * Method responsible for returning the Class Diagram.
     * @return Class Diagram.
     */
    public ClassDiagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Method.
     * @return Method.
     */
    public MethodUML getMethod() {
        return this.method;
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
     * Method responsible for returning the Constructor Check Box.
     * @return Constructor Check Box.
     */
    public JCheckBox getConstructorCheckBox() {
        return this.checkBoxes.get("constructorCheckBox");
    }
    
    /**
     * Method responsible for returning the Return Combo Box.
     * @return Return Combo Box.
     */
    public JComboBox getReturnComboBox() {
        return this.comboBoxes.get("returnComboBox");
    }
    
    /**
     * Method responsible for returning the Abstract Check Box.
     * @return Abstract Check Box.
     */
    public JCheckBox getAbstractCheckBox() {
        return this.checkBoxes.get("abstractCheckBox");
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