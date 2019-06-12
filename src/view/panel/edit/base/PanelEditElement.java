package view.panel.edit.base;

import javax.swing.JScrollPane;
import model.structural.base.Element;
import model.structural.base.Project;
import view.edit.panel.association.PanelDependency;
import view.edit.panel.stereotype.PanelStereotype;
import view.panel.edit.PanelEdit;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditElement</b>.</p> 
 * <p>Class responsible for defining a Panel for Edit the <b>Element</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  12/06/2019
 * @see    view.panel.edit.PanelEdit
 */
public abstract class PanelEditElement extends PanelEdit {
    protected final Project project;
    protected final Element element;
    protected PanelStereotype panelStereotype;
    protected PanelDependency panelDependency;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param element Element.
     */
    public PanelEditElement(ViewMenu viewMenu, Element element) {
        super(viewMenu);
        this.project = this.viewMenu.getProject();
        this.element = element;
    }
    
    /**
     * Method responsible for adding the Panel Stereotype.
     */
    protected void addPanelStereotype() {
        this.panelStereotype  = new PanelStereotype(this.getViewMenu().getProject(), this.element);
        this.createScrollPane("scrollPanelStereotype",  this.panelStereotype);
        this.getScrollPanelStereotype().setViewportView(this.panelStereotype);
        this.tabbedPane.add("Stereotype", this.getScrollPanelStereotype());
    }
    
    /**
     * Method responsible for adding the Panel Dependency.
     */
    protected void addPanelDependency() {
        this.panelDependency  = new PanelDependency(this.getViewMenu().getProject(), this.element);
        this.createScrollPane("scrollPanelDependency",  this.panelDependency);
        this.getScrollPanelDependency().setViewportView(this.panelDependency);
        this.tabbedPane.add("Dependency", this.getScrollPanelDependency());
    }
    
    /**
     * Method responsible for returning the Element.
     * @return Element.
     */
    public Element getElement() {
        return this.element;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Stereotype.
     * @return Scroll Panel Stereotype.
     */
    public JScrollPane getScrollPanelStereotype() {
        return this.scrollPanes.get("scrollPanelStereotype");
    }
    
    /**
     * Method responsible for returning the Panel Stereotype.
     * @return Panel Stereotype.
     */
    public PanelStereotype getPanelStereotype() {
        return this.panelStereotype;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Dependency.
     * @return Scroll Panel Dependency.
     */
    public JScrollPane getScrollPanelDependency() {
        return this.scrollPanes.get("scrollPanelDependency");
    }
    
    /**
     * Method responsible for returning the Panel Dependency.
     * @return Panel Dependency.
     */
    public PanelDependency getPanelDependency() {
        return this.panelDependency;
    }
}