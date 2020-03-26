package view.edit.panel.base.evaluation;

import controller.view.edit.panel.base.evaluation.ControllerPanelBaseTarget;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.controller.structural.base.ControllerProject;
import model.structural.base.Project;
import model.structural.base.evaluation.Measure;
import view.Panel;
import view.new_.evaluation.ViewNewMeasure;

/**
 * <p>Class of View <b>PanelBaseEvalutaion</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Target Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  23/10/2019
 * @see    controller.view.edit.panel.base.evaluation.
 * @see    model.structural.base.evaluation.Measure
 * @see    view.Panel
 */
public final class PanelBaseTarget extends Panel {
    private final ViewNewMeasure viewNew;
    private final Project project;
    private final Measure measure;
    
    /**
     * Default constructor method of Class.
     * @param viewNew View New Measure.
     * @param measure Measure.
     */
    public PanelBaseTarget(ViewNewMeasure viewNew, Measure measure) {
        this.viewNew   = viewNew;
        this.project    = this.viewNew.getProject();
        this.measure    = measure;
        this.controller = new ControllerPanelBaseTarget(this);
        this.setSettings();
        this.addComponents();
        this.addFooter();
        this.setValues();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridLayout(5, 2));
        this.setPreferredSize(new Dimension(200, 300));
        this.setSize(new Dimension(200, 300));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Target: "));
        this.add(this.createComboBox("targetComboBox", this.getTargets(), 15));
        
        this.add(this.createLabel("Elements: "));
        this.add(this.createTextFieldNoEditable("elementsTextField", "", 15));
        
        this.add(this.createLabel("Associations: "));
        this.add(this.createTextFieldNoEditable("associationsTextField", "", 15));
        
        this.add(this.createLabel("Variabilities: "));
        this.add(this.createTextFieldNoEditable("variabilitiesTextField", "", 15));
    }
    
    /**
     * Method responsible for returning the Targets Array.
     * @return Targets Array.
     */
    private Object[] getTargets() {
        return new ControllerProject(this.project).getTargets(this.measure.getMetric().getTarget());
    }
    
    /**
     * Method responsible for setting the Target Values.
     */
    public void setValues() {
        this.getController().updateValues();
    }
    
    /**
     * Method responsible for returning the View New Measure.
     * @return View New Measure.
     */
    public ViewNewMeasure getViewNewMeasure() {
        return this.viewNew;
    }
    
    /**
     * Method responsible for returning the Panel Controller.
     * @return Panel Controller.
     */
    public ControllerPanelBaseTarget getController() {
        return (ControllerPanelBaseTarget) this.controller;
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
     * Method responsible for returning the Target Combo Box.
     * @return Target Combo Box.
     */
    public JComboBox getTargetComboBox() {
        return this.comboBoxes.get("targetComboBox");
    }
    
    /**
     * Method responsible for returning the Elements Text Field.
     * @return Elements Text Field.
     */
    public JTextField getElementsTextField() {
        return this.textFields.get("elementsTextField");
    }
    
    /**
     * Method responsible for returning the Associations Text Field.
     * @return Associations Text Field.
     */
    public JTextField getAssociationsTextField() {
        return this.textFields.get("associationsTextField");
    }
    
    /**
     * Method responsible for returning the Variabilities Text Field.
     * @return Variabilities Text Field.
     */
    public JTextField getVariabilitiesTextField() {
        return this.textFields.get("variabilitiesTextField");
    }
}