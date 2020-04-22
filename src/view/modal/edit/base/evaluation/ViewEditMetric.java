package view.modal.edit.base.evaluation;

import controller.view.modal.edit.base.evaluation.ControllerViewEditMetric;
import java.awt.Dimension;
import model.structural.base.evaluation.Metric;
import view.modal.edit.ViewEdit;
import view.panel.base.evaluation.PanelBaseMetric;
import view.panel.base.evaluation.PanelBaseOperation;
import view.panel.edit.base.evaluation.PanelEditMetric;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditMetric</b>.</p>
 * <p>Class responsible for defining the <b>Metric Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-18
 * @see    controller.view.modal.edit.base.evaluation.ControllerViewEditMetric
 * @see    model.structural.base.evaluation.Metric
 * @see    view.modal.edit.ViewEdit
 */
public final class ViewEditMetric extends ViewEdit {
    private final Metric metric;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param metric Metric.
     */
    public ViewEditMetric(PanelModeling panel, Metric metric) {
        super(panel);
        this.metric     = metric;
        this.controller = new ControllerViewEditMetric(this);
        this.title      = "Edit Metric Data";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(new Dimension(650, 430));
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.addPanelBaseMetric();
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Base Metric.
     */
    private void addPanelBaseMetric() {
        this.addPanel("panelEditMetric", new PanelEditMetric(this.view, this.metric));
        this.getPanel("panelEditMetric").setPreferredSize(new Dimension(500, 300));
        this.add(this.getPanel("panelEditMetric"));
    }
    
    /**
     * Method responsible for returning the Panel Edit Metric.
     * @return Panel Edit Metric.
     */
    public PanelEditMetric getPanelEditMetric() {
        return (PanelEditMetric) this.getPanel("panelEditMetric");
    }
    
    /**
     * Method responsible for returning the Panel Base Metric.
     * @return Panel Base Metric.
     */
    public PanelBaseMetric getPanelBaseMetric() {
        return this.getPanelEditMetric().getPanelBaseMetric();
    }
    
    /**
     * Method responsible for returning the Panel Base Operation.
     * @return Panel Base Operation.
     */
    public PanelBaseOperation getPanelBaseOperation() {
        return this.getPanelEditMetric().getPanelBaseOperation();
    }
    
    /**
     * Method responsible for returning the Metric.
     * @return Metric.
     */
    public Metric getMetric() {
        return this.metric;
    }
}