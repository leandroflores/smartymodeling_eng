package controller.view.modal.delete.base.requirement;

import controller.view.modal.delete.ControllerViewDelete;
import model.structural.base.requirement.Requirement;
import view.modal.delete.base.requirement.ViewDeleteRequirement;

/**
 * <p>Class of Controller <b>ControllerViewDeleteRequirement</b>.</p>
 * <p>Class responsible for controlling the <b>ViewDeleteRequirement</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-17
 * @see    controller.view.modal.delete.ControllerViewDelete
 * @see    model.structural.base.requirement.Requirement
 * @see    view.modal.delete.base.requirement.ViewDeleteRequirement
 */
public final class ControllerViewDeleteRequirement extends ControllerViewDelete {
    private final Requirement requirement;
    
    /**
     * Default constructor method of Class.
     * @param view View Delete Requirement.
     */
    public ControllerViewDeleteRequirement(ViewDeleteRequirement view) {
        super(view);
        requirement = getView().getRequirement();
    }
    
    @Override
    public void delete() {
        getView().getProject().removeRequirement(requirement);
        close();
    }
    
    @Override
    public ViewDeleteRequirement getView() {
        return (ViewDeleteRequirement) super.getView();
    }
}