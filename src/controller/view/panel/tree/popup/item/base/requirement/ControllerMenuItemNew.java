package controller.view.panel.tree.popup.item.base.requirement;

import controller.view.panel.tree.popup.item.ControllerMenuItem;
import javax.swing.JMenuItem;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Project;
import view.modal.new_.base.requirement.ViewNewRequirement;
import view.panel.tree.popup.base.requirement.TreePopupRequirement;

/**
 * <p>Class of Controller <b>ControllerMenuItemNew</b>.</p>
 * <p>Class responsible for controlling the <b>MenuItemNew</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-21
 * @see    controller.view.panel.tree.popup.item.ControllerMenuItem
 * @see    view.panel.tree.popup.base.requirement.TreePopupRequirement
 */
public class ControllerMenuItemNew extends ControllerMenuItem {
    
    /**
     * Default constructor method of Class.
     * @param popup Tree Popup Requirement.
     */
    public ControllerMenuItemNew(TreePopupRequirement popup) {
        super(popup);
    }
    
    @Override
    protected void action(DefaultMutableTreeNode node, JMenuItem item) {
        Object object = node.getUserObject();
        if (object instanceof Project)
            new ViewNewRequirement(this.getPanelModeling().getViewMenu()).setVisible(true);
    }
}