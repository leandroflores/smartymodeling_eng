package controller.view.panel.tree.popup.item.base.evaluation;

import controller.view.panel.tree.popup.item.ControllerMenuItem;
import javax.swing.JMenuItem;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Project;
import model.structural.base.evaluation.Metric;
import view.panel.tree.popup.base.evaluation.TreePopupEvaluation;

/**
 * <p>Class of Controller <b>ControllerMenuItemNew</b>.</p>
 * <p>Class responsible for controlling the <b>MenuItemNew</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-23
 * @see    controller.view.panel.tree.popup.item.ControllerMenuItem
 * @see    view.panel.tree.popup.base.evaluation.TreePopupEvaluation
 */
public class ControllerMenuItemNew extends ControllerMenuItem {
    
    /**
     * Default constructor method of Class.
     * @param popup Tree Popup Evaluation.
     */
    public ControllerMenuItemNew(TreePopupEvaluation popup) {
        super(popup);
    }
    
    @Override
    protected void action(DefaultMutableTreeNode node, JMenuItem item) {
        Object object = node.getUserObject();
        if (object instanceof Project)
            this.getViewMenu().getController().newMetric();
        else if (object instanceof Metric)
            this.getViewMenu().getController().newMeasure();
    }
}