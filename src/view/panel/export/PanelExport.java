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
        this.project  = this.viewMenu.getProject();
    }
    
    /**
     * Method responsible for setting the Default Properties.
     */
    protected void setDefaultProperties() {
        this.setLayout(new GridBagLayout());
    }
    
    @Override
    protected abstract void addComponents();
    
    /**
     * Method responsible for adding the Directory Field.
     */
    protected void addDirectoryField() {
        this.add(this.createLabel("Directory*: "), this.createConstraints(1, 1, 0, 0));
        this.add(this.createTextFieldNoEditable("directoryTextField", "", 15), this.createConstraints(4, 1, 1, 0));
        this.add(this.createButton("searchDirectoryButton", "", "Search Directory", "search.png"), this.createConstraints(1, 1, 5, 0));
        this.createDirectoryChooser("searchDirectoryChooser");
    }
    
    /**
     * Method responsible for adding the Name Text Field.
     */
    protected void addNameTextField() {
        this.add(this.createLabel("Name*: "), this.createConstraints(1, 1, 0, 2));
        this.add(this.createTextField("nameTextField", "", 15), this.createConstraints(5, 1, 1, 2));
    }
    
    /**
     * Method responsible for returning the Directory Text Field.
     * @return Directory Text Field.
     */
    public JTextField getDirectoryTextField() {
        return this.getTextField("directoryTextField");
    }
    
    /**
     * Method responsible for returning the Search Directory Button.
     * @return Search Directory Button.
     */
    public JButton getSearchDirectoryButton() {
        return this.getButton("searchDirectoryButton");
    }
    
    /**
     * Method responsible for returning the Search Directory Chooser.
     * @return Search Directory Chooser.
     */
    public JFileChooser getSearchDirectoryChooser() {
        return this.getFileChooser("searchDirectoryChooser");
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.getTextField("nameTextField");
    }
    
    /**
     * Method responsible for returning the Context Combo Box.
     * @return Context Combo Box.
     */
    public JComboBox getContextComboBox() {
        return this.getComboBox("contextComboBox");
    }
    
    /**
     * Method responsible for returning the Controller.
     * @return Controller.
     */
    public ControllerPanelExport getController() {
        return (ControllerPanelExport) this.controller;
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return this.project;
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
}