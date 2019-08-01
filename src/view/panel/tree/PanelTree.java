package view.panel.tree;

import controller.view.panel.tree.popup.ControllerTreePopup;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.traceability.Traceability;
import model.structural.base.variability.Variability;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.MethodUML;
import model.structural.diagram.classes.base.PackageUML;
import view.Panel;
import view.panel.tree.popup.TreePopup;
import view.panel.tree.renderer.TreeRenderer;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelTree</b>.</p>
 * <p>Class responsible for defining the <b>Tree Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  27/05/2019
 * @see    model.structural.base.Project
 * @see    view.Panel
 */
public final class PanelTree extends Panel {
    private final ViewMenu viewMenu;
    private final Project  project;
    private TreePopup treePopup;
    private JTree tree;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     */
    public PanelTree(ViewMenu viewMenu) {
        this.viewMenu = viewMenu;
        this.project  = this.viewMenu.getProject();
        this.addComponents();
    }
    
    @Override
    public void addComponents() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode(this.project);
        
        this.tree      = new JTree(raiz);
        this.treePopup = new TreePopup(this);
        this.tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        this.tree.addMouseListener(new ControllerTreePopup(this.treePopup));
        this.tree.addKeyListener(new ControllerTreePopup(this.treePopup));
        this.tree.setCellRenderer(new TreeRenderer(this.tree));
        this.addDiagrams(raiz);
        this.addTraceabilities(raiz);
//        this.expandTree();
        this.add(this.tree);
    }
    
    /**
     * Method responsible for expanding Tree.
     */
    private void expandTree() {
        for (int i = 0; i < this.tree.getRowCount(); i++)
            this.tree.expandRow(i);
    }
    
    /**
     * Method responsible for adding Diagrams.
     * @param root Tree Node.
     */
    private void addDiagrams(DefaultMutableTreeNode root) {
        if (this.project != null) {
            for (int i = 0; i < this.project.getDiagramsList().size(); i++)
                root.add(this.getNode(project.getDiagramsList().get(i)));
        }
    }
    
    /**
     * Method responsible for adding Traceabilities.
     * @param root Tree Node.
     */
    private void addTraceabilities(DefaultMutableTreeNode root) {
        if (this.project != null) {
            DefaultMutableTreeNode traceabilityNode = new DefaultMutableTreeNode("Traceabilities");
            List<Traceability>     traceabilities   = this.project.getTraceabilitiesList();
            for (int i = 0; i < traceabilities.size(); i++)
                     traceabilityNode.add(new DefaultMutableTreeNode(traceabilities.get(i)));
            root.add(traceabilityNode);
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
//               this.addAssociations(diagrama, node);
               this.addVariabilities(diagram, node);
        return node;
    }
    
    /**
     * Method responsible for adding Diagram Elements.
     * @param diagram Diagram.
     * @param node Diagram Node.
     */
    private void addElements(Diagram diagram, DefaultMutableTreeNode node) {
        List<Element> elements = diagram.getTreeElementsList();
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i) instanceof PackageUML)
                node.add(this.getNode((PackageUML) elements.get(i)));
            else if (elements.get(i) instanceof Entity)
                node.add(this.getNode((Entity) elements.get(i)));
            else if (this.checkElement(elements.get(i)))
                node.add(new DefaultMutableTreeNode(diagram.getElementsList().get(i)));
        }
    }
    
    /**
     * Method responsible for checking the Element is not Method or Attribute.
     * @param  element Element.
     * @return Element is not a Method or Attribute.
     */
    private boolean checkElement(Element element) {
        return ((element instanceof AttributeUML == false)
             && (element instanceof MethodUML   == false));
    }
    
    /**
     * 
     * @param packageUML
     * @return 
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
        for (int i = 0; i < entity.getAttributesList().size(); i++)
            node.add(new DefaultMutableTreeNode(entity.getAttributesList().get(i)));
    }
    
    /**
     * Method responsible for adding Method Nodes.
     * @param entity Entity.
     * @param node Entity Node.
     */
    private void addMethodsUML(Entity entity, DefaultMutableTreeNode node) {
        for (int i = 0; i < entity.getMethodsList().size(); i++)
            node.add(new DefaultMutableTreeNode(entity.getMethodsList().get(i)));
    }
    
    /**
     * Method responsible for adding Association Nodes.
     * @param diagram Diagram.
     * @param node Diagram Node.
     */
    private void addAssociations(Diagram diagram, DefaultMutableTreeNode node) {
        for (int i = 0; i < diagram.getAssociationsList().size(); i++)
            node.add(new DefaultMutableTreeNode(diagram.getAssociationsList().get(i)));
    }
    
    /**
     * Method responsible for adding Variabilities.
     * @param diagram Diagram.
     * @param node Diagram Node.
     */
    private void addVariabilities(Diagram diagram, DefaultMutableTreeNode node) {
        List<Variability>   variabilities = diagram.getVariabilitiesList();
        for (int i = 0; i < variabilities.size(); i++)
            node.add(this.getNode(variabilities.get(i)));
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
        return node;
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
        for (int i = 0; i < variability.getVariants().size(); i++)
            node.add(new DefaultMutableTreeNode(variability.getVariants().get(i)));
    }
    
    /**
     * Method responsible for returning View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
    
    /**
     * Method responsible for returning Tree.
     * @return Tree.
     */
    public JTree getTree() {
        return this.tree;
    }
}