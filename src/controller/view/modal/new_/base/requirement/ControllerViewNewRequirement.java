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
     * @param view View New Requirement.
     */
    public ControllerViewNewRequirement(ViewNewRequirement view) {
        super(view);
    }
    
    @Override
    public boolean check() {
        return check(getView().getPanelBaseRequirement().getCodeTextField(), "Id is required!")
            && check(getView().getPanelBaseRequirement().getNameTextField(), "Name is required!");
    }

    @Override
    public void new_() {
        getView().getProject().addRequirement(getView().getRequirement());
        getView().getViewMenu().setTabIndex(0);
    }
    
    @Override
    public ViewNewRequirement getView() {
        return (ViewNewRequirement) super.getView();
    }
}