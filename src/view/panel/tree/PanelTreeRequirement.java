package view.panel.tree;

import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Project;
import model.structural.base.requirement.Requirement;
import view.panel.tree.renderer.TreeRendererRequirement;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelTreeRequirement</b>.</p>
 * <p>Class responsible for defining the <b>Requirement Tree Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-15
 * @see    view.panel.tree.PanelTree
 * @see    view.panel.tree.renderer.TreeRendererRequirement
 */
public final class PanelTreeRequirement extends PanelTree {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public PanelTreeRequirement(ViewMenu view) {
        super(view);
        this.addComponents();
    }
    
    @Override
    protected void addControllers() {
        super.addControllers();
        this.getTree().setCellRenderer(new TreeRendererRequirement(this.getTree()));
    }
    
    @Override
    protected DefaultMutableTreeNode createNode(Project project) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(project);
               this.addRequirements(node);
               super.addNode(project, node);
        return node;
    }
    
    /**
     * Method responsible for adding the Requirements of the Project.
     * @param node Project Node.
     */
    protected void addRequirements(DefaultMutableTreeNode node) {
        for (Requirement requirement : this.getProject().getRequirementsList())
            node.add(this.createNode(requirement));
    }
    
    /**
     * Method responsible for returning a New Requirement Node.
     * @param  requirement Requirement.
     * @return New Requirement Node.
     */
    protected DefaultMutableTreeNode createNode(Requirement requirement) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(requirement);
               super.addNode(requirement, node);
        return node;
    }
    
    /**
     * Method responsible for updating the Requirement Node.
     * @param requirement Requirement.
     */
    public void updateNode(Requirement requirement) {
        if (this.getNode(requirement) != null)
            this.getTreeModel().reload(this.getNode(requirement));
    }
}