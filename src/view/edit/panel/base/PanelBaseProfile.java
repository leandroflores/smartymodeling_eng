package view.edit.panel.base;

import controller.view.edit.panel.base.ControllerPanelBaseProfile;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import model.controller.structural.base.ControllerProject;
import model.structural.base.Profile;
import model.structural.base.Stereotype;
import view.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseProfile</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Profile Base</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  21/07/2019
 * @see    controller.view.edit.panel.base.
 * @see    model.structural.base.Profile
 * @see    view.Panel
 */
public final class PanelBaseProfile extends Panel {
    private final ViewMenu viewMenu;
    private final Profile profile;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param profile Profile.
     */
    public PanelBaseProfile(ViewMenu viewMenu, Profile profile) {
        this.viewMenu   = viewMenu;
        this.profile    = profile;
        this.controller = new ControllerPanelBaseProfile(this);
        this.setSettings();
        this.addComponents();
        this.setValues();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridLayout(5, 2));
        this.setPreferredSize(new Dimension(50, 50));
        this.setSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        Stereotype[] values = new ControllerProject(this.viewMenu.getProject()).getAllStereotypes();
        
        this.add(this.createLabel("Mandatory*: "));
        this.add(this.createComboBox("mandatoryComboBox", values, 200, this.profile.getMandatory()));
        
        this.add(this.createLabel("Optional*: "));
        this.add(this.createComboBox("optionalComboBox",  values, 200, this.profile.getOptional()));
        
        this.add(this.createLabel("Var. Point*: "));
        this.add(this.createComboBox("varPointComboBox",  values, 200, this.profile.getVariationPoint()));
        
        this.add(this.createLabel("Inclusive*: "));
        this.add(this.createComboBox("inclusiveComboBox", values, 200, this.profile.getInclusive()));
        
        this.add(this.createLabel("Exclusive*: "));
        this.add(this.createComboBox("exclusiveComboBox", values, 200, this.profile.getExclusive()));
    }
    
    /**
     * Method responsible for setting the Diagram Values.
     */
    public void setValues() {
        this.getMandatoryComboBox().setSelectedItem(this.profile.getMandatory());
        this.getOptionalComboBox().setSelectedItem(this.profile.getOptional());
        this.getVarPointComboBox().setSelectedItem(this.profile.getVariationPoint());
        this.getInclusiveComboBox().setSelectedItem(this.profile.getInclusive());
        this.getExclusiveComboBox().setSelectedItem(this.profile.getExclusive());
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
    
    /**
     * Method responsible for returning the Profile.
     * @return Profile.
     */
    public Profile getProfile() {
        return this.profile;
    }
    
    /**
     * Method responsible for returning the Mandatory Combo Box.
     * @return Mandatory Combo Box.
     */
    public JComboBox getMandatoryComboBox() {
        return this.comboBoxes.get("mandatoryComboBox");
    }
    
    /**
     * Method responsible for returning the Optional Combo Box.
     * @return Optional Combo Box.
     */
    public JComboBox getOptionalComboBox() {
        return this.comboBoxes.get("optionalComboBox");
    }
    
    /**
     * Method responsible for returning the Variation Point Combo Box.
     * @return Variation Point Combo Box.
     */
    public JComboBox getVarPointComboBox() {
        return this.comboBoxes.get("varPointComboBox");
    }
    
    /**
     * Method responsible for returning the Inclusive Combo Box.
     * @return Inclusive Combo Box.
     */
    public JComboBox getInclusiveComboBox() {
        return this.comboBoxes.get("inclusiveComboBox");
    }
    
    /**
     * Method responsible for returning the Exclusive Combo Box.
     * @return Exclusive Combo Box.
     */
    public JComboBox getExclusiveComboBox() {
        return this.comboBoxes.get("exclusiveComboBox");
    }
}