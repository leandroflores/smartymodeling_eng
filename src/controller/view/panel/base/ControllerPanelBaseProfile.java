package controller.view.panel.base;

import view.panel.base.PanelBaseProfile;

/**
 * <p>Class of Controller <b>ControllerPanelBaseProfile</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseProfile</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-21
 * @see    controller.view.panel.base.ControllerPanelBase
 * @see    model.structural.base.Profile
 * @see    view.panel.base.PanelBaseProfile
 */
public class ControllerPanelBaseProfile extends ControllerPanelBase {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Profile.
     */
    public ControllerPanelBaseProfile(PanelBaseProfile panel) {
        super(panel);
    }
    
    @Override
    protected void refresh() {
        getPanelModeling().updateModelingPanels();
        super.refresh();
    }
    
    @Override
    protected void update() {
        getPanel().getProfile().getMandatory().setName(getPanel().getMandatoryTextField().getText());
        getPanel().getProfile().getOptional().setName(getPanel().getOptionalTextField().getText());
        getPanel().getProfile().getVariationPoint().setName(getPanel().getVarPointTextField().getText());
        getPanel().getProfile().getInclusive().setName(getPanel().getInclusiveTextField().getText());
        getPanel().getProfile().getExclusive().setName(getPanel().getExclusiveTextField().getText());
        getPanel().getProfile().getRequires().setName(getPanel().getRequiresTextField().getText());
        getPanel().getProfile().getMutex().setName(getPanel().getMutexTextField().getText());
        refresh();
    }
    
    @Override
    public PanelBaseProfile getPanel() {
        return (PanelBaseProfile) panel;
    }
}