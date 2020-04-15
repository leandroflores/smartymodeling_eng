package view.panel.tree;

import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.variability.Variability;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.MethodUML;
import model.structural.diagram.classes.base.PackageUML;
import view.panel.tree.renderer.TreeRendererDiagram;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelTreeDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Diagram Tree Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-14
 * @see    view.panel.tree.PanelTree
 * @see    view.panel.tree.renderer.TreeRendererDiagram
 */
public final class PanelTreeDiagram extends PanelTree {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public PanelTreeDiagram(ViewMenu view) {
        super(view);
        this.addComponents();
    }
    
    @Override
    protected void addControllers() {
        super.addControllers();
        this.getTree().setCellRenderer(new TreeRendererDiagram(this.getTree()));
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
        for (Diagram diagram : this.getProject().getUMLDiagramsList())
            node.add(this.createNode(diagram));
    }
    
    /**
     * Method responsible for returning a New Diagram Node.
     * @param  diagram Diagram.
     * @return New Diagram Node.
     */
    protected DefaultMutableTreeNode createNode(Diagram diagram) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(diagram);
               this.addElements(diagram, node);
               this.addVariabilities(diagram, node);
               super.addNode(diagram, node);
        return node;
    }
    
    /**
     * Method responsible for checking if the Element is a Leaf Node.
     * @param  element Element.
     * @return Element is a Leaf Node.
     */
    protected boolean isLeaf(Element element) {
        return (element instanceof AttributeUML
             || element instanceof MethodUML);
    }
    
    /**
     * Method responsible for adding the Element Nodes of the UML Diagram.
     * @param diagram UML Diagram.
     * @param node Diagram Node.
     */
    protected void addElements(Diagram diagram, DefaultMutableTreeNode node) {
        for (Element  element : diagram.getTreeElementsList()) {
            DefaultMutableTreeNode new_ = null;
            if (element instanceof PackageUML)
                new_ = this.createNode((PackageUML) element);
            else if (element instanceof Entity)
                new_ = this.createNode((Entity) element);
            else if (!this.isLeaf(element))
                new_ = this.createNode(element);
            super.addElement(element, new_, node);
        }
    }
    
    /**
     * Method responsible for returning a New Package Node.
     * @param  package_ Package UML.
     * @return New Package Node.
     */
    protected DefaultMutableTreeNode createNode(PackageUML package_) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(package_);
            this.addEntities(package_, node);
            this.addPackages(package_, node);
        return node;
    }
    
    /**
     * Method responsible for adding the Entity Nodes of the Package UML.
     * @param package_ Package UML.
     * @param node Package Node.
     */
    protected void addEntities(PackageUML package_, DefaultMutableTreeNode node) {
        for (Entity entity : package_.getEntitiesList())
            node.add(this.createNode(entity));
    }
    
    /**
     * Method responsible for adding the Package Nodes of the Package UML.
     * @param package_ Package UML.
     * @param node Package Node.
     */
    protected void addPackages(PackageUML package_, DefaultMutableTreeNode node) {
        for (PackageUML current : package_.getPackagesList())
            node.add(this.createNode(current));
    }
    
    /**
     * Method responsible for returning a New Entity Node.
     * @param  entity Entity.
     * @return New Entity Node.
     */
    protected DefaultMutableTreeNode createNode(Entity entity) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(entity);
            this.addAttributes(entity, node);
            this.addMethods(entity, node);
        return node;
    }
    
    /**
     * Method responsible for adding the Attribute Nodes of a Entity.
     * @param entity Entity.
     * @param node Entity Node.
     */
    protected void addAttributes(Entity entity, DefaultMutableTreeNode node) {
        for (AttributeUML attribute : entity.getAttributesList()) {
            DefaultMutableTreeNode new_ = new DefaultMutableTreeNode(attribute);
            node.add(new_);
            super.addNode(attribute, new_);
        }
    }
    
    /**
     * Method responsible for adding the Method Nodes of a Entity.
     * @param entity Entity.
     * @param node Entity Node.
     */
    protected void addMethods(Entity entity, DefaultMutableTreeNode node) {
        for (MethodUML method : entity.getMethodsList()) {
            DefaultMutableTreeNode new_ = new DefaultMutableTreeNode(method);
            node.add(new_);
            super.addNode(method, new_);
        }
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
        DefaultMutableTreeNode node   = this.getNode(element);
        DefaultMutableTreeNode parent = this.getParentNode(node);
        if (parent != null && parent.getUserObject() != null) {
            if (parent.getUserObject() instanceof Diagram) {
                this.updateVariationPoints((Diagram) parent.getUserObject(), element);
                this.updateVariants((Diagram) parent.getUserObject(), element);
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