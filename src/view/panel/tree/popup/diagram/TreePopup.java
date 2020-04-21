package view.panel.tree.popup.diagram;

import controller.view.panel.tree.popup.diagram.ControllerMenuItemDelete;
import controller.view.panel.tree.popup.diagram.ControllerMenuItemEdit;
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
 * @since  2019-05-2019
 * @see    javax.swing.JPopupMenu
 * @see    view.panel.tree.base.PanelTree
 */
public final class TreePopup extends JPopupMenu {
    private final PanelTree panel;
    private final HashMap   items;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Tree.
     */
    public TreePopup(PanelTree panel) {
        this.panel = panel;
        this.items = new HashMap<>();
        this.addComponents();
    }
    
    /**
     * Method responsible for adding the Components to Popup.
     */
    protected void addComponents() {
        this.add(this.createMenuItem("editMenuItem",    "Edit",    "edit.png"));
        this.getEditMenuItem().addActionListener(new ControllerMenuItemEdit(this));
        this.add(this.createMenuItem("deleteMenuItem",  "Delete",  "delete.png"));
        this.getDeleteMenuItem().addActionListener(new ControllerMenuItemDelete(this));
//        this.add(this.createMenuItem("detailsMenuItem", "Details", "details.png"));
//        this.getDetailsMenuItem().addActionListener(new ControllerMenuItemDetails(this));
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
                  menuItem.setIcon(new FunctView().createImage("icons/" + url));
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

    /**
     * Method responsible for returning the Details Menu Item.
     * @return Details Menu Item.
     */
    public JMenuItem getDetailsMenuItem() {
        return this.getItems().get("detailsMenuItem");
    }
}