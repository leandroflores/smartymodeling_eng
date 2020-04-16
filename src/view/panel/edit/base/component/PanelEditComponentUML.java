package view.panel.edit.base.component;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.diagram.ComponentDiagram;
import model.structural.diagram.component.base.ComponentUML;
import view.edit.panel.base.PanelBaseElement;
import view.panel.edit.base.PanelEditElement;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditComponentUML</b>.</p> 
 * <p>Class responsible for defining a Panel for Edit the <b>Component UML</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  21/07/2019
 * @see    model.structural.diagram.component.base.ComponentUML
 * @see    view.panel.edit.base.PanelEditElement
 */
public final class PanelEditComponentUML extends PanelEditElement {
    private final ComponentDiagram diagram;
    private final ComponentUML componentUML;
    private PanelBaseElement panelBaseElement;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Component Diagram.
     * @param componentUML Component UML.
     */
    public PanelEditComponentUML(ViewMenu viewMenu, ComponentDiagram diagram, ComponentUML componentUML) {
        super(viewMenu, componentUML);
        this.diagram      = diagram;
        this.componentUML = componentUML;
        this.setPreferredSize(new Dimension(200, 100));
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(100, 100));
        
        this.addPanelBaseElement();
        this.addPanelStereotype();
        this.addPanelDependency();
        
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Element.
     */
    private void addPanelBaseElement() {
        this.panelBaseElement = new PanelBaseElement(this.viewMenu, this.diagram, this.componentUML);
        this.createScrollPane("scrollPanelBaseElement",   this.panelBaseElement);
        this.getScrollPanelBaseElement().setViewportView(this.panelBaseElement);
        this.tabbedPane.add("Component", this.getScrollPanelBaseElement());
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public ComponentDiagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Component UML.
     * @return Component UML.
     */
    public ComponentUML getComponentUML() {
        return this.componentUML;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Element.
     * @return Scroll Panel Base Element.
     */
    public JScrollPane getScrollPanelBaseElement() {
        return this.getScrollPane("scrollPanelBaseElement");
    }

    /**
     * Method responsible for returning the Panel Base Element.
     * @return Panel Base Element.
     */
    public PanelBaseElement getPanelBaseElement() {
        return this.panelBaseElement;
    }
}