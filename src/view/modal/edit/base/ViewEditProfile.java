package view.modal.edit.base;

import controller.view.modal.edit.base.ControllerViewEditProfile;
import java.awt.Dimension;
import model.structural.base.Profile;
import view.modal.edit.ViewEdit;
import view.panel.base.PanelBaseProfile;
import view.panel.edit.base.PanelEditProfile;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditProfile</b>.</p>
 * <p>Class responsible for defining the <b>Profile Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-21
 * @see    controller.view.modal.edit.base.ControllerViewEditProfile
 * @see    model.structural.base.Profile
 * @see    view.modal.edit.ViewEdit
 */
public final class ViewEditProfile extends ViewEdit {
    private final Profile profile;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param profile Profile.
     */
    public ViewEditProfile(PanelModeling panel, Profile profile) {
        super(panel.getViewMenu());
        this.profile    = profile;
        this.controller = new ControllerViewEditProfile(this);
        this.title      = "Edit Profile Data";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(new Dimension(650, 450));
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.addPanelEditProfile();
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Edit Profile.
     */
    private void addPanelEditProfile() {
        this.addPanel("panelEditProfile", new PanelEditProfile(this.view, this.profile));
        this.getPanelEditProfile().setPreferredSize(new Dimension(500, 325));
        this.add(this.getPanelEditProfile());
    }
    
    /**
     * Method responsible for returning the Panel Edit Profile.
     * @return Panel Edit Profile.
     */
    private PanelEditProfile getPanelEditProfile() {
        return (PanelEditProfile) this.getPanel("panelEditProfile");
    }
    
    /**
     * Method responsible for returning the Panel Base Profile.
     * @return Panel Base Profile.
     */
    public PanelBaseProfile getPanelBaseProfile() {
        return this.getPanelEditProfile().getPanelBaseProfile();
    }
    
    /**
     * Method responsible for returning the Profile.
     * @return Profile.
     */
    public Profile getProfile() {
        return this.profile;
    }
}