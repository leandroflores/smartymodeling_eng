package view.panel.tree.renderer.base.requirement;

import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Element;
import model.structural.base.requirement.Requirement;
import model.structural.base.traceability.Reference;
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
        setText(requirement.toString());
        setToolTipText(requirement.toString());
        setIcon(getImage(requirement.getIcon()));
    }
    
    /**
     * Method responsible for setting the Reference.
     * @param reference Reference.
     */
    public void setReferenceIcon(Reference reference) {
        setText(reference.getName());
        setToolTipText(reference.getName());
        setIcon(getImage(reference.getIcon()));
    }
    
    /**
     * Method responsible for setting the Element Icon by Node.
     * @param element Element.
     * @param parent Parent Node.
     */
    public void setElementIcon(Element element, DefaultMutableTreeNode parent) {
        setText(element.getName());
        setToolTipText(element.getName());
        setIcon(getImage(element.getIcon()));
    }
    
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        Object object = ((DefaultMutableTreeNode) value).getUserObject();
        if (object instanceof Requirement)
            setRequirementIcon((Requirement) object);
        else if (object instanceof Reference)
            setReferenceIcon((Reference) object);
        return this;
    }
}