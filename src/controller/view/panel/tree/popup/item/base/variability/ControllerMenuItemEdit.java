package controller.view.panel.tree.popup.item.base.variability;

import controller.view.panel.tree.popup.item.ControllerMenuItem;
import javax.swing.JMenuItem;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Diagram;
import model.structural.base.Project;
import model.structural.base.variability.Variability;
import view.modal.edit.base.ViewEditDiagram;
import view.modal.edit.base.ViewEditProject;
import view.modal.edit.base.variability.ViewEditVariability;
import view.panel.tree.popup.base.variability.TreePopupVariability;

/**
 * <p>Class of Controller <b>ControllerMenuItemEdit</b>.</p>
 * <p>Class responsible for controlling the <b>MenuItemEdit</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-21
 * @see    controller.view.panel.tree.popup.item.ControllerMenuItem
 * @see    view.panel.tree.popup.base.variability.TreePopupVariability
 */
public class ControllerMenuItemEdit extends ControllerMenuItem {
    
    /**
     * Default constructor method of Class.
     * @param popup Tree Popup Variability.
     */
    public ControllerMenuItemEdit(TreePopupVariability popup) {
        super(popup);
    }

    @Override
    protected void action(DefaultMutableTreeNode node, JMenuItem item) {
        Object object = node.getUserObject();
        if (object instanceof Project)
            new ViewEditProject(getPanelModeling(), (Project) object).setVisible(true);
        else if (object instanceof Diagram)
            new ViewEditDiagram(getPanelModeling(), (Diagram) object).setVisible(true);
        else if (object instanceof Variability)
            new ViewEditVariability(getPanelModeling(), getDiagram(node), (Variability) object).setVisible(true);
    }
}