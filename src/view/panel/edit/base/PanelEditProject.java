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
        addComponents();
    }
    
    @Override
    protected void addPanels() {
        addPanelBaseProject();
    }
    
    /**
     * Method responsible for adding the Panel Base Project.
     */
    private void addPanelBaseProject() {
        addPanel("base_project", new PanelBaseProject(viewMenu));
        createScrollPane("base_project", getPanelBaseProject());
        getScrollPane("base_project").setViewportView(getPanelBaseProject());
        tabbedPane.add("Project", getScrollPane("base_project"));
    }
    
    /**
     * Method responsible for returning the Panel Base Project.
     * @return Panel Base Project.
     */
    public PanelBaseProject getPanelBaseProject() {
        return (PanelBaseProject) getPanel("base_project");
    }
}