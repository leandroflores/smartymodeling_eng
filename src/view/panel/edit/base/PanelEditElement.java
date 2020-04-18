package view.panel.edit.base;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Diagram;
import model.structural.base.Element;
import view.edit.panel.dependency.PanelDependency;
import view.panel.base.diagram.PanelBaseElement;
import view.edit.panel.stereotype.PanelStereotype;
import view.panel.edit.PanelEdit;
import view.structural.ViewMenu;

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
    protected void addComponents(String title) {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(100, 100));
            this.addPanelBaseElement(title);
            this.addPanelStereotype();
            this.addPanelDependency();
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Element.
     * @param title Tab Title.
     */
    protected void addPanelBaseElement(String title) {
        this.addPanel("panelBaseElement", new PanelBaseElement(this.viewMenu, this.getDiagram(), this.getElement()));
        this.createScrollPane("scrollPanelBaseElement",  this.getPanelBaseElement());
        this.getScrollPanelBaseElement().setViewportView(this.getPanelBaseElement());
        this.tabbedPane.add(title, this.getScrollPanelBaseElement());
    }
    
    /**
     * Method responsible for adding the Panel Stereotype.
     */
    protected void addPanelStereotype() {
        this.addPanel("panelStereotype", new PanelStereotype(this.getViewMenu(), this.element));
        this.createScrollPane("scrollPanelStereotype",  this.getPanelStereotype());
        this.getScrollPanelStereotype().setViewportView(this.getPanelStereotype());
        this.tabbedPane.add("Stereotype", this.getScrollPanelStereotype());
    }
    
    /**
     * Method responsible for adding the Panel Dependency.
     */
    protected void addPanelDependency() {
        this.addPanel("panelDependency", new PanelDependency(this.getViewMenu(), this.element));
        this.createScrollPane("scrollPanelDependency",  this.getPanelDependency());
        this.getScrollPanelDependency().setViewportView(this.getPanelDependency());
        this.tabbedPane.add("Dependency", this.getScrollPanelDependency());
    }
    
    /**
     * Method responsible for returning the Panel Base Element.
     * @return Panel Base Element.
     */
    public PanelBaseElement getPanelBaseElement() {
        return (PanelBaseElement) this.getPanel("panelBaseElement");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Element.
     * @return Scroll Panel Base Element.
     */
    public JScrollPane getScrollPanelBaseElement() {
        return this.getScrollPane("scrollPanelBaseElement");
    }
    
    /**
     * Method responsible for returning the Panel Stereotype.
     * @return Panel Stereotype.
     */
    public PanelStereotype getPanelStereotype() {
        return (PanelStereotype) this.getPanel("panelStereotype");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Stereotype.
     * @return Scroll Panel Stereotype.
     */
    public JScrollPane getScrollPanelStereotype() {
        return this.getScrollPane("scrollPanelStereotype");
    }
    
    /**
     * Method responsible for returning the Panel Dependency.
     * @return Panel Dependency.
     */
    public PanelDependency getPanelDependency() {
        return (PanelDependency) this.getPanel("panelDependency");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Dependency.
     * @return Scroll Panel Dependency.
     */
    public JScrollPane getScrollPanelDependency() {
        return this.getScrollPane("scrollPanelDependency");
    }
    
    /**
     * Method responsible for returning the Element.
     * @return Element.
     */
    public Element getElement() {
        return this.element;
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return this.diagram;
    }
}