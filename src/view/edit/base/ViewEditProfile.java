package view.edit.base;

import controller.view.edit.base.ControllerViewEditProfile;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Profile;
import view.edit.ViewEdit;
import view.panel.base.PanelBaseProfile;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditProfile</b>.</p>
 * <p>Class responsible for defining the <b>Profile Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-21
 * @see    controller.view.edit.base.ControllerViewEditProfile
 * @see    model.structural.base.Profile
 * @see    view.edit.ViewEdit
 */
public final class ViewEditProfile extends ViewEdit {
    private final Profile profile;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param profile Profile.
     */
    public ViewEditProfile(PanelModeling panel, Profile profile) {
        super(panel);
        this.profile    = profile;
        this.controller = new ControllerViewEditProfile(this);
        this.title      = "Edit Profile Data";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(600, 350);
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(550, 225));
            this.addPanelBaseProfile();
        this.add(this.tabbedPane);
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Base Profile.
     */
    private void addPanelBaseProfile() {
        this.addPanel("panelBaseProfile", new PanelBaseProfile(this.getViewMenu(), this.profile));
        this.createScrollPane("scrollPanelBaseProfile",  this.getPanelBaseProfile());
        this.getScrollPanelBaseProfile().setViewportView(this.getPanelBaseProfile());
        this.tabbedPane.add("Profile", this.getScrollPanelBaseProfile());
    }
    
    /**
     * Method responsible for returning the Panel Base Profile.
     * @return Panel Base Profile.
     */
    public PanelBaseProfile getPanelBaseProfile() {
        return (PanelBaseProfile) this.getPanel("panelBaseDiagram");
    }
    
    /**
     * Method responsible for returning Panel Base Profile.
     * @return Panel Base Profile.
     */
    public JScrollPane getScrollPanelBaseProfile() {
        return this.getScrollPane("scrollPanelBaseProfile");
    }
    
    /**
     * Method responsible for returning the Profile.
     * @return Profile.
     */
    public Profile getProfile() {
        return this.profile;
    }
}