package view.panel.tree;

import controller.view.panel.tree.popup.ControllerTreePopup;
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
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.MethodUML;
import view.Panel;
import view.panel.tree.popup.TreePopup;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelTree</b>.</p>
 * <p>Class responsible for defining the <b>Tree Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-27
 * @see    model.structural.base.Project
 * @see    view.Panel
 */
public abstract class PanelTree extends Panel {
    protected final ViewMenu viewMenu;
    protected final Project  project;
    protected final HashMap  nodes;
    protected TreePopup popup;
    protected JTree tree;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     */
    public PanelTree(ViewMenu viewMenu) {
        this.viewMenu = viewMenu;
        this.project  = this.viewMenu.getProject();
        this.nodes    = new HashMap();
    }
    
    @Override
    public void addComponents() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.initTree();
        this.addControllers();
        this.add(this.tree);
    }
    
    /**
     * Method responsible for initializing the Tree.
     */
    protected void initTree() {
        this.tree  = new JTree(this.createNode(this.getProject()));
        this.popup = new TreePopup(this);
    }
    
    /**
     * Method responsible for adding the Tree Controllers.
     */
    protected void addControllers() {
        this.tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        this.tree.addMouseListener(new ControllerTreePopup(this.popup));
        this.tree.addKeyListener(new ControllerTreePopup(this.popup));
    }
    
    /**
     * Method responsible for expanding the Tree.
     */
    protected void expandTree() {
        for (int i = 0; i < this.tree.getRowCount(); i++)
            this.tree.expandRow(i);
    }
    
    /**
     * Method responsible for returning a New Project Node.
     * @param  project Project.
     * @return New Project Node.
     */
    protected abstract DefaultMutableTreeNode createNode(Project project);
    
    /**
     * Method responsible for updating the Tree.
     */
    public void updateTree() {
        this.getTreeModel().reload(this.getNode(this.project));
    }
    
    /**
     * Method responsible for updating the Diagram Node.
     * @param diagram Diagram. 
     */
    public void updateNode(Diagram diagram) {
        DefaultMutableTreeNode node = this.getNode(diagram);
        if (node != null) {
            boolean expanded = this.tree.isExpanded(new TreePath(node.getPath()));
            node = this.getNode(diagram);
            this.getTreeModel().reload(node);
            if (expanded)
                this.tree.expandPath(new TreePath(node.getPath()));
        }
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
            this.addNode(element, node);
        }
    }
    
    /**
     * Method responsible for checking the Element is not Method or Attribute.
     * @param  element Element.
     * @return Element is not a Method or Attribute.
     */
    private boolean checkElement2(Element element) {
        return  (element instanceof AttributeUML == false)
             && (element instanceof MethodUML    == false);
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
        if (this.createNode(element) != null)
            this.getTreeModel().reload(this.createNode(element));
    }
    
    /**
     * Method responsible for returning the Object Node.
     * @param  object Object.
     * @return Object Node.
     */
    public DefaultMutableTreeNode getNode(Object object) {
        if (this.nodes.get(object) != null)
            return (DefaultMutableTreeNode) this.nodes.get(object);
        return null;
    }
    
    /**
     * Method responsible for returning the Parent Node.
     * @param  node Node.
     * @return Parent Node.
     */
    protected DefaultMutableTreeNode getParent(DefaultMutableTreeNode node) {
        if (node.getParent() != null)
            return (DefaultMutableTreeNode) node.getParent();
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
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return this.viewMenu.getProject();
    }
    
    /**
     * Method responsible for adding a Node Object.
     * @param object Object.
     * @param node Object Node.
     */
    public void addNode(Object object, DefaultMutableTreeNode node) {
        this.nodes.put(object, node);
    }
    
    /**
     * Method responsible for returning the Tree.
     * @return Tree.
     */
    public JTree getTree() {
        return this.tree;
    }
    
    /**
     * Method responsible for returning the Tree.
     * @return Tree Model.
     */
    public DefaultTreeModel getTreeModel() {
        return (DefaultTreeModel) this.tree.getModel();
    }
}