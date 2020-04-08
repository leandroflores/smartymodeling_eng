package view.panel.tree;

import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.evaluation.Metric;
import model.structural.base.product.Artifact;
import model.structural.base.product.Instance;
import model.structural.base.product.Product;
import model.structural.base.traceability.Traceability;
import model.structural.base.variability.Variability;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.MethodUML;
import model.structural.diagram.classes.base.PackageUML;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelTreeUML</b>.</p>
 * <p>Class responsible for defining the <b>Tree Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  27/05/2019
 * @see    model.structural.base.Project
 * @see    view.panel.project.tree.PanelProjectTree
 */
public final class PanelTreeUML extends PanelTree {
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     */
    public PanelTreeUML(ViewMenu viewMenu) {
        super(viewMenu);
        this.addComponents();
    }
    
    @Override
    protected DefaultMutableTreeNode getProjectNode() {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(this.project);
               this.addUMLDiagrams(node);
               this.addTraceabilities(node);
               this.addMetrics(node);
               this.addProducts(node);
               this.nodes.put(this.project, node);
        return node;
    }
    
    /**
     * Method responsible for adding the UML Diagrams.
     * @param root Tree Node.
     */
    private void addUMLDiagrams(DefaultMutableTreeNode root) {
        if (this.project != null) {
            for (Diagram diagram : this.project.getUMLDiagramsList())
                root.add(this.getNode(diagram));
        }
    }
    
    /**
     * Method responsible for adding Traceabilities.
     * @param root Tree Node.
     */
    private void addTraceabilities(DefaultMutableTreeNode root) {
        if ((this.project != null)  && (!this.project.getTraceabilitiesList().isEmpty())) {
            DefaultMutableTreeNode folder = new DefaultMutableTreeNode("Traceabilities");
            for (Traceability traceability : this.project.getTraceabilitiesList())
                     folder.add(this.getNode(traceability));
            root.add(folder);
        }
    }
    
    /**
     * Method responsible for adding Metrics.
     * @param root Tree Node.
     */
    private void addMetrics(DefaultMutableTreeNode root) {
        if ((this.project != null) && (!this.project.getMetricsList().isEmpty())) {
            DefaultMutableTreeNode metricNode = new DefaultMutableTreeNode("Metrics");
            for (Metric metric : this.project.getMetricsList())
                     metricNode.add(new DefaultMutableTreeNode(metric));
            root.add(metricNode);
        }
    }
    
    /**
     * Method responsible for adding the Products.
     * @param root Tree Node.
     */
    private void addProducts(DefaultMutableTreeNode root) {
        if ((this.project != null) && (!this.project.getProductsList().isEmpty())) {
            DefaultMutableTreeNode productNode = new DefaultMutableTreeNode("Products");
            for (Product product : this.project.getProductsList())
                     productNode.add(this.getNode(product));
            root.add(productNode);
        }
    }
    
