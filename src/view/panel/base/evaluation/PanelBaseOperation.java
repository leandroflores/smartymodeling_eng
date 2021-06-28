package view.panel.base.evaluation;

import controller.view.panel.base.evaluation.ControllerPanelBaseOperation;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.controller.structural.base.evaluation.ControllerMetric;
import model.structural.base.evaluation.Metric;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseOperation</b>.</p>
 * <p>Class responsible for defining a <b>Operation Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-08-20
 * @see    controller.view.panel.base.evaluation.ControllerPanelBaseOperation
 * @see    model.structural.base.evaluation.Metric
 * @see    view.panel.base.evaluation.PanelBase
 */
public final class PanelBaseOperation extends PanelBase {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param metric Metric.
     */
    public PanelBaseOperation(ViewMenu view, Metric metric) {
        super(view, metric);
        controller = new ControllerPanelBaseOperation(this);
        setDefaultProperties();
        addComponents();
        setValues();
        getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        setLayout(new GridLayout(5, 2));
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Target*: "));
        add(createComboBox("target", new ControllerMetric(project).getTargets(), 15, getSelectedItem()));
        
        add(createLabel("Operation*: "));
        add(createTextField("operation", "", 15));
    }
    
    /**
     * Method responsible for returning the Selected Item.
     * @return Selected Item.
     */
    private Object getSelectedItem() {
        if (metric.getTarget() != null)
            return metric.getTarget();
        return "";
    }
    
    /**
     * Method responsible for setting the Operation Values.
     */
    public void setValues() {
        setTarget();
        getOperationTextField().setText(metric.getOperation());
    }
    
    /**
     * Method responsible for setting the Target.
     */
    public void setTarget() {
        metric.setTarget(getTargetComboBox().getSelectedItem().toString());
    }
    
    /**
     * Method responsible for returning the Target Combo Box.
     * @return Target Combo Box.
     */
    public JComboBox getTargetComboBox() {
        return getComboBox("target");
    }
    
    /**
     * Method responsible for returning the Operation Text Field.
     * @return Operation Text Field.
     */
    public JTextField getOperationTextField() {
        return getTextField("operation");
    }
}