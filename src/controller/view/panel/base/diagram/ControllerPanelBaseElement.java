package controller.view.panel.base.diagram;

import view.panel.base.diagram.PanelBaseElement;

/**
 * <p>Class of Controller <b>ControllerPanelBaseElement</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseElement</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-14
 * @see    controller.view.panel.base.ControllerPanelBaseElement
 * @see    model.structural.base.Element
 * @see    view.panel.base.diagram.PanelBaseElement
 */
public class ControllerPanelBaseElement extends controller.view.panel.base.ControllerPanelBaseElement {
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Base Element.
     */
    public ControllerPanelBaseElement(PanelBaseElement panel) {
        super(panel);
    }
    
    @Override
    protected void update() {
        getElement().setName(getString(getPanel().getNameTextField()));
        getElement().setMandatory(getPanel().getMandatoryCheckBox().isSelected());
        getDiagram().updateStereotype(getElement());
        refresh();
    }
    
    @Override
    public PanelBaseElement getPanel() {
        return (PanelBaseElement) panel;
    }
}