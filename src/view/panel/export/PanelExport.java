package view.panel.export;

import controller.view.panel.export.ControllerPanelExport;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import model.structural.base.Project;
import view.panel.Panel;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelExport</b>.</p>
 * <p>Class responsible for defining a Abstract Model for <b>Export Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-08
 * @see    controller.view.panel.export.ControllerPanelExport
 * @see    view.panel.Panel
 */
public abstract class PanelExport extends Panel {
    protected final ViewMenu viewMenu;
    protected final Project project;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public PanelExport(ViewMenu view) {
        this.viewMenu = view;
        this.project  = view.getProject();
    }
    
    /**
     * Method responsible for setting the Default Properties.
     */
    protected void setDefaultProperties() {
        setLayout(new GridBagLayout());
    }
    
    @Override
    protected abstract void addComponents();
    
    /**
     * Method responsible for adding the Directory Field.
     */
    protected void addDirectoryField() {
        add(createLabel("Directory*: "), createConstraints(1, 1, 0, 0));
        add(createTextFieldNoEditable("directory", "", 15), createConstraints(4, 1, 1, 0));
        add(createButton("searchDirectory", "", "Search Directory", "search.png"), createConstraints(1, 1, 5, 0));
        createDirectoryChooser("searchDirectory");
    }
    
    /**
     * Method responsible for adding the Name Text Field.
     */
    protected void addNameTextField() {
        add(createLabel("Name*: "), createConstraints(1, 1, 0, 2));
        add(createTextField("name", "", 15), createConstraints(5, 1, 1, 2));
    }
    
    /**
     * Method responsible for returning the Directory Text Field.
     * @return Directory Text Field.
     */
    public JTextField getDirectoryTextField() {
        return getTextField("directory");
    }
    
    /**
     * Method responsible for returning the Search Directory Button.
     * @return Search Directory Button.
     */
    public JButton getSearchDirectoryButton() {
        return getButton("searchDirectory");
    }
    
    /**
     * Method responsible for returning the Search Directory Chooser.
     * @return Search Directory Chooser.
     */
    public JFileChooser getSearchDirectoryChooser() {
        return getFileChooser("searchDirectory");
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return getTextField("name");
    }
    
    /**
     * Method responsible for returning the Context Combo Box.
     * @return Context Combo Box.
     */
    public JComboBox getContextComboBox() {
        return getComboBox("context");
    }
    
    /**
     * Method responsible for returning the Controller.
     * @return Controller.
     */
    public ControllerPanelExport getController() {
        return (ControllerPanelExport) controller;
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return project;
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return viewMenu;
    }
}