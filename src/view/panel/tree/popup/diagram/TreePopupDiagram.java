package view.panel.tree.popup.diagram;

import controller.view.panel.tree.popup.item.diagram.ControllerMenuItemDelete;
import controller.view.panel.tree.popup.item.diagram.ControllerMenuItemEdit;
import view.panel.tree.base.PanelTree;
import view.panel.tree.diagram.PanelTreeDiagram;
import view.panel.tree.popup.TreePopup;

/**
 * <p>Class of View <b>TreePopupDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Diagram Tree Popup</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-20
 * @see    view.panel.tree.diagram.PanelTreeDiagram
 * @see    view.panel.tree.popup.TreePopup
 */
public final class TreePopupDiagram extends TreePopup {
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Tree Diagram.
     */
    public TreePopupDiagram(PanelTreeDiagram panel) {
        super(panel);
        this.createMenuItems();
        this.setControllers();
        this.addMenuItems();
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