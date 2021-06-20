package view.modal.edit.base;

import controller.view.modal.edit.base.ControllerViewEditElement;
import java.awt.Dimension;
import model.structural.base.Diagram;
import model.structural.base.Element;
import view.modal.edit.ViewEdit;
import view.panel.dependency.PanelDependency;
import view.panel.base.diagram.PanelBaseElement;
import view.panel.base.stereotype.PanelStereotype;
import view.panel.edit.diagram.PanelEditElement;
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
        super(panel.getViewMenu());
        this.diagram    = diagram;
        this.element    = element;
        this.controller = new ControllerViewEditElement(this);
        this.title      = "Edit Element Data";
        initComponents();
    }
    
    @Override
    protected Dimension getViewDimension() {
        return new Dimension(600, 375);
    }
    
    @Override
    protected PanelEditElement createPanelEdit() {
        return new PanelEditElement(view, diagram, element);
    }
    
    @Override
    protected Dimension getPanelDimension() {
        return new Dimension(500, 200);
    }
    
    @Override
    public PanelEditElement getPanelEdit() {
        return (PanelEditElement) super.getPanelEdit();
    }
    
    /**
     * Method responsible for returning the Panel Base Element.
     * @return Panel Base Element.
     */
    public PanelBaseElement getPanelBaseElement() {
        return getPanelEdit().getPanelBaseElement();
    }
    
    /**
     * Method responsible for returning the Panel Stereotype.
     * @return Panel Stereotype.
     */
    public PanelStereotype getPanelStereotype() {
        return getPanelEdit().getPanelStereotype();
    }
    
    /**
     * Method responsible for returning the Panel Dependency.
     * @return Panel Dependency.
     */
    public PanelDependency getPanelDependency() {
        return getPanelEdit().getPanelDependency();
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