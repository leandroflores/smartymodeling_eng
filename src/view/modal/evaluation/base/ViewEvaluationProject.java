package view.modal.evaluation.base;

import controller.view.modal.evaluation.base.ControllerViewEvaluationProject;
import java.awt.Dimension;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import view.modal.evaluation.ViewEvaluation;
import view.panel.evaluation.base.PanelEvaluationProject;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewEvaluationProject</b>.</p>
 * <p>Class responsible for defining the <b>Evaluation Project View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-03-28
 * @see    controller.view.modal.evaluation.base.ControllerViewEvaluationProject
 * @see    view.modal.evaluation.ViewEvaluation
 */
public final class ViewEvaluationProject extends ViewEvaluation {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param project Project.
     */
    public ViewEvaluationProject(ViewMenu view, Project project) {
        super(view);
        this.controller = new ControllerViewEvaluationProject(this);
        this.title      = "Evaluation Project";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(600, 520);
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.addTabbedPane();
            this.addPanelEvaluationProject();
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Tabbed Pane.
     */
    private void addTabbedPane() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(500, 400));
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Evaluate Project.
     */
    public void addPanelEvaluationProject() {
        this.addPanel("panelEvaluationProject", new PanelEvaluationProject(this));
        this.tabbedPane.removeAll();
        this.createScrollPane("scrollPanelEvaluationProject",  this.getPanelEvaluationProject());
        this.getScrollPane("scrollPanelEvaluationProject").setViewportView(this.getPanelEvaluationProject());
        this.tabbedPane.add("Evaluation", this.getScrollPane("scrollPanelEvaluationProject"));
    }
    
    /**
     * Method responsible for returning the Panel Evaluation Project.
     * @return Panel Evaluation Project.
     */
    public PanelEvaluationProject getPanelEvaluationProject() {
        return (PanelEvaluationProject) this.getPanel("panelEvaluationProject");
    }
}