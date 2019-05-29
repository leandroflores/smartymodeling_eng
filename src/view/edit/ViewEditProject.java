package view.edit;

import controller.view.edit.ControllerViewEditProject;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import view.edit.panel.PanelDataProject;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditProject</b>.</p>
 * <p>Class responsible for defining the <b>Project Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  28/05/2019
 * @see    controller.view.edit.ControllerViewEditProject
 * @see    view.edit.ViewEdit
 */
public final class ViewEditProject extends ViewEdit {
    private final Project project;
    private PanelDataProject panelDataProject;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param project Project.
     */
    public ViewEditProject(PanelModeling panel, Project project) {
        super(panel);
        this.project    = project;
        this.controller = new ControllerViewEditProject(this);
        this.title      = "Edit Project";
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
        
        this.addPanelProject();
        
        this.add(this.tabbedPane);
        
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Project.
     */
    private void addPanelProject() {
        this.panelDataProject = new PanelDataProject();
        this.createScrollPane("scrollPanelDataProject", this.panelDataProject);
        this.getScrollPanelDataProject().setViewportView(this.panelDataProject);
        this.tabbedPane.add("Project", this.getScrollPanelDataProject());
    }
    
    @Override
    public void setValues() {
        this.panelDataProject.getPathTextField().setText(this.project.getPath());
        this.panelDataProject.getNameTextField().setText(this.project.getName());
        
        this.panelDataProject.getNameTextField().requestFocus();
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
    public JScrollPane getScrollPanelDataProject() {
        return this.scrollPanes.get("scrollPanelDataProject");
    }
    
    /**
     * Method responsible for returning Panel Data Project.
     * @return Panel Data Project.
     */
    public PanelDataProject getPanelDataProject() {
        return this.panelDataProject;
    }
}