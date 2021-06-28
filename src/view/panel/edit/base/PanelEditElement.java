package view.panel.edit.base;

import model.structural.base.Diagram;
import model.structural.base.Element;
import view.panel.dependency.PanelDependency;
import view.panel.base.diagram.PanelBaseElement;
import view.panel.base.stereotype.PanelStereotype;
import view.panel.edit.PanelEdit;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditElement</b>.</p> 
 * <p>Class responsible for defining a <b>Element Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-12
 * @see    model.structural.base.Element
 * @see    view.panel.edit.PanelEdit
 */
public abstract class PanelEditElement extends PanelEdit {
    protected final Diagram diagram;
    protected final Element element;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Diagram.
     * @param element Element.
     */
    public PanelEditElement(ViewMenu view, Diagram diagram, Element element) {
        super(view);
        this.diagram = diagram;
        this.element = element;
    }
    
    /**
     * Method responsible for adding the Components.
     * @param title Tab Element Title.
     */
    protected void addPanels(String title) {
        addPanelBaseElement(title);
        addPanelStereotype();
        addPanelDependency();
    }
    
    /**
     * Method responsible for adding the Panel Base Element.
     * @param title Tab Title.
     */
    protected void addPanelBaseElement(String title) {
        addPanel("base_element", new PanelBaseElement(viewMenu, getDiagram(), getElement()));
        createScrollPane("base_element", getPanelBaseElement());
        getScrollPane("base_element").setViewportView(getPanelBaseElement());
        tabbedPane.add(title, getScrollPane("base_element"));
    }
    
    /**
     * Method responsible for adding the Panel Stereotype.
     */
    protected void addPanelStereotype() {
        addPanel("stereotype", new PanelStereotype(getViewMenu(), element));
        createScrollPane("stereotype", getPanelStereotype());
        getScrollPane("stereotype").setViewportView(getPanelStereotype());
        tabbedPane.add("Stereotype", getScrollPane("stereotype"));
    }
    
    /**
     * Method responsible for adding the Panel Dependency.
     */
    protected void addPanelDependency() {
        addPanel("dependency", new PanelDependency(getViewMenu(), element));
        createScrollPane("dependency", getPanelDependency());
        getScrollPane("dependency").setViewportView(getPanelDependency());
        tabbedPane.add("Dependency", getScrollPane("dependency"));
    }
    
    /**
     * Method responsible for returning the Panel Base Element.
     * @return Panel Base Element.
     */
    public PanelBaseElement getPanelBaseElement() {
        return (PanelBaseElement) getPanel("base_element");
    }
    
    /**
     * Method responsible for returning the Panel Stereotype.
     * @return Panel Stereotype.
     */
    public PanelStereotype getPanelStereotype() {
        return (PanelStereotype) getPanel("stereotype");
    }
    
    /**
     * Method responsible for returning the Panel Dependency.
     * @return Panel Dependency.
     */
    public PanelDependency getPanelDependency() {
        return (PanelDependency) getPanel("dependency");
    }
    
    /**
     * Method responsible for returning the Element.
     * @return Element.
     */
    public Element getElement() {
        return element;
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return diagram;
    }
}