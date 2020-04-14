package view.panel.tree.renderer;

import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.traceability.Traceability;
import model.structural.base.variability.Variability;

/**
 * <p>Class of View <b>TreeRendererTraceability</b>.</p>
 * <p>Class responsible for defining the <b>Traceability Tree Renderer</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-14
 * @see    view.panel.tree.renderer.TreeRenderer
 */
public class TreeRendererTraceability extends TreeRenderer {
    
    /**
     * Default constructor method of Class.
     * @param tree Tree.
     */
    public TreeRendererTraceability(JTree tree) {
        super(tree);
    }
    
    /**
     * Method responsible for setting the Element Icon by Node.
     * @param element Element.
     * @param parent Parent Node.
     */
    public void setElementIcon(Element element, DefaultMutableTreeNode parent) {
        this.setText(element.getName());
        this.setToolTipText(element.getName());
        this.setIcon(this.getImage(this.getIconPath(element, parent.getUserObject())));
    }
    
    /**
     * Method responsible for returning the Icon Path.
     * @param  element Element.
     * @param  object Parent Object.
     * @return Icon Path.
     */
    private String getIconPath(Element element, Object object) {
        if (object != null && object instanceof Variability)
            return ((Variability) object).getIcon(element);
        return element.getIcon();
    }
    
    /**
     * Method responsible for setting the Traceability.
     * @param traceability Traceability.
     */
    public void setTraceabilityIcon(Traceability traceability) {
        this.setText(traceability.getName());
        this.setToolTipText(traceability.getName());
        this.setIcon(this.getImage(traceability.getIcon()));
    }
    
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        DefaultMutableTreeNode node   = (DefaultMutableTreeNode) value;
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
        Object object = node.getUserObject();
        if (object instanceof Project)
            this.setProjectIcon((Project) object);
        else if (object instanceof Traceability)
            this.setTraceabilityIcon((Traceability) object);
        return this;
    }
}