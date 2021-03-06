package controller.view.panel.tree.popup.item.base.requirement;

import controller.view.panel.tree.popup.item.ControllerMenuItem;
import javax.swing.JMenuItem;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Element;
import model.structural.base.requirement.Requirement;
import view.modal.delete.base.requirement.ViewDeleteRequirement;
import view.panel.tree.base.requirement.PanelTreeRequirement;
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
    protected void action(DefaultMutableTreeNode node, JMenuItem item) {
        Object object = node.getUserObject();
        if (object instanceof Requirement)
            new ViewDeleteRequirement(getPanelModeling(), (Requirement) object).setVisible(true);
        else if (object instanceof Element)
            delete(getRequirement(node), (Element) object);
    }

    /**
     * Method responsible for Deleting a Element if Requirement.
     * @param requirement Requirement.
     * @param element Element.
     */
    private void delete(Requirement requirement, Element element) {
        requirement.removeElement(element);
        getPanelTree().getViewMenu().updatePanelTree();
        getPanelTree().getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for returning the Panel Tree.
     * @return Panel Tree.
     */
    protected PanelTreeRequirement getPanelTree() {
        return (PanelTreeRequirement) getPopup().getPanel();
    }
}