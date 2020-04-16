package view.panel.edit;

import java.awt.Dimension;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import view.panel.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEdit</b>.</p> 
 * <p>Class responsible for defining a Abstract Model for <b>Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-11
 * @see    view.panel.Panel
 */
public class PanelEdit extends Panel {
    protected final ViewMenu viewMenu;
    protected final Project project;
    protected JTabbedPane tabbedPane;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public PanelEdit(ViewMenu view) {
        this.viewMenu   = view;
        this.project    = view.getProject();
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(550, 200));
    }
    
    @Override
    protected void addComponents() {}
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return this.project;
    }
}