package view.panel.tree.renderer;

import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.requirement.Requirement;

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
        this.setText(requirement.getName());
        this.setToolTipText(requirement.getName());
        this.setIcon(this.getImage(requirement.getIcon()));
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
        DefaultMutableTreeNode node   = (DefaultMutableTreeNode) value;
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
        Object object = node.getUserObject();
        if (object instanceof Project)
            this.setProjectIcon((Project) object);
        else if (object instanceof Requirement)
            this.setRequirementIcon((Requirement) object);
        return this;
    }
}