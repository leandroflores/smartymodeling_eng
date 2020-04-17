package view.panel.base.evaluation.measure;

import controller.view.panel.base.evaluation.measure.ControllerPanelBaseNewMeasure;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.structural.base.evaluation.Measure;
import model.structural.base.evaluation.Metric;
import view.new_.evaluation.ViewNewMeasure;

/**
 * <p>Class of View <b>PanelBaseNewMeasure</b>.</p> 
 * <p>Class responsible for defining a <b>New Measure Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-09-02
 * @see    controller.view.panel.base.evaluation.measure.ControllerPanelBaseNewMeasure
 * @see    model.structural.base.evaluation.Measure
 * @see    view.panel.base.evaluation.measure.PanelBase
 */
public final class PanelBaseNewMeasure extends PanelBase {
    
    /**
     * Default constructor method of Class.
     * @param view View New Measure.
     * @param measure Measure.
     */
    public PanelBaseNewMeasure(ViewNewMeasure view, Measure measure) {
        super(view, measure);
        this.controller = new ControllerPanelBaseNewMeasure(this);
        this.setDefaultProperties();
        this.addComponents();
        this.addFooter();
        this.setValues();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridLayout(4, 2));
        this.setPreferredSize(new Dimension(50, 50));
        this.setSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Name*: "));
        this.add(this.createTextField("nameTextField",  "", 10));
        
        this.add(this.createLabel("Date*: "));
        this.add(this.createTextField("dateTextField", "", 10));
        
        this.add(this.createLabel("Metric*: "));
        this.add(this.createComboBox("metricComboBox", this.getMetrics(), 15, this.getSelectedItem()));
    }
    
    /**
     * Method responsible for returning the Selected Item.
     * @return Selected Item.
     */
    private Object getSelectedItem() {
        if (this.measure.getMetric() != null)
            return this.measure.getMetric();
        return "";
    }
    
    /**
     * Method responsible for setting the Measure Values.
     */
    public void setValues() {
        this.getNameTextField().setText(this.measure.getName());
        this.getDateTextField().setText(this.measure.getDate());
        this.setMetric();
    }
    
    /**
     * Method responsible for setting the Metric.
     */
    public void setMetric() {
        this.measure.setMetric((Metric) this.getMetricComboBox().getSelectedItem());
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.getTextField("nameTextField");
    }
    
    /**
     * Method responsible for returning the Date Text Field.
     * @return Date Text Field.
     */
    public JTextField getDateTextField() {
        return this.getTextField("dateTextField");
    }
    
    /**
     * Method responsible for returning the Metric Combo Box.
     * @return Metric Combo Box.
     */
    public JComboBox getMetricComboBox() {
        return this.getComboBox("metricComboBox");
    }
}