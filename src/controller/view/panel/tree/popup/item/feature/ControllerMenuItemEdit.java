package controller.view.panel.tree.popup.item.feature;

import controller.view.panel.tree.popup.item.ControllerMenuItem;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import view.edit.base.ViewEditDiagram;
import view.edit.base.ViewEditElement;
import view.edit.base.ViewEditProject;
import view.panel.tree.popup.feature.TreePopupFeature;

/**
 * <p>Class of Controller <b>ControllerMenuItemEdit</b>.</p>
 * <p>Class responsible for controlling the <b>MenuItemEdit</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-21
 * @see    controller.view.panel.tree.popup.item.ControllerMenuItem
 * @see    view.panel.tree.popup.feature.TreePopupFeature
 */
public class ControllerMenuItemEdit extends ControllerMenuItem {
    
    /**
     * Default constructor method of Class.
     * @param popup Tree Popup pFeature.
     */
    public ControllerMenuItemEdit(TreePopupFeature popup) {
        super(popup);
    }
    
    @Override
    protected void action(DefaultMutableTreeNode node) {
        Object object = node.getUserObject();
        if (object instanceof Project)
            new ViewEditProject(this.getPanelModeling(), (Project) object).setVisible(true);
        else if (object instanceof Diagram)
            new ViewEditDiagram(this.getPanelModeling(), (Diagram) object).setVisible(true);
        else if (object instanceof Element)
            new ViewEditElement(this.getPanelModeling(), this.getDiagram(node), (Element) object).setVisible(true);
    }
}