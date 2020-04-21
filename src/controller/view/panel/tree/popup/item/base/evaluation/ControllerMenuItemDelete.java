package controller.view.panel.tree.popup.item.base.evaluation;

import controller.view.panel.tree.popup.item.ControllerMenuItem;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.evaluation.Measure;
import model.structural.base.evaluation.Metric;
import view.delete.base.evaluation.ViewDeleteMeasure;
import view.delete.base.evaluation.ViewDeleteMetric;
import view.panel.tree.popup.base.evaluation.TreePopupEvaluation;

/**
 * <p>Class of Controller <b>ControllerMenuItemDelete</b>.</p>
 * <p>Class responsible for controlling the <b>MenuItemDelete</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-21
 * @see    controller.view.panel.tree.popup.item.ControllerMenuItem
 * @see    view.panel.tree.popup.base.evaluation.TreePopupEvaluation
 */
public class ControllerMenuItemDelete extends ControllerMenuItem {
    
    /**
     * Default constructor method of Class.
     * @param popup Tree Popup.
     */
    public ControllerMenuItemDelete(TreePopupEvaluation popup) {
        super(popup);
    }
    
    @Override
    protected void action(DefaultMutableTreeNode node) {
        if (node.getUserObject() instanceof Metric)
            new ViewDeleteMetric(this.getPanelModeling(),  (Metric) node.getUserObject()).setVisible(true);
        else if (node.getUserObject() instanceof Measure)
            new ViewDeleteMeasure(this.getPanelModeling(), (Measure) node.getUserObject()).setVisible(true);
    }
}