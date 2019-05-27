package view.delete;

import controller.view.delete.ControllerViewDeleteElement;
import model.structural.base.Element;
import view.ViewStyle;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewDeleteElement</b>.</p>
 * <p>Class responsible for defining the <b>Element Delete View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  27/05/2019
 * @see    controller.view.delete.ControllerViewDeleteElement
 * @see    model.structural.base.Element
 * @see    view.delete.ViewDelete
 */
public final class ViewDeleteElement extends ViewDelete {
    private final Element element;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param element Element.
     */
    public ViewDeleteElement(PanelModeling panel, Element element) {
        super(panel);
        this.element    = element;
        this.controller = new ControllerViewDeleteElement(this);
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.setTitle(ViewStyle.SYSTEM + "Delete Element");
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
}