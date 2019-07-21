package controller.view.edit.panel.base;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import model.structural.base.Stereotype;
import view.edit.panel.base.PanelBaseProfile;

/**
 * <p>Class of Controller <b>ControllerPanelBaseProfile</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseProfile</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  21/07/2019
 * @see    controller.view.ControllerPanel
 * @see    view.edit.panel.base.PanelBaseProfile
 */
public class ControllerPanelBaseProfile extends ControllerPanel {
    private final PanelBaseProfile panelBaseProfile;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Profile.
     */
    public ControllerPanelBaseProfile(PanelBaseProfile panel) {
        super(panel);
        this.panelBaseProfile = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.update();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        this.update();
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
        this.update();
    }

    /**
     * Method responsible for checking the Profile.
     * @return Profile checked.
     */
    private boolean check() {
        return true;
    }
    
    /**
     * Method responsible for updating the Profile Values.
     */
    private void updateProfile() {
        this.panelBaseProfile.getProfile().setMandatory((Stereotype) this.panelBaseProfile.getMandatoryComboBox().getSelectedItem());
        this.panelBaseProfile.getProfile().setOptional((Stereotype)  this.panelBaseProfile.getOptionalComboBox().getSelectedItem());
        this.panelBaseProfile.getProfile().setVariationPoint((Stereotype) this.panelBaseProfile.getVarPointComboBox().getSelectedItem());
        this.panelBaseProfile.getProfile().setInclusive((Stereotype) this.panelBaseProfile.getInclusiveComboBox().getSelectedItem());
        this.panelBaseProfile.getProfile().setExclusive((Stereotype) this.panelBaseProfile.getExclusiveComboBox().getSelectedItem());
    }
    
    /**
     * Method responsible for setting the Profile Values.
     */
    private void update() {
        this.updateProfile();
        this.panelBaseProfile.getViewMenu().getPanelProject().getPanelTree().updateUI();
        this.panelBaseProfile.getViewMenu().getPanelModeling().updateDiagrams();
        this.panelBaseProfile.getViewMenu().setSave(false);
    }
}