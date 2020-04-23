package controller.view.panel.tree.popup.item.base.requirement;

import controller.view.panel.tree.popup.item.ControllerMenuItem;
import javax.swing.JMenuItem;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Project;
import model.structural.base.requirement.Requirement;
import view.modal.new_.base.requirement.ViewNewRequirement;
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
    protected void action(DefaultMutableTreeNode node, JMenuItem item) {
        Object object = node.getUserObject();
        if (object instanceof Project)
            new ViewNewRequirement(this.getPanelModeling().getViewMenu()).setVisible(true);
        else if (object instanceof Requirement)
            new ViewNewAddElement(this.getPanelModeling().getViewMenu(), (Requirement) object).setVisible(true);
    }
}