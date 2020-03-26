package controller.view.new_.evaluation;

import controller.view.new_.ControllerViewNew;
import java.awt.event.ActionEvent;
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
    private final ViewNewMeasure viewNewMeasure;

    /**
     * Default constructor method of Class.
     * @param viewNew View New Measure.
     */
    public ControllerViewNewMeasure(ViewNewMeasure viewNew) {
        super(viewNew);
        this.viewNewMeasure = viewNew;
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
    }

    /**
     * Method responsible for checking the Measure Name.
     * @return Name is checked.
     */
    public boolean checkName() {
        return this.check(this.viewNewMeasure.getPanelBaseMeasure().getNameTextField(), "Name is required!");
    }
    
    /**
     * Method responsible for checking the Measure Date.
     * @return Date is checked.
     */
    public boolean checkDate() {
        return this.checkDate(this.viewNewMeasure.getPanelBaseMeasure().getDateTextField(), "Date is required!");
    }
    
    /**
     * Method responsible for checking the Measure Metric.
     * @return Metric is checked.
     */
    public boolean checkMetric() {
        return this.check(this.viewNewMeasure.getPanelBaseMeasure().getMetricComboBox(), "Select a Metric!");
    }
    
    @Override
    public boolean check() {
        return this.checkName()
            && this.checkDate()
            && this.checkMetric();
    }

    @Override
    public void insert() {
        Measure measure = this.viewNewMeasure.getMeasure();
                measure.setName(this.viewNewMeasure.getPanelBaseMeasure().getNameTextField().getText().trim());
                measure.setDate(this.viewNewMeasure.getPanelBaseMeasure().getDateTextField().getText().trim());
                measure.setMetric((Metric) this.viewNewMeasure.getPanelBaseMeasure().getMetricComboBox().getSelectedItem());
                measure.setValue(this.getDouble(this.viewNewMeasure.getPanelBaseEvaluation().getValueTextField()));
        System.out.println("New Measure: ");
        System.out.println(measure);
        System.out.println("");
//        this.viewNewMeasure.getProject().addMeasure(measure);
        this.close();
    }
}