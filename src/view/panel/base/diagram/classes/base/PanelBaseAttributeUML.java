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
import view.main.structural.ViewMenu;

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
        controller = new ControllerPanelBaseAttributeUML(this);
        setDefaultProperties();
        addComponents();
        getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        setLayout(new GridLayout(5, 2, 2, 5));
        setPreferredSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Visibility: "));
        add(createComboBox("visibility", getDiagram().getVisibilities(), 30, getElement().getVisibility()));
        getVisibilityComboBox().setPreferredSize(new Dimension(325, 30));
        
        add(createLabel("Name*: "));
        add(createTextField("name", getElement().getName(), 25));
        
        add(createLabel("Type: "));
        add(createComboBox("type", getProject().getTypesList().toArray(), 30, getElement().getTypeUML()));
        getTypeComboBox().setPreferredSize(new Dimension(325, 30));
        
        add(createLabel("Static: "));
        add(createCheckBox("static", "", getElement().isStatic()));
        
        add(createLabel("Final: "));
        add(createCheckBox("final",  "", getElement().isFinal()));
    }
    
    /**
     * Method responsible for setting the Attribute Values.
     */
    public void setValues() {
        getVisibilityComboBox().setSelectedItem(getElement().getVisibility());
        getNameTextField().setText(getElement().getName());
        getTypeComboBox().setSelectedItem(getElement().getTypeUML());
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
     * Method responsible for returning the Type Combo Box.
     * @return Type Combo Box.
     */
    public JComboBox getTypeComboBox() {
        return getComboBox("type");
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
    public AttributeUML getElement() {
        return (AttributeUML) element;
    }
}