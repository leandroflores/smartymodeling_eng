package view.panel.base.diagram;

import controller.view.panel.base.diagram.ControllerPanelBaseElement;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import model.structural.base.Diagram;
import model.structural.base.Element;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseElement</b>.</p> 
 * <p>Class responsible for defining a <b>Element Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-30
 * @see    controller.view.panel.base.diagram.ControllerPanelBaseElement
 * @see    model.structural.base.Element
 * @see    view.panel.base.PanelBaseElement
 */
public final class PanelBaseElement extends view.panel.base.PanelBaseElement {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Diagram.
     * @param element Element.
     */
    public PanelBaseElement(ViewMenu view, Diagram diagram, Element element) {
        super(view, diagram, element);
        controller = new ControllerPanelBaseElement(this);
        setDefaultProperties();
        addComponents();
        getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        super.setDefaultProperties();
        setLayout(new GridLayout(2, 2));
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Name*: ", 120));
        add(createTextField("name", element.getName(), 20));
        
        add(createLabel("Mandatory: ", 120));
        add(createCheckBox("mandatory", "", element.isMandatory()));
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return getTextField("name");
    }
    
    /**
     * Method responsible for returning the Mandatory Check Box.
     * @return Mandatory Check Box.
     */
    public JCheckBox getMandatoryCheckBox() {
        return getCheckBox("mandatory");
    }
}