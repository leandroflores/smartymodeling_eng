package view.panel.tree.base.requirement;

import controller.view.panel.tree.popup.base.requirement.ControllerTreePopupRequirement;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.requirement.Requirement;
import view.panel.tree.PanelTree;
import view.panel.tree.popup.base.requirement.TreePopupRequirement;
import view.panel.tree.renderer.base.requirement.TreeRendererRequirement;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelTreeRequirement</b>.</p>
 * <p>Class responsible for defining the <b>Requirement Tree Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-15
 * @see    view.panel.tree.PanelTree
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
        addComponents();
    }
    
    @Override
    protected void initTreeRenderer() {
        getTree().setCellRenderer(new TreeRendererRequirement(getTree()));
    }
    
    @Override
    protected void initTreePopup() {
        popup = new TreePopupRequirement(this);
    }
    
    @Override
    protected void setControllers() {
        tree.addMouseListener(new ControllerTreePopupRequirement(getPopup()));
        tree.addKeyListener(new ControllerTreePopupRequirement(getPopup()));
    }
    
    @Override
    protected DefaultMutableTreeNode createNode(Project project) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(project);
               addRequirements(node);
               addNode(project, node);
        return node;
    }
    
    /**
     * Method responsible for adding the Requirements of the Project.
     * @param node Project Node.
     */
    protected void addRequirements(DefaultMutableTreeNode node) {
        for (Requirement requirement : getProject().getRequirementsList())
            node.add(createNode(requirement));
    }
    
    /**
     * Method responsible for returning a New Requirement Node.
     * @param  requirement Requirement.
     * @return New Requirement Node.
     */
    protected DefaultMutableTreeNode createNode(Requirement requirement) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(requirement);
               addElements(requirement, node);
               addNode(requirement, node);
        return node;
    }
    
    /**
     * Method responsible for updating the Requirement Node.
     * @param requirement Requirement.
     */
    public void updateNode(Requirement requirement) {
        if (getNode(requirement) != null)
            getTreeModel().reload(createNode(requirement));
    }
    
    /**
     * Method responsible for adding the Element Nodes of a Requirement.
     * @param requirement Requirement.
     * @param node Requirement Node.
     */
    protected void addElements(Requirement requirement, DefaultMutableTreeNode node) {
        for (Element element : requirement.getAllElements())
            addElement(element, createNode(element), node);
    }
    
    @Override
    public TreePopupRequirement getPopup() {
        return (TreePopupRequirement) popup;
    }
}