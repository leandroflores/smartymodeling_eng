package view.panel.requirement;

import java.awt.Dimension;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import view.panel.Panel;
import view.main.structural.ViewMenu;
import view.modal.requirement.ViewRequirement;

/**
 * <p>Class of View <b>PanelRequirement</b>.</p> 
 * <p>Class responsible for defining a Abstract Model for <b>Requirement Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-25
 * @see    view.panel.Panel
 */
public abstract class PanelRequirement extends Panel {
    protected final ViewRequirement view;
    protected JTabbedPane tabbedPane;
    
    /**
     * Default constructor method of Class.
     * @param view View Requirement.
     */
    public PanelRequirement(ViewRequirement view) {
        this.view       = view;
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(550, 200));
    }
    
    /**
     * Method responsible for setting the Default Properties.
     */
    protected abstract void setDefaultProperties();
    
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
     * Method responsible for returning the View Requirement.
     * @return View Requirement.
     */
    public ViewRequirement getView() {
        return this.view;
    }
}