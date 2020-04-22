package controller.view.panel.tree.popup.item.base.requirement;

import controller.view.panel.tree.popup.item.ControllerMenuItem;
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
    protected void action(DefaultMutableTreeNode node) {
        Object object = node.getUserObject();
        if (object instanceof Requirement)
            new ViewDeleteRequirement(this.getPanelModeling(), (Requirement) object).setVisible(true);
        else if (object instanceof Element)
            this.delete(this.getRequirement(node), (Element) object);
    }

    /**
     * Method responsible for Deleting a Element if Requirement.
     * @param requirement Requirement.
     * @param element Element.
     */
    private void delete(Requirement requirement, Element element) {
        requirement.removeElement(element);
        this.getPanelTree().getViewMenu().updatePanelTree();
        this.getPanelTree().getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for returning the Panel Tree.
     * @return Panel Tree.
     */
    protected PanelTreeRequirement getPanelTree() {
        return (PanelTreeRequirement) this.getPopup().getPanel();
    }
}