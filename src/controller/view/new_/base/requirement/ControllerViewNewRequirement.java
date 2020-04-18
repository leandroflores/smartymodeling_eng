package controller.view.new_.base.requirement;

import controller.view.new_.base.ControllerViewNew;
import view.new_.base.requirement.ViewNewRequirement;

/**
 * <p>Class of Controller <b>ControllerViewNewRequirement</b>.</p>
 * <p>Class responsible for controlling the <b>ViewNewRequirement</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-17
 * @see    controller.view.new_.base.ControllerViewNew
 * @see    model.structural.base.requirement.Requirement
 * @see    view.new_.base.requirement.ViewNewRequirement
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
    public void insert() {
        this.getView().getProject().addRequirement(this.getView().getRequirement());
    }
    
    @Override
    public ViewNewRequirement getView() {
        return (ViewNewRequirement) this.viewModal;
    }
}