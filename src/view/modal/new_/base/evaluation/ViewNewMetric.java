package view.modal.new_.base.evaluation;

import controller.view.modal.new_.base.evaluation.ControllerViewNewMetric;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.evaluation.Metric;
import view.modal.new_.ViewNew;
import view.panel.base.evaluation.PanelBaseMetric;
import view.panel.base.evaluation.PanelBaseOperation;
import view.structural.ViewMenu;

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
        this.metric     = new Metric();
        this.controller = new ControllerViewNewMetric(this);
        this.title      = "New Metric";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(600, 445);
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(550, 325));
            this.addPanelBaseMetric();
            this.addPanelBaseOperation();
        this.add(this.tabbedPane);
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Base Metric.
     */
    private void addPanelBaseMetric() {
        this.addPanel("panelBaseMetric", new PanelBaseMetric(this.getViewMenu(), this.metric));
        this.createScrollPane("scrollPanelBaseMetric",  this.getPanelBaseMetric());
        this.getScrollPanelBaseMetric().setViewportView(this.getPanelBaseMetric());
        this.tabbedPane.add("Metric", this.getScrollPanelBaseMetric());
    }
    
    /**
     * Method responsible for adding the Panel Base Operation.
     */
    private void addPanelBaseOperation() {
        this.addPanel("panelBaseOperation", new PanelBaseOperation(this.getViewMenu(), this.metric));
        this.createScrollPane("scrollPanelBaseOperation",  this.getPanelBaseOperation());
        this.getScrollPanelBaseOperation().setViewportView(this.getPanelBaseOperation());
        this.tabbedPane.add("Operation", this.getScrollPanelBaseOperation());
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
    
    /**
     * Method responsible for returning the Metric.
     * @return Metric.
     */
    public Metric getMetric() {
        return this.metric;
    }
}