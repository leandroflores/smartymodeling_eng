package controller.view.delete.base.requirement;

import controller.view.delete.ControllerViewDelete;
import model.structural.base.requirement.Requirement;
import view.delete.base.requirement.ViewDeleteRequirement;

/**
 * <p>Class of Controller <b>ControllerViewDeleteRequirement</b>.</p>
 * <p>Class responsible for controlling the <b>ViewDeleteRequirement</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-17
 * @see    controller.view.delete.ControllerViewDelete
 * @see    model.structural.base.requirement.Requirement
 * @see    view.delete.base.requirement.ViewDeleteRequirement
 */
public final class ControllerViewDeleteRequirement extends ControllerViewDelete {
    private final Requirement requirement;
    
    /**
     * Default constructor method of Class.
     * @param viewDelete View Delete Requirement.
     */
    public ControllerViewDeleteRequirement(ViewDeleteRequirement viewDelete) {
        super(viewDelete);
        this.requirement = this.getView().getRequirement();
    }
    
    @Override
    public void delete() {
        this.getView().getProject().removeRequirement(this.requirement);
        this.close();
    }
    
    @Override
    public ViewDeleteRequirement getView() {
        return (ViewDeleteRequirement) this.viewModal;
    }
}