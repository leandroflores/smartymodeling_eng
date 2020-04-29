package view.panel.new_.base.evaluation.measure;

import controller.view.panel.new_.base.evaluation.measure.ControllerPanelBaseEvaluation;
import java.awt.GridBagLayout;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import model.structural.base.evaluation.Measure;
import view.panel.new_.base.evaluation.PanelNewMeasure;

/**
 * <p>Class of View <b>PanelBaseEvaluation</b>.</p> 
 * <p>Class responsible for defining a <b>Measure Evaluation Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-09-03
 * @see    controller.view.panel.new_.base.evaluation.measure.ControllerPanelBaseEvaluation
 * @see    model.structural.base.evaluation.Measure
 * @see    view.panel.new_.base.evaluation.measure.PanelBase
 */
public final class PanelBaseEvaluation extends PanelBase {
    
    /**
     * Default constructor method of Class.
     * @param panel Panel New Measure.
     * @param measure Measure.
     */
    public PanelBaseEvaluation(PanelNewMeasure panel, Measure measure) {
        super(panel, measure);
        this.controller = new ControllerPanelBaseEvaluation(this);
        this.setDefaultProperties();
        this.addComponents();
        this.addFooter();
        this.setValues();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridBagLayout());
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Operation*: "), this.createConstraints(1, 1, 0, 0));
        this.add(this.createTextFieldNoEditable("operationTextField", "", 15), this.createConstraints(5, 1, 1, 0));
        
        this.add(this.createLabel("Target: "), this.createConstraints(1, 1, 0, 1));
        this.add(this.createComboBox("targetComboBox", this.getTargets(), 15), this.createConstraints(5, 1, 1, 1));
        
        this.add(this.createLabel("Value: "), this.createConstraints(1, 1, 0, 2));
        this.add(this.createTextFieldNoEditable("valueTextField", "", 15), this.createConstraints(5, 1, 1, 2));
        
        this.createList("detailsList");
        this.add(this.getDetailsScrollPane(), this.createConstraints(6, 1, 0, 3));
    }
    
    @Override
    public void addFooter() {
        this.add(this.getFooter(), this.createConstraints(6, 1, 0, 4));
        this.getNextButton().setEnabled(false);
    }
    
    /**
     * Method responsible for setting the Operation Values.
     */
    public void setValues() {
        this.getOperationTextField().setText(this.measure.getMetric().getOperation());
        this.updateTarget();
    }
    
    /**
     * Method responsible for updating the Target Panel.
     */
    public void updateTarget() {
        Object target = this.getViewNew().getPanelBaseTarget().getTargetComboBox().getSelectedItem();
        this.getTargetComboBox().setSelectedItem(target);
    }
    
    /**
     * Method responsible for updating the Details.
     * @param list Objects List.
     */
    public void updateDetails(List<Object> list) {
        this.getDetailsList().removeAll();
        DefaultListModel model = new DefaultListModel();
        for (Object object :  list)
            model.addElement(object);
        this.getDetailsList().setModel(model);
    }
    
    /**
     * Method responsible for returning the Operation Text Field.
     * @return Operation Text Field.
     */
    public JTextField getOperationTextField() {
        return this.getTextField("operationTextField");
    }
    
    /**
     * Method responsible for returning the Target Combo Box.
     * @return Target Combo Box.
     */
    public JComboBox getTargetComboBox() {
        return this.getComboBox("targetComboBox");
    }
    
    /**
     * Method responsible for returning the Value Text Field.
     * @return Value Text Field.
     */
    public JTextField getValueTextField() {
        return this.getTextField("valueTextField");
    }
    
    /**
     * Method responsible for return the Details List.
     * @return Details List.
     */
    public JList getDetailsList() {
        return this.getList("detailsList");
    }
    
    /**
     * Method responsible for return the Details Scroll Pane.
     * @return Details Scroll Pane.
     */
    public JScrollPane getDetailsScrollPane() {
        return this.getScrollPane("detailsList");
    }
}