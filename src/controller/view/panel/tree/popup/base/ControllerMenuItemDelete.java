package controller.view.panel.tree.popup.base;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Diagram;
import view.panel.modeling.PanelModeling;
import view.panel.tree.popup.TreePopup;

/**
 * <p>Class of Controller <b>ControllerMenuItemDelete</b>.</p>
 * <p>Class responsible for controlling the <b>MenuItemDelete</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-21
 * @see    java.awt.event.ActionListener
 * @see    view.panel.tree.popup.TreePopup
 */
public abstract class ControllerMenuItemDelete implements ActionListener {
    protected final TreePopup popup;
    
    /**
     * Default constructor method of Class.
     * @param popup Tree Popup.
     */
    public ControllerMenuItemDelete(TreePopup popup) {
        this.popup = popup;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.getSelectedNode() != null)
            this.delete(this.getSelectedNode());
    }
    
    /**
     * Method responsible for deleting the Object Node.
     * @param node 
     */
    protected abstract void delete(DefaultMutableTreeNode node);
    
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
        return (DefaultMutableTreeNode) this.popup.getPanel().getTree().getLastSelectedPathComponent();
    }
    
    /**
     * Method responsible for returning the Panel Modeling.
     * @return Panel Modeling.
     */
    protected PanelModeling getPanelModeling() {
        return this.popup.getPanel().getViewMenu().getPanelModeling();
    }
}