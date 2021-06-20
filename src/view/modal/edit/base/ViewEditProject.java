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
        initComponents();
    }
    
    @Override
    protected Dimension getViewDimension() {
        return new Dimension(620, 350);
    }
    
    @Override
    protected PanelEditProject createPanelEdit() {
        return new PanelEditProject(view);
    }
    
    @Override
    protected Dimension getPanelDimension() {
        return new Dimension(500, 225);
    }
    
    @Override
    public PanelEditProject getPanelEdit() {
        return (PanelEditProject) super.getPanelEdit();
    }
    
    /**
     * Method responsible for returning the Panel Base Project.
     * @return Panel Base Project.
     */
    public PanelBaseProject getPanelBaseProject() {
        return getPanelEdit().getPanelBaseProject();
    }
}