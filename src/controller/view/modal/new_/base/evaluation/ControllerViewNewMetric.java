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
     * @param viewNew View New Metric.
     */
    public ControllerViewNewMetric(ViewNewMetric viewNew) {
        super(viewNew);
    }
    
    @Override
    public boolean check() {
        return this.check(this.getView().getPanelBaseMetric().getNameTextField(),  "Name is required!")
            && this.check(this.getView().getPanelBaseMetric().getLabelTextField(), "Label is required!")
            && this.check(this.getView().getPanelBaseOperation().getOperationTextField(), "Operation is required!");
    }

    @Override
    public void new_() {
        this.getView().getProject().addMetric(this.getView().getMetric());
        this.getView().getViewMenu().setTabIndex(5);
    }
    
    @Override
    public ViewNewMetric getView() {
        return (ViewNewMetric) this.viewModal;
    }
}