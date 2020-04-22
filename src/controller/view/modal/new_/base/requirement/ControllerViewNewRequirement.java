package controller.view.modal.new_.base.requirement;

import controller.view.modal.new_.ControllerViewNew;
import view.modal.new_.base.requirement.ViewNewRequirement;

/**
 * <p>Class of Controller <b>ControllerViewNewRequirement</b>.</p>
 * <p>Class responsible for controlling the <b>ViewNewRequirement</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-17
 * @see    controller.view.modal.new_.ControllerViewNew
 * @see    model.structural.base.requirement.Requirement
 * @see    view.modal.new_.base.requirement.ViewNewRequirement
 */
public class ControllerViewNewRequirement extends ControllerViewNew {

    /**
     * Default constructor method of Class.
     * @param viewNew View New Requirement.
     */
    public ControllerViewNewRequirement(ViewNewRequirement viewNew) {
        super(viewNew);
    }
    
    @Override
    public boolean check() {
        return this.check(this.getView().getPanelBaseRequirement().getCodeTextField(), "Id is required!")
            && this.check(this.getView().getPanelBaseRequirement().getNameTextField(), "Name is required!");
    }

    @Override
    public void new_() {
        this.getView().getProject().addRequirement(this.getView().getRequirement());
        this.getView().getViewMenu().setTabIndex(0);
    }
    
    @Override
    public ViewNewRequirement getView() {
        return (ViewNewRequirement) this.viewModal;
    }
}