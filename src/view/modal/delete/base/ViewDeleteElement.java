package view.modal.delete.base;

import controller.view.modal.delete.base.ControllerViewDeleteElement;
import model.structural.base.Diagram;
import model.structural.base.Element;
import view.modal.delete.ViewDelete;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewDeleteElement</b>.</p>
 * <p>Class responsible for defining the <b>Element Delete View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-27
 * @see    controller.view.modal.delete.base.ControllerViewDeleteElement
 * @see    model.structural.base.Element
 * @see    view.modal.delete.ViewDelete
 */
public final class ViewDeleteElement extends ViewDelete {
    private final Diagram diagram;
    private final Element element;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param diagram Element Diagram.
     * @param element Element.
     */
    public ViewDeleteElement(PanelModeling panel, Diagram diagram, Element element) {
        super(panel);
        this.diagram    = diagram;
        this.element    = element;
        this.controller = new ControllerViewDeleteElement(this);
        this.title      = "Delete Element";
        this.initComponents();
        this.addComponents();
    }

    @Override
    public void addComponents() {
        super.addComponents(this.element.toString());
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