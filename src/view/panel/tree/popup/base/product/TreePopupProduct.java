package view.panel.tree.popup.base.product;

import controller.view.panel.tree.popup.item.base.product.ControllerMenuItemDelete;
import controller.view.panel.tree.popup.item.base.product.ControllerMenuItemEdit;
import view.panel.tree.base.product.PanelTreeProduct;
import view.panel.tree.popup.TreePopup;

/**
 * <p>Class of View <b>TreePopupProduct</b>.</p>
 * <p>Class responsible for defining the <b>Product Tree Popup</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-20
 * @see    view.panel.tree.base.product.PanelTreeProduct
 * @see    view.panel.tree.popup.TreePopup
 */
public final class TreePopupProduct extends TreePopup {
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Tree Product.
     */
    public TreePopupProduct(PanelTreeProduct panel) {
        super(panel);
        this.addComponents();
    }
    
    @Override
    protected void setControllers() {
        this.getEditMenuItem().addActionListener(new ControllerMenuItemEdit(this));
        this.getDeleteMenuItem().addActionListener(new ControllerMenuItemDelete(this));
    }
    
    @Override
    protected void addMenuItems() {
        this.add(getEditMenuItem());
        this.add(getDeleteMenuItem());
    }
}