package view.new_.evaluation;

import controller.view.new_.evaluation.ControllerViewNewMetric;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import model.structural.base.evaluation.Metric;
import view.edit.panel.base.evaluation.PanelBaseMetric;
import view.edit.panel.base.evaluation.PanelBaseOperation;
import view.new_.ViewNew;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewEditMetric</b>.</p>
 * <p>Class responsible for defining the <b>New Metric View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  20/08/2019
 * @see    controller.view.new_.evaluation.ControllerViewNewMetric
 * @see    model.structural.base.evaluation.Metric
 * @see    view.new_.ViewNew
 */
public final class ViewNewMetric extends ViewNew { 
    private final Project project;
    private final Metric  metric;
    private PanelBaseMetric panelBaseMetric;
    private PanelBaseOperation panelBaseOperation;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param project Project.
     */
    public ViewNewMetric(ViewMenu view, Project project) {
        super(view);
        this.project    = project;
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
        this.panelBaseMetric = new PanelBaseMetric(this.getViewMenu(), this.metric);
        this.createScrollPane("scrollPanelBaseMetric", this.panelBaseMetric);
        this.getScrollPanelBaseMetric().setViewportView(this.panelBaseMetric);
        this.tabbedPane.add("Metric", this.getScrollPanelBaseMetric());
    }
    
    /**
     * Method responsible for adding the Panel Base Operation.
     */
    private void addPanelBaseOperation() {
        this.panelBaseOperation  = new PanelBaseOperation(this.getViewMenu(), this.metric);
        this.createScrollPane("scrollPanelOperation",  this.panelBaseOperation);
        this.getScrollPanelOperation().setViewportView(this.panelBaseOperation);
        this.tabbedPane.add("Operation", this.getScrollPanelOperation());
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return this.project;
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
        return this.panelBaseMetric;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Metric.
     * @return Scroll Panel Base Metric.
     */
    public JScrollPane getScrollPanelBaseMetric() {
        return this.scrollPanes.get("scrollPanelBaseMetric");
    }
    
    /**
     * Method responsible for returning the Panel Base Operation.
     * @return Panel Base Operation.
     */
    public PanelBaseOperation getPanelBaseOperation() {
        return this.panelBaseOperation;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Operation.
     * @return Scroll Panel Operation.
     */
    public JScrollPane getScrollPanelOperation() {
        return this.scrollPanes.get("scrollPanelOperation");
    }
}