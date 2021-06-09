package controller.view.panel.base;

import model.structural.base.Diagram;
import model.structural.base.Element;
import view.panel.base.PanelBaseElement;

/**
 * <p>Class of Controller <b>ControllerPanelBaseElement</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseElement</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-14
 * @see    controller.view.panel.base.ControllerPanelBase
 * @see    view.panel.base.PanelBaseElement
 */
public abstract class ControllerPanelBaseElement extends ControllerPanelBase {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Element.
     */
    public ControllerPanelBaseElement(PanelBaseElement panel) {
        super(panel);
    }

    @Override
    protected void refresh() {
        getPanelTree().updateNode(getElement());
        getPanelModeling().updateDiagram(getDiagram());
        getPanelModeling().updateInstancePanels();
        getPanelModeling().setSelected(getDiagram(), getElement().getId());
        super.refresh();
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    protected Diagram getDiagram() {
        return getPanel().getDiagram();
    }
    
    /**
     * Method responsible for returning the Element.
     * @return Element.
     */
    protected Element getElement() {
        return getPanel().getElement();
    }
    
    @Override
    public PanelBaseElement getPanel() {
        return (PanelBaseElement) panel;
    }
}