package view.panel.tree.base.product;

import controller.view.panel.tree.popup.base.product.ControllerTreePopupProduct;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Project;
import model.structural.base.product.Artifact;
import model.structural.base.product.Instance;
import model.structural.base.product.Product;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.PackageUML;
import view.panel.tree.PanelTree;
import view.panel.tree.popup.base.product.TreePopupProduct;
import view.panel.tree.renderer.base.product.TreeRendererProduct;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelTreeProduct</b>.</p>
 * <p>Class responsible for defining the <b>Product Tree Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-14
 * @see    view.panel.tree.PanelTree
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
        addComponents();
    }
    
    @Override
    protected void initTreeRenderer() {
        getTree().setCellRenderer(new TreeRendererProduct(getTree()));
    }
    
    @Override
    protected void initTreePopup() {
        popup = new TreePopupProduct(this);
    }
    
    @Override
    protected void setControllers() {
        tree.addMouseListener(new ControllerTreePopupProduct(getPopup()));
        tree.addKeyListener(new ControllerTreePopupProduct(getPopup()));
    }
    
    @Override
    protected DefaultMutableTreeNode createNode(Project project) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(project);
               addProducts(node);
               addNode(project, node);
        return node;
    }
    
    /**
     * Method responsible for adding the Products of the Project.
     * @param node Project Node.
     */
    protected void addProducts(DefaultMutableTreeNode node) {
        for (Product product : getProject().getProductsList())
            node.add(createNode(product));
    }
    
    /**
     * Method responsible for returning a New Product Node.
     * @param  product Product.
     * @return New Product Node.
     */
    protected DefaultMutableTreeNode createNode(Product product) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(product);
               addInstances(product, node);
               addNode(product, node);
        return node;
    }
    
    /**
     * Method responsible for adding the Instance Nodes of a Product.
     * @param product Product.
     * @param node Product Node.
     */
    protected void addInstances(Product product, DefaultMutableTreeNode node) {
        for (Instance instance : product.getInstancesList())
            node.add(createNode(instance));
    }
    
    /**
     * Method responsible for returning a New Instance Node.
     * @param  instance Instance.
     * @return New Instance Node.
     */
    protected DefaultMutableTreeNode createNode(Instance instance) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(instance);
               addArtifacts(instance, node);
               addNode(instance, node);
        return node;
    }
    
    /**
     * Method responsible for adding the Artifact Nodes of a Instance.
     * @param instance Instance.
     * @param node Instance Node.
     */
    protected void addArtifacts(Instance instance, DefaultMutableTreeNode node) {
        for (Artifact artifact : instance.getTreeArtifactsList())
            node.add(createNode(instance, artifact));
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
            addEntities(instance, (PackageUML) artifact.getElement(), node);
            addPackages(instance, (PackageUML) artifact.getElement(), node);
        }
               addNode(artifact, node);
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
                node.add(createNode(instance, instance.getArtifact(artifact)));
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
                node.add(createNode(instance, instance.getArtifact(artifact)));
        }
    }
    
    /**
     * Method responsible for updating the Product Node.
     * @param product Diagram. 
     */
    public void updateNode(Product product) {
        if (getNode(product) != null)
            getTreeModel().reload(getNode(product));
    }
    
    /**
     * Method responsible for updating the Instance Node.
     * @param instance Instance. 
     */
    public void updateNode(Instance instance) {
        if (getNode(instance) != null)
            getTreeModel().reload(getNode(instance));
    }
    
    @Override
    public TreePopupProduct getPopup() {
        return (TreePopupProduct) popup;
    }
}