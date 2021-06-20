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
        super(panel.getViewMenu());
        this.metric     = metric;
        this.controller = new ControllerViewEditMetric(this);
        this.title      = "Edit Metric Data";
        initComponents();
    }
    
    @Override
    protected Dimension getViewDimension() {
        return new Dimension(650, 430);
    }
    
    @Override
    protected PanelEditMetric createPanelEdit() {
        return new PanelEditMetric(view, metric);
    }
    
    @Override
    protected Dimension getPanelDimension() {
        return new Dimension(500, 300);
    }
    
    @Override
    public PanelEditMetric getPanelEdit() {
        return (PanelEditMetric) super.getPanelEdit();
    }
    
    /**
     * Method responsible for returning the Panel Base Metric.
     * @return Panel Base Metric.
     */
    public PanelBaseMetric getPanelBaseMetric() {
        return getPanelEdit().getPanelBaseMetric();
    }
    
    /**
     * Method responsible for returning the Panel Base Operation.
     * @return Panel Base Operation.
     */
    public PanelBaseOperation getPanelBaseOperation() {
        return getPanelEdit().getPanelBaseOperation();
    }
    
    /**
     * Method responsible for returning the Metric.
     * @return Metric.
     */
    public Metric getMetric() {
        return metric;
    }
}