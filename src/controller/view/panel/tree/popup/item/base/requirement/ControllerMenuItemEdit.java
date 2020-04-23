package controller.view.panel.tree.popup.item.base.requirement;

import controller.view.panel.tree.popup.item.ControllerMenuItem;
import javax.swing.JMenuItem;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.requirement.Requirement;
import view.modal.edit.base.ViewEditProject;
import view.modal.edit.base.requirement.ViewEditRequirement;
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
    protected void action(DefaultMutableTreeNode node, JMenuItem item) {
        Object object = node.getUserObject();
        if (object instanceof Project)
            new ViewEditProject(this.getPanelModeling(), (Project) object).setVisible(true);
        else if (object instanceof Requirement)
            new ViewEditRequirement(this.getPanelModeling(), (Requirement) object, 0).setVisible(true);
        else if (object instanceof Element)
            this.edit(this.getRequirement(node), (Element) object);
    }
    
    /**
     * Method responsible for Editing a Element of Requirement.
     * @param requirement Requirement.
     * @param element Element.
     */
    private void edit(Requirement requirement, Element element) {
        new ViewEditRequirement(this.getPanelModeling(), requirement, element.getDiagram().getIndex()).setVisible(true);
    }
}