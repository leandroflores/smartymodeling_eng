package view.panel.base.diagram;

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
 * <p>Class responsible for defining a Panel for showing the <b>Element Base</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  30/05/2019
 * @see    controller.view.edit.panel.base.ControllerPanelBaseElement
 * @see    model.structural.base.Element
 * @see    view.panel.Panel
 */
public final class PanelBaseElement extends Panel {
    private final ViewMenu viewMenu;
    private final Diagram diagram;
    private final Element element;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Diagram.
     * @param element Element.
     */
    public PanelBaseElement(ViewMenu viewMenu, Diagram diagram, Element element) {
        this.viewMenu   = viewMenu;
        this.diagram    = diagram;
        this.element    = element;
//        this.controller = new ControllerPanelBaseElement(this);
        this.setSettings();
        this.addComponents();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    protected void setSettings() {
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