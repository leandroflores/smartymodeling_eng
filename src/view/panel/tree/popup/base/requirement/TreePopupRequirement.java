package view.panel.tree.popup.base.requirement;

import controller.view.panel.tree.popup.item.base.requirement.ControllerMenuItemAdd;
import controller.view.panel.tree.popup.item.base.requirement.ControllerMenuItemDelete;
import controller.view.panel.tree.popup.item.base.requirement.ControllerMenuItemEdit;
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
        this.addComponents();
    }
    
    @Override
    protected void createMenuItems() {
        super.createMenuItems();
        this.createMenuItem("new_requirement", "New Requirement", "menu/requirement/requirement");
        this.createMenuItem("new_element",     "Add Element",     "add");
    }
    
    @Override
    protected void setControllers() {
        this.getNewRequirementMenuItem().addActionListener(new ControllerMenuItemAdd(this));
        this.getAddElementMenuItem().addActionListener(new ControllerMenuItemAdd(this));
        this.getEditMenuItem().addActionListener(new ControllerMenuItemEdit(this));
        this.getDeleteMenuItem().addActionListener(new ControllerMenuItemDelete(this));
    }
    
    @Override
    protected void addMenuItems() {
        this.add(this.getNewRequirementMenuItem());
        this.add(this.getAddElementMenuItem());
        this.addSeparator();
        this.add(this.getEditMenuItem());
        this.addSeparator();
        this.add(this.getDeleteMenuItem());
    }
    
    /**
     * Method responsible for returning the New Requirement Menu Item.
     * @return New Requirement Menu Item.
     */
    public JMenuItem getNewRequirementMenuItem() {
        return this.getItems().get("new_requirement");
    }
    
    /**
     * Method responsible for returning the Add Element Menu Item.
     * @return Add Element Menu Item.
     */
    public JMenuItem getAddElementMenuItem() {
        return this.getItems().get("new_element");
    }
}