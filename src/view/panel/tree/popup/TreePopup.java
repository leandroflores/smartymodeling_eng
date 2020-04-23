package view.panel.tree.popup;

import funct.FunctView;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import view.panel.tree.PanelTree;
import view.style.ViewStyle;

/**
 * <p>Class of View <b>TreePopup</b>.</p>
 * <p>Class responsible for defining the <b>Tree Popup</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-20
 * @see    javax.swing.JPopupMenu
 * @see    view.panel.tree1.PanelTree
 */
public abstract class TreePopup extends JPopupMenu {
    protected final PanelTree panel;
    protected final HashMap   menus;
    protected final HashMap   items;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Tree.
     */
    public TreePopup(PanelTree panel) {
        this.panel = panel;
        this.menus = new HashMap<>();
        this.items = new HashMap<>();
    }
    
    /**
     * Method responsible for adding the Components.
     */
    protected void addComponents() {
        this.createMenuItems();
        this.setControllers();
        this.addMenuItems();
    }
    
    /**
     * Method responsible for creating the Menu Items.
     */
    protected void createMenuItems() {
        this.createMenu("new_menu", "New");
        this.createMenuItem("editMenuItem",   "Edit",   "edit",   KeyEvent.VK_F2, 0);
        this.createMenuItem("deleteMenuItem", "Delete", "delete", KeyEvent.VK_DELETE, 0);
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
     * Method responsible for returning a New Menu.
     * @param  id Menu Id.
     * @param  title Menu Title.
     * @return New Menu.
     */
    protected JMenu createMenu(String id, String title) {
        JMenu  menu = new JMenu(title);
               menu.setFont(new Font(ViewStyle.STYLE, ViewStyle.BOLD, ViewStyle.SIZE));
               this.menus.put(id, menu);
        return menu;
    }
    
    /**
     * Method responsible for returning the Menu by Id.
     * @param  id Menu Id.
     * @return Menu found.
     */
    protected JMenu getMenu(String id) {
        return (JMenu) this.menus.get(id);
    }
    
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
     * Method responsible for returning a New Menu Item.
     * @param  id Menu Item Id.
     * @param  title Menu Item Title.
     * @param  path Menu Item Image Path.
     * @param  keychar Menu Item Key Char.
     * @param  mask Menu Item Key Mask.
     * @return New Menu Item.
     */
    protected JMenuItem createMenuItem(String id, String title, String path, int keychar, int mask) {
        JMenuItem menuItem = this.createMenuItem(id, title, path);
                  menuItem.setAccelerator(KeyStroke.getKeyStroke(keychar, mask));
                  menuItem.setMnemonic(keychar);
        return    menuItem;
    }
    
    /**
     * Method responsible for returning the New Menu.
     * @return New Menu.
     */
    public JMenu getNewMenu() {
        return this.getMenu("new_menu");
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

    /**
     * Method responsible for returning the Itens Map.
     * @return Itens Map.
     */
    public HashMap<String, JMenuItem> getItems() {
        return this.items;
    }
    
    /**
     * Method responsible for returning the Panel Tree.
     * @return Panel Tree.
     */
    public PanelTree getPanel() {
        return this.panel;
    }
}