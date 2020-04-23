package view.modal.edit.base;

import controller.view.modal.edit.base.ControllerViewEditElement;
import java.awt.Dimension;
import javax.swing.JTabbedPane;
import model.structural.base.Diagram;
import model.structural.base.Element;
import view.modal.edit.ViewEdit;
import view.panel.dependency.PanelDependency;
import view.panel.base.diagram.PanelBaseElement;
import view.panel.base.stereotype.PanelStereotype;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditElement</b>.</p>
 * <p>Class responsible for defining the <b>Element Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-30
 * @see    controller.view.modal.edit.base.ControllerViewEditElement
 * @see    model.structural.base.Element
 * @see    view.modal.edit.ViewEdit
 */
public final class ViewEditElement extends ViewEdit {
    private final Diagram diagram;
    private final Element element;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param diagram Diagram.
     * @param element Element.
     */
    public ViewEditElement(PanelModeling panel, Diagram diagram, Element element) {
        super(panel);
        this.diagram    = diagram;
        this.element    = element;
        this.controller = new ControllerViewEditElement(this);
        this.title      = "Edit Element Data";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(640, 500);
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(570, 375));
            this.addPanelBaseElement();
            this.addPanelStereotype();
            this.addPanelDependency();
        this.add(this.tabbedPane);
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Base Element.
     */
    private void addPanelBaseElement() {
        this.addPanel("panelBaseElement", new PanelBaseElement(this.getViewMenu(), this.diagram, this.element));
        this.createScrollPane("scrollPanelBaseElement",  this.getPanelBaseElement());
        this.getScrollPane("scrollPanelBaseElement").setViewportView(this.getPanelBaseElement());
        this.tabbedPane.add("Element", this.getScrollPane("scrollPanelBaseElement"));
    }
    
    /**
     * Method responsible for adding the Panel Stereotype.
     */
    private void addPanelStereotype() {
        this.addPanel("panelStereotype", new PanelStereotype(this.getViewMenu(), this.element));
        this.createScrollPane("scrollPanelStereotype",  this.getPanelStereotype());
        this.getScrollPane("scrollPanelStereotype").setViewportView(this.getPanelStereotype());
        this.tabbedPane.add("Stereotype", this.getScrollPane("scrollPanelStereotype"));
    }
    
    /**
     * Method responsible for adding the Panel Dependency.
     */
    private void addPanelDependency() {
        this.addPanel("panelDependency", new PanelDependency(this.getViewMenu(), this.element));
        this.createScrollPane("scrollPanelDependency",  this.getPanelDependency());
        this.getScrollPane("scrollPanelDependency").setViewportView(this.getPanelDependency());
        this.tabbedPane.add("Dependency", this.getScrollPane("scrollPanelDependency"));
    }
    
    /**
     * Method responsible for returning the Panel Base Element.
     * @return Panel Base Element.
     */
    public PanelBaseElement getPanelBaseElement() {
        return (PanelBaseElement) this.getPanel("panelBaseElement");
    }
    
    /**
     * Method responsible for returning the Panel Stereotype.
     * @return Panel Stereotype.
     */
    public PanelStereotype getPanelStereotype() {
        return (PanelStereotype) this.getPanel("panelStereotype");
    }
    
    /**
     * Method responsible for returning the Panel Dependency.
     * @return Panel Dependency.
     */
    public PanelDependency getPanelDependency() {
        return (PanelDependency) this.getPanel("panelDependency");
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