package view.edit.base;

import controller.view.edit.base.ControllerViewEditProject;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import view.panel.base.PanelBaseProject;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditProject</b>.</p>
 * <p>Class responsible for defining the <b>Project Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  28/05/2019
 * @see    controller.view.edit.base.ControllerViewEditProject
 * @see    view.edit.base.ViewEdit
 */
public final class ViewEditProject extends ViewEdit {
    private final Project project;
    private PanelBaseProject panelBaseProject;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param project Project.
     */
    public ViewEditProject(PanelModeling panel, Project project) {
        super(panel);
        this.project    = project;
        this.controller = new ControllerViewEditProject(this);
        this.title      = "Edit Project Data";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(600, 320);
        this.addHeader();
        this.addComponents();
        this.addFooter();
        this.setValues();
    }
    
    @Override
    public void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(550, 200));
        
        this.addPanelBaseProject();
        
        this.add(this.tabbedPane);

        this.addLines(1);
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
    
    @Override
    public void setValues() {
        this.panelBaseProject.getPathTextField().setText(this.project.getPath());
        this.panelBaseProject.getNameTextField().setText(this.project.getName());
        
        this.panelBaseProject.getNameTextField().requestFocus();
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return this.project;
    }
    
    /**
     * Method responsible for returning Panel Data Project.
     * @return Panel Data Project.
     */
    public JScrollPane getScrollPanelBaseProject() {
        return this.getScrollPane("scrollPanelBaseProject");
    }
    
    /**
     * Method responsible for returning Panel Data Project.
     * @return Panel Data Project.
     */
    public PanelBaseProject getPanelBaseProject() {
        return this.panelBaseProject;
    }
}