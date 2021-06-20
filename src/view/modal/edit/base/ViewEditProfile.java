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
        initComponents();
    }
    
    @Override
    protected Dimension getViewDimension() {
        return new Dimension(650, 450);
    }
    
    @Override
    protected PanelEditProfile createPanelEdit() {
        return new PanelEditProfile(view, profile);
    }
    
    @Override
    protected Dimension getPanelDimension() {
        return new Dimension(500, 325);
    }
    
    @Override
    public PanelEditProfile getPanelEdit() {
        return (PanelEditProfile) super.getPanelEdit();
    }
    
    /**
     * Method responsible for returning the Panel Base Profile.
     * @return Panel Base Profile.
     */
    public PanelBaseProfile getPanelBaseProfile() {
        return getPanelEdit().getPanelBaseProfile();
    }
    
    /**
     * Method responsible for returning the Profile.
     * @return Profile.
     */
    public Profile getProfile() {
        return profile;
    }
}