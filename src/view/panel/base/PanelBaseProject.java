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
        controller = new ControllerPanelBaseProject(this);
        setDefaultProperties();
        addComponents();
        getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        setLayout(new GridLayout(5, 1, 2, 5));
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Name*: "));
        add(createTextField("name", project.getName(), 5));
        
        add(createLabel("Path: "));
        add(createTextFieldNoEditable("path", project.getPath(), 5));
        
        add(createLabel("Version: "));
        add(createTextField("version", project.getVersion(), 5));
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return getTextField("name");
    }
    
    /**
     * Method responsible for returning the Path Text Field.
     * @return Path Text Field.
     */
    public JTextField getPathTextField() {
        return getTextField("path");
    }
    
    /**
     * Method responsible for returning the Version Text Field.
     * @return Version Text Field.
     */
    public JTextField getVersionTextField() {
        return getTextField("version");
    }
}