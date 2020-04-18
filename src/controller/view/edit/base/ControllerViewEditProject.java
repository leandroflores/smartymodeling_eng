package controller.view.edit.base;

import view.edit.base.ViewEditProject;

/**
 * <p>Class of Controller <b>ControllerViewEditProject</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewEditProject</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  29/05/2019
 * @see    controller.view.edit.base.ControllerViewEdit
 * @see    view.edit.base.ViewEditProject
 */
public class ControllerViewEditProject extends ControllerViewEdit {
    private final ViewEditProject viewEditProject;

    /**
     * Default constructor method of Class.
     * @param viewEdit View Edit Project.
     */
    public ControllerViewEditProject(ViewEditProject viewEdit) {
        super(viewEdit);
        this.viewEditProject = viewEdit;
    }

    /**
     * Method responsible for checking the Project Name.
     * @return Name checked.
     */
    private boolean checkName() {
        return this.check(this.viewEditProject.getPanelBaseProject().getNameTextField(), "Name is a required field!");
    }
    
    @Override
    public boolean check() {
        return this.checkName();
    }

    @Override
    public void save() {
        this.viewEditProject.getProject().setName(this.viewEditProject.getPanelBaseProject().getNameTextField().getText());
        this.close();
    }
}