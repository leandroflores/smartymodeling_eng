package view.modal.new_.base.evaluation;

import controller.view.modal.new_.base.evaluation.ControllerViewNewMetric;
import java.awt.Dimension;
import model.structural.base.evaluation.Metric;
import view.modal.new_.ViewNew;
import view.panel.base.evaluation.PanelBaseMetric;
import view.panel.base.evaluation.PanelBaseOperation;
import view.main.structural.ViewMenu;
import view.panel.edit.base.evaluation.PanelEditMetric;

/**
 * <p>Class of View <b>ViewNewMetric</b>.</p>
 * <p>Class responsible for defining the <b>New Metric View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-08-20
 * @see    controller.view.modal.new_.base.evaluation.ControllerViewNewMetric
 * @see    model.structural.base.evaluation.Metric
 * @see    view.modal.new_.ViewNew
 */
public final class ViewNewMetric extends ViewNew {
    private final Metric metric;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewNewMetric(ViewMenu view) {
        super(view);
        metric     = new Metric();
        controller = new ControllerViewNewMetric(this);
        title      = "New Metric";
        initComponents();
    }
    
    @Override
    protected Dimension getViewDimension() {
        return new Dimension(600, 445);
    }
    
    @Override
    protected PanelEditMetric createPanelNew() {
        return new PanelEditMetric(view, metric);
    }
    
    @Override
    protected Dimension getPanelDimension() {
        return new Dimension(500, 325);
    }
    
    @Override
    protected PanelEditMetric getPanelNew() {
        return (PanelEditMetric) super.getPanelNew();
    }
    
    /**
     * Method responsible for returning the Panel Base Metric.
     * @return Panel Base Metric.
     */
    public PanelBaseMetric getPanelBaseMetric() {
        return getPanelNew().getPanelBaseMetric();
    }
    
    /**
     * Method responsible for returning the Panel Base Operation.
     * @return Panel Base Operation.
     */
    public PanelBaseOperation getPanelBaseOperation() {
        return getPanelNew().getPanelBaseOperation();
    }
    
    /**
     * Method responsible for returning the Metric.
     * @return Metric.
     */
    public Metric getMetric() {
        return metric;
    }
}