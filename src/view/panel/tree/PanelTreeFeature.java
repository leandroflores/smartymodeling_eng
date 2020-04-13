package view.panel.tree;

import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Diagram;
import model.structural.base.Element;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelTreeFeature</b>.</p>
 * <p>Class responsible for defining the <b>Features Tree Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  06/04/2020
 * @see    model.structural.base.Project
 * @see    view.panel.tree.
 */
public final class PanelTreeFeature extends PanelTree {
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     */
    public PanelTreeFeature(ViewMenu viewMenu) {
        super(viewMenu);
        this.addComponents();
    }
    
    @Override
    protected DefaultMutableTreeNode getProjectNode() {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(this.project);
               this.addDiagrams(node);
               this.nodes.put(this.project, node);
        return node;
    }
    
    /**
     * Method responsible for updating the Panel Tree.
     */
    public void update() {
        DefaultMutableTreeNode node = super.getNodeTree(this.project);
        if (node != null) {
            node.removeAllChildren();
            this.addDiagrams(node);
            this.getTreeModel().reload(node);
        }
    }
    
    /**
     * Method responsible for adding the Feature Diagrams.
     * @param root Tree Node.
     */
    private void addDiagrams(DefaultMutableTreeNode root) {
        if (this.project != null) {
            for (Diagram diagram : this.project.getFeatureDiagramsList())
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
               this.nodes.put(diagram, node);
        return node;
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
    
    @Override
    public void updateNode(Diagram diagram) {
        DefaultMutableTreeNode node = super.getNodeTree(diagram);
        if (node != null) {
            node.removeAllChildren();            
            this.addElements(diagram, node);
            this.getTreeModel().reload(node);
        }
    }
    
    @Override
    public void updateNode(Element element) {
        if (this.getNodeTree(element) != null)
            this.getTreeModel().reload(this.getNodeTree(element));
    }
}