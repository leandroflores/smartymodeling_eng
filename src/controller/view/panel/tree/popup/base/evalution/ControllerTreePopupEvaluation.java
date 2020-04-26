package controller.view.panel.tree.popup.base.evalution;

import controller.view.panel.tree.popup.ControllerTreePopup;
import java.awt.event.MouseEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Project;
import model.structural.base.evaluation.Measure;
import model.structural.base.evaluation.Metric;
import view.modal.delete.base.evaluation.ViewDeleteMeasure;
import view.modal.delete.base.evaluation.ViewDeleteMetric;
import view.modal.edit.base.evaluation.ViewEditMetric;
import view.panel.tree.popup.base.evaluation.TreePopupEvaluation;

/**
 * <p>Class of Controller <b>ControllerTreePopupEvalution</b>.</p>
 * <p>Class responsible for controlling the <b>TreePopupEvalution</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-21
 * @see    controller.view.panel.tree.popup.ControllerTreePopup
 * @see    view.panel.tree.popup.base.evaluation.TreePopupEvaluation
 */
public class ControllerTreePopupEvaluation extends ControllerTreePopup {
    
    /**
     * Default constructor method of Class.
     * @param popup Tree Popup Evalution.
     */
    public ControllerTreePopupEvaluation(TreePopupEvaluation popup) {
        super(popup);
    }
    
    @Override
    protected void showPopup(DefaultMutableTreeNode node, MouseEvent event) {
        if (node.getUserObject() instanceof Project)
            this.setProjectPopup();
        else if (node.getUserObject() instanceof Metric)
            this.setMetricPopup();
        else if (node.getUserObject() instanceof Measure)
            super.setPopupFlag(false, true, true);
        this.getPopup().show(event.getComponent(), event.getX(), event.getY());
    }
    
    /**
     * Method responsible for setting the Project Popup.
     */
    private void setProjectPopup() {
        super.setPopupFlag(true, true, false);
        this.getPopup().getMetricMenuItem().setVisible(true);
        this.getPopup().getMeasureMenuItem().setVisible(false);
    }
    
    /**
     * Method responsible for setting the Metric Popup.
     */
    private void setMetricPopup() {
        super.setPopupFlag(true, true, true);
        this.getPopup().getMetricMenuItem().setVisible(false);
        this.getPopup().getMeasureMenuItem().setVisible(true);
    }

    @Override
    protected void showPanelEdit(DefaultMutableTreeNode node, Object object) {
        if (object instanceof Project)
            this.getPanelProject().initPanelEditProject();
        else if (object instanceof Metric)
            this.getPanelProject().initPanelEditMetric((Metric) object);
    }
    
    @Override
    protected void delete(DefaultMutableTreeNode node, Object object) {
        if (object instanceof Metric)
            new ViewDeleteMetric(this.getPanelModeling(),  (Metric)  object).setVisible(true);
        else if (object instanceof Measure)
            new ViewDeleteMeasure(this.getPanelModeling(), (Measure) object).setVisible(true);        
    }
    
    @Override
    protected void edit(DefaultMutableTreeNode node, Object object) {
        if (object instanceof Metric)
            new ViewEditMetric(this.getPanelModeling(),  (Metric) object).setVisible(true);
    }
    
    @Override
    protected TreePopupEvaluation getPopup() {
        return (TreePopupEvaluation) this.popup;
    }
}