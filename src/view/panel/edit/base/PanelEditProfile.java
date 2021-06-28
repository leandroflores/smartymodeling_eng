package view.panel.edit.base;

import model.structural.base.Profile;
import view.panel.edit.PanelEdit;
import view.main.structural.ViewMenu;
import view.panel.base.PanelBaseProfile;

/**
 * <p>Class of View <b>PanelEditProfile</b>.</p> 
 * <p>Class responsible for defining a <b>Profile Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-24
 * @see    model.structural.base.Profile
 * @see    view.panel.edit.PanelEdit
 */
public final class PanelEditProfile extends PanelEdit {
    private final Profile profile;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param profile Profile.
     */
    public PanelEditProfile(ViewMenu view, Profile profile) {
        super(view);
        this.profile = profile;
        addComponents();
    }
    
    @Override
    protected void addPanels() {
        addPanelBaseProfile();
    }
    
    /**
     * Method responsible for adding the Panel Base Profile.
     */
    private void addPanelBaseProfile() {
        addPanel("base_profile", new PanelBaseProfile(viewMenu, profile));
        createScrollPane("base_profile", getPanelBaseProfile());
        getScrollPane("base_profile").setViewportView(getPanelBaseProfile());
        tabbedPane.add("Profile", getScrollPane("base_profile"));
    }
    
    /**
     * Method responsible for returning the Panel Base Profile.
     * @return Panel Base Profile.
     */
    public PanelBaseProfile getPanelBaseProfile() {
        return (PanelBaseProfile) getPanel("base_profile");
    }
    
    /**
     * Method responsible for returning the Profile.
     * @return Profile.
     */
    public Profile getProfile() {
        return profile;
    }
}