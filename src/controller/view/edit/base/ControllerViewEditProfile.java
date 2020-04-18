package controller.view.edit.base;

import controller.view.edit.ControllerViewEdit;
import view.edit.base.ViewEditProfile;

/**
 * <p>Class of Controller <b>ControllerViewEditProfile</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEditProfile</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-21
 * @see    controller.view.edit.ControllerViewEdit
 * @see    model.structural.base.Profile
 * @see    view.edit.base.ViewEditProfile
 */
public class ControllerViewEditProfile extends ControllerViewEdit {

    /**
     * Default constructor method of Class.
     * @param viewEdit View Edit Profile.
     */
    public ControllerViewEditProfile(ViewEditProfile viewEdit) {
        super(viewEdit);
    }
    
    @Override
    public boolean check() {
        return true;
    }

    @Override
    public void save() {
        this.getViewMenu().getPanelModeling().updateModelingPanels();
        this.close();
    }
    
    @Override
    public ViewEditProfile getView() {
        return (ViewEditProfile) this.viewModal;
    }
}