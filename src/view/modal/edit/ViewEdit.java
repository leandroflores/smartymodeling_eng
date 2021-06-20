package view.modal.edit;

import java.awt.Dimension;
import javax.swing.JButton;
import model.structural.base.Project;
import view.modal.ViewModal;
import view.main.structural.ViewMenu;
import view.panel.edit.PanelEdit;

/**
 * <p>Class of View <b>ViewEdit</b>.</p>
 * <p>Class responsible for defining the <b>Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-28
 * @see    controller.view.modal.edit.ControllerViewEdit
 * @see    view.modal.ViewModal
 */
public abstract class ViewEdit extends ViewModal {
    protected final ViewMenu view;
    protected final Project  project;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewEdit(ViewMenu view) {
        super(view);
        this.view    = view;
        this.project = view.getProject();
    }
    
    @Override
    public void initComponents() {
        addHeader();
        addComponents();
        addFooter();
        setSize(getViewDimension());
    }
    
    /**
     * Method responsible for returning the View Dimension.
     * @return View Dimension.
     */
    protected abstract Dimension getViewDimension();
    
    @Override
    public void addComponents() {
        addPanel("edit", createPanelEdit());
        getPanelEdit().setPreferredSize(getPanelDimension());
        add(getPanelEdit());
        addLines(1);
    }
    
    /**
     * Method responsible for creating the Panel Edit.
     * @return Panel Edit.
     */
    protected abstract PanelEdit createPanelEdit();
    
    /**
     * Method responsible for returning the Panel Dimension.
     * @return Panel Dimension.
     */
    protected abstract Dimension getPanelDimension();
    
    @Override
    public void addFooter() {
        add(createButton("save",   "  Save  ", "save"));
        add(createButton("cancel", " Cancel ", "cancel"));
    }
    
    /**
     * Method responsible for returning the Panel Edit.
     * @return Panel Edit.
     */
    protected PanelEdit getPanelEdit() {
        return (PanelEdit) getPanel("edit");
    }
    
    /**
     * Method responsible for returning the Save Button.
     * @return Save Button.
     */
    public JButton getSaveButton() {
        return getButton("save");
    }
    
    /**
     * Method responsible for returning the Cancel Button.
     * @return Cancel Button.
     */
    public JButton getCancelButton() {
        return getButton("cancel");
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
        return view;
    }
}