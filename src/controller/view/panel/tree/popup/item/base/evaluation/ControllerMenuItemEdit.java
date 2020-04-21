package controller.view.panel.tree.popup.item.base.evaluation;

import controller.view.panel.tree.popup.item.ControllerMenuItem;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.evaluation.Metric;
import view.edit.base.evaluation.ViewEditMetric;
import view.panel.tree.popup.base.evaluation.TreePopupEvaluation;

/**
 * <p>Class of Controller <b>ControllerMenuItemEdit</b>.</p>
 * <p>Class responsible for controlling the <b>MenuItemEdit</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-21
 * @see    controller.view.panel.tree.popup.item.ControllerMenuItem
 * @see    view.panel.tree.popup.base.evaluation.TreePopupEvaluation
 */
public class ControllerMenuItemEdit extends ControllerMenuItem {
    
    /**
     * Default constructor method of Class.
     * @param popup Tree Popup Evaluation.
     */
    public ControllerMenuItemEdit(TreePopupEvaluation popup) {
        super(popup);
    }
    
    @Override
    protected void action(DefaultMutableTreeNode node) {
        if (node.getUserObject() instanceof Metric)
            new ViewEditMetric(this.popup.getPanel().getViewMenu().getPanelModeling(), (Metric) node.getUserObject()).setVisible(true);
    }
}