    /**
     * Method responsible for returning Diagram Node.
     * @param  diagram Diagram.
     * @return Diagram Node.
     */
    private DefaultMutableTreeNode getNode(Diagram diagram) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(diagram);
               this.addElements(diagram, node);
               this.addVariabilities(diagram, node);
               this.nodes.put(diagram, node);
        return node;
    }
    
    /**
     * Method responsible for adding Diagram Elements.
     * @param diagram Diagram.
     * @param node Diagram Node.
     */
    private void addElements(Diagram diagram, DefaultMutableTreeNode node) {
        for (Element  element : diagram.getTreeElementsList()) {
            DefaultMutableTreeNode new_ = null;
            if (element instanceof PackageUML)
                new_ = this.getNode((PackageUML) element);
            else if (element instanceof Entity)
                new_ = this.getNode((Entity) element);
            else if (this.checkElement(element))
                new_ = this.getNode(element);
            this.addElement(element, new_, node);
        }
    }
    
    /**
     * Method responsible for checking the Element is not Method or Attribute.
     * @param  element Element.
     * @return Element is not a Method or Attribute.
     */
    private boolean checkElement(Element element) {
        return  (element instanceof AttributeUML == false)
             && (element instanceof MethodUML    == false);
    }
    
    @Override
    public void updateNode(Element element) {
        if (this.getNodeTree(element) != null) {
            this.getTreeModel().reload(this.getNodeTree(element));
            this.updateVariability(element);
        }
    }
    
    /**
     * Method responsible for updating the Variability by Element Node.
     * @param element Element.
     */
    public void updateVariability(Element element) {
        DefaultMutableTreeNode node   = this.getNodeTree(element);
        DefaultMutableTreeNode parent = this.getParent(node);
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
    
    /**
     * Method responsible for returning the Package Node.
     * @param  packageUML Package UML.
     * @return Package Node.
     */
    private DefaultMutableTreeNode getNode(PackageUML packageUML) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(packageUML);
            this.addEntities(packageUML, node);
            this.addPackages(packageUML, node);
        return node;
    }
    
    /**
     * Method responsible for adding the Entities Nodes.
     * @param packageUML Package UML.
     * @param node Package Node.
     */
    private void addEntities(PackageUML packageUML, DefaultMutableTreeNode node) {
        for (Entity current : packageUML.getEntitiesList())
            node.add(this.getNode(current));
    }
    
    /**
     * Method responsible for adding the Packages Nodes.
     * @param packageUML Package UML.
     * @param node Package Node.
     */
    private void addPackages(PackageUML packageUML, DefaultMutableTreeNode node) {
        for (PackageUML current : packageUML.getPackagesList())
            node.add(this.getNode(current));
    }
    
    /**
     * Method responsible for adding Entity to Class Diagram.
     * @param  entity Entity.
     * @param  node Diagram Node.
     * @return Entity Node.
     */
    private DefaultMutableTreeNode getNode(Entity entity) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(entity);
            this.addAttributesUML(entity, node);
            this.addMethodsUML(entity, node);
        return node;
    }
    
    /**
     * Method responsible for adding Attribute Nodes.
     * @param entity Entity.
     * @param node Entity Node.
     */
    private void addAttributesUML(Entity entity, DefaultMutableTreeNode node) {
        for (AttributeUML attribute : entity.getAttributesList()) {
            DefaultMutableTreeNode new_ = new DefaultMutableTreeNode(attribute);
            node.add(new_);
            this.nodes.put(attribute, new_);
        }
    }
    
    /**
     * Method responsible for adding Method Nodes.
     * @param entity Entity.
     * @param node Entity Node.
     */
    private void addMethodsUML(Entity entity, DefaultMutableTreeNode node) {
        for (MethodUML method : entity.getMethodsList()) {
            DefaultMutableTreeNode new_ = new DefaultMutableTreeNode(method);
            node.add(new_);
            this.nodes.put(method, new_);
        }
    }
    
    /**
     * Method responsible for adding Variabilities.
     * @param diagram Diagram.
     * @param node Diagram Node.
     */
    private void addVariabilities(Diagram diagram, DefaultMutableTreeNode node) {
        for (Variability variability : diagram.getVariabilitiesList())
            node.add(this.getNode(variability));
    }
    
    /**
     * Method responsible for returning Variability Node.
     * @param  variability Variability.
     * @return Variability Node.
     */
    private DefaultMutableTreeNode getNode(Variability variability) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(variability);
               this.addVariationPoint(variability, node);
               this.addVariants(variability, node);
               this.nodes.put(variability, node);
        return node;
    }
    
    /**
     * Method responsible for updating the Variability Node.
     * @param variability 
     */
    public void updateNode(Variability variability) {
        if (this.getNodeTree(variability) != null) {
            DefaultMutableTreeNode node = this.getNodeTree(variability);
                                   node.removeAllChildren();
            this.addVariationPoint(variability, node);
            this.addVariants(variability, node);
            this.getTreeModel().reload(node);
        }
    }
    
    /**
     * Method responsible for adding Variation Point Node.
     * @param variability Variability.
     * @param node Variability Node.
     */
    private void addVariationPoint(Variability variability, DefaultMutableTreeNode node) {
        node.add(new DefaultMutableTreeNode(variability.getVariationPoint()));
    }
    
    /**
     * Method responsible for adding Variants Nodes.
     * @param variability Variability.
     * @param node Variability Node.
     */
    private void addVariants(Variability variability, DefaultMutableTreeNode node) {
        for (Element variant : variability.getVariants())
            node.add(new DefaultMutableTreeNode(variant));
    }
    
    /**
     * Method responsible for returning the Traceability Node.
     * @param  traceability Traceability.
     * @return Traceability Node.
     */
    private DefaultMutableTreeNode getNode(Traceability traceability) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(traceability);
            this.addElements(traceability, node);
        return node;
    }
    
    /**
     * Method responsible for adding Traceability Elements.
     * @param traceability Traceability.
     * @param node Traceability Node.
     */
    private void addElements(Traceability traceability, DefaultMutableTreeNode node) {
        for (Element element : traceability.getElements())
            node.add(new DefaultMutableTreeNode(element));
    }
    
    /**
     * Method responsible for returning the Product Node.
     * @param  product Product.
     * @return Product Node.
     */
    private DefaultMutableTreeNode getNode(Product product) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(product);
            this.addInstances(product, node);
        return node;
    }
    
    /**
     *  Method responsible for adding the Product Instances.
     * @param product Product.
     * @param node Product Node.
     */
    private void addInstances(Product product, DefaultMutableTreeNode node) {
        for (Instance instance : product.getInstancesList())
            node.add(this.getNode(instance));
    }
    
    /**
     * Method responsible for returning the Instance Node.
     * @param  instance Instance.
     * @return Instance Node.
     */
    private DefaultMutableTreeNode getNode(Instance instance) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(instance);
            this.addArtifacts(instance, node);
        return node;
    }
    
    /**
     *  Method responsible for adding the Instance Artifacts.
     * @param instance Instance.
     * @param node Instance Node.
     */
    private void addArtifacts(Instance instance, DefaultMutableTreeNode node) {
        for (Artifact artifact : instance.getTreeArtifactsList())
            node.add(this.getNode(instance, artifact));
    }
    
    /**
     * Method responsible for returning the Artifact Node.
     * @param  instance Instance.
     * @param  artifact Artifact.
     * @return Artifact Node.
     */
    private DefaultMutableTreeNode getNode(Instance instance, Artifact artifact) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(artifact);
        if (artifact.isPackage()) {
            this.addEntities(instance, artifact, node);
            this.addPackages(instance, artifact, node);
        }
        return node;
    }
    
    /**
     * Method responsible for adding the Artifact Nodes.
     * @param instance Instance.
     * @param artifact Artifact.
     * @param node Artifact Node.
     */
    private void addEntities(Instance instance, Artifact artifact, DefaultMutableTreeNode node) {
        for (Entity current : ((PackageUML) artifact.getElement()).getEntitiesList()) {
            if (instance.contains(current))
                node.add(this.getNode(instance, instance.getArtifact(current)));
        }
    }
    
    /**
     * Method responsible for adding the Artifacts Nodes.
     * @param instance Instance.
     * @param artifact Artifact.
     * @param node Artifact Node.
     */
    private void addPackages(Instance instance, Artifact artifact, DefaultMutableTreeNode node) {
        for (PackageUML current : ((PackageUML) artifact.getElement()).getPackagesList()) {
            if (instance.contains(current))
                node.add(this.getNode(instance, instance.getArtifact(current)));
        }
    }
}