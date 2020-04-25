package view.panel.new_.base.evaluation;

import model.structural.base.evaluation.Measure;
import view.modal.new_.base.evaluation.ViewNewMeasure;
import view.panel.new_.base.evaluation.measure.PanelBaseEvaluation;
import view.panel.new_.base.evaluation.measure.PanelBaseMeasure;
import view.panel.new_.base.evaluation.measure.PanelBaseTarget;
import view.panel.new_.PanelNew;

/**
 * <p>Class of View <b>PanelNewMeasure</b>.</p> 
 * <p>Class responsible for defining a <b>New Measure Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-24
 * @see    model.structural.base.evaluation.Measure
 * @see    view.panel.new_.PanelNew
 */
public final class PanelNewMeasure extends PanelNew {
    private final Measure measure;
    
    /**
     * Default constructor method of Class.
     * @param view View New Measure.
     * @param measure Measure.
     */
    public PanelNewMeasure(ViewNewMeasure view, Measure measure) {
        super(view);
        this.measure = measure;
        this.addComponents();
    }
    
    @Override
    protected void addPanels() {
        this.addPanelBaseMeasure();
    }
    
    /**
     * Method responsible for adding the Panel Base Measure.
     */
    public void addPanelBaseMeasure() {
        this.addPanel("panelBaseMeasure", new PanelBaseMeasure(this, this.measure));
        this.tabbedPane.removeAll();
        this.createScrollPane("scrollPanelBaseMeasure",  this.getPanelBaseMeasure());
        this.getScrollPane("scrollPanelBaseMeasure").setViewportView(this.getPanelBaseMeasure());
        this.tabbedPane.add("Measure", this.getScrollPane("scrollPanelBaseMeasure"));
    }
    
    /**
     * Method responsible for adding the Panel Base Target.
     */
    public void addPanelBaseTarget() {
        this.addPanel("panelBaseTarget", new PanelBaseTarget(this, this.measure));
        this.createScrollPane("scrollPanelTarget",  this.getPanelBaseTarget());
        this.getScrollPane("scrollPanelTarget").setViewportView(this.getPanelBaseTarget());
        this.tabbedPane.add("Target", this.getScrollPane("scrollPanelTarget"));
        this.tabbedPane.setSelectedComponent(this.tabbedPane.getComponentAt(1));
        this.tabbedPane.setEnabledAt(0, false);
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
        this.getScrollPane("scrollPanelEvaluation").setViewportView(this.getPanelBaseEvaluation());
        this.tabbedPane.add("Evaluation", this.getScrollPane("scrollPanelEvaluation"));
        this.tabbedPane.setSelectedComponent(this.tabbedPane.getComponentAt(2));
        this.tabbedPane.setEnabledAt(1, false);
        this.getView().getInsertButton().setEnabled(true);
    }
    
    /**
     * Method responsible for removing the Panel Base Evaluation.
     */
    public void removePanelBaseEvaluation() {
        this.tabbedPane.getComponent(1).setEnabled(true);
        this.tabbedPane.setSelectedComponent(this.tabbedPane.getComponent(1));
        this.tabbedPane.remove(2);
        this.getView().getInsertButton().setEnabled(false);
    }
    
    /**
     * Method responsible for returning the Panel Base Measure.
     * @return Panel Base Measure.
     */
    public PanelBaseMeasure getPanelBaseMeasure() {
        return (PanelBaseMeasure) this.getPanel("panelBaseMeasure");
    }
    
    /**
     * Method responsible for returning the Panel Base Target.
     * @return Panel Base Target.
     */
    public PanelBaseTarget getPanelBaseTarget() {
        return (PanelBaseTarget) this.getPanel("panelBaseTarget");
    }
    
    /**
     * Method responsible for returning the Panel Base Evaluation.
     * @return Panel Base Evaluation.
     */
    public PanelBaseEvaluation getPanelBaseEvaluation() {
        return (PanelBaseEvaluation) this.getPanel("panelBaseEvaluation");
    }
    
    /**
     * Method responsible for returning the Measure.
     * @return Measure.
     */
    public Measure getMeasure() {
        return this.measure;
    }
    
    @Override
    public ViewNewMeasure getView() {
        return (ViewNewMeasure) this.viewNew;
    }
}