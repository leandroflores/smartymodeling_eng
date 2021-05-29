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
     * @param view View New Measure.
     */
    public ControllerViewNewMeasure(ViewNewMeasure view) {
        super(view);
    }
    
    @Override
    public boolean check() {
        return check(getView().getPanelBaseEvaluation().getValueTextField(), "Invalid Value!");
    }
    
    @Override
    public void new_() {
        Measure measure = getView().getMeasure();
                measure.setName(getView().getPanelBaseMeasure().getNameTextField().getText().trim());
                measure.setDate(getView().getPanelBaseMeasure().getDateTextField().getText().trim());
                measure.setMetric((Metric) getView().getPanelBaseMeasure().getMetricComboBox().getSelectedItem());
                measure.setTarget(getValue(getView().getPanelBaseTarget().getTargetComboBox()));
                measure.setValue(getDouble(getView().getPanelBaseEvaluation().getValueTextField()));
        getView().getProject().addMeasure(measure);
        getView().getViewMenu().setTabIndex(5);
    }
    
    @Override
    public ViewNewMeasure getView() {
        return (ViewNewMeasure) super.getView();
    }
}