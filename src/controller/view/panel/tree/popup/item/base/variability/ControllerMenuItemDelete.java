package controller.view.panel.tree.popup.item.base.variability;

import controller.view.panel.tree.popup.item.ControllerMenuItem;
import javax.swing.JMenuItem;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.variability.Variability;
import view.modal.delete.base.variability.ViewDeleteVariability;
import view.modal.message.ViewError;
import view.panel.tree.popup.base.variability.TreePopupVariability;

/**
 * <p>Class of Controller <b>ControllerMenuItemDelete</b>.</p>
 * <p>Class responsible for controlling the <b>MenuItemDelete</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-21
 * @see    controller.view.panel.tree.popup.item.ControllerMenuItem
 * @see    view.panel.tree.popup.base.variability.TreePopupVariability
 */
public class ControllerMenuItemDelete extends ControllerMenuItem {
    
    /**
     * Default constructor method of Class.
     * @param popup Tree Popup Variability.
     */
    public ControllerMenuItemDelete(TreePopupVariability popup) {
        super(popup);
    }

    @Override
    protected void action(DefaultMutableTreeNode node, JMenuItem item) {
        Object object = node.getUserObject();
        if (object instanceof Variability)
            new ViewDeleteVariability(getPanelModeling(), getDiagram(node), (Variability) object).setVisible(true);
        else if (object instanceof Element)
            delete((Element) object, node);
    }
    
    /**
     * Method responsible for deleting the Element by Node.
     * @param element Element.
     * @param node Tree Node.
     */
    private void delete(Element element, DefaultMutableTreeNode node) {
        Diagram diagram = getDiagram(node);
        if (getVariability(node) != null)
            delete(diagram, getVariability(node), element);
    }
    
    /**
     * Method responsible for deleting a Element of Variability.
     * @param diagram Diagram.
     * @param variability Variability.
     * @param element Element.
     */
    private void delete(Diagram diagram, Variability variability, Element element) {
        if (variability.getVariationPoint().equals(element))
            new ViewError(getViewMenu(), "Set a New Variation Point!").setVisible(true);
        else
            variability.removeVariant(element);
        diagram.updateElementsStereotype();
        getPopup().getPanel().updateTree();
    }
    
    /**
     * Method responsible for returning the Variability Parent Node.
     * @param  node Tree Node.
     * @return Variability Parent.
     */
    protected Variability getVariability(DefaultMutableTreeNode node) {
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
        if (parent != null && parent.getUserObject() instanceof Variability)
            return (Variability) parent.getUserObject();
        return null;
    }
}