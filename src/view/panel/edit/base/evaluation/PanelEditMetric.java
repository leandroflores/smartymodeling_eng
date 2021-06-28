package view.panel.edit.base.evaluation;

import javax.swing.JScrollPane;
import model.structural.base.evaluation.Metric;
import view.panel.base.evaluation.PanelBaseMetric;
import view.panel.base.evaluation.PanelBaseOperation;
import view.panel.edit.PanelEdit;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditMetric</b>.</p> 
 * <p>Class responsible for defining a <b>Metric Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-08-20
 * @see    model.structural.base.evaluation.Metric
 * @see    view.panel.edit.PanelEdit
 */
public final class PanelEditMetric extends PanelEdit {
    private final Metric metric;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param metric Metric.
     */
    public PanelEditMetric(ViewMenu view, Metric metric) {
        super(view);
        this.metric = metric;
        addComponents();
    }
    
    @Override
    protected void addPanels() {
        addPanelBaseMetric();
        addPanelBaseOperation();
    }
    
    /**
     * Method responsible for adding the Panel Base Metric.
     */
    protected void addPanelBaseMetric() {
        addPanel("base_metric", new PanelBaseMetric(viewMenu, metric));
        createScrollPane("base_metric", getPanelBaseMetric());
        getScrollPanelBaseMetric().setViewportView(getPanelBaseMetric());
        tabbedPane.add("Metric", getScrollPanelBaseMetric());
    }
    
    /**
     * Method responsible for adding the Panel Base Operation.
     */
    protected void addPanelBaseOperation() {
        addPanel("base_operation", new PanelBaseOperation(viewMenu, metric));
        createScrollPane("base_operation",  getPanelBaseOperation());
        getScrollPanelBaseOperation().setViewportView(getPanelBaseOperation());
        tabbedPane.add("Operation", getScrollPanelBaseOperation());
    }
    
    /**
     * Method responsible for returning the Panel Base Metric.
     * @return Panel Base Metric.
     */
    public PanelBaseMetric getPanelBaseMetric() {
        return (PanelBaseMetric) getPanel("base_metric");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Metric.
     * @return Scroll Panel Base Metric.
     */
    public JScrollPane getScrollPanelBaseMetric() {
        return getScrollPane("base_metric");
    }
    
    /**
     * Method responsible for returning the Panel Base Operation.
     * @return Panel Base Operation.
     */
    public PanelBaseOperation getPanelBaseOperation() {
        return (PanelBaseOperation) getPanel("base_operation");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Operation.
     * @return Scroll Panel Base Operation.
     */
    public JScrollPane getScrollPanelBaseOperation() {
        return getScrollPane("base_operation");
    }
    
    /**
     * Method responsible for returning the Metric.
     * @return Metric.
     */
    public Metric getMetric() {
        return metric;
    }
}