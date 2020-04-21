package view.panel.tree.popup.feature;

import controller.view.panel.tree.popup.item.feature.ControllerMenuItemDelete;
import controller.view.panel.tree.popup.item.feature.ControllerMenuItemEdit;
import view.panel.tree.feature.PanelTreeFeature;
import view.panel.tree.popup.TreePopup;

/**
 * <p>Class of View <b>TreePopupFeature</b>.</p>
 * <p>Class responsible for defining the <b>Feature Tree Popup</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-20
 * @see    view.panel.tree.feature.PanelTreeFeature
 * @see    view.panel.tree.popup.TreePopup
 */
public final class TreePopupFeature extends TreePopup {
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Tree Feature.
     */
    public TreePopupFeature(PanelTreeFeature panel) {
        super(panel);
        this.addComponents();
    }
    
    @Override
    protected void setControllers() {
        this.getEditMenuItem().addActionListener(new ControllerMenuItemEdit(this));
        this.getDeleteMenuItem().addActionListener(new ControllerMenuItemDelete(this));
    }
    
    @Override
    protected void addMenuItems() {
        this.add(getEditMenuItem());
        this.add(getDeleteMenuItem());
    }
}