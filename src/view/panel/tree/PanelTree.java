package view.panel.tree;

import java.awt.FlowLayout;
import java.util.HashMap;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import view.panel.Panel;
import view.panel.tree.popup.TreePopup;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelTree</b>.</p>
 * <p>Class responsible for defining the <b>Tree Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-27
 * @see    model.structural.base.Project
 * @see    view.panel.Panel
 */
public abstract class PanelTree extends Panel {
    protected final ViewMenu viewMenu;
    protected final Project  project;
    protected final HashMap  nodes;
    protected TreePopup popup;
    protected JTree tree;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public PanelTree(ViewMenu view) {
        viewMenu = view;
        project  = view.getProject();
        nodes    = new HashMap();
    }
    
    @Override
    public void addComponents() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        initTree();
        initTreeRenderer();
        initTreePopup();
        setControllers();
        add(tree);
    }
    
    /**
     * Method responsible for initializing the Tree.
     */
    protected void initTree() {
        tree  = new JTree(createNode(getProject()));
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    }
    
    /**
     * Method responsible for initializing the Tree Renderer.
     */
    protected abstract void initTreeRenderer();
    
    /**
     * Method responsible for initializing the Tree Popup.
     */
    protected abstract void initTreePopup();
    
    /**
     * Method responsible for setting the Tree Controllers.
     */
    protected abstract void setControllers();
    
    /**
     * Method responsible for expanding the Tree.
     */
    protected void expandTree() {
        for (int i = 0; i < tree.getRowCount(); i++)
            tree.expandRow(i);
    }
    
    /**
     * Method responsible for updating the Tree.
     */
    public void updateTree() {
        getTreeModel().reload(createNode(project));
    }
    
    /**
     * Method responsible for returning a New Project Node.
     * @param  project Project.
     * @return New Project Node.
     */
    protected abstract DefaultMutableTreeNode createNode(Project project);
    
    /**
     * Method responsible for updating the Node Project.
     * @param project Project.
     */
    public void updateNode(Project project) {
        getTreeModel().reload(getNode(project));
    }
    
    /**
     * Method responsible for updating the Diagram Node.
     * @param diagram Diagram. 
     */
    public void updateNode(Diagram diagram) {
        if (getNode(diagram) != null)
            getTreeModel().reload(getNode(diagram));
//            boolean expanded = isExpanded(node);
//            if (expanded)
//                tree.expandPath(new TreePath(node.getPath()));
//        }
    }
    
    /**
     * Method responsible for adding the Element Node in your Parent Node.
     * @param element Element.
     * @param node Element Node.
     * @param parent Parent Node.
     */
    protected void addElement(Element element, DefaultMutableTreeNode node, DefaultMutableTreeNode parent) {
        if (node != null) {
            parent.add(node);
            addNode(element, node);
        }
    }
    
    /**
     * Method responsible for returning a New Element Node.
     * @param  element Element.
     * @return New Element Node.
     */
    protected DefaultMutableTreeNode createNode(Element element) {
       return new DefaultMutableTreeNode(element);
    }
    
    /**
     * Method responsible for updating the Element Node.
     * @param element Element. 
     */
    public void updateNode(Element element) {
        if (getNode(element) != null)
            getTreeModel().reload(getNode(element));
    }
    
    /**
     * Method responsible for returning the Object Node.
     * @param  object Object.
     * @return Object Node.
     */
    public DefaultMutableTreeNode getNode(Object object) {
        if (nodes.get(object) != null)
            return (DefaultMutableTreeNode) nodes.get(object);
        return null;
    }
    
    /**
     * Method responsible for returning the Parent Node.
     * @param  node Node.
     * @return Parent Node.
     */
    protected DefaultMutableTreeNode getParentNode(DefaultMutableTreeNode node) {
        if (node.getParent() != null)
            return (DefaultMutableTreeNode) node.getParent();
        return null;
    }
    
    /**
     * Method responsible for returning if the Node is Expanded.
     * @param  node Node.
     * @return Node is Expanded.
     */
    protected boolean isExpanded(DefaultMutableTreeNode node) {
        return tree.isExpanded(new TreePath(node.getPath()));
    }
    
    /**
     * Method responsible for setting the Expanded Node.
     * @param node Tree Node.
     * @param expanded Expanded Flag.
     */
    protected void setExpanded(DefaultMutableTreeNode node, boolean expanded) {
        if (expanded)
            tree.expandPath(new TreePath(node.getPath()));
    }
    
    /**
     * Method responsible for adding a Node Object.
     * @param object Object.
     * @param node Object Node.
     */
    public void addNode(Object object, DefaultMutableTreeNode node) {
        nodes.put(object, node);
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return viewMenu;
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return project;
    }
    
    /**
     * Method responsible for returning the Tree.
     * @return Tree.
     */
    public JTree getTree() {
        return tree;
    }
    
    /**
     * Method responsible for returning the Tree Model.
     * @return Tree Model.
     */
    public DefaultTreeModel getTreeModel() {
        return (DefaultTreeModel) tree.getModel();
    }
    
    /**
     * Method Method responsible for returning the Tree Popup.
     * @return Tree Popup.
     */
    public TreePopup getPopup() {
        return popup;
    }
}