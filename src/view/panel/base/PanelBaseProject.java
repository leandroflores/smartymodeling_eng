package view.panel.base;

import controller.view.panel.base.ControllerPanelBaseProject;
import java.awt.GridLayout;
import javax.swing.JTextField;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseProject</b>.</p>
 * <p>Class responsible for defining a <b>Project Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-29
 * @see    controller.view.panel.base.ControllerPanelBaseProject
 * @see    model.structural.base.Project
 * @see    view.panel.base.PanelBase
 */
public final class PanelBaseProject extends PanelBase {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public PanelBaseProject(ViewMenu view) {
        super(view);
        this.controller = new ControllerPanelBaseProject(this);
        this.setDefaultProperties();
        this.addComponents();
        this.getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridLayout(5, 1, 2, 5));
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