package view.panel.base;

import controller.view.panel.base.ControllerPanelBaseProfile;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import model.structural.base.Profile;
import view.main.structural.ViewMenu;

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
        setDefaultProperties();
        addComponents();
        getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        setLayout(new GridBagLayout());
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Mandatory*: "), createConstraints(1, 1, 0, 0));
        add(createTextField("mandatory", profile.getMandatory().getName(), 20), createConstraints(4, 1, 1, 0));
        
        add(createLabel("Optional*: "), createConstraints(1, 1, 0, 1));
        add(createTextField("optional", profile.getOptional().getName(), 20), createConstraints(4, 1, 1, 1));
        
        add(createLabel("Var. Point*: "), createConstraints(1, 1, 0, 2));
        add(createTextField("var_point", profile.getVariationPoint().getName(), 20), createConstraints(4, 1, 1, 2));
        
        add(createLabel("Inclusive*: "), createConstraints(1, 1, 0, 3));
        add(createTextField("inclusive", profile.getInclusive().getName(), 20), createConstraints(4, 1, 1, 3));
        
        add(createLabel("Exclusive*: "), createConstraints(1, 1, 0, 4));
        add(createTextField("exclusive", profile.getExclusive().getName(), 20), createConstraints(4, 1, 1, 4));
        
        add(createLabel("Requires*: "), createConstraints(1, 1, 0, 5));
        add(createTextField("requires", profile.getRequires().getName(), 20), createConstraints(4, 1, 1, 5));
        
        add(createLabel("Mutex*: "), createConstraints(1, 1, 0, 6));
        add(createTextField("mutex", profile.getMutex().getName(), 20), createConstraints(4, 1, 1, 6));
    }
    
    /**
     * Method responsible for returning the Profile.
     * @return Profile.
     */
    public Profile getProfile() {
        return profile;
    }
    
    /**
     * Method responsible for returning the Mandatory Text Field.
     * @return Mandatory Text Field.
     */
    public JTextField getMandatoryTextField() {
        return getTextField("mandatory");
    }
    
    /**
     * Method responsible for returning the Optional Text Field.
     * @return Optional Text Field.
     */
    public JTextField getOptionalTextField() {
        return getTextField("optional");
    }
    
    /**
     * Method responsible for returning the Variation Point Text Field.
     * @return Variation Point Text Field.
     */
    public JTextField getVarPointTextField() {
        return getTextField("var_point");
    }
    
    /**
     * Method responsible for returning the Inclusive Text Field.
     * @return Inclusive Text Field.
     */
    public JTextField getInclusiveTextField() {
        return getTextField("inclusive");
    }
    
    /**
     * Method responsible for returning the Exclusive Text Field.
     * @return Exclusive Text Field.
     */
    public JTextField getExclusiveTextField() {
        return getTextField("exclusive");
    }
    
    /**
     * Method responsible for returning the Requires Text Field.
     * @return Requires Text Field.
     */
    public JTextField getRequiresTextField() {
        return getTextField("requires");
    }
    
    /**
     * Method responsible for returning the Mutex Text Field.
     * @return Mutex Text Field.
     */
    public JTextField getMutexTextField() {
        return getTextField("mutex");
    }
}