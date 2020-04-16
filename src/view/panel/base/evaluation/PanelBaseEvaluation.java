package view.panel.base.evaluation;

import controller.view.edit.panel.base.evaluation.ControllerPanelBaseEvaluation;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import model.controller.structural.base.ControllerProject;
import model.structural.base.Project;
import model.structural.base.evaluation.Measure;
import view.panel.Panel;
import view.new_.evaluation.ViewNewMeasure;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseEvalutaion</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Evalutaion Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  03/09/2019
 * @see    controller.view.edit.panel.base.evaluation.ControllerPanelBaseEvaluation
 * @see    model.structural.base.evaluation.Measure
 * @see    view.panel.Panel
 */
public final class PanelBaseEvaluation extends Panel {
    private final ViewNewMeasure viewNew;
    private final ViewMenu viewMenu;
    private final Project project;
    private final Measure measure;
    
    /**
     * Default constructor method of Class.
     * @param viewNew View New Measure.
     * @param measure Measure.
     */
    public PanelBaseEvaluation(ViewNewMeasure viewNew, Measure measure) {
        this.viewNew    = viewNew;
        this.viewMenu   = viewNew.getViewMenu();
        this.project    = this.viewMenu.getProject();
        this.measure    = measure;
        this.controller = new ControllerPanelBaseEvaluation(this);
        this.setSettings();
        this.addComponents();
        this.addFooter();
        this.setValues();
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
        this.add(this.createLabel("Operation*: "), this.createConstraints(1, 1, 0, 0));
        this.add(this.createTextFieldNoEditable("operationTextField", "", 15), this.createConstraints(5, 1, 1, 0));
        
        this.add(this.createLabel("Target: "), this.createConstraints(1, 1, 0, 1));
        this.add(this.createComboBox("targetComboBox", this.getTargets(), 15), this.createConstraints(5, 1, 1, 1));
        
        this.add(this.createLabel("Value: "), this.createConstraints(1, 1, 0, 2));
        this.add(this.createTextFieldNoEditable("valueTextField", "", 15), this.createConstraints(5, 1, 1, 2));
        
        this.createList("detailsList");
        this.add(this.getDetailsScrollPane(), this.createConstraints(6, 1, 0, 3));
    }
    
    /**
     * Method responsible for returning the Targets Array.
     * @return Targets Array.
     */
    private Object[] getTargets() {
        return new ControllerProject(this.project).getTargets(this.measure.getMetric().getTarget());
    }
    
    /**
     * Method responsible for setting the Operation Values.
     */
    public void setValues() {
        this.getOperationTextField().setText(this.measure.getMetric().getOperation());
        this.updateTarget();
    }
    
    /**
     * Method responsible for updating the Target.
     */
    public void updateTarget() {
        Object target = this.viewNew.getPanelBaseTarget().getTargetComboBox().getSelectedItem();
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
    
    @Override
    public void addFooter() {
        this.add(this.createButton("backButton", "  Back  ", "Back", "back.png"), this.createConstraints(3, 1, 0, 4));
        this.add(this.createButton("nextButton", "  Next  ", "Next", "next.png"), this.createConstraints(3, 1, 3, 4));
        
        this.getBackButton().setPreferredSize(new Dimension(200, 30));
        this.getNextButton().setPreferredSize(new Dimension(200, 30));
    }
    
    /**
     * Method responsible for returning the View New Measure.
     * @return View New Measure.
     */
    public ViewNewMeasure getViewNewMeasure() {
        return this.viewNew;
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return this.project;
    }
    
    /**
     * Method responsible for return the Measure.
     * @return Measure.
     */
    public Measure getMeasure() {
        return this.measure;
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