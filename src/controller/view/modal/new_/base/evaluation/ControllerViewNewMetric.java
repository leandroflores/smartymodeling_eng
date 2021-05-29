package controller.view.modal.new_.base.evaluation;

import controller.view.modal.new_.ControllerViewNew;
import view.modal.new_.base.evaluation.ViewNewMetric;

/**
 * <p>Class of Controller <b>ControllerViewNewMetric</b>.</p>
 * <p>Class responsible for controlling the <b>ViewNewMetric</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-08-20
 * @see    controller.view.modal.new_.ControllerViewNew
 * @see    model.structural.base.evaluation.Metric
 * @see    view.modal.new_.base.evaluation.ViewNewMetric
 */
public class ControllerViewNewMetric extends ControllerViewNew {

    /**
     * Default constructor method of Class.
     * @param view View New Metric.
     */
    public ControllerViewNewMetric(ViewNewMetric view) {
        super(view);
    }
    
    @Override
    public boolean check() {
        return check(getView().getPanelBaseMetric().getNameTextField(),  "Name is required!")
            && check(getView().getPanelBaseMetric().getLabelTextField(), "Label is required!")
            && check(getView().getPanelBaseOperation().getOperationTextField(), "Operation is required!");
    }

    @Override
    public void new_() {
        getView().getProject().addMetric(getView().getMetric());
        getView().getViewMenu().setTabIndex(5);
    }
    
    @Override
    public ViewNewMetric getView() {
        return (ViewNewMetric) super.getView();
    }
}