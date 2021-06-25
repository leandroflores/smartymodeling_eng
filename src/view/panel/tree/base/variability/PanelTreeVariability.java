package view.panel.tree.base.variability;

import controller.view.panel.tree.popup.base.variability.ControllerTreePopupVariability;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.variability.Variability;
import view.panel.tree.PanelTree;
import view.panel.tree.popup.base.variability.TreePopupVariability;
import view.panel.tree.renderer.base.variability.TreeRendererVariability;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelTreeVariability</b>.</p>
 * <p>Class responsible for defining the <b>Variability Tree Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-14
 * @see    view.panel.tree.PanelTree
 * @see    view.panel.tree.popup.base.variability.TreePopupVariability
 * @see    view.panel.tree.renderer.base.variability.TreeRendererVariability
 */
public final class PanelTreeVariability extends PanelTree {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public PanelTreeVariability(ViewMenu view) {
        super(view);
        addComponents();
    }
    
    @Override
    protected void initTreeRenderer() {
        getTree().setCellRenderer(new TreeRendererVariability(getTree()));
    }
    
    @Override
    protected void initTreePopup() {
        popup = new TreePopupVariability(this);
    }
    
    @Override
    protected void setControllers() {
        tree.addMouseListener(new ControllerTreePopupVariability(getPopup()));
        tree.addKeyListener(new ControllerTreePopupVariability(getPopup()));
    }
    
    @Override
    protected DefaultMutableTreeNode createNode(Project project) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(project);
               addDiagrams(node);
               addNode(project, node);
        return node;
    }
    
    /**
     * Method responsible for adding the UML Diagrams of the Project.
     * @param node Project Node.
     */
    protected void addDiagrams(DefaultMutableTreeNode node) {
        for (Diagram diagram : getProject().getVariabilityDiagramsList())
            node.add(createNode(diagram));
    }
    
    /**
     * Method responsible for returning a New Diagram Node.
     * @param  diagram Diagram.
     * @return New Diagram Node.
     */
    protected DefaultMutableTreeNode createNode(Diagram diagram) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(diagram);
               addVariabilities(diagram, node);
               addNode(diagram, node);
        return node;
    }
    
    /**
     * Method responsible for adding the Variability Nodes of the UML Diagram.
     * @param diagram UML Diagram.
     * @param node UML Diagram Node.
     */
    protected void addVariabilities(Diagram diagram, DefaultMutableTreeNode node) {
        for (Variability variability : diagram.getVariabilitiesList())
            node.add(createNode(variability));
    }
    
    /**
     * Method responsible for returning a New Variability Node.
     * @param  variability Variability.
     * @return New Variability Node.
     */
    protected DefaultMutableTreeNode createNode(Variability variability) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(variability);
               addVariationPoint(variability, node);
               addVariants(variability, node);
               addNode(variability, node);
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
        if (getNode(variability) != null) {
            DefaultMutableTreeNode node = getNode(variability);
                                   node.removeAllChildren();
            addVariationPoint(variability, node);
            addVariants(variability, node);
            getTreeModel().reload(node);
        }
    }
    
    /**
     * Method responsible for updating the Variability by Element Node.
     * @param element Element.
     */
    public void updateVariability(Element element) {
        if (getNode(element) != null) {
            DefaultMutableTreeNode node   = getNode(element);
            DefaultMutableTreeNode parent = getParentNode(node);
            if (parent != null && parent.getUserObject() != null && parent.getUserObject() instanceof Diagram) {
                updateVariationPoints((Diagram) parent.getUserObject(), element);
                updateVariants((Diagram) parent.getUserObject(), element);
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
            updateNode(variability);
    }
    
    /**
     * Method responsible for updating the Variants Nodes.
     * @param diagram Diagram.
     * @param element Element.
     */
    private void updateVariants(Diagram diagram, Element element) {
        for (Variability variability : diagram.filterVariants(element, ""))
            updateNode(variability);
    }
    
    @Override
    public TreePopupVariability getPopup() {
        return (TreePopupVariability) popup;
    }
}