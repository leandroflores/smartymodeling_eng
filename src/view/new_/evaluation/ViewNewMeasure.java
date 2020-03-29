package view.new_.evaluation;

import controller.view.new_.evaluation.ControllerViewNewMeasure;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import model.structural.base.evaluation.Measure;
import view.edit.panel.base.evaluation.PanelBaseEvaluation;
import view.edit.panel.base.evaluation.PanelBaseMeasure;
import view.edit.panel.base.evaluation.PanelBaseTarget;
import view.new_.ViewNew;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewNewMeasure</b>.</p>
 * <p>Class responsible for defining the <b>New Measure View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  03/09/2019
 * @see    controller.view.new_.evaluation.ControllerViewNewMeasure
 * @see    model.structural.base.evaluation.Measure
 * @see    view.new_.ViewNew
 */
public final class ViewNewMeasure extends ViewNew { 
    private final Project project;
    private final Measure measure;
    private PanelBaseMeasure    panelBaseMeasure;
    private PanelBaseTarget     panelBaseTarget;
    private PanelBaseEvaluation panelBaseEvaluation;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param project Project.
     */
    public ViewNewMeasure(ViewMenu view, Project project) {
        super(view);
        this.project    = project;
        this.measure    = new Measure();
        this.controller = new ControllerViewNewMeasure(this);
        this.title      = "New Measure";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(600, 520);
        this.addHeader();
        this.addComponents();
        this.addFooter();
        this.addPanelBaseMeasure();
    }
    
    @Override
    public void addComponents() {
        this.addTabbedPane();
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
     * Method responsible for adding the Panel Base Measure.
     */
    public void addPanelBaseMeasure() {
        this.panelBaseMeasure = new PanelBaseMeasure(this, this.measure);
        this.tabbedPane.removeAll();
        this.createScrollPane("scrollPanelBaseMeasure",  this.panelBaseMeasure);
        this.getScrollPanelBaseMeasure().setViewportView(this.panelBaseMeasure);
        this.tabbedPane.add("Measure", this.getScrollPanelBaseMeasure());
        this.getInsertButton().setEnabled(false);
    }
    
    /**
     * Method responsible for adding the Panel Base Target.
     */
    public void addPanelBaseTarget() {
        this.panelBaseTarget = new PanelBaseTarget(this, this.measure);
        this.createScrollPane("scrollPanelBaseTarget",  this.panelBaseTarget);
        this.getScrollPanelBaseTarget().setViewportView(this.panelBaseTarget);
        this.tabbedPane.add("Target", this.getScrollPanelBaseTarget());
        this.tabbedPane.setSelectedComponent(this.tabbedPane.getComponentAt(1));
        this.tabbedPane.setEnabledAt(0, false);
        this.getInsertButton().setEnabled(false);
    }
    
    /**
     * Method responsible for removing the Panel Base Target.
     */
    public void removePanelBaseTarget() {
        this.tabbedPane.getComponent(0).setEnabled(true);
        this.tabbedPane.setSelectedComponent(this.tabbedPane.getComponent(0));
        this.tabbedPane.remove(1);
    }
    
    /**
     * Method responsible for adding the Panel Base Evaluation.
     */
    public void addPanelBaseEvaluation() {
        this.panelBaseEvaluation  = new PanelBaseEvaluation(this, this.measure);
        this.createScrollPane("scrollPanelEvaluation",  this.panelBaseEvaluation);
        this.getScrollPanelEvaluation().setViewportView(this.panelBaseEvaluation);
        this.tabbedPane.add("Evaluation", this.getScrollPanelEvaluation());
        this.tabbedPane.setSelectedComponent(this.tabbedPane.getComponentAt(2));
        this.tabbedPane.setEnabledAt(1, false);
        this.getInsertButton().setEnabled(true);
    }
    
    /**
     * Method responsible for removing the Panel Base Evaluation.
     */
    public void removePanelBaseEvaluation() {
        this.tabbedPane.getComponent(1).setEnabled(true);
        this.tabbedPane.setSelectedComponent(this.tabbedPane.getComponent(1));
        this.tabbedPane.remove(2);
        this.getInsertButton().setEnabled(false);
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
     * Method responsible for returning the Measure.
     * @return Measure.
     */
    public Measure getMeasure() {
        return this.measure;
    }
    
    /**
     * Method responsible for returning the Panel Base Measure.
     * @return Panel Base Measure.
     */
    public PanelBaseMeasure getPanelBaseMeasure() {
        return this.panelBaseMeasure;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Measure.
     * @return Scroll Panel Base Measure.
     */
    public JScrollPane getScrollPanelBaseMeasure() {
        return this.scrollPanes.get("scrollPanelBaseMeasure");
    }
    
    /**
     * Method responsible for returning the Panel Base Target.
     * @return Panel Base Target.
     */
    public PanelBaseTarget getPanelBaseTarget() {
        return this.panelBaseTarget;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Target.
     * @return Scroll Panel Target.
     */
    public JScrollPane getScrollPanelBaseTarget() {
        return this.scrollPanes.get("scrollPanelBaseTarget");
    }
    
    /**
     * Method responsible for returning the Panel Base Evaluation.
     * @return Panel Base Evaluation.
     */
    public PanelBaseEvaluation getPanelBaseEvaluation() {
        return this.panelBaseEvaluation;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Evaluation.
     * @return Scroll Panel Evaluation.
     */
    public JScrollPane getScrollPanelEvaluation() {
        return this.scrollPanes.get("scrollPanelEvaluation");
    }
}