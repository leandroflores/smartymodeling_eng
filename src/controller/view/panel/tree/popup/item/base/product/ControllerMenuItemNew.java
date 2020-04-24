package controller.view.panel.tree.popup.item.base.product;

import controller.view.panel.tree.popup.item.ControllerMenuItem;
import javax.swing.JMenuItem;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Project;
import model.structural.base.product.Product;
import view.modal.new_.base.product.ViewNewInstance;
import view.modal.new_.base.product.ViewNewProduct;
import view.panel.tree.popup.base.product.TreePopupProduct;

/**
 * <p>Class of Controller <b>ControllerMenuItemNew</b>.</p>
 * <p>Class responsible for controlling the <b>MenuItemNew</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-23
 * @see    controller.view.panel.tree.popup.item.ControllerMenuItem
 * @see    view.panel.tree.popup.base.product.TreePopupProduct
 */
public class ControllerMenuItemNew extends ControllerMenuItem {
    
    /**
     * Default constructor method of Class.
     * @param popup Tree Popup Product.
     */
    public ControllerMenuItemNew(TreePopupProduct popup) {
        super(popup);
    }
    
    @Override
    protected void action(DefaultMutableTreeNode node, JMenuItem item) {
        Object object = node.getUserObject();
        if (object instanceof Project)
            new ViewNewProduct(this.getViewMenu(), this.getViewMenu().getProject()).setVisible(true);
        else if (object instanceof Product)
            new ViewNewInstance(this.getViewMenu()).setVisible(true);
    }
}