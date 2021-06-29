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
        controller = new ControllerPanelBaseEvaluation(this);
        setDefaultProperties();
        addComponents();
        addFooter();
        setValues();
    }
    
    @Override
    protected void setDefaultProperties() {
        setLayout(new GridBagLayout());
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Operation*: "), createConstraints(1, 1, 0, 0));
        add(createTextFieldNoEditable("operation", "", 15), createConstraints(5, 1, 1, 0));
        
        add(createLabel("Target: "), createConstraints(1, 1, 0, 1));
        add(createComboBox("target", getTargets(), 15), createConstraints(5, 1, 1, 1));
        
        add(createLabel("Value: "), createConstraints(1, 1, 0, 2));
        add(createTextFieldNoEditable("value", "", 15), createConstraints(5, 1, 1, 2));
        
        createList("details");
        add(getDetailsScrollPane(), createConstraints(6, 1, 0, 3));
    }
    
    @Override
    public void addFooter() {
        add(getFooter(), createConstraints(6, 1, 0, 4));
        getNextButton().setEnabled(false);
    }
    
    /**
     * Method responsible for setting the Operation Values.
     */
    public void setValues() {
        getOperationTextField().setText(measure.getMetric().getOperation());
        updateTarget();
    }
    
    /**
     * Method responsible for updating the Target Panel.
     */
    public void updateTarget() {
        Object target = getViewNew().getPanelBaseTarget().getTargetComboBox().getSelectedItem();
        getTargetComboBox().setSelectedItem(target);
    }
    
    /**
     * Method responsible for updating the Details.
     * @param list Objects List.
     */
    public void updateDetails(List<Object> list) {
        getDetailsList().removeAll();
        DefaultListModel model = new DefaultListModel();
        for (Object object :  list)
            model.addElement(object);
        getDetailsList().setModel(model);
    }
    
    /**
     * Method responsible for returning the Operation Text Field.
     * @return Operation Text Field.
     */
    public JTextField getOperationTextField() {
        return getTextField("operation");
    }
    
    /**
     * Method responsible for returning the Target Combo Box.
     * @return Target Combo Box.
     */
    public JComboBox getTargetComboBox() {
        return getComboBox("target");
    }
    
    /**
     * Method responsible for returning the Value Text Field.
     * @return Value Text Field.
     */
    public JTextField getValueTextField() {
        return getTextField("value");
    }
    
    /**
     * Method responsible for return the Details List.
     * @return Details List.
     */
    public JList getDetailsList() {
        return getList("details");
    }
    
    /**
     * Method responsible for return the Details Scroll Pane.
     * @return Details Scroll Pane.
     */
    public JScrollPane getDetailsScrollPane() {
        return getScrollPane("details");
    }
}