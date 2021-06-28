package view.panel.edit.diagram.classes.base;

import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.ClassUML;
import view.panel.base.diagram.classes.base.PanelBaseClassUML;
import view.panel.edit.base.PanelEditElement;
import view.main.structural.ViewMenu;
import view.panel.base.diagram.classes.base.PanelBaseDescription;

/**
 * <p>Class of View <b>PanelEditClassUML</b>.</p>
 * <p>Class responsible for defining a <b>Class UML Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-17
 * @see    model.structural.diagram.classes.base.ClassUML
 * @see    view.panel.edit.base.PanelEditElement
 */
public final class PanelEditClassUML extends PanelEditElement {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Class Diagram.
     * @param class_ Class UML.
     */
    public PanelEditClassUML(ViewMenu view, ClassDiagram diagram, ClassUML class_) {
        super(view, diagram, class_);
        addComponents();
    }
    
    @Override
    protected void addPanels() {
        addPanelBaseClassUML();
        addPanelStereotype();
        addPanelDependency();
        addPanelBaseDescription();
    }
    
    /**
     * Method responsible for adding the Panel Base Class UML.
     */
    protected void addPanelBaseClassUML() {
        addPanel("base_class", new PanelBaseClassUML(viewMenu, getDiagram(), getElement()));
        createScrollPane("base_class",  getPanelBaseClassUML());
        getScrollPane("base_class").setViewportView(getPanelBaseClassUML());
        tabbedPane.add("Class", getScrollPane("base_class"));
    }
    
    /**
     * Method responsible for adding the Panel Base Description.
     */
    protected void addPanelBaseDescription() {
        addPanel("description", new PanelBaseDescription(viewMenu, getElement()));
        createScrollPane("description", getPanelBaseDescription());
        getScrollPane("description").setViewportView(getPanelBaseDescription());
        tabbedPane.add("Description", getScrollPane("description"));
    }
    
    /**
     * Method responsible for returning the Panel Base Class UML.
     * @return Panel Base Class UML.
     */
    public PanelBaseClassUML getPanelBaseClassUML() {
        return (PanelBaseClassUML) getPanel("base_class");
    }
    
    /**
     * Method responsible for returning the Panel Base Description.
     * @return Panel Base Description.
     */
    public PanelBaseDescription getPanelBaseDescription() {
        return (PanelBaseDescription) getPanel("description");
    }
    
    @Override
    public ClassUML getElement() {
        return (ClassUML) element;
    }
    
    @Override
    public ClassDiagram getDiagram() {
        return (ClassDiagram) diagram;
    }
}