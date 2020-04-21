package controller.view.panel.tree.popup.item.base.requirement;

import controller.view.panel.tree.popup.item.ControllerMenuItem;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Element;
import model.structural.base.requirement.Requirement;
import view.delete.base.requirement.ViewDeleteRequirement;
import view.panel.tree.popup.TreePopup;

/**
 * <p>Class of Controller <b>ControllerMenuItemDelete</b>.</p>
 * <p>Class responsible for controlling the <b>MenuItemDelete</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-21
 * @see    controller.view.panel.tree.popup.item.ControllerMenuItem
 * @see    view.panel.tree.popup.TreePopup
 */
public class ControllerMenuItemDelete extends ControllerMenuItem {
    
    /**
     * Default constructor method of Class.
     * @param popup Tree Popup.
     */
    public ControllerMenuItemDelete(TreePopup popup) {
        super(popup);
    }
    
    @Override
    protected void action(DefaultMutableTreeNode node) {
        if (node.getUserObject() instanceof Requirement)
            new ViewDeleteRequirement(this.getPanelModeling(), (Requirement) node.getUserObject()).setVisible(true);
        else if (node.getUserObject() instanceof Element)
            this.getRequirement(node).removeElement((Element) node.getUserObject());
    }

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
}