package view.panel.tree.base.product;

import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Project;
import model.structural.base.product.Artifact;
import model.structural.base.product.Instance;
import model.structural.base.product.Product;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.PackageUML;
import view.panel.tree.base.PanelTree;
import view.panel.tree.popup.base.product.TreePopupProduct;
import view.panel.tree.renderer.base.product.TreeRendererProduct;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelTreeProduct</b>.</p>
 * <p>Class responsible for defining the <b>Product Tree Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-14
 * @see    view.panel.tree.base.PanelTree
 * @see    view.panel.tree.popup.base.product.TreePopupProduct
 * @see    view.panel.tree.renderer.base.product.TreeRendererProduct
 */
public final class PanelTreeProduct extends PanelTree {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public PanelTreeProduct(ViewMenu view) {
        super(view);
        this.addComponents();
    }
    
    @Override
    protected void initTreeRenderer() {
        this.getTree().setCellRenderer(new TreeRendererProduct(this.getTree()));
    }
    
    @Override
    protected void initTreePopup() {
        this.popup = new TreePopupProduct(this);
    }
    
    @Override
    protected void setControllers() {
//        this.tree.addMouseListener(new ControllerTreePopup((TreePopupDiagram) this.popup));
//        this.tree.addKeyListener(new ControllerTreePopup((TreePopupDiagram) this.popup));
    }
    
    @Override
    protected DefaultMutableTreeNode createNode(Project project) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(project);
               this.addProducts(node);
               super.addNode(project, node);
        return node;
    }
    
    /**
     * Method responsible for adding the Products of the Project.
     * @param node Project Node.
     */
    protected void addProducts(DefaultMutableTreeNode node) {
        for (Product product : this.getProject().getProductsList())
            node.add(this.createNode(product));
    }
    
    /**
     * Method responsible for returning a New Product Node.
     * @param  product Product.
     * @return New Product Node.
     */
    protected DefaultMutableTreeNode createNode(Product product) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(product);
               this.addInstances(product, node);
               super.addNode(product, node);
        return node;
    }
    
    /**
     * Method responsible for adding the Instance Nodes of a Product.
     * @param product Product.
     * @param node Product Node.
     */
    protected void addInstances(Product product, DefaultMutableTreeNode node) {
        for (Instance instance : product.getInstancesList())
            node.add(this.createNode(instance));
    }
    
    /**
     * Method responsible for returning a New Instance Node.
     * @param  instance Instance.
     * @return New Instance Node.
     */
    protected DefaultMutableTreeNode createNode(Instance instance) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(instance);
            this.addArtifacts(instance, node);
            super.addNode(instance, node);
        return node;
    }
    
    /**
     * Method responsible for adding the Artifact Nodes of a Instance.
     * @param instance Instance.
     * @param node Instance Node.
     */
    protected void addArtifacts(Instance instance, DefaultMutableTreeNode node) {
        for (Artifact artifact : instance.getTreeArtifactsList())
            node.add(this.createNode(instance, artifact));
    }
    
    /**
     * Method responsible for returning a New Artifact Node.
     * @param  instance Instance.
     * @param  artifact Artifact.
     * @return New Artifact Node.
     */
    protected DefaultMutableTreeNode createNode(Instance instance, Artifact artifact) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(artifact);
        if (artifact.isPackage()) {
            this.addEntities(instance, (PackageUML) artifact.getElement(), node);
            this.addPackages(instance, (PackageUML) artifact.getElement(), node);
        }
        super.addNode(artifact, node);
        return node;
    }
    
    /**
     * Method responsible for adding the Entity Nodes of a Package Artifact.
     * @param instance Instance.
     * @param package_ Package Artifact.
     * @param node Artifact Node.
     */
    private void addEntities(Instance instance, PackageUML package_, DefaultMutableTreeNode node) {
        for (Entity artifact : package_.getEntitiesList()) {
            if (instance.contains(artifact))
                node.add(this.createNode(instance, instance.getArtifact(artifact)));
        }
    }
    
    /**
     * Method responsible for adding the Package Nodes of a Package Artifact.
     * @param instance Instance.
     * @param package_ Package Artifact.
     * @param node Artifact Node.
     */
    private void addPackages(Instance instance, PackageUML package_, DefaultMutableTreeNode node) {
        for (PackageUML artifact : package_.getPackagesList()) {
            if (instance.contains(artifact))
                node.add(this.createNode(instance, instance.getArtifact(artifact)));
        }
    }
    
    /**
     * Method responsible for updating the Product Node.
     * @param product Diagram. 
     */
    public void updateNode(Product product) {
        if (this.getNode(product) != null)
            this.getTreeModel().reload(this.getNode(product));
    }
    
    /**
     * Method responsible for updating the Instance Node.
     * @param instance Instance. 
     */
    public void updateNode(Instance instance) {
        if (this.getNode(instance) != null)
            this.getTreeModel().reload(this.getNode(instance));
    }
    
    @Override
    public TreePopupProduct getPopup() {
        return (TreePopupProduct) this.popup;
    }
}