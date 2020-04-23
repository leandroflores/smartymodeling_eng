package view.panel.tree.popup.feature;

import controller.view.panel.tree.popup.item.feature.ControllerMenuItemDelete;
import controller.view.panel.tree.popup.item.feature.ControllerMenuItemEdit;
import controller.view.panel.tree.popup.item.feature.ControllerMenuItemNew;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenuItem;
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
    protected void createMenuItems() {
        super.createMenuItems();
        this.createMenuItem("feature_diagram",  "Feature Diagram",  "menu/diagram/feature",  KeyEvent.VK_F, InputEvent.CTRL_MASK);
        this.getNewMenu().add(this.getFeatureDiagramMenuItem());
    }
    
    @Override
    protected void setControllers() {
        this.getFeatureDiagramMenuItem().addActionListener(new ControllerMenuItemNew(this));
        this.getEditMenuItem().addActionListener(new ControllerMenuItemEdit(this));
        this.getDeleteMenuItem().addActionListener(new ControllerMenuItemDelete(this));
    }
    
    @Override
    protected void addMenuItems() {
        this.add(getNewMenu());
        this.addSeparator();
        this.add(getEditMenuItem());
        this.addSeparator();
        this.add(getDeleteMenuItem());
    }
    
    /**
     * Method responsible for returning the Feature Diagram Menu Item.
     * @return Feature Diagram Menu Item.
     */
    public JMenuItem getFeatureDiagramMenuItem() {
        return this.getItems().get("feature_diagram");
    }
}