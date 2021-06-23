package view.modal.requirement;

import java.awt.Dimension;
import javax.swing.JButton;
import model.structural.base.Project;
import view.modal.ViewModal;
import view.main.structural.ViewMenu;
import view.panel.requirement.PanelRequirement;

/**
 * <p>Class of View <b>ViewRequirement</b>.</p>
 * <p>Class responsible for defining the <b>Requirement View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-21
 * @see    controller.view.modal.requirement.ControllerViewRequirement
 * @see    view.modal.ViewModal
 */
public abstract class ViewRequirement extends ViewModal {
    protected final ViewMenu view;
    protected final Project  project;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewRequirement(ViewMenu view) {
        super(view);
        this.view    = view;
        this.project = view.getProject();
    }
    
    @Override
    public void initComponents() {
        updateTitle();
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
        addPanel("requirement", createPanelRequirement());
        getPanelRequirement().setMinimumSize(getPanelDimension());
        getPanelRequirement().setMaximumSize(getPanelDimension());
        getPanelRequirement().setPreferredSize(getPanelDimension());
        add(getPanelRequirement());
        addLines(1);
    }
    
    /**
     * Method responsible for returning the Panel Dimension.
     * @return Panel Dimension.
     */
    protected abstract Dimension getPanelDimension();
    
    /**
     * Method responsible for creating the Panel Requirement.
     * @return Panel Requirement.
     */
    protected abstract PanelRequirement createPanelRequirement();
    
    @Override
    public void addFooter() {
        add(createButton("save",   "  Save  ", "save"));
        add(createButton("cancel", " Cancel ", "cancel"));
    }
    
    /**
     * Method responsible for returning the Panel Requirement.
     * @return Panel Requirement.
     */
    protected PanelRequirement getPanelRequirement() {
        return (PanelRequirement) getPanel("requirement");
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