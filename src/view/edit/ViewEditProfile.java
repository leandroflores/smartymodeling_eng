package view.edit;

import controller.view.edit.ControllerViewEditProfile;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Profile;
import view.edit.panel.base.PanelBaseProfile;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditProfile</b>.</p>
 * <p>Class responsible for defining the <b>Profile Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  21/07/2019
 * @see    controller.view.edit.ControllerViewEditProfile
 * @see    view.edit.ViewEdit
 */
public final class ViewEditProfile extends ViewEdit {
    private final Profile profile;
    private PanelBaseProfile panelBaseProfile;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param profile Profile.
     */
    public ViewEditProfile(PanelModeling panel, Profile profile) {
        super(panel);
        this.profile    = profile;
        this.controller = new ControllerViewEditProfile(this);
        this.title      = "Edit Profile";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(600, 320);
        this.addHeader();
        this.addComponents();
        this.addFooter();
        this.setValues();
    }
    
    @Override
    public void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(550, 200));
        
        this.addPanelBaseProfile();
        
        this.add(this.tabbedPane);
        
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Base Profile.
     */
    private void addPanelBaseProfile() {
        this.panelBaseProfile = new PanelBaseProfile(this.getViewMenu(), this.profile);
        this.createScrollPane("scrollPanelBaseProfile",  this.panelBaseProfile);
        this.getScrollPanelBaseProfile().setViewportView(this.panelBaseProfile);
        this.tabbedPane.add("Profile", this.getScrollPanelBaseProfile());
    }
    
    @Override
    public void setValues() {
        this.panelBaseProfile.getMandatoryTextField().requestFocus();
    }
    
    /**
     * Method responsible for returning the Profile.
     * @return Profile.
     */
    public Profile getProfile() {
        return this.profile;
    }
    
    /**
     * Method responsible for returning Panel Base Profile.
     * @return Panel Base Profile.
     */
    public JScrollPane getScrollPanelBaseProfile() {
        return this.scrollPanes.get("scrollPanelBaseProfile");
    }
    
    /**
     * Method responsible for returning Panel Base Profile.
     * @return Panel Base Profile.
     */
    public PanelBaseProfile getPanelBaseProfile() {
        return this.panelBaseProfile;
    }
}