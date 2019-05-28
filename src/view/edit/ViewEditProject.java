package view.edit;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import model.structural.base.Project;
import view.edit.panel.PanelProject;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditProject</b>.</p>
 * <p>Class responsible for defining the <b>Project Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  28/05/2019
 * @see    controller.view.edit.
 * @see    view.edit.ViewEdit
 */
public final class ViewEditProject extends ViewEdit {
    private final Project project;
    private JTabbedPane   tabbedPane;
    private PanelProject  panelProject;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param project Project.
     */
    public ViewEditProject(PanelModeling panel, Project project) {
        super(panel);
        this.project    = project;
//        this.controller = new ControllerViewEditarProjeto(this);
        this.title      = "Edit Project";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(580, 500);
        this.addHeader();
        this.addComponents();
//        this.addFooter();
        this.setValues();
    }
    
    @Override
    public void addComponents() {
        this.tabbedPane = new JTabbedPane();
        
        this.createPanelProject();
        
        this.tabbedPane.add("Base", this.panelProject);
        
        this.add(this.tabbedPane);
//        this.tabbedPane.add
//        this.add(this.createLabel("Caminho: ", 120));
//        this.add(this.createTextFieldNoEditable("textFieldCaminho", this.project.getCaminho(), 30));
//        
//        this.addLinhas(1);
//        
//        this.add(this.createLabel("Nome*: ", 120));
//        this.add(this.createTextField("textFieldNome", "", 30));
//        
//        this.addLinhas(1);
        
    }
    
    /**
     * Method responsible for creating Panel Project.
     */
    private void createPanelProject() {
        this.panelProject = new PanelProject();
        this.createScrollPane("scrollPanelProject", this.panelProject);
        this.getScrollPanelProject().setViewportView(this.panelProject);
    }
    
    @Override
    public void setValues() {
        this.panelProject.getPathTextField().setText(this.project.getPath());
        this.panelProject.getNameTextField().setText(this.project.getName());
        
        this.panelProject.getNameTextField().requestFocus();
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return this.project;
    }
    
    /**
     * Method responsible for returning Panel Project.
     * @return Panel Project.
     */
    public JScrollPane getScrollPanelProject() {
        return this.scrollPanes.get("scrollPanelProject");
    }
    
    /**
     * Method responsible for returning Panel Project.
     * @return Panel Project.
     */
    public PanelProject getPanelProject() {
        return this.panelProject;
    }
}