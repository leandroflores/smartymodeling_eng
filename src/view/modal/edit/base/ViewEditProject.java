package view.modal.edit.base;

import controller.view.modal.edit.base.ControllerViewEditProject;
import java.awt.Dimension;
import model.structural.base.Project;
import view.modal.edit.ViewEdit;
import view.panel.base.PanelBaseProject;
import view.panel.edit.base.PanelEditProject;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditProject</b>.</p>
 * <p>Class responsible for defining the <b>Project Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-28
 * @see    controller.view.modal.edit.base.ControllerViewEditProject
 * @see    model.structural.base.Project
 * @see    view.modal.edit.ViewEdit
 */
public final class ViewEditProject extends ViewEdit {
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param project Project.
     */
    public ViewEditProject(PanelModeling panel, Project project) {
        super(panel.getViewMenu());
        this.controller = new ControllerViewEditProject(this);
        this.title      = "Edit Project Data";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(new Dimension(620, 350));
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.addPanelEditProject();
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Edit Element.
     */
    private void addPanelEditProject() {
        this.addPanel("panelEditProject", new PanelEditProject(this.view));
        this.getPanelEditProject().setPreferredSize(new Dimension(500, 225));
        this.add(this.getPanelEditProject());
    }
    
    /**
     * Method responsible for returning the Panel Edit Project.
     * @return Panel Edit Project.
     */
    private PanelEditProject getPanelEditProject() {
        return (PanelEditProject) this.getPanel("panelEditProject");
    }
    
    /**
     * Method responsible for returning the Panel Base Project.
     * @return Panel Base Project.
     */
    public PanelBaseProject getPanelBaseProject() {
        return this.getPanelEditProject().getPanelBaseProject();
    }
}