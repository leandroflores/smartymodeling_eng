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
        this.controller = new ControllerPanelBaseMeasure(this);
        this.setDefaultProperties();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridBagLayout());
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Name*: "),   this.createConstraints(1, 1, 0, 0));
        this.add(this.createTextField("nameTextField", this.measure.getName(), 10), this.createConstraints(3, 1, 1, 0));
        
        this.add(this.createLabel("Date*: "),   this.createConstraints(1, 1, 0, 1));
        this.add(this.createTextField("dateTextField", this.measure.getDate(), 10), this.createConstraints(3, 1, 1, 1));
        
        this.add(this.createLabel("Metric*: "), this.createConstraints(1, 1, 0, 2));
        this.add(this.createComboBox("metricComboBox", this.getMetrics(), 15, this.getSelectedItem()), this.createConstraints(3, 1, 1, 2));
    }
    
    /**
     * Method responsible for adding the Panel Footer.
     */
    @Override
    public void addFooter() {
        this.add(this.getFooter(), this.createConstraints(4, 1, 0, 3));
        this.getReturnButton().setEnabled(false);
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