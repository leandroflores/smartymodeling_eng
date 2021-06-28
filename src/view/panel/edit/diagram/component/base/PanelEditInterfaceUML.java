package view.panel.edit.diagram.component.base;

import model.structural.diagram.ComponentDiagram;
import model.structural.diagram.component.base.InterfaceUML;
import view.panel.edit.base.PanelEditElement;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditInterfaceUML</b>.</p> 
 * <p>Class responsible for defining a <b>Interface UML Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-21
 * @see    model.structural.diagram.component.base.InterfaceUML
 * @see    view.panel.edit.base.PanelEditElement
 */
public final class PanelEditInterfaceUML extends PanelEditElement {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Component Diagram.
     * @param interface_ Interface UML.
     */
    public PanelEditInterfaceUML(ViewMenu view, ComponentDiagram diagram, InterfaceUML interface_) {
        super(view, diagram, interface_);
        addComponents();
    }
    
    @Override
    protected void addPanels() {
        addPanels("Interface");
    }
    
    @Override
    public InterfaceUML getElement() {
        return (InterfaceUML) element;
    }
    
    @Override
    public ComponentDiagram getDiagram() {
        return (ComponentDiagram) diagram;
    }
}