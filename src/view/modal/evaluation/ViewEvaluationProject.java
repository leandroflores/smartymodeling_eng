package view.modal.evaluation;

import controller.view.modal.evaluation.ControllerViewEvaluationProject;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import view.panel.evaluation.PanelEvaluationProject;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewEvaluationProject</b>.</p>
 * <p>Class responsible for defining the <b>Evaluation Project View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  28/03/2020
 * @see    controller.view.modal.evaluation.ControllerViewEvaluationProject
 * @see    view.modal.evaluation.ViewEvaluation
 */
public final class ViewEvaluationProject extends ViewEvaluation {
    private PanelEvaluationProject panelEvaluationProject;
    
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
        this.panelEvaluationProject = new PanelEvaluationProject(this);
        this.tabbedPane.removeAll();
        this.createScrollPane("scrollPanelEvaluationProject",  this.panelEvaluationProject);
        this.getScrollPanelEvaluationProject().setViewportView(this.panelEvaluationProject);
        this.tabbedPane.add("Evaluation", this.getScrollPanelEvaluationProject());
    }
    
    /**
     * Method responsible for returning the Panel Evaluation Project.
     * @return Panel Evaluation Project.
     */
    public PanelEvaluationProject getPanelEvaluationProject() {
        return this.panelEvaluationProject;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Evaluation Project.
     * @return Scroll Panel Evaluation Project.
     */
    public JScrollPane getScrollPanelEvaluationProject() {
        return this.getScrollPane("scrollPanelEvaluationProject");
    }
}