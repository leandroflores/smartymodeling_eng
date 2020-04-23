package view.panel.tree.feature;

import controller.view.panel.tree.popup.feature.ControllerTreePopupFeature;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import view.panel.tree.PanelTree;
import view.panel.tree.popup.feature.TreePopupFeature;
import view.panel.tree.renderer.feature.TreeRendererFeature;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelTreeFeature</b>.</p>
 * <p>Class responsible for defining the <b>Feature Tree Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-06
 * @see    view.panel.tree1.PanelTree
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
        this.addComponents();
    }
    
    @Override
    protected void initTreeRenderer() {
        this.getTree().setCellRenderer(new TreeRendererFeature(this.getTree()));
    }
    
    @Override
    protected void initTreePopup() {
        this.popup = new TreePopupFeature(this);
    }
    
    @Override
    protected void setControllers() {
        this.tree.addMouseListener(new ControllerTreePopupFeature(this.getPopup()));
        this.tree.addKeyListener(new ControllerTreePopupFeature(this.getPopup()));
    }
    
    @Override
    protected DefaultMutableTreeNode createNode(Project project) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(project);
               this.addDiagrams(node);
               super.addNode(project, node);
        return node;
    }
    
    /**
     * Method responsible for adding the Feature Diagrams of the Project.
     * @param node Project Node.
     */
    protected void addDiagrams(DefaultMutableTreeNode node) {
        for (Diagram diagram : this.getProject().getFeatureDiagramsList())
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
            super.addNode(diagram, node);
        return node;
    }
    
    /**
     * Method responsible for adding the Element Nodes of a Feature Diagram.
     * @param diagram Feature Diagram.
     * @param node Feature Diagram Node.
     */
    protected void addElements(Diagram diagram, DefaultMutableTreeNode node) {
        for (Element  element : diagram.getTreeElementsList())
            super.addElement(element, super.createNode(element), node);
    }
    
    @Override
    public TreePopupFeature getPopup() {
        return (TreePopupFeature) this.popup;
    }
}