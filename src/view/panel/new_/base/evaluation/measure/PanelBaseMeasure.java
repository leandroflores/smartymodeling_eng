package view.panel.new_.base.evaluation.measure;

import controller.view.panel.new_.base.evaluation.measure.ControllerPanelBaseMeasure;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.structural.base.evaluation.Measure;
import model.structural.base.evaluation.Metric;
import view.panel.new_.base.evaluation.PanelNewMeasure;

/**
 * <p>Class of View <b>PanelBaseMeasure</b>.</p> 
 * <p>Class responsible for defining a <b>New Measure Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-09-02
 * @see    controller.view.panel.new_.base.evaluation.measure.ControllerPanelBaseMeasure
 * @see    model.structural.base.evaluation.Measure
 * @see    view.panel.new_.base.evaluation.measure.PanelBase
 */
public final class PanelBaseMeasure extends PanelBase {
    
    /**
     * Default constructor method of Class.
     * @param panel Panel New Measure.
     * @param measure Measure.
     */
    public PanelBaseMeasure(PanelNewMeasure panel, Measure measure) {
        super(panel, measure);
        controller = new ControllerPanelBaseMeasure(this);
        setDefaultProperties();
        addComponents();
        addFooter();
    }
    
    @Override
    protected void setDefaultProperties() {
        setLayout(new GridBagLayout());
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Name*: "),   createConstraints(1, 1, 0, 0));
        add(createTextField("name", measure.getName(), 10), createConstraints(3, 1, 1, 0));
        
        add(createLabel("Date*: "),   createConstraints(1, 1, 0, 1));
        add(createTextField("date", measure.getDate(), 10), createConstraints(3, 1, 1, 1));
        
        add(createLabel("Metric*: "), createConstraints(1, 1, 0, 2));
        add(createComboBox("metric", getMetrics(), 15, getSelectedItem()), createConstraints(3, 1, 1, 2));
    }
    
    /**
     * Method responsible for adding the Panel Footer.
     */
    @Override
    public void addFooter() {
        add(getFooter(), createConstraints(4, 1, 0, 3));
        getReturnButton().setEnabled(false);
    }
    
    /**
     * Method responsible for returning the Selected Item.
     * @return Selected Item.
     */
    private Object getSelectedItem() {
        if (measure.getMetric() != null)
            return measure.getMetric();
        return "";
    }
    
    /**
     * Method responsible for setting the Metric.
     */
    public void setMetric() {
        measure.setMetric((Metric) getMetricComboBox().getSelectedItem());
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return getTextField("name");
    }
    
    /**
     * Method responsible for returning the Date Text Field.
     * @return Date Text Field.
     */
    public JTextField getDateTextField() {
        return getTextField("date");
    }
    
    /**
     * Method responsible for returning the Metric Combo Box.
     * @return Metric Combo Box.
     */
    public JComboBox getMetricComboBox() {
        return getComboBox("metric");
    }
}