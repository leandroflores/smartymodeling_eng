package view.panel.tree.renderer.base.requirement;

import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Element;
import model.structural.base.requirement.Requirement;
import model.structural.base.traceability.Traceability;
import view.panel.tree.renderer.TreeRenderer;

/**
 * <p>Class of View <b>TreeRendererRequirement</b>.</p>
 * <p>Class responsible for defining the <b>Requirement Tree Renderer</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-15
 * @see    view.panel.tree.renderer.TreeRenderer
 */
public class TreeRendererRequirement extends TreeRenderer {
    
    /**
     * Default constructor method of Class.
     * @param tree Tree.
     */
    public TreeRendererRequirement(JTree tree) {
        super(tree);
    }
    
    /**
     * Method responsible for setting the Requirement.
     * @param requirement Requirement.
     */
    public void setRequirementIcon(Requirement requirement) {
        this.setText(requirement.toString());
        this.setToolTipText(requirement.toString());
        this.setIcon(this.getImage(requirement.getIcon()));
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
    
    /**
     * Method responsible for setting the Element Icon by Node.
     * @param element Element.
     * @param parent Parent Node.
     */
    public void setElementIcon(Element element, DefaultMutableTreeNode parent) {
        this.setText(element.getName());
        this.setToolTipText(element.getName());
        this.setIcon(this.getImage(element.getIcon()));
    }
    
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        Object object = ((DefaultMutableTreeNode) value).getUserObject();
        if (object instanceof Requirement)
            this.setRequirementIcon((Requirement) object);
        else if (object instanceof Traceability)
            this.setTraceabilityIcon((Traceability) object);
        return this;
    }
}