package view.panel.tree.base.evaluation;

import controller.view.panel.tree.popup.base.evalution.ControllerTreePopupEvaluation;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Project;
import model.structural.base.evaluation.Measure;
import model.structural.base.evaluation.Metric;
import view.panel.tree.base.PanelTree;
import view.panel.tree.popup.base.evaluation.TreePopupEvaluation;
import view.panel.tree.renderer.base.evaluation.TreeRendererEvaluation;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelTreeEvaluation</b>.</p>
 * <p>Class responsible for defining the <b>Evaluation Tree Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-14
 * @see    view.panel.tree.base.PanelTree
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
        this.addComponents();
    }
    
    @Override
    protected void initTreeRenderer() {
        this.getTree().setCellRenderer(new TreeRendererEvaluation(this.getTree()));
    }
    
    @Override
    protected void initTreePopup() {
        this.popup = new TreePopupEvaluation(this);
    }
    
    @Override
    protected void setControllers() {
        this.tree.addMouseListener(new ControllerTreePopupEvaluation(this.getPopup()));
        this.tree.addKeyListener(new ControllerTreePopupEvaluation(this.getPopup()));
    }
    
    @Override
    protected DefaultMutableTreeNode createNode(Project project) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(project);
               this.addMetrics(node);
               super.addNode(project, node);
        return node;
    }
    
    /**
     * Method responsible for adding the Metrics of the Project.
     * @param node Project Node.
     */
    protected void addMetrics(DefaultMutableTreeNode node) {
        for (Metric metric : this.getProject().getMetricsList())
            node.add(this.createNode(metric));
    }
    
    /**
     * Method responsible for returning a New Metric Node.
     * @param  metric Metric.
     * @return New Metric Node.
     */
    protected DefaultMutableTreeNode createNode(Metric metric) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(metric);
               this.addMeasures(metric, node);
               super.addNode(metric, node);
        return node;
    }
    
    /**
     * Method responsible for adding the Measure Nodes of a Metric.
     * @param metric Metric.
     * @param node Metric Node.
     */
    protected void addMeasures(Metric metric, DefaultMutableTreeNode node) {
        for (Measure measure : this.getProject().getMeasuresByMetric(metric))
            node.add(this.createNode(measure));
    }
    
    /**
     * Method responsible for returning a New Measure Node.
     * @param  measure Measure.
     * @return New Measure Node.
     */
    protected DefaultMutableTreeNode createNode(Measure measure) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(measure);
            super.addNode(measure, node);
        return node;
    }
    
    @Override
    public TreePopupEvaluation getPopup() {
        return (TreePopupEvaluation) this.popup;
    }
}