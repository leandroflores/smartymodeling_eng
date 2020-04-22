package controller.view.modal.edit.base;

import controller.view.modal.edit.ControllerViewEdit;
import view.modal.edit.base.ViewEditProfile;

/**
 * <p>Class of Controller <b>ControllerViewEditProfile</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEditProfile</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-21
 * @see    controller.view.modal.edit.ControllerViewEdit
 * @see    model.structural.base.Profile
 * @see    view.modal.edit.base.ViewEditProfile
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