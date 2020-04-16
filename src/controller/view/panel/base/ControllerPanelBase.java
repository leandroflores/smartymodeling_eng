package controller.view.panel.base;

import controller.view.panel.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import model.structural.base.Project;
import view.panel.base.PanelBase;
import view.panel.modeling.PanelModeling;
import view.panel.project.tree.PanelTree;
import view.structural.ViewMenu;

/**
 * <p>Class of Controller <b>ControllerPanelBase</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBase</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-16
 * @see    controller.view.panel.ControllerPanel
 * @see    view.panel.base.PanelBase
 */
public abstract class ControllerPanelBase extends ControllerPanel {
    protected final PanelBase panelBase;
    protected boolean ready;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base.
     */
    public ControllerPanelBase(PanelBase panel) {
        super(panel);
        this.panelBase = panel;
        this.ready     = false;
    }

    /**
     * Method responsible for setting the Ready True.
     */
    public void setReady() {
        this.ready = true;
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.ready)
            this.update();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {}
    
    @Override
    public void keyReleased(KeyEvent event) {
        if (this.ready)
            this.update();
    }
    
    /**
     * Method responsible for refreshing the Panel Base.
     */
    protected void refresh() {
        this.getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for updating the Panel Base.
     */
    protected abstract void update();
    
    /**
     * Method responsible for returning the Panel Tree.
     * @return Panel Tree.
     */
    protected PanelTree getPanelTree() {
        return this.getViewMenu().getPanelProject().getPanelTree();
    }
    
    /**
     * Method responsible for returning the Panel Modeling.
     * @return Panel Modeling.
     */
    protected PanelModeling getPanelModeling() {
        return this.getViewMenu().getPanelModeling();
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    protected Project getProject() {
        return this.getPanel().getProject();
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    protected ViewMenu getViewMenu() {
        return this.getPanel().getViewMenu();
    }
    
    @Override
    public PanelBase getPanel() {
        return (PanelBase) this.panel;
    }
}