package controller.view.panel.tree.popup.item.base.requirement;

import controller.view.panel.tree.popup.item.ControllerMenuItem;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.requirement.Requirement;
import view.edit.base.requirement.ViewEditRequirement;
import view.panel.tree.popup.TreePopup;

/**
 * <p>Class of Controller <b>ControllerMenuItemEdit</b>.</p>
 * <p>Class responsible for controlling the <b>MenuItemEdit</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-21
 * @see    controller.view.panel.tree.popup.item.ControllerMenuItem
 * @see    view.panel.tree.popup.TreePopup
 */
public class ControllerMenuItemEdit extends ControllerMenuItem {
    
    /**
     * Default constructor method of Class.
     * @param popup Tree Popup.
     */
    public ControllerMenuItemEdit(TreePopup popup) {
        super(popup);
    }
    
    @Override
    protected void action(DefaultMutableTreeNode node) {
        if (node.getUserObject() instanceof Requirement)
            new ViewEditRequirement(this.popup.getPanel().getViewMenu().getPanelModeling(), (Requirement) node.getUserObject()).setVisible(true);
    }
}