package view.panel.tree.renderer;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Element;
import model.structural.base.variability.Variability;
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.MethodUML;

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
     * Method responsible for setting the Attribute UML Icon.
     * @param attribute Attribute UML.
     */
    public void setAttributeIcon(AttributeUML attribute) {
        this.setText(attribute.getName());
        this.setToolTipText(attribute.getName());
        this.setIcon(this.getImage(attribute.getIcon()));
    }
    
    /**
     * Method responsible for setting the Method UML Icon.
     * @param method Method UML.
     */
    public void setMethodIcon(MethodUML method) {
        this.setText(method.getName());
        this.setToolTipText(method.getName());
        this.setIcon(this.getImage(method.getIcon()));
    }
    
    /**
     * Method responsible for setting the Variability.
     * @param variability Variability.
     */
    public void setVariabilityIcon(Variability variability) {
        this.setText("<html><b>" + variability.getName() + "</b></html>");
        this.setToolTipText(variability.getName());
        this.setIcon(this.getImage(variability.getIcon()));
        this.setForeground(new Color(13, 57 ,115));
    }
    
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        DefaultMutableTreeNode node   = (DefaultMutableTreeNode) value;
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
        Object object = node.getUserObject();
        if (object instanceof Element)
            this.setElementIcon((Element) object, parent);
        else if (object instanceof AttributeUML)
            this.setAttributeIcon((AttributeUML) object);
        else if (object instanceof MethodUML)
            this.setMethodIcon((MethodUML) object);
        else if (object instanceof Variability)
            this.setVariabilityIcon((Variability) object);
        return this;
    }
}