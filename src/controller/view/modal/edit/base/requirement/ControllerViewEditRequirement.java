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
     * @param viewEdit View Edit Requirement.
     */
    public ControllerViewEditRequirement(ViewEditRequirement viewEdit) {
        super(viewEdit);
    }
    
    @Override
    public boolean check() {
        return this.check(this.getView().getPanelBaseRequirement().getCodeTextField(), "Id is required!")
            && this.check(this.getView().getPanelBaseRequirement().getNameTextField(), "Name is required!");
    }

    @Override
    public void save() {
        this.close();
    }
    
    @Override
    public ViewEditRequirement getView() {
        return (ViewEditRequirement) this.viewModal;
    }
}