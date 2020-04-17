package controller.view.panel.base.diagram;

import view.panel.base.diagram.PanelBaseElement;

/**
 * <p>Class of Controller <b>ControllerPanelBaseElement</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseElement</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-14
 * @see    controller.view.panel.base.ControllerPanelBaseElement
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
        this.getElement().setName(this.getString(this.getPanel().getNameTextField()));
        this.getElement().setMandatory(this.getPanel().getMandatoryCheckBox().isSelected());
        this.getDiagram().updateStereotype(this.getElement());
        this.refresh();
    }
    
    @Override
    public PanelBaseElement getPanel() {
        return (PanelBaseElement) this.panel;
    }
}