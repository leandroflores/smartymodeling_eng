package view.edit.panel.base;

import controller.view.edit.panel.base.ControllerPanelBaseProject;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JTextField;
import model.structural.base.Project;
import view.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseProject</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Project Base</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  29/05/2019
 * @see    controller.view.edit.panel.base.ControllerPanelBaseProject
 * @see    model.structural.base.Project
 * @see    view.Panel
 */
public final class PanelBaseProject extends Panel {
    private final ViewMenu viewMenu;
    private final Project  project;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     */
    public PanelBaseProject(ViewMenu viewMenu) {
        this.viewMenu   = viewMenu;
        this.project    = this.viewMenu.getProject();
        this.controller = new ControllerPanelBaseProject(this);
        this.setSettings();
        this.addComponents();
//        this.setValues();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridLayout(5, 1, 2, 5));
        this.setPreferredSize(new Dimension(50, 50));
        this.setSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Name*: "));
        this.add(this.createTextField("nameTextField", this.project.getName(), 5));
        
        this.add(this.createLabel("Path: "));
        this.add(this.createTextFieldNoEditable("pathTextField", this.project.getPath(), 5));
        
        this.add(this.createLabel("Version: "));
        this.add(this.createTextField("versionTextField", this.project.getVersion(), 5));
    }
    
    /**
     * Method responsible for setting the Project Values.
     */
    public void setValues() {
        this.getNameTextField().setText(this.project.getName());
        this.getPathTextField().setText(this.project.getPath());
        this.getVersionTextField().setText(this.project.getVersion());
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return this.project;
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.getTextField("nameTextField");
    }
    
    /**
     * Method responsible for returning the Path Text Field.
     * @return Path Text Field.
     */
    public JTextField getPathTextField() {
        return this.getTextField("pathTextField");
    }
    
    /**
     * Method responsible for returning the Version Text Field.
     * @return Version Text Field.
     */
    public JTextField getVersionTextField() {
        return this.getTextField("versionTextField");
    }
}