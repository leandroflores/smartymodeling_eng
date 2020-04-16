package view.panel.edit.base;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import view.edit.panel.base.PanelBaseProject;
import view.panel.edit.PanelEdit;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditProject</b>.</p> 
 * <p>Class responsible for defining a Panel for Edit the <b>Project</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  11/06/2019
 * @see    model.structural.base.Project
 * @see    view.panel.edit.PanelEdit
 */
public final class PanelEditProject extends PanelEdit {
    private final Project project;
    private PanelBaseProject panelBaseProject;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     */
    public PanelEditProject(ViewMenu viewMenu) {
        super(viewMenu);
        this.project = this.viewMenu.getProject();
        this.setPreferredSize(new Dimension(200, 100));
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(100, 100));
        
        this.addPanelBaseProject();
        
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Project.
     */
    private void addPanelBaseProject() {
        this.panelBaseProject = new PanelBaseProject(this.getViewMenu());
        this.createScrollPane("scrollPanelBaseProject", this.panelBaseProject);
        this.getScrollPanelBaseProject().setViewportView(this.panelBaseProject);
        this.tabbedPane.add("Project", this.getScrollPanelBaseProject());
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return this.project;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Project.
     * @return Scroll Panel Base Project.
     */
    public JScrollPane getScrollPanelBaseProject() {
        return this.getScrollPane("scrollPanelBaseProject");
    }
}