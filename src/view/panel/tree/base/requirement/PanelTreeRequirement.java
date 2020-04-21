package view.panel.tree.base.requirement;

import controller.view.panel.tree.popup.base.requirement.ControllerTreePopupRequirement;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.requirement.Requirement;
import view.panel.tree.base.PanelTree;
import view.panel.tree.popup.base.requirement.TreePopupRequirement;
import view.panel.tree.renderer.base.requirement.TreeRendererRequirement;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelTreeRequirement</b>.</p>
 * <p>Class responsible for defining the <b>Requirement Tree Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-15
 * @see    view.panel.tree.base.PanelTree
 * @see    view.panel.tree.popup.base.requirement.TreePopupRequirement
 * @see    view.panel.tree.renderer.base.requirement.TreeRendererRequirement
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
    protected void initTreeRenderer() {
        this.getTree().setCellRenderer(new TreeRendererRequirement(this.getTree()));
    }
    
    @Override
    protected void initTreePopup() {
        this.popup = new TreePopupRequirement(this);
    }
    
    @Override
    protected void setControllers() {
        this.tree.addMouseListener(new ControllerTreePopupRequirement(this.getPopup()));
        this.tree.addKeyListener(new ControllerTreePopupRequirement(this.getPopup()));
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
                this.addElements(requirement, node);
            super.addNode(requirement, node);
        return node;
    }
    
    /**
     * Method responsible for updating the Requirement Node.
     * @param requirement Requirement.
     */
    public void updateNode(Requirement requirement) {
        if (this.getNode(requirement) != null)
            this.getTreeModel().reload(this.createNode(requirement));
    }
    
    /**
     * Method responsible for adding the Element Nodes of a Requirement.
     * @param requirement Requirement.
     * @param node Requirement Node.
     */
    protected void addElements(Requirement requirement, DefaultMutableTreeNode node) {
        for (Element element : requirement.getAllElements())
            super.addElement(element, super.createNode(element), node);
    }
    
    @Override
    public TreePopupRequirement getPopup() {
        return (TreePopupRequirement) this.popup;
    }
}