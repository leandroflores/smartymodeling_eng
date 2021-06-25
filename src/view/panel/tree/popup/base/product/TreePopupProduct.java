package view.panel.tree.popup.base.product;

import controller.view.panel.tree.popup.item.base.product.ControllerMenuItemDelete;
import controller.view.panel.tree.popup.item.base.product.ControllerMenuItemEdit;
import controller.view.panel.tree.popup.item.base.product.ControllerMenuItemNew;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenuItem;
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
        addComponents();
    }
    
    @Override
    protected void createMenuItems() {
        super.createMenuItems();
        createMenuItem("product",  "Product",  "menu/product_line/product",  KeyEvent.VK_P, InputEvent.CTRL_MASK);
        createMenuItem("instance", "Instance", "menu/product_line/instance", KeyEvent.VK_I, InputEvent.CTRL_MASK);
        
        getNewMenu().add(getProductMenuItem());
        getNewMenu().add(getInstanceMenuItem());
    }
    
    @Override
    protected void setControllers() {
        getProductMenuItem().addActionListener(new ControllerMenuItemNew(this));
        getInstanceMenuItem().addActionListener(new ControllerMenuItemNew(this));
        getEditMenuItem().addActionListener(new ControllerMenuItemEdit(this));
        getDeleteMenuItem().addActionListener(new ControllerMenuItemDelete(this));
    }
    
    @Override
    protected void addMenuItems() {
        add(getNewMenu());
        addSeparator();
        add(getEditMenuItem());
        addSeparator();
        add(getDeleteMenuItem());
    }
    
    /**
     * Method responsible for returning the Product Menu Item.
     * @return Product Menu Item.
     */
    public JMenuItem getProductMenuItem() {
        return getItems().get("product");
    }
    
    /**
     * Method responsible for returning the Instance Menu Item.
     * @return Instance Menu Item.
     */
    public JMenuItem getInstanceMenuItem() {
        return getItems().get("instance");
    }
}