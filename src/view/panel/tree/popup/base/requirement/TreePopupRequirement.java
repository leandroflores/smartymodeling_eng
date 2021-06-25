package view.panel.tree.popup.base.requirement;

import controller.view.panel.tree.popup.item.base.requirement.ControllerMenuItemNew;
import controller.view.panel.tree.popup.item.base.requirement.ControllerMenuItemDelete;
import controller.view.panel.tree.popup.item.base.requirement.ControllerMenuItemEdit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenuItem;
import view.panel.tree.base.requirement.PanelTreeRequirement;
import view.panel.tree.popup.TreePopup;

/**
 * <p>Class of View <b>TreePopupRequirement</b>.</p>
 * <p>Class responsible for defining the <b>Requirement Tree Popup</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-21
 * @see    view.panel.tree.base.requirement.PanelTreeRequirement
 * @see    view.panel.tree.popup.TreePopup
 */
public final class TreePopupRequirement extends TreePopup {
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Tree Requirement.
     */
    public TreePopupRequirement(PanelTreeRequirement panel) {
        super(panel);
        addComponents();
    }
    
    @Override
    protected void createMenuItems() {
        super.createMenuItems();
        createMenuItem("new_requirement", "New Requirement", "menu/requirement/requirement", KeyEvent.VK_R, InputEvent.CTRL_MASK);
        createMenuItem("new_element",     "Add Element",     "add");
        getNewMenu().add(getNewRequirementMenuItem());
    }
    
    @Override
    protected void setControllers() {
        getNewRequirementMenuItem().addActionListener(new ControllerMenuItemNew(this));
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
     * Method responsible for returning the New Requirement Menu Item.
     * @return New Requirement Menu Item.
     */
    public JMenuItem getNewRequirementMenuItem() {
        return getItems().get("new_requirement");
    }
}