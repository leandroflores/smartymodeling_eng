package view.panel.evaluation;

import controller.view.panel.evaluation.ControllerPanelEvaluation;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import model.structural.base.Project;
import view.Panel;
import view.evaluation.ViewEvaluation;

/**
 * <p>Class of View <b>PanelEvaluation</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Panel Evaluation</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  01/04/2020
 * @see    controller.view.edit.panel.base.evaluation.
 * @see    view.Panel
 * @see    view.evaluation.ViewEvaluation
 */
public abstract class PanelEvaluation extends Panel {
    protected final Project project;
    protected final ViewEvaluation viewEvaluation;
    
    /**
     * Default constructor method of Class.
     * @param viewEvaluation View Evaluation.
     */
    public PanelEvaluation(ViewEvaluation viewEvaluation) {
        this.project        = viewEvaluation.getViewMenu().getProject();
        this.viewEvaluation = viewEvaluation;
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    protected void setSettings() {
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(200, 300));
        this.setSize(new Dimension(200, 300));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Target*: "), this.getConstraints(1, 1, 0, 0));
        this.add(this.createComboBox("targetComboBox", this.getTargets(), 15), this.getConstraints(5, 1, 1, 0));
        
        this.add(this.createLabel("Operation*: "), this.getConstraints(1, 1, 0, 1));
        this.add(this.createTextField("operationTextField", "", 15), this.getConstraints(4, 1, 1, 1));
        this.add(this.createButton("applyButton", "", "apply.png"), this.getConstraints(1, 1, 5, 1));
        
        this.add(this.createLabel("Value: "), this.getConstraints(1, 1, 0, 2));
        this.add(this.createTextFieldNoEditable("valueTextField", "", 15), this.getConstraints(5, 1, 1, 2));
        
        this.createList("detailsList");
        this.add(this.getDetailsScrollPane(), this.getConstraints(6, 1, 0, 3));
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
        this.getDetailsList().removeAll();
        
        this.getOperationTextField().requestFocus();
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
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return this.project;
    }
    
    /**
     * Method responsible for returning the Target Combo Box.
     * @return Target Combo Box.
     */
    public JComboBox getTargetComboBox() {
        return this.comboBoxes.get("targetComboBox");
    }
    
    /**
     * Method responsible for returning the Operation Text Field.
     * @return Operation Text Field.
     */
    public JTextField getOperationTextField() {
        return this.textFields.get("operationTextField");
    }
    
    /**
     * Method responsible for returning the Apply Button.
     * @return Apply Button.
     */
    public JButton getApplyButton() {
        return this.buttons.get("applyButton");
    }
    
    /**
     * Method responsible for returning the Value Text Field.
     * @return Value Text Field.
     */
    public JTextField getValueTextField() {
        return this.textFields.get("valueTextField");
    }
    
    /**
     * Method responsible for return the Details List.
     * @return Details List.
     */
    public JList getDetailsList() {
        return this.lists.get("detailsList");
    }
    
    /**
     * Method responsible for return the Details Scroll Pane.
     * @return Details Scroll Pane.
     */
    public JScrollPane getDetailsScrollPane() {
        return this.scrollPanes.get("detailsList");
    }
}