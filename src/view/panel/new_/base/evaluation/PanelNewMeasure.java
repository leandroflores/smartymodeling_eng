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
        addComponents();
    }
    
    @Override
    protected void addPanels() {
        addPanelBaseMeasure();
    }
    
    /**
     * Method responsible for adding the Panel Base Measure.
     */
    public void addPanelBaseMeasure() {
        addPanel("base_measure", new PanelBaseMeasure(this, measure));
        tabbedPane.removeAll();
        createScrollPane("base_measure",  getPanelBaseMeasure());
        getScrollPane("base_measure").setViewportView(getPanelBaseMeasure());
        tabbedPane.add("Measure", getScrollPane("base_measure"));
    }
    
    /**
     * Method responsible for adding the Panel Base Target.
     */
    public void addPanelBaseTarget() {
        addPanel("target", new PanelBaseTarget(this, measure));
        createScrollPane("target",  getPanelBaseTarget());
        getScrollPane("target").setViewportView(getPanelBaseTarget());
        tabbedPane.add("Target", getScrollPane("target"));
        tabbedPane.setSelectedComponent(tabbedPane.getComponentAt(1));
        tabbedPane.setEnabledAt(0, false);
    }
    
    /**
     * Method responsible for removing the Panel Base Target.
     */
    public void removePanelBaseTarget() {
        tabbedPane.getComponent(0).setEnabled(true);
        tabbedPane.setSelectedComponent(tabbedPane.getComponent(0));
        tabbedPane.remove(1);
    }
    
    /**
     * Method responsible for adding the Panel Base Evaluation.
     */
    public void addPanelBaseEvaluation() {
        addPanel("evaluation", new PanelBaseEvaluation(this, measure));
        createScrollPane("evaluation",  getPanelBaseEvaluation());
        getScrollPane("evaluation").setViewportView(getPanelBaseEvaluation());
        tabbedPane.add("Evaluation", getScrollPane("evaluation"));
        tabbedPane.setSelectedComponent(tabbedPane.getComponentAt(2));
        tabbedPane.setEnabledAt(1, false);
        getView().getInsertButton().setEnabled(true);
    }
    
    /**
     * Method responsible for removing the Panel Base Evaluation.
     */
    public void removePanelBaseEvaluation() {
        tabbedPane.getComponent(1).setEnabled(true);
        tabbedPane.setSelectedComponent(tabbedPane.getComponent(1));
        tabbedPane.remove(2);
        getView().getInsertButton().setEnabled(false);
    }
    
    /**
     * Method responsible for returning the Panel Base Measure.
     * @return Panel Base Measure.
     */
    public PanelBaseMeasure getPanelBaseMeasure() {
        return (PanelBaseMeasure) getPanel("base_measure");
    }
    
    /**
     * Method responsible for returning the Panel Base Target.
     * @return Panel Base Target.
     */
    public PanelBaseTarget getPanelBaseTarget() {
        return (PanelBaseTarget) getPanel("target");
    }
    
    /**
     * Method responsible for returning the Panel Base Evaluation.
     * @return Panel Base Evaluation.
     */
    public PanelBaseEvaluation getPanelBaseEvaluation() {
        return (PanelBaseEvaluation) getPanel("evaluation");
    }
    
    /**
     * Method responsible for returning the Measure.
     * @return Measure.
     */
    public Measure getMeasure() {
        return measure;
    }
    
    @Override
    public ViewNewMeasure getView() {
        return (ViewNewMeasure) viewNew;
    }
}