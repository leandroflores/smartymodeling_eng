package view.panel.edit.base.evaluation;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.evaluation.Metric;
import view.panel.base.evaluation.PanelBaseMetric;
import view.panel.base.evaluation.PanelBaseOperation;
import view.panel.edit.PanelEdit;
import view.structural.ViewMenu;

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
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
            this.addPanelBaseMetric();
            this.addPanelBaseOperation();
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Metric.
     */
    protected void addPanelBaseMetric() {
        this.addPanel("panelBaseMetric", new PanelBaseMetric(this.viewMenu, this.metric));
        this.createScrollPane("scrollPanelBaseMetric",  this.getPanelBaseMetric());
        this.getScrollPanelBaseMetric().setViewportView(this.getPanelBaseMetric());
        this.tabbedPane.add("Metric", this.getScrollPanelBaseMetric());
    }
    
    /**
     * Method responsible for adding the Panel Base Operation.
     */
    protected void addPanelBaseOperation() {
        this.addPanel("panelBaseOperation", new PanelBaseOperation(this.viewMenu, this.metric));
        this.createScrollPane("scrollPanelBaseOperation",  this.getPanelBaseOperation());
        this.getScrollPanelBaseOperation().setViewportView(this.getPanelBaseOperation());
        this.tabbedPane.add("Operation", this.getScrollPanelBaseOperation());
    }
    
    /**
     * Method responsible for returning the Metric.
     * @return Metric.
     */
    public Metric getMetric() {
        return this.metric;
    }
    
    /**
     * Method responsible for returning the Panel Base Metric.
     * @return Panel Base Metric.
     */
    public PanelBaseMetric getPanelBaseMetric() {
        return (PanelBaseMetric) this.getPanel("panelBaseMetric");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Metric.
     * @return Scroll Panel Base Metric.
     */
    public JScrollPane getScrollPanelBaseMetric() {
        return this.getScrollPane("scrollPanelBaseMetric");
    }
    
    /**
     * Method responsible for returning the Panel Base Operation.
     * @return Panel Base Operation.
     */
    public PanelBaseOperation getPanelBaseOperation() {
        return (PanelBaseOperation) this.getPanel("panelBaseOperation");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Operation.
     * @return Scroll Panel Base Operation.
     */
    public JScrollPane getScrollPanelBaseOperation() {
        return this.getScrollPane("scrollPanelBaseOperation");
    }
}