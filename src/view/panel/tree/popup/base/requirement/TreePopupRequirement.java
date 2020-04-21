package view.panel.tree.popup.base.requirement;

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
        createMenuItems();
        setControllers();
        addMenuItems();
    }
    
    @Override
    protected void createMenuItems() {
        super.createMenuItems();
        this.createMenuItem("addElementMenuItem", "Add Element", "add");
    }
    
    @Override
    protected void setControllers() {
        
    }
    
    @Override
    protected void addMenuItems() {
        add(getAddElementMenuItem());
        add(getEditMenuItem());
        add(getDeleteMenuItem());
    }
    
    /**
     * Method responsible for returning the Add Element Menu Item.
     * @return Add Element Menu Item.
     */
    public JMenuItem getAddElementMenuItem() {
        return this.getItems().get("addElementMenuItem");
    }
}