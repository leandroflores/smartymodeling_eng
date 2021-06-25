package view.panel.tree.renderer;

import funct.FunctView;
import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;

/**
 * <p>Class of View <b>TreeRenderer</b>.</p>
 * <p>Class responsible for defining the <b>Tree Renderer</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-13
 * @see    javax.swing.JTree
 * @see    javax.swing.tree.DefaultTreeCellRenderer
 */
public abstract class TreeRenderer extends DefaultTreeCellRenderer {
    protected JTree tree;
    
    /**
     * Default constructor method of Class.
     * @param tree Tree.
     */
    public TreeRenderer(JTree tree) {
        this.tree = tree;
    }
    
    /**
     * Method responsible for returning the Image Icon.
     * @param  path Image Path.
     * @return Image Icon.
     */
    protected ImageIcon getImage(String path) {
        return new FunctView().createImage(path);
    }
    
    /**
     * Method responsible for setting the Project Icon.
     * @param project Project.
     */
    public void setProjectIcon(Project project) {
        setText("<html><b>" + project.getName() + "</b></html>");
        setToolTipText(project.getName());
        setIcon(getImage(project.getIcon()));
        setForeground(new Color(13, 57 ,115));
    }
    
    /**
     * Method responsible for setting the Diagram Icon.
     * @param diagram Diagrama.
     */
    public void setDiagramIcon(Diagram diagram) {
        setText(diagram.getName());
        setToolTipText(diagram.getName());
        setIcon(getImage(diagram.getIcon()));
    }
    
    /**
     * Method responsible for setting the Element Icon.
     * @param element Element.
     */
    public void setElementIcon(Element element) {
        setText(element.getName());
        setToolTipText(element.getName());
        setIcon(getImage(element.getIcon()));
    }
    
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        DefaultMutableTreeNode node   = (DefaultMutableTreeNode) value;
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
        Object object = node.getUserObject();
        if (object instanceof Project)
            setProjectIcon((Project) object);
        else if (object instanceof Diagram)
            setDiagramIcon((Diagram) object);
        else if (object instanceof Element)
            setElementIcon((Element) object);
        return this;
    }
    
    /**
     * Method responsible for returning a New JLabel.
     * @param  title JLabel Title.
     * @param  url JLabel Image URL.
     * @return New JLabel.
     */
    protected JLabel createLabel(String title, String url) {
        JLabel label = new JLabel();
               label.setText(title);
               label.setIcon(new FunctView().createImage("icons/" + url));
               label.setToolTipText(title);
               label.setOpaque(true);
               label.addMouseListener(tree.getMouseListeners()[0]);
        return label;
    }
    
    /**
     * Method responsible for setting the Project Tree.
     * @param tree Project Tree.
     */
    public void setTree(JTree tree) {
        this.tree = tree;
    }
}