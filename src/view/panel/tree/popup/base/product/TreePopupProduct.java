package view.panel.tree.popup.base.product;

import controller.view.panel.tree.popup.item.base.product.ControllerMenuItemDelete;
import controller.view.panel.tree.popup.item.base.product.ControllerMenuItemEdit;
import controller.view.panel.tree.popup.item.base.product.ControllerMenuItemNew;
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
        this.addComponents();
    }
    
    @Override
    protected void createMenuItems() {
        super.createMenuItems();
        this.createMenuItem("product",  "Product",  "menu/product_line/product");
        this.createMenuItem("instance", "Instance", "menu/product_line/instance");
        
        this.getNewMenu().add(this.getProductMenuItem());
        this.getNewMenu().add(this.getInstanceMenuItem());
    }
    
    @Override
    protected void setControllers() {
        this.getProductMenuItem().addActionListener(new ControllerMenuItemNew(this));
        this.getInstanceMenuItem().addActionListener(new ControllerMenuItemNew(this));
        this.getEditMenuItem().addActionListener(new ControllerMenuItemEdit(this));
        this.getDeleteMenuItem().addActionListener(new ControllerMenuItemDelete(this));
    }
    
    @Override
    protected void addMenuItems() {
        this.add(this.getNewMenu());
        this.addSeparator();
        this.add(getEditMenuItem());
        this.addSeparator();
        this.add(getDeleteMenuItem());
    }
    
    /**
     * Method responsible for returning the Product Menu Item.
     * @return Product Menu Item.
     */
    public JMenuItem getProductMenuItem() {
        return this.getItems().get("product");
    }
    
    /**
     * Method responsible for returning the Instance Menu Item.
     * @return Instance Menu Item.
     */
    public JMenuItem getInstanceMenuItem() {
        return this.getItems().get("instance");
    }
}