package view.modal.requirement;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import view.modal.ViewModal;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewRequirement</b>.</p>
 * <p>Class responsible for defining the <b>Requirement View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-21
 * @see    controller.view.
 * @see    view.modal.ViewModal
 */
public abstract class ViewRequirement extends ViewModal {
    protected final ViewMenu view;
    protected final Project  project;
    protected JTabbedPane tabbedPane;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewRequirement(ViewMenu view) {
        super(view);
        this.view    = view;
        this.project = view.getProject();
    }
    
    @Override
    public void addFooter() {
        this.add(this.createButton("refreshButton", " Refresh ", "refresh"));
        this.add(this.createButton("cancelButton",  " Cancel  ", "cancel"));
    }
    
    /**
     * Method responsible for returning the Refresh Button.
     * @return Refresh Button.
     */
    public JButton getRefreshButton() {
        return this.getButton("refreshButton");
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