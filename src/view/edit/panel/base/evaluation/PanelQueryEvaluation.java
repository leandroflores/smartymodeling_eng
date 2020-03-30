package view.edit.panel.base.evaluation;

import controller.view.edit.panel.base.evaluation.ControllerPanelQueryEvaluation;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import model.controller.structural.base.ControllerProject;
import model.structural.base.Project;
import view.Panel;
import view.query.ViewQueryEvaluation;

/**
 * <p>Class of View <b>PanelQueryEvalutaion</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Evalutaion Query Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  28/03/2020
 * @see    controller.view.edit.panel.base.evaluation.ControllerPanelBaseEvaluation
 * @see    view.Panel
 * @see    view.query.ViewQueryEvaluation
 */
public final class PanelQueryEvaluation extends Panel {
    private final ViewQueryEvaluation viewQuery;
    private final Project project;
    
    /**
     * Default constructor method of Class.
     * @param viewQuery View Query Evaluation.
     */
    public PanelQueryEvaluation(ViewQueryEvaluation viewQuery) {
        this.viewQuery  = viewQuery;
        this.project    = viewQuery.getProject();
        this.controller = new ControllerPanelQueryEvaluation(this);
        this.setSettings();
        this.addComponents();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
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
    private Object[] getTargets() {
        return new ControllerProject(this.project).getTargets();
    }
    
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
     * Method responsible for returning the View Query Evaluation.
     * @return View Query Evaluation.
     */
    public ViewQueryEvaluation getViewQueryEvaluation() {
        return this.viewQuery;
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