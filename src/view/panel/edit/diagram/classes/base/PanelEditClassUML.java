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
        this.addComponents();
    }
    
    @Override
    protected void addPanels() {
        this.addPanelBaseClassUML();
        super.addPanelStereotype();
        super.addPanelDependency();
        this.addPanelBaseDescription();
    }
    
    /**
     * Method responsible for adding the Panel Base Class UML.
     */
    protected void addPanelBaseClassUML() {
        this.addPanel("panelBaseClassUML", new PanelBaseClassUML(this.viewMenu, this.getDiagram(), this.getElement()));
        this.createScrollPane("scrollPanelBaseClassUML",  this.getPanelBaseClassUML());
        this.getScrollPane("scrollPanelBaseClassUML").setViewportView(this.getPanelBaseClassUML());
        this.tabbedPane.add("Class", this.getScrollPane("scrollPanelBaseClassUML"));
    }
    
    /**
     * Method responsible for adding the Panel Base Description.
     */
    protected void addPanelBaseDescription() {
        this.addPanel("panelBaseDescription", new PanelBaseDescription(this.viewMenu, this.getElement()));
        this.createScrollPane("scrollPanelBaseDescription",  this.getPanelBaseDescription());
        this.getScrollPane("scrollPanelBaseDescription").setViewportView(this.getPanelBaseDescription());
        this.tabbedPane.add("Description", this.getScrollPane("scrollPanelBaseDescription"));
    }
    
    /**
     * Method responsible for returning the Panel Base Class UML.
     * @return Panel Base Class UML.
     */
    public PanelBaseClassUML getPanelBaseClassUML() {
        return (PanelBaseClassUML) this.getPanel("panelBaseClassUML");
    }
    
    /**
     * Method responsible for returning the Panel Base Description.
     * @return Panel Base Description.
     */
    public PanelBaseDescription getPanelBaseDescription() {
        return (PanelBaseDescription) this.getPanel("panelBaseDescription");
    }
    
    @Override
    public ClassUML getElement() {
        return (ClassUML) this.element;
    }
    
    @Override
    public ClassDiagram getDiagram() {
        return (ClassDiagram) this.diagram;
    }
}