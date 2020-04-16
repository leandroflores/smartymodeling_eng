package view.edit;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import view.ViewModal;
import view.panel.modeling.PanelModeling;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewEdit</b>.</p>
 * <p>Class responsible for defining the <b>Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  28/05/2019
 * @see    controller.view.edit.ControllerViewEdit
 * @see    view.ViewModal
 */
public abstract class ViewEdit extends ViewModal {
    protected final PanelModeling panel;
    protected JTabbedPane tabbedPane;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     */
    public ViewEdit(PanelModeling panel) {
        super(panel.getViewMenu());
        this.panel = panel;
    }
    
    /**
     * Method responsible for setting Values.
     */
    public abstract void setValues();
    
    @Override
    public void addFooter() {
        this.add(this.createButton("saveButton",   "  Save  ", "save"));
        this.add(this.createButton("cancelButton", " Cancel ", "cancel"));
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.panel.getViewMenu();
    }
    
    /**
     * Method responsible for returning the Save Button.
     * @return Save Button.
     */
    public JButton getSaveButton() {
        return this.getButton("saveButton");
    }
    
    /**
     * Method responsible for returning the Cancel Button.
     * @return Cancel Button.
     */
    public JButton getCancelButton() {
        return this.getButton("cancelButton");
    }
}