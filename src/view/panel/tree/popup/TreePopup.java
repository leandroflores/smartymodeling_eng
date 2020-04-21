package view.panel.tree.popup;

import funct.FunctView;
import java.awt.Font;
import java.util.HashMap;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import view.panel.tree.base.PanelTree;

/**
 * <p>Class of View <b>TreePopup</b>.</p>
 * <p>Class responsible for defining the <b>Tree Popup</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-20
 * @see    javax.swing.JPopupMenu
 * @see    view.panel.tree.base.PanelTree
 */
public abstract class TreePopup extends JPopupMenu {
    protected final PanelTree panel;
    protected final HashMap   items;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Tree.
     */
    public TreePopup(PanelTree panel) {
        this.panel = panel;
        this.items = new HashMap<>();
    }
    
    /**
     * Method responsible for creating the Menu Items.
     */
    protected void createMenuItems() {
        this.createMenuItem("editMenuItem",   "Edit",   "edit");
        this.createMenuItem("deleteMenuItem", "Delete", "delete");
    }
    
    /**
     * Method responsible for setting the Menu Items Controllers.
     */
    protected abstract void setControllers();
    
    /**
     * Method responsible for adding the Menu Items to Popup.
     */
    protected abstract void addMenuItems();
    
    /**
     * Method responsible for returning a New JMenuItem.
     * @param  id Menu Item Id.
     * @param  title Menu Item Title.
     * @param  url Menu Item Image URL.
     * @return New JMenuItem.
     */
    protected JMenuItem createMenuItem(String id, String title, String url) {
        JMenuItem menuItem = new JMenuItem();
                  menuItem.setText(title);
                  menuItem.setIcon(new FunctView().createImage("icons/" + url + ".png"));
                  menuItem.setFont(new Font("Arial", Font.BOLD, 15));
        this.items.put(id, menuItem);
        return    menuItem;
    }
    
    /**
     * Method responsible for returning the Panel Tree.
     * @return Panel Tree.
     */
    public PanelTree getPanel() {
        return this.panel;
    }

    /**
     * Method responsible for returning the Itens Map.
     * @return Itens Map.
     */
    public HashMap<String, JMenuItem> getItems() {
        return this.items;
    }
    
    /**
     * Method responsible for returning the Edit Menu Item.
     * @return Edit Menu Item.
     */
    public JMenuItem getEditMenuItem() {
        return this.getItems().get("editMenuItem");
    }

    /**
     * Method responsible for returning the Delete Menu Item.
     * @return Delete Menu Item.
     */
    public JMenuItem getDeleteMenuItem() {
        return this.getItems().get("deleteMenuItem");
    }
}