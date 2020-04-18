package view.new_.base.evaluation;

import controller.view.new_.base.evaluation.ControllerViewNewMeasure;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import model.structural.base.evaluation.Measure;
import view.panel.base.evaluation.measure.PanelBaseEvaluation;
import view.panel.base.evaluation.measure.PanelBaseNewMeasure;
import view.panel.base.evaluation.measure.PanelBaseTarget;
import view.new_.ViewNew;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewNewMeasure</b>.</p>
 * <p>Class responsible for defining the <b>New Measure View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-09-03
 * @see    controller.view.new_.base.evaluation.ControllerViewNewMeasure
 * @see    model.structural.base.evaluation.Measure
 * @see    view.new_.ViewNew
 */
public final class ViewNewMeasure extends ViewNew {
    private final Measure measure;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param project Project.
     */
    public ViewNewMeasure(ViewMenu view, Project project) {
        super(view);
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
        this.addPanel("panelBaseNewMeasure", new PanelBaseNewMeasure(this, this.measure));
        this.tabbedPane.removeAll();
        this.createScrollPane("scrollPanelBaseMeasure",  this.getPanelBaseMeasure());
        this.getScrollPanelBaseMeasure().setViewportView(this.getPanelBaseMeasure());
        this.tabbedPane.add("Measure", this.getScrollPanelBaseMeasure());
        this.getInsertButton().setEnabled(false);
    }
    
    /**
     * Method responsible for adding the Panel Base Target.
     */
    public void addPanelBaseTarget() {
        this.addPanel("panelBaseTarget", new PanelBaseTarget(this, this.measure));
        this.createScrollPane("scrollPanelBaseTarget",  this.getPanelBaseTarget());
        this.getScrollPanelBaseTarget().setViewportView(this.getPanelBaseTarget());
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
        this.addPanel("panelBaseEvaluation", new PanelBaseEvaluation(this, this.measure));
        this.createScrollPane("scrollPanelEvaluation",  this.getPanelBaseEvaluation());
        this.getScrollPanelEvaluation().setViewportView(this.getPanelBaseEvaluation());
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
     * Method responsible for returning the Panel Base Measure.
     * @return Panel Base Measure.
     */
    public PanelBaseNewMeasure getPanelBaseMeasure() {
        return (PanelBaseNewMeasure) this.getPanel("panelBaseMeasure");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Measure.
     * @return Scroll Panel Base Measure.
     */
    public JScrollPane getScrollPanelBaseMeasure() {
        return this.getScrollPane("scrollPanelBaseMeasure");
    }
    
    /**
     * Method responsible for returning the Panel Base Target.
     * @return Panel Base Target.
     */
    public PanelBaseTarget getPanelBaseTarget() {
        return (PanelBaseTarget) this.getPanel("panelBaseTarget");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Target.
     * @return Scroll Panel Target.
     */
    public JScrollPane getScrollPanelBaseTarget() {
        return this.getScrollPane("scrollPanelBaseTarget");
    }
    
    /**
     * Method responsible for returning the Panel Base Evaluation.
     * @return Panel Base Evaluation.
     */
    public PanelBaseEvaluation getPanelBaseEvaluation() {
        return (PanelBaseEvaluation) this.getPanel("panelBaseEvaluation");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Evaluation.
     * @return Scroll Panel Evaluation.
     */
    public JScrollPane getScrollPanelEvaluation() {
        return this.getScrollPane("scrollPanelEvaluation");
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
}