package view.panel.base.evaluation;

import controller.view.panel.base.evaluation.ControllerPanelBaseMetric;
import java.awt.GridBagLayout;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.structural.base.evaluation.Metric;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseMetric</b>.</p> 
 * <p>Class responsible for defining a <b>Metric Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-23
 * @see    controller.view.panel.base.evaluation.ControllerPanelBaseMetric
 * @see    model.structural.base.evaluation.Metric
 * @see    view.panel.base.evaluation.PanelBase
 */
public final class PanelBaseMetric extends PanelBase {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param metric Metric.
     */
    public PanelBaseMetric(ViewMenu view, Metric metric) {
        super(view, metric);
        this.controller = new ControllerPanelBaseMetric(this);
        this.setDefaultProperties();
        this.addComponents();
        this.getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridBagLayout());
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Name*: "),  this.createConstraints(1, 1, 0, 0));
        this.add(this.createTextField("nameTextField", this.metric.getName(), 10),  this.createConstraints(2, 1, 1, 0));
        
        this.add(this.createLabel("Label*: "), this.createConstraints(1, 1, 0, 1));
        this.add(this.createTextField("labelTextField", this.metric.getLabel(), 10), this.createConstraints(2, 1, 1, 1));
        
        this.createTextArea("descriptionTextArea", this.metric.getDescription());
        this.add(this.createLabel("Description: "), this.createConstraints(1, 1, 0, 2));
        this.add(this.getScrollPane("descriptionTextArea"), this.createConstraints(2, 5, 1, 2));
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.getTextField("nameTextField");
    }
    
    /**
     * Method responsible for returning the Label Text Field.
     * @return Label Text Field.
     */
    public JTextField getLabelTextField() {
        return this.getTextField("labelTextField");
    }
    
    /**
     * Method responsible for returning the Description Text Area.
     * @return Description Text Area.
     */
    public JTextArea getDescriptionTextArea() {
        return this.getTextArea("descriptionTextArea");
    }
}