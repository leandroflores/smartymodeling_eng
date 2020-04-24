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
        this.addComponents();
    }
    
    @Override
    protected void addPanels() {
        this.addPanelBaseProfile();
    }
    
    /**
     * Method responsible for adding the Panel Base Profile.
     */
    private void addPanelBaseProfile() {
        this.addPanel("panelBaseProfile", new PanelBaseProfile(this.viewMenu, this.profile));
        this.createScrollPane("scrollPanelBaseProfile",  this.getPanelBaseProfile());
        this.getScrollPane("scrollPanelBaseProfile").setViewportView(this.getPanelBaseProfile());
        this.tabbedPane.add("Profile", this.getScrollPane("scrollPanelBaseProfile"));
    }
    
    /**
     * Method responsible for returning the Panel Base Profile.
     * @return Panel Base Profile.
     */
    public PanelBaseProfile getPanelBaseProfile() {
        return (PanelBaseProfile) this.getPanel("panelBaseProfile");
    }
    
    /**
     * Method responsible for returning the Profile.
     * @return Profile.
     */
    public Profile getProfile() {
        return this.profile;
    }
}