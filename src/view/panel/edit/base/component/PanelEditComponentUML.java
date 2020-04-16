package view.panel.edit.base.component;

import model.structural.diagram.ComponentDiagram;
import model.structural.diagram.component.base.ComponentUML;
import view.panel.edit.base.PanelEditElement;
import view.structural.ViewMenu;

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
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        super.addComponents("Component");
    }
    
    @Override
    public ComponentDiagram getDiagram() {
        return (ComponentDiagram) this.diagram;
    }
    
    @Override
    public ComponentUML getElement() {
        return (ComponentUML) this.element;
    }
}