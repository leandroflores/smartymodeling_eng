package view.panel.base;

import controller.view.panel.base.ControllerPanelBaseProfile;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import model.structural.base.Profile;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseProfile</b>.</p> 
 * <p>Class responsible for defining a <b>Profile Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-21
 * @see    controller.view.panel.base.ControllerPanelBaseProfile
 * @see    model.structural.base.Profile
 * @see    view.panel.base.PanelBase
 */
public final class PanelBaseProfile extends PanelBase {
    private final Profile profile;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param profile Profile.
     */
    public PanelBaseProfile(ViewMenu view, Profile profile) {
        super(view);
        this.profile    = profile;
        this.controller = new ControllerPanelBaseProfile(this);
        this.setDefaultProperties();
        this.addComponents();
        this.getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridBagLayout());
//        this.setLayout(new GridLayout(7, 2));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Mandatory*: "),  this.createConstraints(1, 1, 0, 0));
        this.add(this.createTextField("mandatoryTextField", this.profile.getMandatory().getName(), 20), this.createConstraints(4, 1, 1, 0));
        
        this.add(this.createLabel("Optional*: "),   this.createConstraints(1, 1, 0, 1));
        this.add(this.createTextField("optionalTextField",  this.profile.getOptional().getName(), 20), this.createConstraints(4, 1, 1, 1));
        
        this.add(this.createLabel("Var. Point*: "), this.createConstraints(1, 1, 0, 2));
        this.add(this.createTextField("varPointTextField",  this.profile.getVariationPoint().getName(), 20), this.createConstraints(4, 1, 1, 2));
        
        this.add(this.createLabel("Inclusive*: "),  this.createConstraints(1, 1, 0, 3));
        this.add(this.createTextField("inclusiveTextField", this.profile.getInclusive().getName(), 20), this.createConstraints(4, 1, 1, 3));
        
        this.add(this.createLabel("Exclusive*: "),  this.createConstraints(1, 1, 0, 4));
        this.add(this.createTextField("exclusiveTextField", this.profile.getExclusive().getName(), 20), this.createConstraints(4, 1, 1, 4));
        
        this.add(this.createLabel("Requires*: "),   this.createConstraints(1, 1, 0, 5));
        this.add(this.createTextField("requiresTextField", this.profile.getRequires().getName(), 20), this.createConstraints(4, 1, 1, 5));
        
        this.add(this.createLabel("Mutex*: "),      this.createConstraints(1, 1, 0, 6));
        this.add(this.createTextField("mutexTextField", this.profile.getMutex().getName(), 20), this.createConstraints(4, 1, 1, 6));
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