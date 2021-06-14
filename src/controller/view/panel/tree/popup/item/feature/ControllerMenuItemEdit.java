package controller.view.panel.tree.popup.item.feature;

import controller.view.panel.tree.popup.item.ControllerMenuItem;
import javax.swing.JMenuItem;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Diagram;
import model.structural.base.Project;
import model.structural.diagram.FeatureDiagram;
import model.structural.diagram.feature.base.Feature;
import view.modal.edit.base.ViewEditDiagram;
import view.modal.edit.base.ViewEditProject;
import view.modal.edit.diagram.feature.base.ViewEditFeature;
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
     * @param popup Tree Popup Feature.
     */
    public ControllerMenuItemEdit(TreePopupFeature popup) {
        super(popup);
    }
    
    @Override
    protected void action(DefaultMutableTreeNode node, JMenuItem item) {
        Object object = node.getUserObject();
        if (object instanceof Project)
            new ViewEditProject(getPanelModeling(), (Project) object).setVisible(true);
        else if (object instanceof Diagram)
            new ViewEditDiagram(getPanelModeling(), (Diagram) object).setVisible(true);
        else if (object instanceof Feature)
            new ViewEditFeature(getPanelModeling(), (FeatureDiagram) getDiagram(node), (Feature) object).setVisible(true);
    }
}