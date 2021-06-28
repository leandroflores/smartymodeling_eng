package view.panel.edit.diagram.component.base;

import model.structural.diagram.ComponentDiagram;
import model.structural.diagram.component.base.ComponentUML;
import view.panel.edit.base.PanelEditElement;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditComponentUML</b>.</p> 
 * <p>Class responsible for defining a <b>Component UML Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-21
 * @see    model.structural.diagram.component.base.ComponentUML
 * @see    view.panel.edit.base.PanelEditElement
 */
public final class PanelEditComponentUML extends PanelEditElement {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Component Diagram.
     * @param component Component UML.
     */
    public PanelEditComponentUML(ViewMenu view, ComponentDiagram diagram, ComponentUML component) {
        super(view, diagram, component);
        addComponents();
    }
    
    @Override
    protected void addPanels() {
        addPanels("Component");
    }
    
    @Override
    public ComponentUML getElement() {
        return (ComponentUML) element;
    }
    
    @Override
    public ComponentDiagram getDiagram() {
        return (ComponentDiagram) diagram;
    }
}