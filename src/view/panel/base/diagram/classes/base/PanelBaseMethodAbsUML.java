package view.panel.base.diagram.classes.base;

import controller.view.panel.base.diagram.classes.base.ControllerPanelBaseMethodAbsUML;
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
 * <p>Class of View <b>PanelBaseMethodAbsUML</b>.</p>
 * <p>Class responsible for defining a <b>Abstract Method UML Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-01-27
 * @see    controller.view.panel.base.diagram.classes.base.ControllerPanelBaseMethodAbsUML
 * @see    model.structural.diagram.classes.base.MethodUML
 * @see    view.panel.base.PanelBaseElement
 */
public final class PanelBaseMethodAbsUML extends PanelBaseElement {
    
    /**
     * Default constructor methodUML of Class.
     * @param view View Menu.
     * @param diagram Class Diagram.
     * @param method Method UML.
     */
    public PanelBaseMethodAbsUML(ViewMenu view, ClassDiagram diagram, MethodUML method) {
        super(view, diagram, method);
        controller = new ControllerPanelBaseMethodAbsUML(this);
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
        add(createComboBox("visibility", getDiagram().getAbstractVisibilites(), 30, getElement().getVisibility()));
        getVisibilityComboBox().setPreferredSize(new Dimension(325, 30));
        
        add(createLabel("Name*: "));
        add(createTextField("name", getElement().getName(), 25));
        
        add(createLabel("Return: "));
        add(createComboBox("return", getProject().getTypesList().toArray(), 30, getElement().getReturn()));
        getReturnComboBox().setPreferredSize(new Dimension(200, 30));
        
        add(createLabel("Abstract: "));
        add(createCheckBox("abstract", "", getElement().isAbstract()));
        getAbstractCheckBox().setEnabled(false);
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
    
    @Override
    public ClassDiagram getDiagram() {
        return (ClassDiagram) diagram;
    }
    
    @Override
    public MethodUML getElement() {
        return (MethodUML) element;
    }
}