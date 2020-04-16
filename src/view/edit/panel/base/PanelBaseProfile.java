package view.edit.panel.base;

import controller.view.edit.panel.base.ControllerPanelBaseProfile;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JTextField;
import model.structural.base.Profile;
import view.panel.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseProfile</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Profile Base</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  21/07/2019
 * @see    controller.view.edit.panel.base.ControllerPanelBaseProfile
 * @see    model.structural.base.Profile
 * @see    view.panel.Panel
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
        this.setLayout(new GridLayout(7, 2));
        this.setPreferredSize(new Dimension(50, 70));
        this.setSize(new Dimension(50, 70));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Mandatory*: "));
        this.add(this.createTextField("mandatoryTextField",  "", 20));
        
        this.add(this.createLabel("Optional*: "));
        this.add(this.createTextField("optionalTextField",  "", 20));
        
        this.add(this.createLabel("Var. Point*: "));
        this.add(this.createTextField("varPointTextField",  "", 20));
        
        this.add(this.createLabel("Inclusive*: "));
        this.add(this.createTextField("inclusiveTextField", "", 20));
        
        this.add(this.createLabel("Exclusive*: "));
        this.add(this.createTextField("exclusiveTextField", "", 20));
        
        this.add(this.createLabel("Requires*: "));
        this.add(this.createTextField("requiresTextField", "", 20));
        
        this.add(this.createLabel("Mutex*: "));
        this.add(this.createTextField("mutexTextField", "", 20));
    }
    
    /**
     * Method responsible for setting the Profile Values.
     */
    public void setValues() {
        this.getMandatoryTextField().setText(this.profile.getMandatory().getName());
        this.getOptionalTextField().setText(this.profile.getOptional().getName());
        this.getVarPointTextField().setText(this.profile.getVariationPoint().getName());
        this.getInclusiveTextField().setText(this.profile.getInclusive().getName());
        this.getExclusiveTextField().setText(this.profile.getExclusive().getName());
//        this.getRequiresTextField().setText(this.profile.getRequires().getName());
//        this.getMutexTextField().setText(this.profile.getMutex().getName());
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
     * Method responsible for returning the Mandatory Text Field.
     * @return Mandatory Text Field.
     */
    public JTextField getMandatoryTextField() {
        return this.getTextField("mandatoryTextField");
    }
    
    /**
     * Method responsible for returning the Optional Text Field.
     * @return Optional Text Field.
     */
    public JTextField getOptionalTextField() {
        return this.getTextField("optionalTextField");
    }
    
    /**
     * Method responsible for returning the Variation Point Text Field.
     * @return Variation Point Text Field.
     */
    public JTextField getVarPointTextField() {
        return this.getTextField("varPointTextField");
    }
    
    /**
     * Method responsible for returning the Inclusive Text Field.
     * @return Inclusive Text Field.
     */
    public JTextField getInclusiveTextField() {
        return this.getTextField("inclusiveTextField");
    }
    
    /**
     * Method responsible for returning the Exclusive Text Field.
     * @return Exclusive Text Field.
     */
    public JTextField getExclusiveTextField() {
        return this.getTextField("exclusiveTextField");
    }
    
    /**
     * Method responsible for returning the Requires Text Field.
     * @return Requires Text Field.
     */
    public JTextField getRequiresTextField() {
        return this.getTextField("requiresTextField");
    }
    
    /**
     * Method responsible for returning the Mutex Text Field.
     * @return Mutex Text Field.
     */
    public JTextField getMutexTextField() {
        return this.getTextField("mutexTextField");
    }
}