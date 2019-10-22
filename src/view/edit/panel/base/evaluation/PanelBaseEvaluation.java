package view.edit.panel.base.evaluation;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JTextField;
import model.controller.structural.base.ControllerProject;
import model.structural.base.Project;
import model.structural.base.evaluation.Measure;
import view.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseEvalutaion</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Evalutaion Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  03/09/2019
 * @see    controller.view.edit.panel.base.evaluation.
 * @see    model.structural.base.evaluation.Measure
 * @see    view.Panel
 */
public final class PanelBaseEvaluation extends Panel {
    private final ViewMenu viewMenu;
    private final Project project;
    private final Measure measure;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param measure Measure.
     */
    public PanelBaseEvaluation(ViewMenu viewMenu, Measure measure) {
        this.viewMenu   = viewMenu;
        this.project    = this.viewMenu.getProject();
        this.measure    = measure;
//        this.controller = new ControllerPanelBaseOperation(this);
        this.setSettings();
        this.addComponents();
        this.setValues();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridLayout(5, 2));
        this.setPreferredSize(new Dimension(50, 50));
        this.setSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Operation*: "));
        this.add(this.createTextFieldNoEditable("operationTextField", "", 15));
        
        this.add(this.createLabel("Target: "));
        this.add(this.createComboBox("targetComboBox", this.getTargets(), 15));
    }
    
    /**
     * Method responsible for returning the Targets Array.
     * @return Targets Array.
     */
    private Object[] getTargets() {
        return new ControllerProject(this.project).getDiagrams(this.measure.getMetric().getTarget());
    }
    
    /**
     * Method responsible for setting the Operation Values.
     */
    public void setValues() {
//        this.setMetric();
        this.getOperationTextField().setText(this.measure.getMetric().getOperation());
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
        return this.textFields.get("operationTextField");
    }
}