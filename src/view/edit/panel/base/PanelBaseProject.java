package view.edit.panel.base;

import controller.view.edit.panel.base.ControllerPanelBaseProject;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setPreferredSize(new Dimension(300, 100));
    }
    
    @Override
    protected void addComponents() {
        this.addLines(1);
        
        this.add(this.createLabel("Path*: ", 80));
        this.add(this.createTextFieldNoEditable("pathTextField", "", 15));

        this.addLines(1);
        
        this.add(this.createLabel("Name*: ", 80));
        this.add(this.createTextField("nameTextField", "", 15));
        
        this.addLines(1);
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
     * Method responsible for returning the Path Text Field.
     * @return Path Text Field.
     */
    public JTextField getPathTextField() {
        return this.textFields.get("pathTextField");
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.textFields.get("nameTextField");
    }
}