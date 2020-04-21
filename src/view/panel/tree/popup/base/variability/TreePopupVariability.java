package view.panel.tree.popup.base.variability;

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
        this.createMenuItems();
        this.setControllers();
        this.addMenuItems();
    }
    
    @Override
    protected void setControllers() {
//        this.getEditMenuItem().addActionListener(new ControllerMenuItemEdit(this));
//        this.getDeleteMenuItem().addActionListener(new ControllerMenuItemDelete(this));
    }
    
    @Override
    protected void addMenuItems() {
        this.add(getEditMenuItem());
        this.add(getDeleteMenuItem());
    }
}