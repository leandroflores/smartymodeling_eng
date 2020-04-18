package controller.view.new_.evaluation;

import controller.view.new_.ControllerViewNew;
import view.new_.evaluation.ViewNewMetric;

/**
 * <p>Class of Controller <b>ControllerViewNewMetric</b>.</p>
 * <p>Class responsible for controlling the <b>ViewNewMetric</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-08-20
 * @see    controller.view.new_.ControllerViewNew
 * @see    model.structural.base.evaluation.Metric
 * @see    view.new_.evaluation.ViewNewMetric
 */
public class ControllerViewNewMetric extends ControllerViewNew {
    private final ViewNewMetric viewNewMetric;

    /**
     * Default constructor method of Class.
     * @param viewNew View New Metric.
     */
    public ControllerViewNewMetric(ViewNewMetric viewNew) {
        super(viewNew);
        this.viewNewMetric = viewNew;
    }
    
    @Override
    public boolean check() {
        return this.check(this.viewNewMetric.getPanelBaseMetric().getNameTextField(),  "Name is required!")
            && this.check(this.viewNewMetric.getPanelBaseMetric().getLabelTextField(), "Label is required!")
            && this.check(this.viewNewMetric.getPanelBaseOperation().getOperationTextField(), "Operation is required!");
    }

    @Override
    public void insert() {
        this.viewNewMetric.getProject().addMetric(this.viewNewMetric.getMetric());
    }
    
    @Override
    public ViewNewMetric getView() {
        return (ViewNewMetric) this.viewModal;
    }
}