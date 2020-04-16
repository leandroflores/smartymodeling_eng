package view.edit.panel.base.classes;

import controller.view.edit.panel.base.classes.ControllerPanelBaseMethodAbsUML;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.MethodUML;
import view.panel.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseMethodAbsUML</b>.</p> 
 * <p>Class responsible for defining a Base Panel for the <b>Abstract Method UML</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  27/01/2020
 * @see    controller.view.edit.panel.base.classes.ControllerPanelBaseMethodAbsUML
 * @see    model.structural.diagram.classes.base.MethodUML
 * @see    view.panel.Panel
 */
public final class PanelBaseMethodAbsUML extends Panel {
    private final ViewMenu viewMenu;
    private final ClassDiagram diagram;
    private final MethodUML methodUML;
    
    /**
     * Default constructor methodUML of Class.
     * @param viewMenu View Menu.
     * @param diagram Class Diagram.
     * @param method Method UML.
     */
    public PanelBaseMethodAbsUML(ViewMenu viewMenu, ClassDiagram diagram, MethodUML method) {
        this.viewMenu   = viewMenu;
        this.diagram    = diagram;
        this.methodUML  = method;
        this.controller = new ControllerPanelBaseMethodAbsUML(this);
        this.setSettings();
        this.addComponents();
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
        this.add(this.createComboBox("visibilityComboBox", this.diagram.getAbstractVisibilites(), 30, this.methodUML.getVisibility()));
        this.getVisibilityComboBox().setPreferredSize(new Dimension(325, 30));
        
        this.add(this.createLabel("Name*: "));
        this.add(this.createTextField("nameTextField", this.methodUML.getName(), 25));
        
        this.add(this.createLabel("Return: "));
        this.add(this.createComboBox("returnComboBox", this.diagram.getProject().getTypesList().toArray(), 30, this.methodUML.getReturn()));
        this.getReturnComboBox().setPreferredSize(new Dimension(200, 30));
        
        this.add(this.createLabel("Abstract: "));
        this.add(this.createCheckBox("abstractCheckBox", "", this.methodUML.isAbstract()));
        this.getAbstractCheckBox().setEnabled(false);
    }
    
    /**
     * Method responsible for setting the Abstract Method Values.
     */
    public void setValues() {
        this.getVisibilityComboBox().setSelectedItem(this.methodUML.getVisibility());
        this.getNameTextField().setText(this.methodUML.getName());
        this.getReturnComboBox().setSelectedItem(this.methodUML.getReturn());
        this.getAbstractCheckBox().setSelected(true);
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
     * Method responsible for returning the Return Combo Box.
     * @return Return Combo Box.
     */
    public JComboBox getReturnComboBox() {
        return this.getComboBox("returnComboBox");
    }
    
    /**
     * Method responsible for returning the Abstract Check Box.
     * @return Abstract Check Box.
     */
    public JCheckBox getAbstractCheckBox() {
        return this.getCheckBox("abstractCheckBox");
    }
}