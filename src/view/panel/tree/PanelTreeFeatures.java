package view.panel.tree;

import java.awt.FlowLayout;
import java.util.HashMap;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.variability.Variability;
import view.Panel;
import view.panel.tree.popup.TreePopup;
import view.panel.tree.renderer.TreeRendererUML;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelTreeFeatures</b>.</p>
 * <p>Class responsible for defining the <b>Features Tree Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  06/04/2020
 * @see    model.structural.base.Project
 * @see    view.Panel
 */
public final class PanelTreeFeatures extends Panel {
    private final ViewMenu viewMenu;
    private final Project  project;
    private final HashMap  nodes;
    private TreePopup treePopup;
    private JTree tree;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     */
    public PanelTreeFeatures(ViewMenu viewMenu) {
        this.viewMenu = viewMenu;
        this.project  = this.viewMenu.getProject();
        this.nodes    = new HashMap();
        this.addComponents();
    }
    
    @Override
    public void addComponents() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.addControllers(this.getProjectNode());
//        this.expandTree();
        this.add(this.tree);
    }
    
    /**
     * Method responsible for adding the Tree Controllers.
     * @param node Project Node.
     */
    private void addControllers(DefaultMutableTreeNode node) {
        this.tree      = new JTree(node);
//        this.treePopup = new TreePopup(this);
        this.tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
//        this.tree.addMouseListener(new ControllerTreePopup(this.treePopup));
//        this.tree.addKeyListener(new ControllerTreePopup(this.treePopup));
        this.tree.setCellRenderer(new TreeRendererUML(this.tree));
    }
    
    /**
     * Method responsible for expanding Tree.
     */
    private void expandTree() {
        for (int i = 0; i < this.tree.getRowCount(); i++)
            this.tree.expandRow(i);
    }
    
    /**
     * Method responsible for returning the Project Node.
     * @return Project Node.
     */
    private DefaultMutableTreeNode getProjectNode() {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(this.project);
               this.addDiagrams(node);
               this.nodes.put(this.project, node);
        return node;
    }
    
    /**
     * Method responsible for updating the Project Node.
     */
    public void updateProjectNode() {
        this.getTreeModel().reload(this.getNodeTree(this.project));
    }
    
    /**
     * Method responsible for adding Diagrams.
     * @param root Tree Node.
     */
    private void addDiagrams(DefaultMutableTreeNode root) {
        if (this.project != null) {
            for (Diagram diagram : this.project.getDiagramsList())
                root.add(this.getNode(diagram));
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
     * Method responsible for updating the Diagram Node.
     * @param diagram Diagram. 
     */
    public void updateNode(Diagram diagram) {
        this.getTreeModel().reload(this.getNodeTree(diagram));
    }
    
    /**
     * Method responsible for adding Diagram Elements.
     * @param diagram Diagram.
     * @param node Diagram Node.
     */
    private void addElements(Diagram diagram, DefaultMutableTreeNode node) {
        for (Element  element : diagram.getTreeElementsList())
            this.addElement(element, this.getNode(element), node);
    }
    
    /**
     * Method responsible for adding the Element Node in your Parent Node.
     * @param element Element.
     * @param node Element Node.
     * @param parent Parent Node.
     */
    private void addElement(Element element, DefaultMutableTreeNode node, DefaultMutableTreeNode parent) {
        if (node != null) {
            parent.add(node);
            this.nodes.put(element, node);
        }
    }
    
    /**
     * Method responsible for returning the Element Node.
     * @param  element Element.
     * @return Element Node.
     */
    private DefaultMutableTreeNode getNode(Element element) {
       return new DefaultMutableTreeNode(element);
    }
    
    /**
     * Method responsible for updating the Element Node.
     * @param element Element. 
     */
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
     * Method responsible for returning the Parent Node.
     * @param  node Node.
     * @return Parent Node.
     */
    private DefaultMutableTreeNode getParent(DefaultMutableTreeNode node) {
        if (node.getParent() != null)
            return (DefaultMutableTreeNode) node.getParent();
        return null;
    }
    
    /**
     * Method responsible for returning the Object Node.
     * @param  object Object.
     * @return Object Node.
     */
    public DefaultMutableTreeNode getNodeTree(Object object) {
        if (this.nodes.get(object) != null)
            return (DefaultMutableTreeNode) this.nodes.get(object);
        return null;
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
    
    /**
     * Method responsible for returning the Tree.
     * @return Tree Model.
     */
    public DefaultTreeModel getTreeModel() {
        return (DefaultTreeModel) this.tree.getModel();
    }
    
    /**
     * Method responsible for returning the Tree.
     * @return Tree.
     */
    public JTree getTree() {
        return this.tree;
    }
}