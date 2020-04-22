package view.modal.edit.base.evaluation;

import controller.view.modal.edit.base.evaluation.ControllerViewEditMetric;
import java.awt.Dimension;
import javax.swing.JTabbedPane;
import model.structural.base.evaluation.Metric;
import view.modal.edit.ViewEdit;
import view.panel.base.evaluation.PanelBaseMetric;
import view.panel.base.evaluation.PanelBaseOperation;
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
        this.setSize(new Dimension(650, 350));
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(550, 225));
            this.addPanelBaseMetric();
        this.add(this.tabbedPane);
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Base Metric.
     */
    private void addPanelBaseMetric() {
        this.addPanel("panelBaseMetric", new PanelBaseMetric(this.getViewMenu(), this.metric));
        this.createScrollPane("scrollPanelBaseMetric",  this.getPanelBaseMetric());
        this.getScrollPane("scrollPanelBaseMetric").setViewportView(this.getPanelBaseMetric());
        this.tabbedPane.add("Metric", this.getScrollPane("scrollPanelBaseMetric"));
    }
    
    /**
     * Method responsible for adding the Panel Base Operation.
     */
    private void addPanelBaseOperation() {
        this.addPanel("panelBaseOperation", new PanelBaseOperation(this.getViewMenu(), this.metric));
        this.createScrollPane("scrollPanelBaseOperation",  this.getPanelBaseOperation());
        this.getScrollPane("scrollPanelBaseOperation").setViewportView(this.getPanelBaseOperation());
        this.tabbedPane.add("Operation", this.getScrollPane("scrollPanelBaseOperation"));
    }
    
    /**
     * Method responsible for returning the Panel Base Metric.
     * @return Panel Base Metric.
     */
    public PanelBaseMetric getPanelBaseMetric() {
        return (PanelBaseMetric) this.getPanel("panelBaseMetric");
    }
    
    /**
     * Method responsible for returning the Panel Base Operation.
     * @return Panel Base Operation.
     */
    public PanelBaseOperation getPanelBaseOperation() {
        return (PanelBaseOperation) this.getPanel("panelBaseOperation");
    }
    
    /**
     * Method responsible for returning the Metric.
     * @return Metric.
     */
    public Metric getMetric() {
        return this.metric;
    }
}