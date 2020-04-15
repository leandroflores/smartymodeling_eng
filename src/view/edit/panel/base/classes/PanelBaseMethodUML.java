package view.edit.panel.base.classes;

import controller.view.edit.panel.base.classes.ControllerPanelBaseMethodUML;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.MethodUML;
import view.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseMethodUML</b>.</p> 
 * <p>Class responsible for defining a Base Panel for the <b>Method UML</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  16/06/2019
 * @see    controller.view.edit.panel.base.classes.ControllerPanelBaseMethodUML
 * @see    model.structural.diagram.classes.base.MethodUML
 * @see    view.Panel
 */
public final class PanelBaseMethodUML extends Panel {
    private final ViewMenu viewMenu;
    private final ClassDiagram diagram;
    private final MethodUML methodUML;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Class Diagram.
     * @param method Method UML.
     */
    public PanelBaseMethodUML(ViewMenu viewMenu, ClassDiagram diagram, MethodUML method) {
        this.viewMenu   = viewMenu;
        this.diagram    = diagram;
        this.methodUML  = method;
        this.controller = new ControllerPanelBaseMethodUML(this);
        this.setSettings();
        this.addComponents();
//        this.setValues();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridLayout(7, 2, 2, 5));
        this.setPreferredSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Visibility*: "));
        this.add(this.createComboBox("visibilityComboBox", this.diagram.getVisibilities(), 30, this.methodUML.getVisibility()));
        this.getVisibilityComboBox().setPreferredSize(new Dimension(325, 30));
        
        this.add(this.createLabel("Name*: "));
        this.add(this.createTextField("nameTextField", this.methodUML.getName(), 25));
        
        this.add(this.createLabel("Constructor: "));
        this.add(this.createCheckBox("constructorCheckBox", "", this.methodUML.isConstructor()));
        
        this.add(this.createLabel("Return: "));
        this.add(this.createComboBox("returnComboBox", this.diagram.getProject().getTypesList().toArray(), 30, this.methodUML.getReturn()));
        this.getReturnComboBox().setPreferredSize(new Dimension(200, 30));
        
        this.add(this.createLabel("Abstract: "));
        this.add(this.createCheckBox("abstractCheckBox", "", this.methodUML.isAbstract()));
        
        this.add(this.createLabel("Static: "));
        this.add(this.createCheckBox("staticCheckBox",   "", this.methodUML.isStatic()));
        
        this.add(this.createLabel("Final: "));
        this.add(this.createCheckBox("finalCheckBox",    "", this.methodUML.isFinal()));
    }
    
    /**
     * Method responsible for setting the Method Values.
     */
    public void setValues() {
        this.getVisibilityComboBox().setSelectedItem(this.methodUML.getVisibility());
        this.getNameTextField().setText(this.methodUML.getName());
        this.getConstructorCheckBox().setSelected(this.methodUML.isConstructor());
        this.getReturnComboBox().setSelectedItem(this.methodUML.getReturn());
        this.getReturnComboBox().setEnabled(!this.methodUML.isConstructor());
        this.getAbstractCheckBox().setSelected(this.methodUML.isAbstract());
        this.getStaticCheckBox().setSelected(this.methodUML.isStatic());
        this.getFinalCheckBox().setSelected(this.methodUML.isFinal());
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
     * Method responsible for returning the Method.
     * @return Method.
     */
    public MethodUML getMethodUML() {
        return this.methodUML;
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