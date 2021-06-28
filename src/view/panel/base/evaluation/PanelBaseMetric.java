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
        controller = new ControllerPanelBaseMetric(this);
        setDefaultProperties();
        addComponents();
        getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        setLayout(new GridBagLayout());
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Name*: "),  createConstraints(1, 1, 0, 0));
        add(createTextField("name", metric.getName(), 10), createConstraints(2, 1, 1, 0));
        
        add(createLabel("Label*: "), createConstraints(1, 1, 0, 1));
        add(createTextField("label", metric.getLabel(), 10), createConstraints(2, 1, 1, 1));
        
        createTextArea("description", metric.getDescription());
        add(createLabel("Description: "), createConstraints(1, 1, 0, 2));
        add(getScrollPane("description"), createConstraints(2, 5, 1, 2));
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return getTextField("name");
    }
    
    /**
     * Method responsible for returning the Label Text Field.
     * @return Label Text Field.
     */
    public JTextField getLabelTextField() {
        return getTextField("label");
    }
    
    /**
     * Method responsible for returning the Description Text Area.
     * @return Description Text Area.
     */
    public JTextArea getDescriptionTextArea() {
        return getTextArea("description");
    }
}