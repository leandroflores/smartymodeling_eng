package controller.view.panel.new_.base.evaluation.measure;

import model.structural.base.evaluation.Metric;
import view.panel.new_.base.evaluation.measure.PanelBaseMeasure;

/**
 * <p>Class of Controller <b>ControllerPanelBaseMeasure</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseMeasure</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-09-04
 * @see    controller.view.panel.new_.base.evaluation.measure.ControllerPanelBase
 * @see    view.panel.new_.base.evaluation.measure.PanelBaseMeasure
 */
public class ControllerPanelBaseMeasure extends ControllerPanelBase {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Measure.
     */
    public ControllerPanelBaseMeasure(PanelBaseMeasure panel) {
        super(panel);
    }
    
    @Override
    protected void return_() {}

    @Override
    protected boolean check() {
        return check(getPanel().getNameTextField(),  "Name is required!")
            && checkDate(getPanel().getDateTextField(), "Type a Date (YYYY-MM-DD)!")
            && check(getPanel().getMetricComboBox(), "Select a Metric!");
    }
    
    @Override
    public void next() {
        update();
        getPanel().getPanelNew().addPanelBaseTarget();
    }
    
    @Override
    public void update() {
        getMeasure().setName(getString(getPanel().getNameTextField()));
        getMeasure().setDate(getString(getPanel().getDateTextField()));
        getMeasure().setMetric((Metric) getPanel().getMetricComboBox().getSelectedItem());
        super.refresh();
    }
    
    @Override
    public PanelBaseMeasure getPanel() {
        return (PanelBaseMeasure) panel;
    }
}