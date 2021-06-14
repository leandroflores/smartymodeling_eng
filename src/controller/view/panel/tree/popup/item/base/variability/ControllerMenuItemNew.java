package controller.view.panel.tree.popup.item.base.variability;

import controller.view.panel.tree.popup.item.ControllerMenuItem;
import javax.swing.JMenuItem;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Diagram;
import view.modal.message.ViewError;
import view.modal.new_.base.variability.ViewNewVariability;
import view.panel.tree.popup.base.variability.TreePopupVariability;

/**
 * <p>Class of Controller <b>ControllerMenuItemNew</b>.</p>
 * <p>Class responsible for controlling the <b>MenuItemNew</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-23
 * @see    controller.view.panel.tree.popup.item.ControllerMenuItem
 * @see    view.panel.tree.popup.base.variability.TreePopupVariability
 */
public class ControllerMenuItemNew extends ControllerMenuItem {
    
    /**
     * Default constructor method of Class.
     * @param popup Tree Popup Variability.
     */
    public ControllerMenuItemNew(TreePopupVariability popup) {
        super(popup);
    }
    
    @Override
    protected void action(DefaultMutableTreeNode node, JMenuItem item) {
        Object object = node.getUserObject();
        if (object instanceof Diagram)
            newVariability((Diagram) object);
    }
    
    /**
     * Method responsible for adding a New Variability.
     * @param diagram Diagram.
     */
    private void newVariability(Diagram diagram) {
        if (diagram.getElementsList().isEmpty())
            new ViewError(getViewMenu(), "Diagram with no Elements!").setVisible(true);
        else
            new ViewNewVariability(getViewMenu(), diagram).setVisible(true);
    }
}