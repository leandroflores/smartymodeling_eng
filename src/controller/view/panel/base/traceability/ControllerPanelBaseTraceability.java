package controller.view.panel.base.traceability;

import view.panel.base.traceability.PanelBaseTraceability;

/**
 * <p>Class of Controller <b>ControllerPanelBaseTraceability</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseTraceability</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-22
 * @see    controller.view.panel.base.traceability.ControllerPanelBase
 * @see    view.panel.base.traceability.PanelBaseTraceability
 */
public class ControllerPanelBaseTraceability extends ControllerPanelBase {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Traceability.
     */
    public ControllerPanelBaseTraceability(PanelBaseTraceability panel) {
        super(panel);
    }

    @Override
    protected void update() {
        this.getTraceability().setName(this.getString(this.getPanel().getNameTextField()));
        this.getTraceability().setDescription(this.getString(this.getPanel().getDescriptionTextField()));
        this.refresh();
    }
    
    @Override
    public PanelBaseTraceability getPanel() {
        return (PanelBaseTraceability) this.panel;
    }
}