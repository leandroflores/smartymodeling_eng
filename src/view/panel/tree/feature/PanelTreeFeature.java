package view.panel.tree.feature;

import controller.view.panel.tree.popup.feature.ControllerTreePopupFeature;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.diagram.FeatureDiagram;
import model.structural.diagram.feature.base.Feature;
import model.structural.diagram.feature.base.Variability;
import view.panel.tree.PanelTree;
import view.panel.tree.popup.feature.TreePopupFeature;
import view.panel.tree.renderer.feature.TreeRendererFeature;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelTreeFeature</b>.</p>
 * <p>Class responsible for defining the <b>Feature Tree Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-06
 * @see    view.panel.tree.PanelTree
 * @see    view.panel.tree.popup.feature.TreePopupFeature
 * @see    view.panel.tree.renderer.feature.TreeRendererFeature
 */
public final class PanelTreeFeature extends PanelTree {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public PanelTreeFeature(ViewMenu view) {
        super(view);
        addComponents();
    }
    
    @Override
    protected void initTreeRenderer() {
        getTree().setCellRenderer(new TreeRendererFeature(getTree()));
    }
    
    @Override
    protected void initTreePopup() {
        popup = new TreePopupFeature(this);
    }
    
    @Override
    protected void setControllers() {
        tree.addMouseListener(new ControllerTreePopupFeature(getPopup()));
        tree.addKeyListener(new ControllerTreePopupFeature(getPopup()));
    }
    
    @Override
    protected DefaultMutableTreeNode createNode(Project project) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(project);
               addDiagrams(node);
               addNode(project, node);
        return node;
    }
    
    /**
     * Method responsible for adding the Feature Diagrams of the Project.
     * @param node Project Node.
     */
    protected void addDiagrams(DefaultMutableTreeNode node) {
        for (Diagram diagram : getProject().getFeatureDiagramsList())
            node.add(createNode(diagram));
    }
    
    /**
     * Method responsible for returning a New Diagram Node.
     * @param  diagram Diagram.
     * @return New Diagram Node.
     */
    protected DefaultMutableTreeNode createNode(Diagram diagram) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(diagram);
               addFeatures(diagram, node);
               addVariabilities(diagram, node);
               addNode(diagram, node);
        return node;
    }
    
    /**
     * Method responsible for adding the Feature Nodes of a Feature Diagram.
     * @param diagram Feature Diagram.
     * @param node Feature Diagram Node.
     */
    protected void addFeatures(Diagram diagram, DefaultMutableTreeNode node) {
        for (Feature feature : ((FeatureDiagram) diagram).getFeaturesList())
            addElement(feature, createNode(feature), node);
    }
    
    /**
     * Method responsible for adding the Variability Nodes of a Feature Diagram.
     * @param diagram Feature Diagram.
     * @param node Feature Diagram Node.
     */
    protected void addVariabilities(Diagram diagram, DefaultMutableTreeNode node) {
        for (Variability variability : ((FeatureDiagram) diagram).getVariability())
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
    
    @Override
    public TreePopupFeature getPopup() {
        return (TreePopupFeature) popup;
    }
}