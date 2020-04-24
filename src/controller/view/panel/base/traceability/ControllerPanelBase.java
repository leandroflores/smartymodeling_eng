package controller.view.panel.base.traceability;

import java.awt.event.KeyEvent;
import model.structural.base.traceability.Traceability;
import view.panel.base.traceability.PanelBase;

/**
 * <p>Class of Controller <b>ControllerPanelBase</b>.</p>
 * <p>Class responsible for controlling the <b>Traceability Panel Base</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-22
 * @see    controller.view.panel.base.ControllerPanelBase
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
        if (this.ready)
            this.update();
    }
    
    @Override
    protected void refresh() {
//        this.getPanelTree().updateNode(this.getTraceability());
        super.refresh();
    }
    
    /**
     * Method responsible for returning the Traceability.
     * @return Traceability.
     */
    protected Traceability getTraceability() {
        return this.getPanel().getTraceability();
    }
    
    @Override
    public PanelBase getPanel() {
        return (PanelBase) this.panel;
    }
}