package view.panel.base;

import controller.view.panel.base.ControllerPanelBaseElement;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import model.structural.base.Diagram;
import model.structural.base.Element;
import view.panel.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseElement</b>.</p>
 * <p>Class responsible for defining the <b>Element Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-30
 * @see    controller.view.panel.base.ControllerPanelBaseElement
 * @see    model.structural.base.Element
 * @see    view.panel.Panel
 */
public abstract class PanelBaseElement extends PanelBase {
    protected final Diagram diagram;
    protected final Element element;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Diagram.
     * @param element Element.
     */
    public PanelBaseElement(ViewMenu view, Diagram diagram, Element element) {
        super(view);
        this.diagram = diagram;
        this.element = element;
    }
    
    /**
     * Method responsible for setting the Default Properties.
     */
    protected void setDefaultProperties() {
        this.setLayout(new GridLayout(2, 2));
        this.setPreferredSize(new Dimension(50, 50));
        this.setSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Name*: ", 120));
        this.add(this.createTextField("nameTextField", this.element.getName(), 20));
        
        this.add(this.createLabel("Mandatory: ", 120));
        this.add(this.createCheckBox("mandatoryCheckBox", "", this.element.isMandatory()));
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Element.
     * @return Element.
     */
    public Element getElement() {
        return this.element;
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.getTextField("nameTextField");
    }
    
    /**
     * Method responsible for returning the Mandatory Check Box.
     * @return Mandatory Check Box.
     */
    public JCheckBox getMandatoryCheckBox() {
        return this.getCheckBox("mandatoryCheckBox");
    }
}