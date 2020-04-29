package view.panel.tree.renderer.base.evaluation;

import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.evaluation.Measure;
import model.structural.base.evaluation.Metric;
import view.panel.tree.renderer.TreeRenderer;

/**
 * <p>Class of View <b>TreeRendererEvaluation</b>.</p>
 * <p>Class responsible for defining the <b>Evaluation Tree Renderer</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-14
 * @see    view.panel.tree.renderer.TreeRenderer
 */
public class TreeRendererEvaluation extends TreeRenderer {
    
    /**
     * Default constructor method of Class.
     * @param tree Tree.
     */
    public TreeRendererEvaluation(JTree tree) {
        super(tree);
    }
    
    /**
     * Method responsible for setting the Metric.
     * @param metric Metric.
     */
    public void setMetricIcon(Metric metric) {
        this.setText(metric.getName());
        this.setToolTipText(metric.getName());
        this.setIcon(this.getImage(metric.getIcon()));
    }
    
    /**
     * Method responsible for setting the Measure.
     * @param measure Measure.
     */
    public void setMeasureIcon(Measure measure) {
        this.setText(measure.getName());
        this.setToolTipText(measure.getName());
        this.setIcon(this.getImage(measure.getIcon()));
    }
    
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        DefaultMutableTreeNode node   = (DefaultMutableTreeNode) value;
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
        Object object = node.getUserObject();
        if (object instanceof Metric)
            this.setMetricIcon((Metric) object);
        else if (object instanceof Measure)
            this.setMeasureIcon((Measure) object);
        return this;
    }
}