package view.panel.base.diagram.classes.base;

import controller.view.panel.base.diagram.classes.base.ControllerPanelBaseMethodUML;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.MethodUML;
import view.panel.base.PanelBaseElement;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseMethodUML</b>.</p> 
 * <p>Class responsible for defining a <b>Method UML Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-16
 * @see    controller.view.panel.base.diagram.classes.base.ControllerPanelBaseMethodUML
 * @see    model.structural.diagram.classes.base.MethodUML
 * @see    view.panel.base.PanelBaseElement
 */
public final class PanelBaseMethodUML extends PanelBaseElement {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Class Diagram.
     * @param method Method UML.
     */
    public PanelBaseMethodUML(ViewMenu view, ClassDiagram diagram, MethodUML method) {
        super(view, diagram, method);
        this.controller = new ControllerPanelBaseMethodUML(this);
        this.setDefaultProperties();
        this.addComponents();
        this.getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridLayout(7, 2, 2, 5));
        this.setPreferredSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Visibility*: "));
        this.add(this.createComboBox("visibilityComboBox", this.getDiagram().getVisibilities(), 30, this.getElement().getVisibility()));
        this.getVisibilityComboBox().setPreferredSize(new Dimension(325, 30));
        
        this.add(this.createLabel("Name*: "));
        this.add(this.createTextField("nameTextField", this.getElement().getName(), 25));
        
        this.add(this.createLabel("Constructor: "));
        this.add(this.createCheckBox("constructorCheckBox", "", this.getElement().isConstructor()));
        
        this.add(this.createLabel("Return: "));
        this.add(this.createComboBox("returnComboBox", this.diagram.getProject().getTypesList().toArray(), 30, this.getElement().getReturn()));
        this.getReturnComboBox().setPreferredSize(new Dimension(200, 30));
        
        this.add(this.createLabel("Abstract: "));
        this.add(this.createCheckBox("abstractCheckBox", "", this.getElement().isAbstract()));
        
        this.add(this.createLabel("Static: "));
        this.add(this.createCheckBox("staticCheckBox",   "", this.getElement().isStatic()));
        
        this.add(this.createLabel("Final: "));
        this.add(this.createCheckBox("finalCheckBox",    "", this.getElement().isFinal()));
    }
    
    /**
     * Method responsible for setting the Method Values.
     */
    public void setValues() {
        this.getVisibilityComboBox().setSelectedItem(this.getElement().getVisibility());
        this.getNameTextField().setText(this.getElement().getName());
        this.getConstructorCheckBox().setSelected(this.getElement().isConstructor());
        this.getReturnComboBox().setSelectedItem(this.getElement().getReturn());
        this.getReturnComboBox().setEnabled(!this.getElement().isConstructor());
        this.getAbstractCheckBox().setSelected(this.getElement().isAbstract());
        this.getStaticCheckBox().setSelected(this.getElement().isStatic());
        this.getFinalCheckBox().setSelected(this.getElement().isFinal());
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
     * Method responsible for returning the Constructor Check Box.
     * @return Constructor Check Box.
     */
    public JCheckBox getConstructorCheckBox() {
        return this.getCheckBox("constructorCheckBox");
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
    
    @Override
    public ClassDiagram getDiagram() {
        return (ClassDiagram) this.diagram;
    }
    
    @Override
    public MethodUML getElement() {
        return (MethodUML) this.element;
    }
}