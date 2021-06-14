package controller.view.panel.tree.popup.base.evalution;

import controller.view.panel.tree.popup.ControllerTreePopup;
import java.awt.event.MouseEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Project;
import model.structural.base.evaluation.Measure;
import model.structural.base.evaluation.Metric;
import view.modal.delete.base.evaluation.ViewDeleteMeasure;
import view.modal.delete.base.evaluation.ViewDeleteMetric;
import view.modal.edit.base.evaluation.ViewEditMeasure;
import view.modal.edit.base.evaluation.ViewEditMetric;
import view.panel.tree.popup.base.evaluation.TreePopupEvaluation;

/**
 * <p>Class of Controller <b>ControllerTreePopupEvaluation</b>.</p>
 * <p>Class responsible for controlling the <b>TreePopupEvaluation</b> Events of SMartyModeling.</p>
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
            setProjectPopup();
        else if (node.getUserObject() instanceof Metric)
            setMetricPopup();
        else if (node.getUserObject() instanceof Measure)
            setPopupFlag(false, true, true);
        getPopup().show(event.getComponent(), event.getX(), event.getY());
    }
    
    /**
     * Method responsible for setting the Project Popup.
     */
    private void setProjectPopup() {
        setPopupFlag(true, true, false);
        getPopup().getMetricMenuItem().setVisible(true);
        getPopup().getMeasureMenuItem().setVisible(false);
    }
    
    /**
     * Method responsible for setting the Metric Popup.
     */
    private void setMetricPopup() {
        setPopupFlag(true, true, true);
        getPopup().getMetricMenuItem().setVisible(false);
        getPopup().getMeasureMenuItem().setVisible(true);
    }

    @Override
    protected void showPanelEdit(DefaultMutableTreeNode node, Object object) {
        if (object instanceof Project)
            getPanelProject().initPanelEditProject();
        else if (object instanceof Metric)
            getPanelProject().initPanelEditMetric((Metric) object);
        else if (object instanceof Measure)
            getPanelProject().initPanelEditMeasure((Measure) object);
    }
    
    @Override
    protected void delete(DefaultMutableTreeNode node, Object object) {
        if (object instanceof Metric)
            new ViewDeleteMetric(getPanelModeling(),  (Metric)  object).setVisible(true);
        else if (object instanceof Measure)
            new ViewDeleteMeasure(getPanelModeling(), (Measure) object).setVisible(true);        
    }
    
    @Override
    protected void edit(DefaultMutableTreeNode node, Object object) {
        if (object instanceof Metric)
            new ViewEditMetric(getPanelModeling(),  (Metric)  object).setVisible(true);
        if (object instanceof Measure)
            new ViewEditMeasure(getPanelModeling(), (Measure) object).setVisible(true);
    }
    
    @Override
    protected TreePopupEvaluation getPopup() {
        return (TreePopupEvaluation) popup;
    }
}