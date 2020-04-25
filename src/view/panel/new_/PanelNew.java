package view.panel.new_;

import java.awt.Dimension;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import view.panel.Panel;
import view.main.structural.ViewMenu;
import view.modal.new_.ViewNew;

/**
 * <p>Class of View <b>PanelNew</b>.</p> 
 * <p>Class responsible for defining a Abstract Model for <b>New Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-24
 * @see    view.panel.Panel
 */
public abstract class PanelNew extends Panel {
    protected final ViewNew viewNew;
    protected JTabbedPane tabbedPane;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public PanelNew(ViewNew view) {
        this.viewNew    = view;
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(550, 200));
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
            this.addPanels();
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panels.
     */
    protected abstract void addPanels();
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return this.getView().getProject();
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.getView().getViewMenu();
    }
    
    /**
     * Method responsible for returning the View New.
     * @return View New.
     */
    public ViewNew getView() {
        return this.viewNew;
    }
}