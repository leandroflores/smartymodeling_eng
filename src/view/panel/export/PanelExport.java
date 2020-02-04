package view.panel.export;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import model.structural.base.Project;
import view.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelExport</b>.</p> 
 * <p>Class responsible for defining a Panel Model for <b>Export</b> on SMartyModeling.</p>
 * @author Leandro
 * @since  08/11/2019
 * @see    controller.view.panel.export.
 * @see    view.Panel
 */
public abstract class PanelExport extends Panel {
    protected final ViewMenu viewMenu;
    protected final Project project;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     */
    public PanelExport(ViewMenu viewMenu) {
        this.viewMenu = viewMenu;
        this.project  = this.viewMenu.getProject();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    protected void setSettings() {
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(50, 50));
        this.setSize(new Dimension(50, 50));
    }
    
    @Override
    protected abstract void addComponents();
    
    /**
     * Method responsible for adding the Directory Field.
     */
    protected void addDirectoryField() {
        this.add(this.createLabel("Directory*: "), this.getConstraints(1, 1, 0, 0));
        this.add(this.createTextFieldNoEditable("directoryTextField", "", 15), this.getConstraints(4, 1, 1, 0));
        this.add(this.createButton("searchDirectoryButton", "", "Search Directory", "search.png"), this.getConstraints(1, 1, 5, 0));
        this.createDirectoryChooser("searchDirectoryChooser");
    }
    
    /**
     * Method responsible for adding the Name Text Field.
     */
    protected void addNameTextField() {
        this.add(this.createLabel("Name*: "), this.getConstraints(1, 1, 0, 2));
        this.add(this.createTextField("nameTextField", "", 15), this.getConstraints(5, 1, 1, 2));
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
     * Method responsible for returning the Directory Text Field.
     * @return Directory Text Field.
     */
    public JTextField getDirectoryTextField() {
        return this.textFields.get("directoryTextField");
    }
    
    /**
     * Method responsible for returning the Search Directory Button.
     * @return Search Directory Button.
     */
    public JButton getSearchDirectoryButton() {
        return this.buttons.get("searchDirectoryButton");
    }
    
    /**
     * Method responsible for returning the Search Directory Chooser.
     * @return Search Directory Chooser.
     */
    public JFileChooser getSearchDirectoryChooser() {
        return this.fileChoosers.get("searchDirectoryChooser");
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.textFields.get("nameTextField");
    }
}