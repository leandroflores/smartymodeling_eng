package controller.view.panel.base;

import view.panel.base.PanelBaseProfile;

/**
 * <p>Class of Controller <b>ControllerPanelBaseProfile</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseProfile</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-21
 * @see    controller.view.panel.base.ControllerPanelBase
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
        this.getPanelModeling().updateModelingPanels();
        super.refresh();
    }
    
    @Override
    protected void update() {
        this.getPanel().getProfile().getMandatory().setName(this.getPanel().getMandatoryTextField().getText());
        this.getPanel().getProfile().getOptional().setName(this.getPanel().getOptionalTextField().getText());
        this.getPanel().getProfile().getVariationPoint().setName(this.getPanel().getVarPointTextField().getText());
        this.getPanel().getProfile().getInclusive().setName(this.getPanel().getInclusiveTextField().getText());
        this.getPanel().getProfile().getExclusive().setName(this.getPanel().getExclusiveTextField().getText());
        this.getPanel().getProfile().getRequires().setName(this.getPanel().getRequiresTextField().getText());
        this.getPanel().getProfile().getMutex().setName(this.getPanel().getMutexTextField().getText());
        this.refresh();
    }
    
    @Override
    public PanelBaseProfile getPanel() {
        return (PanelBaseProfile) this.panel;
    }
}