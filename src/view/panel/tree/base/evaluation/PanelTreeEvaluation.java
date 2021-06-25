package view.panel.tree.base.evaluation;

import controller.view.panel.tree.popup.base.evalution.ControllerTreePopupEvaluation;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Project;
import model.structural.base.evaluation.Measure;
import model.structural.base.evaluation.Metric;
import view.panel.tree.PanelTree;
import view.panel.tree.popup.base.evaluation.TreePopupEvaluation;
import view.panel.tree.renderer.base.evaluation.TreeRendererEvaluation;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelTreeEvaluation</b>.</p>
 * <p>Class responsible for defining the <b>Evaluation Tree Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-14
 * @see    view.panel.tree.PanelTree
 * @see    view.panel.tree.popup.base.evaluation.TreePopupEvaluation
 * @see    view.panel.tree.renderer.base.evaluation.TreeRendererEvaluation
 */
public final class PanelTreeEvaluation extends PanelTree {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public PanelTreeEvaluation(ViewMenu view) {
        super(view);
        addComponents();
    }
    
    @Override
    protected void initTreeRenderer() {
        getTree().setCellRenderer(new TreeRendererEvaluation(getTree()));
    }
    
    @Override
    protected void initTreePopup() {
        popup = new TreePopupEvaluation(this);
    }
    
    @Override
    protected void setControllers() {
        tree.addMouseListener(new ControllerTreePopupEvaluation(getPopup()));
        tree.addKeyListener(new ControllerTreePopupEvaluation(getPopup()));
    }
    
    @Override
    protected DefaultMutableTreeNode createNode(Project project) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(project);
               addMetrics(node);
               addNode(project, node);
        return node;
    }
    
    /**
     * Method responsible for adding the Metrics of the Project.
     * @param node Project Node.
     */
    protected void addMetrics(DefaultMutableTreeNode node) {
        for (Metric metric : getProject().getMetricsList())
            node.add(createNode(metric));
    }
    
    /**
     * Method responsible for returning a New Metric Node.
     * @param  metric Metric.
     * @return New Metric Node.
     */
    protected DefaultMutableTreeNode createNode(Metric metric) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(metric);
               addMeasures(metric, node);
               addNode(metric, node);
        return node;
    }
    
    /**
     * Method responsible for adding the Measure Nodes of a Metric.
     * @param metric Metric.
     * @param node Metric Node.
     */
    protected void addMeasures(Metric metric, DefaultMutableTreeNode node) {
        for (Measure measure : getProject().getMeasuresByMetric(metric))
            node.add(createNode(measure));
    }
    
    /**
     * Method responsible for returning a New Measure Node.
     * @param  measure Measure.
     * @return New Measure Node.
     */
    protected DefaultMutableTreeNode createNode(Measure measure) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(measure);
               addNode(measure, node);
        return node;
    }
    
    /**
     * Method responsible for updating the Metric Node.
     * @param metric Metric. 
     */
    public void updateNode(Metric metric) {
        if (getNode(metric) != null)
            getTreeModel().reload(createNode(metric));
    }
    
    /**
     * Method responsible for updating the Measure Node.
     * @param measure Measure. 
     */
    public void updateNode(Measure measure) {
        if (getNode(measure) != null)
            getTreeModel().reload(getNode(measure));
    }
    
    @Override
    public TreePopupEvaluation getPopup() {
        return (TreePopupEvaluation) popup;
    }
}