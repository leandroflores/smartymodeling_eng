package view.panel.base;

import java.awt.Dimension;
import model.structural.base.Diagram;
import model.structural.base.Element;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseElement</b>.</p>
 * <p>Class responsible for defining a Abstract Model of <b>Element Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-30
 * @see    controller.view.panel.base.ControllerPanelBaseElement
 * @see    model.structural.base.Element
 * @see    view.panel.base.PanelBase
 */
public abstract class PanelBaseElement extends PanelBase {
    protected final Diagram diagram;
    protected final Element element;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Diagram.
     * @param element Element.
     */
    public PanelBaseElement(ViewMenu view, Diagram diagram, Element element) {
        super(view);
        this.diagram = diagram;
        this.element = element;
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setPreferredSize(new Dimension(100, 100));
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Element.
     * @return Element.
     */
    public Element getElement() {
        return this.element;
    }
}