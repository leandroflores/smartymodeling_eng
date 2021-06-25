package view.panel.tree.renderer.diagram;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Element;
import model.structural.base.variability.Variability;
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.MethodUML;
import view.panel.tree.renderer.TreeRenderer;

/**
 * <p>Class of View <b>TreeRendererDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Diagram Tree Renderer</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-14
 * @see    view.panel.tree.renderer.TreeRenderer
 */
public class TreeRendererDiagram extends TreeRenderer {
    
    /**
     * Default constructor method of Class.
     * @param tree Tree.
     */
    public TreeRendererDiagram(JTree tree) {
        super(tree);
    }
    
    /**
     * Method responsible for setting the Element Icon by Node.
     * @param element Element.
     * @param parent Parent Node.
     */
    public void setElementIcon(Element element, DefaultMutableTreeNode parent) {
        setText(element.getName());
        setToolTipText(element.getName());
        setIcon(getImage(getIconPath(element, parent.getUserObject())));
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
     * Method responsible for setting the Attribute UML Icon.
     * @param attribute Attribute UML.
     */
    public void setAttributeIcon(AttributeUML attribute) {
        setText(attribute.getName());
        setToolTipText(attribute.getName());
        setIcon(getImage(attribute.getIcon()));
    }
    
    /**
     * Method responsible for setting the Method UML Icon.
     * @param method Method UML.
     */
    public void setMethodIcon(MethodUML method) {
        setText(method.getName());
        setToolTipText(method.getName());
        setIcon(getImage(method.getIcon()));
    }
    
    /**
     * Method responsible for setting the Variability.
     * @param variability Variability.
     */
    public void setVariabilityIcon(Variability variability) {
        setText("<html><b>" + variability.getName() + "</b></html>");
        setToolTipText(variability.getName());
        setIcon(getImage(variability.getIcon()));
        setForeground(new Color(13, 57 ,115));
    }
    
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        DefaultMutableTreeNode node   = (DefaultMutableTreeNode) value;
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
        Object object = node.getUserObject();
        if (object instanceof AttributeUML)
            setAttributeIcon((AttributeUML) object);
        else if (object instanceof MethodUML)
            setMethodIcon((MethodUML) object);
        else if (object instanceof Variability)
            setVariabilityIcon((Variability) object);
        return this;
    }
}