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
    protected final ViewEvaluation view;
    protected final Project project;
    
    /**
     * Default constructor method of Class.
     * @param view View Evaluation.
     */
    public PanelEvaluation(ViewEvaluation view) {
        this.view    = view;
        this.project = view.getViewMenu().getProject();
    }
    
    /**
     * Method responsible for setting the Default Properties.
     */
    protected void setDefaultProperties() {
        setLayout(new GridBagLayout());
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Target*: "), createConstraints(1, 1, 0, 0));
        add(createComboBox("target", getTargets(), 15), createConstraints(5, 1, 1, 0));
        
        add(createLabel("Operation*: "), createConstraints(1, 1, 0, 1));
        add(createTextField("operation", "", 15), createConstraints(4, 1, 1, 1));
        add(createButton("apply", "", "apply.png"), createConstraints(1, 1, 5, 1));
        
        add(createLabel("Value: "), createConstraints(1, 1, 0, 2));
        add(createTextFieldNoEditable("value", "", 15), createConstraints(5, 1, 1, 2));
        
        createList("details");
        add(getScrollPane("details"), createConstraints(6, 1, 0, 3));
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
        getTargetComboBox().setSelectedIndex(0);
        getOperationTextField().setText("");
        getValueTextField().setText("");
        clearDetails();
        
        getOperationTextField().requestFocus();
    }
    
    /**
     * Method responsible for clearing the Details List.
     */
    private void clearDetails() {
        getDetailsList().removeAll();
        getDetailsList().setModel(new DefaultListModel());
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
    
    /**
     * Method responsible for returning the Apply Button.
     * @return Apply Button.
     */
    public JButton getApplyButton() {
        return getButton("apply");
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
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return project;
    }
    
    /**
     * Method responsible for returning the View Evaluation.
     * @return View Evaluation.
     */
    public ViewEvaluation getView() {
        return view;
    }
    
    /**
     * Method responsible for returning the Controller.
     * @return Controller.
     */
    public ControllerPanelEvaluation getController() {
        return (ControllerPanelEvaluation) controller;
    }
}