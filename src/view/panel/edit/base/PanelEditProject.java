package view.panel.edit.base;

import view.panel.base.PanelBaseProject;
import view.panel.edit.PanelEdit;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditProject</b>.</p> 
 * <p>Class responsible for defining a <b>Project Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-11
 * @see    model.structural.base.Project
 * @see    view.panel.edit.PanelEdit
 */
public final class PanelEditProject extends PanelEdit {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public PanelEditProject(ViewMenu view) {
        super(view);
        this.addComponents();
    }
    
    @Override
    protected void addPanels() {
        this.addPanelBaseProject();
    }
    
    /**
     * Method responsible for adding the Panel Base Project.
     */
    private void addPanelBaseProject() {
        this.addPanel("panelBaseProject", new PanelBaseProject(this.viewMenu));
        this.createScrollPane("scrollPanelBaseProject",  this.getPanelBaseProject());
        this.getScrollPane("scrollPanelBaseProject").setViewportView(this.getPanelBaseProject());
        this.tabbedPane.add("Project", this.getScrollPane("scrollPanelBaseProject"));
    }
    
    /**
     * Method responsible for returning the Panel Base Project.
     * @return Panel Base Project.
     */
    public PanelBaseProject getPanelBaseProject() {
        return (PanelBaseProject) this.getPanel("panelBaseProject");
    }
}