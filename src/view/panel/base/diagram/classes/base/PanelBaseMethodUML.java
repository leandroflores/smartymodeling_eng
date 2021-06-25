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
        controller = new ControllerPanelBaseMethodUML(this);
        setDefaultProperties();
        addComponents();
        getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        setLayout(new GridLayout(7, 2, 2, 5));
        setPreferredSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Visibility*: "));
        add(createComboBox("visibility", getDiagram().getVisibilities(), 30, getElement().getVisibility()));
        getVisibilityComboBox().setPreferredSize(new Dimension(325, 30));
        
        add(createLabel("Name*: "));
        add(createTextField("name", getElement().getName(), 25));
        
        add(createLabel("Constructor: "));
        add(createCheckBox("constructor", "", getElement().isConstructor()));
        
        add(createLabel("Return: "));
        add(createComboBox("return", diagram.getProject().getTypesList().toArray(), 30, getElement().getReturn()));
        getReturnComboBox().setPreferredSize(new Dimension(200, 30));
        
        add(createLabel("Abstract: "));
        add(createCheckBox("abstract", "", getElement().isAbstract()));
        
        add(createLabel("Static: "));
        add(createCheckBox("static", "", getElement().isStatic()));
        
        add(createLabel("Final: "));
        add(createCheckBox("final", "", getElement().isFinal()));
    }
    
    /**
     * Method responsible for setting the Method Values.
     */
    public void setValues() {
        getVisibilityComboBox().setSelectedItem(getElement().getVisibility());
        getNameTextField().setText(getElement().getName());
        getConstructorCheckBox().setSelected(getElement().isConstructor());
        getReturnComboBox().setSelectedItem(getElement().getReturn());
        getReturnComboBox().setEnabled(!getElement().isConstructor());
        getAbstractCheckBox().setSelected(getElement().isAbstract());
        getStaticCheckBox().setSelected(getElement().isStatic());
        getFinalCheckBox().setSelected(getElement().isFinal());
    }
    
    /**
     * Method responsible for returning the Visibility Combo Box.
     * @return Visibility Combo Box.
     */
    public JComboBox getVisibilityComboBox() {
        return getComboBox("visibility");
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return getTextField("name");
    }
    
    /**
     * Method responsible for returning the Constructor Check Box.
     * @return Constructor Check Box.
     */
    public JCheckBox getConstructorCheckBox() {
        return getCheckBox("constructor");
    }
    
    /**
     * Method responsible for returning the Return Combo Box.
     * @return Return Combo Box.
     */
    public JComboBox getReturnComboBox() {
        return getComboBox("return");
    }
    
    /**
     * Method responsible for returning the Abstract Check Box.
     * @return Abstract Check Box.
     */
    public JCheckBox getAbstractCheckBox() {
        return getCheckBox("abstract");
    }
    
    /**
     * Method responsible for returning the Static Check Box.
     * @return Static Check Box.
     */
    public JCheckBox getStaticCheckBox() {
        return getCheckBox("static");
    }
    
    /**
     * Method responsible for returning the Final Check Box.
     * @return Final Check Box.
     */
    public JCheckBox getFinalCheckBox() {
        return getCheckBox("final");
    }
    
    @Override
    public ClassDiagram getDiagram() {
        return (ClassDiagram) diagram;
    }
    
    @Override
    public MethodUML getElement() {
        return (MethodUML) element;
    }
}