package view.panel.base;

import controller.view.panel.base.ControllerPanelBase;
import model.structural.base.Project;
import view.panel.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBase</b>.</p> 
 * <p>Class responsible for defining a Abstract Model for <b>Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-16
 * @see    controller.view.panel.base.ControllerPanelBase
 * @see    view.panel.Panel
 */
public abstract class PanelBase extends Panel {
    protected final ViewMenu viewMenu;
    protected final Project project;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public PanelBase(ViewMenu view) {
        this.viewMenu = view;
        this.project  = view.getProject();
    }
    
    /**
     * Method responsible for setting the Default Properties.
     */
    protected abstract void setDefaultProperties();
    
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
    
    /**
     * Method responsible for returning the Panel Base Controller.
     * @return Panel Base Controller.
     */
    public ControllerPanelBase getController() {
        return (ControllerPanelBase) this.controller;
    }
}