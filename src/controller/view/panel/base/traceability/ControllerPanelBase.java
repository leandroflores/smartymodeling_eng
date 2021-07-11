package controller.view.panel.base.traceability;

import java.awt.event.KeyEvent;
import model.structural.base.traceability.Reference;
import view.panel.base.traceability.PanelBase;

/**
 * <p>Class of Controller <b>ControllerPanelBase</b>.</p>
 * <p>Class responsible for controlling the <b>Reference Panel Base</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-22
 * @see    controller.view.panel.base.ControllerPanelBase
 * @see    model.structural.base.traceability.Reference
 * @see    view.panel.base.traceability.PanelBase
 */
public abstract class ControllerPanelBase extends controller.view.panel.base.ControllerPanelBase {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base.
     */
    public ControllerPanelBase(PanelBase panel) {
        super(panel);
    }
    
    @Override
    public void keyPressed(KeyEvent event) {}
    
    @Override
    public void keyReleased(KeyEvent event) {
        if (ready)
            update();
    }
    
    /**
     * Method responsible for returning the Reference.
     * @return Reference.
     */
    protected Reference getReference() {
        return getPanel().getReference();
    }
    
    @Override
    public PanelBase getPanel() {
        return (PanelBase) panel;
    }
}