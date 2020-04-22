package controller.view.panel.base.requirement.traceability;

import controller.view.panel.base.ControllerPanelBase;
import java.awt.event.ActionEvent;
import view.panel.base.requirement.traceability.PanelBaseAddElement;

/**
 * <p>Class of Controller <b>ControllerPanelBaseAddElement</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseAddElement</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-22
 * @see    controller.view.panel.base.ControllerPanelBase
 * @see    view.panel.base.requirement.traceability.PanelBaseAddElement
 */
public class ControllerPanelBaseAddElement extends ControllerPanelBase {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Requirement Add Element.
     */
    public ControllerPanelBaseAddElement(PanelBaseAddElement panel) {
        super(panel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.ready) {
            if (this.getPanel().getContextComboBox().equals(event.getSource()))
                this.getPanel().updateElementComboBox();
            this.update();
        }
    }
    
    @Override
    protected void update() {
        this.refresh();
    }
    
    @Override
    public PanelBaseAddElement getPanel() {
        return (PanelBaseAddElement) this.panel;
    }
}