package view.panel.tree;

import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.variability.Variability;
import view.panel.tree.renderer.TreeRendererVariability;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelTreeVariability</b>.</p>
 * <p>Class responsible for defining the <b>Variability Tree Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-14
 * @see    view.panel.tree.PanelTree
 * @see    view.panel.tree.renderer.TreeRendererVariability
 */
public final class PanelTreeVariability extends PanelTree {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public PanelTreeVariability(ViewMenu view) {
        super(view);
        this.addComponents();
    }
    
    @Override
    protected void addControllers() {
        super.addControllers();
        this.getTree().setCellRenderer(new TreeRendererVariability(this.getTree()));
    }
    
    @Override
    protected DefaultMutableTreeNode createNode(Project project) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(project);
               this.addDiagrams(node);
               super.addNode(project, node);
        return node;
    }
    
    /**
     * Method responsible for adding the UML Diagrams of the Project.
     * @param node Project Node.
     */
    protected void addDiagrams(DefaultMutableTreeNode node) {
        for (Diagram diagram : this.getProject().getVariabilityDiagramsList())
            node.add(this.createNode(diagram));
    }
    
    /**
     * Method responsible for returning a New Diagram Node.
     * @param  diagram Diagram.
     * @return New Diagram Node.
     */
    protected DefaultMutableTreeNode createNode(Diagram diagram) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(diagram);
               this.addVariabilities(diagram, node);
               super.addNode(diagram, node);
        return node;
    }
    
    /**
     * Method responsible for adding the Variability Nodes of the UML Diagram.
     * @param diagram UML Diagram.
     * @param node UML Diagram Node.
     */
    protected void addVariabilities(Diagram diagram, DefaultMutableTreeNode node) {
        for (Variability variability : diagram.getVariabilitiesList())
            node.add(this.createNode(variability));
    }
    
    /**
     * Method responsible for returning a New Variability Node.
     * @param  variability Variability.
     * @return New Variability Node.
     */
    protected DefaultMutableTreeNode createNode(Variability variability) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(variability);
               this.addVariationPoint(variability, node);
               this.addVariants(variability, node);
               super.addNode(variability, node);
        return node;
    }
    
    /**
     * Method responsible for adding the Variation Point Node of a Variability.
     * @param variability Variability.
     * @param node Variability Node.
     */
    private void addVariationPoint(Variability variability, DefaultMutableTreeNode node) {
        node.add(new DefaultMutableTreeNode(variability.getVariationPoint()));
    }
    
    /**
     * Method responsible for adding the Variant Nodes of a Variability.
     * @param variability Variability.
     * @param node Variability Node.
     */
    private void addVariants(Variability variability, DefaultMutableTreeNode node) {
        for (Element variant : variability.getVariants())
            node.add(new DefaultMutableTreeNode(variant));
    }
    
    /**
     * Method responsible for updating the Variability Node.
     * @param variability 
     */
    public void updateNode(Variability variability) {
        if (this.getNode(variability) != null) {
            DefaultMutableTreeNode node = this.getNode(variability);
                                   node.removeAllChildren();
            this.addVariationPoint(variability, node);
            this.addVariants(variability, node);
            this.getTreeModel().reload(node);
        }
    }
    
    /**
     * Method responsible for updating the Variability by Element Node.
     * @param element Element.
     */
    public void updateVariability(Element element) {
        if (this.getNode(element) != null) {
            DefaultMutableTreeNode node   = this.getNode(element);
            DefaultMutableTreeNode parent = this.getParentNode(node);
            if (parent != null && parent.getUserObject() != null) {
                if (parent.getUserObject() instanceof Diagram) {
                    this.updateVariationPoints((Diagram) parent.getUserObject(), element);
                    this.updateVariants((Diagram) parent.getUserObject(), element);
                }
            }
        }
    }
    
    /**
     * Method responsible for updating the Variation Points Nodes.
     * @param diagram Diagram.
     * @param element Element.
     */
    private void updateVariationPoints(Diagram diagram, Element element) {
        for (Variability variability : diagram.getVariationPoints(element))
            this.updateNode(variability);
    }
    
    /**
     * Method responsible for updating the Variants Nodes.
     * @param diagram Diagram.
     * @param element Element.
     */
    private void updateVariants(Diagram diagram, Element element) {
        for (Variability variability : diagram.filterVariants(element, ""))
            this.updateNode(variability);
    }
}