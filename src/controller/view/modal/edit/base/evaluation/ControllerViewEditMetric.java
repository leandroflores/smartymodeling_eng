package controller.view.modal.edit.base.evaluation;

import controller.view.modal.edit.ControllerViewEdit;
import view.modal.edit.base.evaluation.ViewEditMetric;

/**
 * <p>Class of Controller <b>ControllerViewEditMetric</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEditMetric</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-18
 * @see    controller.view.modal.edit.ControllerViewEdit
 * @see    model.structural.base.evaluation.Metric
 * @see    view.modal.edit.base.evaluation.ViewEditMetric
 */
public class ControllerViewEditMetric extends ControllerViewEdit {

    /**
     * Default constructor method of Class.
     * @param view View Edit Metric.
     */
    public ControllerViewEditMetric(ViewEditMetric view) {
        super(view);
    }
    
    @Override
    public boolean check() {
        return this.check(this.getView().getPanelBaseMetric().getNameTextField(),  "Name is required!")
            && this.check(this.getView().getPanelBaseMetric().getLabelTextField(), "Label is required!")
            && this.check(this.getView().getPanelBaseOperation().getOperationTextField(), "Operation is required!");
    }

    @Override
    public void save() {
        this.close();
    }
    
    @Override
    public ViewEditMetric getView() {
        return (ViewEditMetric) this.viewModal;
    }
}