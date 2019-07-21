package controller.view.edit;

import view.edit.ViewEditProfile;

/**
 * <p>Class of Controller <b>ControllerViewEditProfile</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewEditProfile</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  21/07/2019
 * @see    controller.view.edit.ControllerViewEdit
 * @see    view.edit.ViewEditProfile
 */
public class ControllerViewEditProfile extends ControllerViewEdit {
    private final ViewEditProfile viewEditProfile;

    /**
     * Default constructor method of Class.
     * @param viewEdit View Edit Profile.
     */
    public ControllerViewEditProfile(ViewEditProfile viewEdit) {
        super(viewEdit);
        this.viewEditProfile = viewEdit;
    }
    
    @Override
    public boolean check() {
        return true;
    }

    @Override
    public void save() {
        this.viewEditProfile.getViewMenu().getPanelModeling().updateDiagrams();
        this.viewEditProfile.getViewMenu().update();
        this.viewEditProfile.dispose();
    }
}