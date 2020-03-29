package view.query;

import controller.view.new_.evaluation.ControllerViewNewMeasure;
import controller.view.query.ControllerViewQueryEvaluation;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import view.edit.panel.base.evaluation.PanelQueryEvaluation;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewQueryEvaluation</b>.</p>
 * <p>Class responsible for defining the <b>Evaluation Query View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  28/03/2020
 * @see    controller.view.query.ControllerViewQueryEvaluation
 * @see    view.query.ViewQuery
 */
public final class ViewQueryEvaluation extends ViewQuery { 
    private final Project project;
    private PanelQueryEvaluation panelQueryEvaluation;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param project Project.
     */
    public ViewQueryEvaluation(ViewMenu view, Project project) {
        super(view);
        this.project    = project;
        this.controller = new ControllerViewQueryEvaluation(this);
        this.title      = "Query Evalution";
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
        this.addPanelQueryMeasure();
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
     * Method responsible for adding the Panel Query Measure.
     */
    public void addPanelQueryMeasure() {
        this.panelQueryEvaluation = new PanelQueryEvaluation(this);
        this.tabbedPane.removeAll();
        this.createScrollPane("scrollPanelQueryEvaluation",  this.panelQueryEvaluation);
        this.getScrollPanelQueryEvaluation().setViewportView(this.panelQueryEvaluation);
        this.tabbedPane.add("Evaluation", this.getScrollPanelQueryEvaluation());
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
    public ControllerViewNewMeasure getController() {
        return (ControllerViewNewMeasure) this.controller;
    }
    
    /**
     * Method responsible for returning the Panel Query Evaluation.
     * @return Panel Query Evaluation.
     */
    public PanelQueryEvaluation getPanelQueryEvaluation() {
        return this.panelQueryEvaluation;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Query Evaluation.
     * @return Scroll Panel Query Evaluation.
     */
    public JScrollPane getScrollPanelQueryEvaluation() {
        return this.scrollPanes.get("scrollPanelQueryEvaluation");
    }
}