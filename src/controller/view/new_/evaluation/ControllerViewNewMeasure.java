package controller.view.new_.evaluation;

import controller.view.new_.ControllerViewNew;
import model.structural.base.evaluation.Measure;
import model.structural.base.evaluation.Metric;
import view.new_.evaluation.ViewNewMeasure;

/**
 * <p>Class of Controller <b>ControllerViewNewMeasure</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewNewMeasure</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  26/03/2019
 * @see    controller.view.new_.ControllerViewNew
 * @see    view.new_.evaluation.ViewNewMeasure
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
        return this.check(this.getView().getPanelBaseMeasure().getNameTextField(),  "Name is required!")
            && this.checkDate(this.getView().getPanelBaseMeasure().getDateTextField(), "Type a Date (YYYY-MM-DD)!")
            && this.check(this.getView().getPanelBaseMeasure().getMetricComboBox(), "Select a Metric!");
    }

    @Override
    public void insert() {
        Measure measure = this.getView().getMeasure();
                measure.setName(this.getView().getPanelBaseMeasure().getNameTextField().getText().trim());
                measure.setDate(this.getView().getPanelBaseMeasure().getDateTextField().getText().trim());
                measure.setMetric((Metric) this.getView().getPanelBaseMeasure().getMetricComboBox().getSelectedItem());
                measure.setValue(this.getDouble(this.getView().getPanelBaseEvaluation().getValueTextField()));
        System.out.println("New Measure: ");
        System.out.println(measure);
        System.out.println("");
//        this.viewNewMeasure.getProject().addMeasure(measure);
    }
    
    @Override
    public ViewNewMeasure getView() {
        return (ViewNewMeasure) this.viewModal;
    }
}