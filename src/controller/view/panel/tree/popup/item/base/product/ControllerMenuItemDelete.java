package controller.view.panel.tree.popup.item.base.product;

import controller.view.panel.tree.popup.item.ControllerMenuItem;
import javax.swing.JMenuItem;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.product.Artifact;
import model.structural.base.product.Instance;
import model.structural.base.product.Product;
import view.modal.delete.base.product.ViewDeleteArtifact;
import view.modal.delete.base.product.ViewDeleteInstance;
import view.modal.delete.base.product.ViewDeleteProduct;
import view.panel.tree.popup.base.product.TreePopupProduct;

/**
 * <p>Class of Controller <b>ControllerMenuItemDelete</b>.</p>
 * <p>Class responsible for controlling the <b>MenuItemDelete</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-21
 * @see    controller.view.panel.tree.popup.item.ControllerMenuItem
 * @see    view.panel.tree.popup.base.product.TreePopupProduct
 */
public class ControllerMenuItemDelete extends ControllerMenuItem {
    
    /**
     * Default constructor method of Class.
     * @param popup Tree Popup Product.
     */
    public ControllerMenuItemDelete(TreePopupProduct popup) {
        super(popup);
    }
    
    @Override
    protected void action(DefaultMutableTreeNode node, JMenuItem item) {
        Object object = node.getUserObject();
        if (object instanceof Product)
            new ViewDeleteProduct(getPanelModeling(),  (Product) object).setVisible(true);
        else if (object instanceof Instance)
            new ViewDeleteInstance(getPanelModeling(), (Instance) object).setVisible(true);
        else if (object instanceof Artifact)
            new ViewDeleteArtifact(getPanelModeling(), (Artifact) object).setVisible(true);
    }
}