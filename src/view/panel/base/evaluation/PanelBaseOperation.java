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
        this.controller = new ControllerPanelBaseOperation(this);
        this.setDefaultProperties();
        this.addComponents();
        this.setValues();
        this.getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridLayout(5, 2));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Target*: "));
        this.add(this.createComboBox("targetComboBox", new ControllerMetric(project).getTargets(), 15, this.getSelectedItem()));
        
        this.add(this.createLabel("Operation*: "));
        this.add(this.createTextField("operationTextField", "", 15));
    }
    
    /**
     * Method responsible for returning the Selected Item.
     * @return Selected Item.
     */
    private Object getSelectedItem() {
        if (this.metric.getTarget() != null)
            return this.metric.getTarget();
        return "";
    }
    
    /**
     * Method responsible for setting the Operation Values.
     */
    public void setValues() {
        this.setTarget();
        this.getOperationTextField().setText(this.metric.getOperation());
    }
    
    /**
     * Method responsible for setting the Target.
     */
    public void setTarget() {
        this.metric.setTarget(this.getTargetComboBox().getSelectedItem().toString());
    }
    
    /**
     * Method responsible for returning the Target Combo Box.
     * @return Target Combo Box.
     */
    public JComboBox getTargetComboBox() {
        return this.getComboBox("targetComboBox");
    }
    
    /**
     * Method responsible for returning the Operation Text Field.
     * @return Operation Text Field.
     */
    public JTextField getOperationTextField() {
        return this.getTextField("operationTextField");
    }
}