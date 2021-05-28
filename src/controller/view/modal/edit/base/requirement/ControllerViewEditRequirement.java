package controller.view.modal.edit.base.requirement;

import controller.view.modal.edit.ControllerViewEdit;
import view.modal.edit.base.requirement.ViewEditRequirement;

/**
 * <p>Class of Controller <b>ControllerViewEditRequirement</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEditRequirement</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-18
 * @see    controller.view.modal.edit.ControllerViewEdit
 * @see    model.structural.base.requirement.Requirement
 * @see    view.modal.edit.base.requirement.ViewEditRequirement
 */
public class ControllerViewEditRequirement extends ControllerViewEdit {

    /**
     * Default constructor method of Class.
     * @param view View Edit Requirement.
     */
    public ControllerViewEditRequirement(ViewEditRequirement view) {
        super(view);
    }
    
    @Override
    public boolean check() {
        return check(getView().getPanelBaseRequirement().getCodeTextField(), "Id is required!")
            && check(getView().getPanelBaseRequirement().getNameTextField(), "Name is required!");
    }

    @Override
    public void save() {
        close();
    }
    
    @Override
    public ViewEditRequirement getView() {
        return (ViewEditRequirement) super.getView();
    }
}