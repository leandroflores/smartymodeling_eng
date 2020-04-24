package view.modal.new_;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import view.modal.ViewModal;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewNew</b>.</p>
 * <p>Class responsible for defining the <b>New View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-04
 * @see    controller.view.modal.new_.ControllerViewNew
 * @see    view.modal.ViewModal
 */
public abstract class ViewNew extends ViewModal {
    protected final ViewMenu view;
    protected final Project  project;
    protected JTabbedPane tabbedPane;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewNew(ViewMenu view) {
        super(view);
        this.view    = view;
        this.project = view.getProject();
    }
    
    @Override
    public void addFooter() {
        this.add(this.createButton("insertButton", " Insert ", "insert"));
        this.add(this.createButton("backButton",   " Cancel ", "back"));
    }
    
    /**
     * Method responsible for returning the Insert Button.
     * @return Insert Button.
     */
    public JButton getInsertButton() {
        return this.getButton("insertButton");
    }
    
    /**
     * Method responsible for returning the Back Button.
     * @return Back Button.
     */
    public JButton getBackButton() {
        return this.getButton("backButton");
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