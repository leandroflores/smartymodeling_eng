package view.panel.tree.renderer;

import funct.FunctView;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.association.Association;
import model.structural.base.evaluation.Metric;
import model.structural.base.traceability.Traceability;
import model.structural.base.variability.Variability;
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.MethodUML;

/**
 * <p>Class of View <b>TreeRenderer</b>.</p>
 * <p>Class responsible for defining the <b>Tree Renderer</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  27/05/2019
 * @see    javax.swing.tree.DefaultTreeCellRenderer
 */
public class TreeRenderer extends DefaultTreeCellRenderer {
    protected JTree tree;
    
    /**
     * Default constructor method of Class.
     * @param tree Tree.
     */
    public TreeRenderer(JTree tree) {
        this.tree = tree;
    }
    
    /**
     * Method responsible for returning Image Icon.
     * @param  name Image Name.
     * @return Image Icon.
     */
    protected ImageIcon getImage(String name) {
        return new ImageIcon("src/images/icons/" + name + ".png");
    }
    
    /**
     * Method responsible for defining Project Icon.
     * @param project Project.
     */
    public void setProjectIcon(Project project) {
        this.setText(project.getName());
        this.setToolTipText(project.getName());
        this.setIcon(this.getImage("project"));
    }
    
    /**
     * Method responsible for defining Diagram Icon.
     * @param diagram Diagrama.
     */
    public void setDiagramIcon(Diagram diagram) {
        this.setText(diagram.getName());
        this.setToolTipText(diagram.getName());
        this.setIcon(this.getImage(diagram.getIcon()));
    }
    
    /**
     * Method responsible for defining Element Icon.
     * @param element Element.
     */
    public void setElementIcon(Element element) {
        this.setText(element.getName());
        this.setToolTipText(element.getName());
        this.setIcon(new ImageIcon(element.getIcon()));
    }
    
    /**
     * Method responsible for defining AttributeUML Icon.
     * @param attribute Attribute UML.
     */
    public void setAttributeIcon(AttributeUML attribute) {
        this.setText(attribute.getName());
        this.setToolTipText(attribute.getName());
        this.setIcon(new ImageIcon(attribute.getIcon()));
    }
    
    /**
     * Method responsible for defining MethodUML Icon.
     * @param method Method UML.
     */
    public void setMethodIcon(MethodUML method) {
        this.setText(method.getName());
        this.setToolTipText(method.getName());
        this.setIcon(new ImageIcon(method.getIcon()));
    }
    
    /**
     * Method responsible for defining Association.
     * @param association Association.
     */
    public void setAssociationIcon(Association association) {
        this.setText(association.toString());
        this.setToolTipText(association.toString());
        this.setIcon(this.getImage("association"));
    }
    
    /**
     * Method responsible for defining Variability.
     * @param variability Variability.
     */
    public void setVariabilityIcon(Variability variability) {
        this.setText(variability.getName());
        this.setToolTipText(variability.getName());
        this.setIcon(new ImageIcon(variability.getIcon()));
    }
    
    /**
     * Method responsible for defining Traceability.
     * @param traceability Traceability.
     */
    public void setTraceabilityIcon(Traceability traceability) {
        this.setText(traceability.getName());
        this.setToolTipText(traceability.getName());
        this.setIcon(new ImageIcon(traceability.getIcon()));
    }
    
    /**
     * Method responsible for defining Metric.
     * @param metric Metric.
     */
    public void setMetricIcon(Metric metric) {
        this.setText(metric.getName());
        this.setToolTipText(metric.getName());
        this.setIcon(new ImageIcon(metric.getIcon()));
    }
    
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        Component component = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        Object    object    = ((DefaultMutableTreeNode) value).getUserObject();
        if (object instanceof Project)
            this.setProjectIcon((Project) object);
        else if (object instanceof Diagram)
            this.setDiagramIcon((Diagram) object);
        else if (object instanceof Element)
            this.setElementIcon((Element) object);
        else if (object instanceof AttributeUML)
            this.setAttributeIcon((AttributeUML) object);
        else if (object instanceof MethodUML)
            this.setMethodIcon((MethodUML) object);
        else if (object instanceof Association)
            this.setAssociationIcon((Association) object);
        else if (object instanceof Variability)
            this.setVariabilityIcon((Variability) object);
        else if (object instanceof Traceability)
            this.setTraceabilityIcon((Traceability) object);
        else if (object instanceof Metric)
            this.setMetricIcon((Metric) object);
        return this;
    }
    
    /**
     * Method responsible for returning a new JLabel.
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
               label.addMouseListener(this.tree.getMouseListeners()[0]);
        return label;
    }
    
    /**
     * Method responsible for defining Diagram Tree.
     * @param tree Diagram Tree.
     */
    public void setTree(JTree tree) {
        this.tree = tree;
    }
}