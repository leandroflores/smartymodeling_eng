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
import model.structural.base.product.Artifact;
import model.structural.base.product.Instance;
import model.structural.base.product.Product;
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
     * Method responsible for returning the Image Icon.
     * @param  name Image Name.
     * @return Image Icon.
     */
    protected ImageIcon getImage(String name) {
        return new ImageIcon("src/images/icons/" + name + ".png");
    }
    
    /**
     * Method responsible for setting the Project Icon.
     * @param project Project.
     */
    public void setProjectIcon(Project project) {
        this.setText(project.getName());
        this.setToolTipText(project.getName());
        this.setIcon(this.getImage("project"));
    }
    
    /**
     * Method responsible for setting the Diagram Icon.
     * @param diagram Diagrama.
     */
    public void setDiagramIcon(Diagram diagram) {
        this.setText(diagram.getName());
        this.setToolTipText(diagram.getName());
        this.setIcon(this.getImage(diagram.getIcon()));
    }
    
    /**
     * Method responsible for setting the Element Icon.
     * @param element Element.
     */
    public void setElementIcon(Element element) {
        this.setText(element.getName());
        this.setToolTipText(element.getName());
        this.setIcon(new ImageIcon(element.getIcon()));
    }
    
    /**
     * Method responsible for setting the Attribute UML Icon.
     * @param attribute Attribute UML.
     */
    public void setAttributeIcon(AttributeUML attribute) {
        this.setText(attribute.getName());
        this.setToolTipText(attribute.getName());
        this.setIcon(new ImageIcon(attribute.getIcon()));
    }
    
    /**
     * Method responsible for setting the Method UML Icon.
     * @param method Method UML.
     */
    public void setMethodIcon(MethodUML method) {
        this.setText(method.getName());
        this.setToolTipText(method.getName());
        this.setIcon(new ImageIcon(method.getIcon()));
    }
    
    /**
     * Method responsible for setting the Association.
     * @param association Association.
     */
    public void setAssociationIcon(Association association) {
        this.setText(association.toString());
        this.setToolTipText(association.toString());
        this.setIcon(this.getImage("association"));
    }
    
    /**
     * Method responsible for setting the Variability.
     * @param variability Variability.
     */
    public void setVariabilityIcon(Variability variability) {
        this.setText(variability.getName());
        this.setToolTipText(variability.getName());
        this.setIcon(new ImageIcon(variability.getIcon()));
    }
    
    /**
     * Method responsible for setting the Traceability.
     * @param traceability Traceability.
     */
    public void setTraceabilityIcon(Traceability traceability) {
        this.setText(traceability.getName());
        this.setToolTipText(traceability.getName());
        this.setIcon(new ImageIcon(traceability.getIcon()));
    }
    
    /**
     * Method responsible for setting the Metric.
     * @param metric Metric.
     */
    public void setMetricIcon(Metric metric) {
        this.setText(metric.getName());
        this.setToolTipText(metric.getName());
        this.setIcon(new ImageIcon(metric.getIcon()));
    }
    
    /**
     * Method responsible for setting the Product.
     * @param product Product.
     */
    public void setProductIcon(Product product) {
        this.setText(product.getName());
        this.setToolTipText(product.getName());
        this.setIcon(new ImageIcon(product.getIcon()));
    }
    
    /**
     * Method responsible for setting the Instance.
     * @param instance Instance.
     */
    public void setInstanceIcon(Instance instance) {
        this.setText(instance.getName());
        this.setToolTipText(instance.getName());
        this.setIcon(new ImageIcon(instance.getIcon()));
    }
    
    /**
     * Method responsible for setting the Artifact.
     * @param artifact Artifact.
     */
    public void setArtifactIcon(Artifact artifact) {
        this.setText(artifact.getElement().getName());
        this.setToolTipText(artifact.getElement().getName());
        this.setIcon(new ImageIcon(artifact.getIcon()));
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
        else if (object instanceof Product)
            this.setProductIcon((Product) object);
        else if (object instanceof Instance)
            this.setInstanceIcon((Instance) object);
        else if (object instanceof Artifact)
            this.setArtifactIcon((Artifact) object);
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
     * Method responsible for setting the Diagram Tree.
     * @param tree Diagram Tree.
     */
    public void setTree(JTree tree) {
        this.tree = tree;
    }
}