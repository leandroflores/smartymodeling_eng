package view.panel.evaluation;

import controller.view.panel.evaluation.ControllerPanelEvaluation;
import java.awt.GridBagLayout;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;
import model.structural.base.Project;
import view.panel.Panel;
import view.modal.evaluation.ViewEvaluation;

/**
 * <p>Class of View <b>PanelEvaluation</b>.</p> 
 * <p>Class responsible for defining a Abstract Model for <b>Evaluation Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-01
 * @see    controller.view.panel.evaluation.ControllerPanelEvaluation
 * @see    view.panel.Panel
 * @see    view.modal.evaluation.ViewEvaluation
 */
public abstract class PanelEvaluation extends Panel {
    protected final ViewEvaluation viewEvaluation;
    protected final Project project;
    
    /**
     * Default constructor method of Class.
     * @param view View Evaluation.
     */
    public PanelEvaluation(ViewEvaluation view) {
        this.viewEvaluation = view;
        this.project        = view.getViewMenu().getProject();
    }
    
    /**
     * Method responsible for setting the Default Properties.
     */
    protected void setDefaultProperties() {
        this.setLayout(new GridBagLayout());
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Target*: "), this.createConstraints(1, 1, 0, 0));
        this.add(this.createComboBox("targetComboBox", this.getTargets(), 15), this.createConstraints(5, 1, 1, 0));
        
        this.add(this.createLabel("Operation*: "), this.createConstraints(1, 1, 0, 1));
        this.add(this.createTextField("operationTextField", "", 15), this.createConstraints(4, 1, 1, 1));
        this.add(this.createButton("applyButton", "", "apply.png"), this.createConstraints(1, 1, 5, 1));
        
        this.add(this.createLabel("Value: "), this.createConstraints(1, 1, 0, 2));
        this.add(this.createTextFieldNoEditable("valueTextField", "", 15), this.createConstraints(5, 1, 1, 2));
        
        this.createList("detailsList");
        this.add(this.getScrollPane("detailsList"), this.createConstraints(6, 1, 0, 3));
    }
    
    /**
     * Method responsible for returning the Targets Array.
     * @return Targets Array.
     */
    protected abstract Object[] getTargets();
    
    /**
     * Method responsible for clearing the Panel.
     */
    public void clear() {
        this.getTargetComboBox().setSelectedIndex(0);
        this.getOperationTextField().setText("");
        this.getValueTextField().setText("");
        this.clearDetails();
        
        this.getOperationTextField().requestFocus();
    }
    
    /**
     * Method responsible for clearing the Details List.
     */
    private void clearDetails() {
        this.getDetailsList().removeAll();
        this.getDetailsList().setModel(new DefaultListModel());
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
    
    /**
     * Method responsible for returning the Apply Button.
     * @return Apply Button.
     */
    public JButton getApplyButton() {
        return this.getButton("applyButton");
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
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return this.project;
    }
    
    /**
     * Method responsible for returning the View Evaluation.
     * @return View Evaluation.
     */
    public ViewEvaluation getViewEvaluation() {
        return this.viewEvaluation;
    }
    
    /**
     * Method responsible for returning the Controller.
     * @return Controller.
     */
    public ControllerPanelEvaluation getController() {
        return (ControllerPanelEvaluation) this.controller;
    }
}