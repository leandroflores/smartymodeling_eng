package view.modal.evaluation;

import controller.view.modal.evaluation.ControllerViewEvaluation;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import view.modal.ViewModal;
import view.panel.modeling.PanelModeling;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewEvaluation</b>.</p>
 * <p>Class responsible for defining the <b>Evaluation View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  28/03/2019
 * @see    controller.view.modal.evaluation.ControllerViewEvaluation
 * @see    view.modal.ViewModal
 */
public abstract class ViewEvaluation extends ViewModal {
    protected final ViewMenu view;
    protected final PanelModeling panel;
    protected final Project project;
    protected JTabbedPane tabbedPane;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewEvaluation(ViewMenu view) {
        super(view);
        this.view    = view;
        this.panel   = null;
        this.project = view.getProject();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param panel Panel Modeling.
     */
    public ViewEvaluation(PanelModeling panel) {
        super(panel.getViewMenu());
        this.view    = panel.getViewMenu();
        this.panel   = panel;
        this.project = panel.getViewMenu().getProject();
    }
    
    @Override
    public void addFooter() {
        this.add(this.createButton("refreshButton", " Refresh ", "refresh"));
        this.add(this.createButton("clearButton",   "  Clear  ", "clear"));
        this.add(this.createButton("cancelButton",  "  Cancel ", "back"));
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.view;
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return this.project;
    }
    
    /**
     * Method responsible for returning the Controller.
     * @return Controller.
     */
    public ControllerViewEvaluation getController() {
        return (ControllerViewEvaluation) this.controller;
    }
    
    /**
     * Method responsible for returning the Refresh Button.
     * @return Refresh Button.
     */
    public JButton getRefreshButton() {
        return this.getButton("refreshButton");
    }
    
    /**
     * Method responsible for returning the Clear Button.
     * @return Clear Button.
     */
    public JButton getClearButton() {
        return this.getButton("clearButton");
    }
    
    /**
     * Method responsible for returning the Cancel Button.
     * @return Cancel Button.
     */
    public JButton getCancelButton() {
        return this.getButton("cancelButton");
    }
}