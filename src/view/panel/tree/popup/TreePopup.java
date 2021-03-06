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
 * @see    view.panel.tree.PanelTree
 */
public abstract class TreePopup extends JPopupMenu {
    protected final PanelTree panel;
    protected final HashMap menus;
    protected final HashMap items;
    
    /**
     * Default constructor method of Class.
     * @param panelTree Panel Tree.
     */
    public TreePopup(PanelTree panelTree) {
        panel = panelTree;
        menus = new HashMap<>();
        items = new HashMap<>();
    }
    
    /**
     * Method responsible for adding the Components.
     */
    protected void addComponents() {
        createMenuItems();
        setControllers();
        addMenuItems();
    }
    
    /**
     * Method responsible for creating the Menu Items.
     */
    protected void createMenuItems() {
        createMenu("new", "New");
        createMenuItem("edit",   "Edit",   "edit",   KeyEvent.VK_F2, 0);
        createMenuItem("delete", "Delete", "delete", KeyEvent.VK_DELETE, 0);
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
               menus.put(id, menu);
        return menu;
    }
    
    /**
     * Method responsible for returning the Menu by Id.
     * @param  id Menu Id.
     * @return Menu found.
     */
    protected JMenu getMenu(String id) {
        return (JMenu) menus.get(id);
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
                  items.put(id, menuItem);
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
        JMenuItem menuItem = createMenuItem(id, title, path);
                  menuItem.setAccelerator(KeyStroke.getKeyStroke(keychar, mask));
                  menuItem.setMnemonic(keychar);
        return    menuItem;
    }
    
    /**
     * Method responsible for returning the New Menu.
     * @return New Menu.
     */
    public JMenu getNewMenu() {
        return getMenu("new");
    }
    
    /**
     * Method responsible for returning the Edit Menu Item.
     * @return Edit Menu Item.
     */
    public JMenuItem getEditMenuItem() {
        return getItems().get("edit");
    }

    /**
     * Method responsible for returning the Delete Menu Item.
     * @return Delete Menu Item.
     */
    public JMenuItem getDeleteMenuItem() {
        return getItems().get("delete");
    }

    /**
     * Method responsible for returning the Itens Map.
     * @return Itens Map.
     */
    public HashMap<String, JMenuItem> getItems() {
        return items;
    }
    
    /**
     * Method responsible for returning the Panel Tree.
     * @return Panel Tree.
     */
    public PanelTree getPanel() {
        return panel;
    }
}