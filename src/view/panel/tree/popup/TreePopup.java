package view.panel.tree.popup;

import controller.view.panel.tree.popup.event.ControllerMenuItemDelete;
import controller.view.panel.tree.popup.event.ControllerMenuItemDetails;
import controller.view.panel.tree.popup.event.ControllerMenuItemEdit;
import funct.FunctView;
import java.awt.Font;
import java.util.HashMap;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import view.panel.tree.PanelTree;

/**
 * <p>Class of View <b>TreePopup</b>.</p>
 * <p>Class responsible for defining the <b>Tree Popup</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  27/05/2019
 * @see    view.panel.tree.PanelTree
 */
public final class TreePopup extends JPopupMenu {
    protected PanelTree panelTree;
    private final HashMap<String, JMenuItem> items;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Tree.
     */
    public TreePopup(PanelTree panel) {
        this.panelTree = panel;
        this.items     = new HashMap<>();
        this.initComponents();
    }
    
    /**
     * Method responsible for adding Components to Popup.
     */
    protected void initComponents() {
        this.add(this.createMenuItem("menuItemEdit",    "Edit",    "edit.png"));
        this.getMenuItemEdit().addActionListener(new ControllerMenuItemEdit(this));
        this.add(this.createMenuItem("menuItemDelete",  "Delete",  "delete.png"));
        this.getMenuItemDelete().addActionListener(new ControllerMenuItemDelete(this));
        this.add(this.createMenuItem("menuItemDetails", "Details", "details.png"));
        this.getMenuItemDetails().addActionListener(new ControllerMenuItemDetails(this));
    }
    
    /**
     * Method responsible for returning a new JMenuItem.
     * @param  id Menu Item Id.
     * @param  title Menu Item Title.
     * @param  url Menu Item Image URL.
     * @return New JMenuItem.
     */
    protected JMenuItem createMenuItem(String id, String title, String url) {
        JMenuItem menuItem = new JMenuItem();
                  menuItem.setText(title);
                  menuItem.setIcon(new FunctView().createImage("icons/" + url));
                  menuItem.setFont(new Font("Arial", Font.BOLD, 15));
        this.items.put(id, menuItem);
        return    menuItem;
    }
    
    /**
     * Method responsible for returning Panel Tree.
     * @return Panel Tree.
     */
    public PanelTree getPanelTree() {
        return this.panelTree;
    }
    
    /**
     * Method responsible for returning Menu Item Edit.
     * @return Menu Item Edit.
     */
    public JMenuItem getMenuItemEdit() {
        return this.items.get("menuItemEdit");
    }

    /**
     * Method responsible for returning Menu Item Delete.
     * @return Menu Item Delete.
     */
    public JMenuItem getMenuItemDelete() {
        return this.items.get("menuItemDelete");
    }

    /**
     * Metodo responsavel por retornar o JMenuItem Details.
     * @return JMenuItem Details.
     */
    public JMenuItem getMenuItemDetails() {
        return this.items.get("menuItemDetails");
    }
}