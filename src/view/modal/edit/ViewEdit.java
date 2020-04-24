package view.modal.edit;

import javax.swing.JButton;
import model.structural.base.Project;
import view.modal.ViewModal;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewEdit</b>.</p>
 * <p>Class responsible for defining the <b>Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-28
 * @see    controller.view.modal.edit.ControllerViewEdit
 * @see    view.modal.ViewModal
 */
public abstract class ViewEdit extends ViewModal {
    protected final ViewMenu view;
    protected final Project  project;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewEdit(ViewMenu view) {
        super(view);
        this.view    = view;
        this.project = view.getProject();
    }
    
    @Override
    public void addFooter() {
        this.add(this.createButton("saveButton",   "  Save  ", "save"));
        this.add(this.createButton("cancelButton", " Cancel ", "cancel"));
    }
    
    /**
     * Method responsible for returning the Save Button.
     * @return Save Button.
     */
    public JButton getSaveButton() {
        return this.getButton("saveButton");
    }
    
    /**
     * Method responsible for returning the Cancel Button.
     * @return Cancel Button.
     */
    public JButton getCancelButton() {
        return this.getButton("cancelButton");
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return this.project;
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.view;
    }
}