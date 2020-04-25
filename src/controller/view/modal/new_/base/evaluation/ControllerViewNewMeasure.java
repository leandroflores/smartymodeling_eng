package controller.view.modal.new_.base.evaluation;

import controller.view.modal.new_.ControllerViewNew;
import model.structural.base.evaluation.Measure;
import model.structural.base.evaluation.Metric;
import view.modal.new_.base.evaluation.ViewNewMeasure;

/**
 * <p>Class of Controller <b>ControllerViewNewMeasure</b>.</p>
 * <p>Class responsible for controlling the <b>ViewNewMeasure</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-03-26
 * @see    controller.view.modal.new_.ControllerViewNew
 * @see    model.structural.base.evaluation.Measure
 * @see    view.modal.new_.base.evaluation.ViewNewMeasure
 */
public class ControllerViewNewMeasure extends ControllerViewNew {

    /**
     * Default constructor method of Class.
     * @param viewNew View New Measure.
     */
    public ControllerViewNewMeasure(ViewNewMeasure viewNew) {
        super(viewNew);
    }
    
    @Override
    public boolean check() {
        return this.check(this.getView().getPanelBaseEvaluation().getValueTextField(), "Invalid Value!");
    }

    @Override
    public void insert() {
        super.insert();
    }
    
    @Override
    public void new_() {
        Measure measure = this.getView().getMeasure();
                measure.setName(this.getView().getPanelBaseMeasure().getNameTextField().getText().trim());
                measure.setDate(this.getView().getPanelBaseMeasure().getDateTextField().getText().trim());
                measure.setMetric((Metric) this.getView().getPanelBaseMeasure().getMetricComboBox().getSelectedItem());
                measure.setTarget(this.getValue(this.getView().getPanelBaseTarget().getTargetComboBox()));
                measure.setValue(this.getDouble(this.getView().getPanelBaseEvaluation().getValueTextField()));
        this.getView().getProject().addMeasure(measure);
        this.getView().getViewMenu().setTabIndex(5);
    }
    
    @Override
    public ViewNewMeasure getView() {
        return (ViewNewMeasure) this.viewModal;
    }
}