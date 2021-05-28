package controller.view.modal.edit.base;

import controller.view.modal.edit.ControllerViewEdit;
import view.modal.edit.base.ViewEditProject;

/**
 * <p>Class of Controller <b>ControllerViewEditProject</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEditProject</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-29
 * @see    controller.view.modal.edit.ControllerViewEdit
 * @see    model.structural.base.Project
 * @see    view.modal.edit.base.ViewEditProject
 */
public class ControllerViewEditProject extends ControllerViewEdit {

    /**
     * Default constructor method of Class.
     * @param view View Edit Project.
     */
    public ControllerViewEditProject(ViewEditProject view) {
        super(view);
    }
    
    @Override
    public boolean check() {
        return check(getView().getPanelBaseProject().getNameTextField(), "Name is a required!");
    }

    @Override
    public void save() {
        close();
    }
    
    @Override
    public ViewEditProject getView() {
        return (ViewEditProject) super.getView();
    }
}