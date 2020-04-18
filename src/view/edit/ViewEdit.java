package view.edit;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import view.ViewModal;
import view.panel.modeling.PanelModeling;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewEdit</b>.</p>
 * <p>Class responsible for defining the <b>Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-28
 * @see    controller.view.edit.base.ControllerViewEdit
 * @see    view.ViewModal
 */
public abstract class ViewEdit extends ViewModal {
    protected final ViewMenu view;
    protected final Project  project;
    protected final PanelModeling panel;
    protected JTabbedPane tabbedPane;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     */
    public ViewEdit(PanelModeling panel) {
        super(panel.getViewMenu());
        this.view    = panel.getViewMenu();
        this.project = this.view.getProject();
        this.panel   = panel;
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