package controller.view.panel.tree.popup.item;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Diagram;
import model.structural.base.requirement.Requirement;
import view.main.structural.ViewMenu;
import view.panel.modeling.PanelModeling;
import view.panel.tree.popup.TreePopup;

/**
 * <p>Class of Controller <b>ControllerMenuItem</b>.</p>
 * <p>Class responsible for controlling the <b>MenuItem</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-21
 * @see    java.awt.event.ActionListener
 * @see    view.panel.tree.popup.TreePopup
 */
public abstract class ControllerMenuItem implements ActionListener {
    protected final TreePopup popup;
    
    /**
     * Default constructor method of Class.
     * @param popup Tree Popup.
     */
    public ControllerMenuItem(TreePopup popup) {
        this.popup = popup;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (getSelectedNode() != null && getSelectedNode().getUserObject() != null
              && event.getSource() != null && event.getSource() instanceof JMenuItem)
            action(getSelectedNode(), (JMenuItem) event.getSource());
    }
    
    /**
     * Method responsible for action the Object Node.
     * @param node Object Node.
     * @param item Menu Item.
     */
    protected abstract void action(DefaultMutableTreeNode node, JMenuItem item);
    
    /**
     * Method responsible for returning the Requirement Node.
     * @param  node Tree Node.
     * @return Requirement Node.
     */
    protected Requirement getRequirement(DefaultMutableTreeNode node) {
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
        if (parent != null && parent.getUserObject() instanceof Requirement)
            return (Requirement) parent.getUserObject();
        return null;
    }
    
    /**
     * Method responsible for returning the Diagram Node.
     * @param  node JTree Node.
     * @return Diagram Node.
     */
    protected Diagram getDiagram(DefaultMutableTreeNode node) {
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
        while ((parent != null) && !(parent.getUserObject() instanceof Diagram))
               parent = (DefaultMutableTreeNode) parent.getParent();
        return parent == null ? null : (Diagram) parent.getUserObject();
    }
    
    /**
     * Method responsible for returning the Selected Node.
     * @return Selected Node.
     */
    protected DefaultMutableTreeNode getSelectedNode() {
        return (DefaultMutableTreeNode) popup.getPanel().getTree().getLastSelectedPathComponent();
    }
    
    /**
     * Method responsible for returning the Panel Modeling.
     * @return Panel Modeling.
     */
    protected PanelModeling getPanelModeling() {
        return getViewMenu().getPanelModeling();
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    protected ViewMenu getViewMenu() {
        return getPopup().getPanel().getViewMenu();
    }
    
    /**
     * Method responsible for returning the Tree Popup.
     * @return Tree Popup.
     */
    public TreePopup getPopup() {
        return popup;
    }
}