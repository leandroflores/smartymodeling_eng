package controller.view.panel.base.traceability;

import view.panel.base.traceability.PanelBaseReference;

/**
 * <p>Class of Controller <b>ControllerPanelBaseReference</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseReference</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-22
 * @see    controller.view.panel.base.traceability.ControllerPanelBase
 * @see    model.structural.base.traceability.Reference
 * @see    view.panel.base.traceability.PanelBaseReference
 */
public class ControllerPanelBaseReference extends ControllerPanelBase {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Reference.
     */
    public ControllerPanelBaseReference(PanelBaseReference panel) {
        super(panel);
    }

    @Override
    protected void update() {
        getReference().setName(getString(getPanel().getNameTextField()));
        getReference().setDescription(getString(getPanel().getDescriptionTextField()));
        refresh();
    }
    
    @Override
    public PanelBaseReference getPanel() {
        return (PanelBaseReference) panel;
    }
}