package view.panel.tree.renderer.base.product;

import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.product.Artifact;
import model.structural.base.product.Instance;
import model.structural.base.product.Product;
import view.panel.tree.renderer.TreeRenderer;

/**
 * <p>Class of View <b>TreeRendererProduct</b>.</p>
 * <p>Class responsible for defining the <b>Product Tree Renderer</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-14
 * @see    view.panel.tree.renderer.TreeRenderer
 */
public class TreeRendererProduct extends TreeRenderer {
    
    /**
     * Default constructor method of Class.
     * @param tree Tree.
     */
    public TreeRendererProduct(JTree tree) {
        super(tree);
    }
    
    /**
     * Method responsible for setting the Product.
     * @param product Product.
     */
    public void setProductIcon(Product product) {
        setText(product.getName());
        setToolTipText(product.getName());
        setIcon(getImage(product.getIcon()));
    }
    
    /**
     * Method responsible for setting the Instance.
     * @param instance Instance.
     */
    public void setInstanceIcon(Instance instance) {
        setText(instance.getName());
        setToolTipText(instance.getName());
        setIcon(getImage(instance.getIcon()));
    }
    
    /**
     * Method responsible for setting the Artifact.
     * @param artifact Artifact.
     */
    public void setArtifactIcon(Artifact artifact) {
        setText(artifact.getElement().getName());
        setToolTipText(artifact.getElement().getName());
        setIcon(getImage(artifact.getIcon()));
    }
    
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        DefaultMutableTreeNode node   = (DefaultMutableTreeNode) value;
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
        Object object = node.getUserObject();
        if (object instanceof Product)
            setProductIcon((Product) object);
        else if (object instanceof Instance)
            setInstanceIcon((Instance) object);
        else if (object instanceof Artifact)
            setArtifactIcon((Artifact) object);
        return this;
    }
}