package view.modal.edit.base;

import controller.view.modal.edit.base.ControllerViewEditProject;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import view.modal.edit.ViewEdit;
import view.panel.base.PanelBaseProject;
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
        super(panel);
        this.controller = new ControllerViewEditProject(this);
        this.title      = "Edit Project Data";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(new Dimension(650, 350));
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(550, 225));
            this.addPanelBaseProject();
        this.add(this.tabbedPane);
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Base Project.
     */
    private void addPanelBaseProject() {
        this.addPanel("panelBaseProject", new PanelBaseProject(this.getViewMenu()));
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