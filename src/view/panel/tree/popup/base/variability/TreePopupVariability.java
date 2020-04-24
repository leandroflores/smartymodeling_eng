package view.panel.tree.popup.base.variability;

import controller.view.panel.tree.popup.item.base.variability.ControllerMenuItemDelete;
import controller.view.panel.tree.popup.item.base.variability.ControllerMenuItemEdit;
import controller.view.panel.tree.popup.item.base.variability.ControllerMenuItemNew;
import javax.swing.JMenuItem;
import view.panel.tree.base.variability.PanelTreeVariability;
import view.panel.tree.popup.TreePopup;

/**
 * <p>Class of View <b>TreePopupVariability</b>.</p>
 * <p>Class responsible for defining the <b>Variability Tree Popup</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-20
 * @see    view.panel.tree.base.variability.PanelTreeVariability
 * @see    view.panel.tree.popup.TreePopup
 */
public final class TreePopupVariability extends TreePopup {
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Tree Variability.
     */
    public TreePopupVariability(PanelTreeVariability panel) {
        super(panel);
        this.addComponents();
    }
    
    @Override
    protected void createMenuItems() {
        super.createMenuItems();
        this.createMenuItem("variability", "Variability", "variability");
        
        this.getNewMenu().add(this.getVariabilityMenuItem());
    }
    
    @Override
    protected void setControllers() {
        this.getVariabilityMenuItem().addActionListener(new ControllerMenuItemNew(this));
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
     * Method responsible for returning the Variability Menu Item.
     * @return Variability Menu Item.
     */
    public JMenuItem getVariabilityMenuItem() {
        return this.getItems().get("variability");
    }
}