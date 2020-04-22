package controller.view.panel.tree.popup.item.base.requirement;

import controller.view.panel.tree.popup.item.ControllerMenuItem;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.requirement.Requirement;
import view.modal.new_.base.requirement.traceability.ViewNewAddElement;
import view.panel.tree.popup.TreePopup;

/**
 * <p>Class of Controller <b>ControllerMenuItemDelete</b>.</p>
 * <p>Class responsible for controlling the <b>MenuItemDelete</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-21
 * @see    controller.view.panel.tree.popup.item.ControllerMenuItem
 * @see    view.panel.tree.popup.TreePopup
 */
public class ControllerMenuItemAdd extends ControllerMenuItem {
    
    /**
     * Default constructor method of Class.
     * @param popup Tree Popup.
     */
    public ControllerMenuItemAdd(TreePopup popup) {
        super(popup);
    }
    
    @Override
    protected void action(DefaultMutableTreeNode node) {
        Object object = node.getUserObject();
        if (object instanceof Requirement)
            new ViewNewAddElement(this.getPanelModeling().getViewMenu(), (Requirement) object).setVisible(true);
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