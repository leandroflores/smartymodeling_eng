package view.panel.edit.base.evaluation;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import model.structural.base.evaluation.Metric;
import view.edit.panel.base.evaluation.PanelBaseMetric;
import view.edit.panel.base.evaluation.PanelBaseOperation;
import view.panel.edit.PanelEdit;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditMetric</b>.</p> 
 * <p>Class responsible for defining a Panel for Edit the <b>Metric</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  20/08/2019
 * @see    model.structural.base.evaluation.Metric
 * @see    view.panel.edit.PanelEdit
 */
public final class PanelEditMetric extends PanelEdit {
    private final Project project;
    private final Metric  metric;
    private PanelBaseMetric panelBaseMetric;
    private PanelBaseOperation panelBaseOperation;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param metric Metric.
     */
    public PanelEditMetric(ViewMenu viewMenu, Metric metric) {
        super(viewMenu);
        this.project = this.viewMenu.getProject();
        this.metric  = metric;
        this.setPreferredSize(new Dimension(200, 100));
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(100, 100));
        
        this.addPanelBaseMetric();
        this.addPanelBaseOperation();
        
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Metric.
     */
    private void addPanelBaseMetric() {
        this.panelBaseMetric = new PanelBaseMetric(this.viewMenu, this.metric);
        this.createScrollPane("scrollPanelBaseMetric",  this.panelBaseMetric);
        this.getScrollPanelBaseMetric().setViewportView(this.panelBaseMetric);
        this.tabbedPane.add("Metric", this.getScrollPanelBaseMetric());
    }
    
    /**
     * Method responsible for adding the Panel Base Operation.
     */
    private void addPanelBaseOperation() {
        this.panelBaseOperation = new PanelBaseOperation(this.viewMenu, this.metric);
        this.createScrollPane("scrollPanelBaseOperation",  this.panelBaseOperation);
        this.getScrollPanelBaseOperation().setViewportView(this.panelBaseOperation);
        this.tabbedPane.add("Operation", this.getScrollPanelBaseOperation());
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
     * Method responsible for returning the Scroll Panel Base Metric.
     * @return Scroll Panel Base Metric.
     */
    public JScrollPane getScrollPanelBaseMetric() {
        return this.scrollPanes.get("scrollPanelBaseMetric");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Operation.
     * @return Scroll Panel Base Operation.
     */
    public JScrollPane getScrollPanelBaseOperation() {
        return this.scrollPanes.get("scrollPanelBaseOperation");
    }
}