package controller.view.panel.tree.popup.item.base.product;

import controller.view.panel.tree.popup.item.ControllerMenuItem;
import javax.swing.JMenuItem;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.product.Instance;
import model.structural.base.product.Product;
import view.modal.edit.base.product.ViewEditInstance;
import view.modal.edit.base.product.ViewEditProduct;
import view.panel.tree.popup.base.product.TreePopupProduct;

/**
 * <p>Class of Controller <b>ControllerMenuItemEdit</b>.</p>
 * <p>Class responsible for controlling the <b>MenuItemEdit</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-21
 * @see    controller.view.panel.tree.popup.item.ControllerMenuItem
 * @see    view.panel.tree.popup.base.product.TreePopupProduct
 */
public class ControllerMenuItemEdit extends ControllerMenuItem {
    
    /**
     * Default constructor method of Class.
     * @param popup Tree Popup Product.
     */
    public ControllerMenuItemEdit(TreePopupProduct popup) {
        super(popup);
    }
    
    @Override
    protected void action(DefaultMutableTreeNode node, JMenuItem item) {
        Object object = node.getUserObject();
        if (object instanceof Product)
            new ViewEditProduct(this.getPanelModeling(), (Product) object).setVisible(true);
        else if (object instanceof Instance)
            new ViewEditInstance(this.getPanelModeling(), (Instance) object).setVisible(true);
    }
}