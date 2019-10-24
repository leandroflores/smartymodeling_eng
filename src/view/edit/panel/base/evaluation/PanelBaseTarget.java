package view.edit.panel.base.evaluation;

import controller.view.edit.panel.base.evaluation.ControllerPanelBaseTarget;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.controller.structural.base.ControllerProject;
import model.structural.base.Project;
import model.structural.base.evaluation.Measure;
import view.Panel;
import view.structural.ViewMenu;

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
    private final ViewMenu viewMenu;
    private final Project project;
    private final Measure measure;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param measure Measure.
     */
    public PanelBaseTarget(ViewMenu viewMenu, Measure measure) {
        this.viewMenu   = viewMenu;
        this.project    = this.viewMenu.getProject();
        this.measure    = measure;
        this.controller = new ControllerPanelBaseTarget(this);
        this.setSettings();
        this.addComponents();
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
        this.add(this.createLabel("Target: "), this.getConstraints(1, 1, 0, 0));
        this.add(this.createComboBox("targetComboBox", this.getTargets(), 15), this.getConstraints(4, 1, 1, 0));
        
        this.add(this.createLabel("Elements: "), this.getConstraints(1, 1, 0, 1));
        this.add(this.createTextFieldNoEditable("elementsTextField", "", 15), this.getConstraints(4, 1, 1, 1));
        
        this.add(this.createLabel("Associations: "), this.getConstraints(1, 1, 0, 2));
        this.add(this.createTextFieldNoEditable("associationsTextField", "", 15), this.getConstraints(4, 1, 1, 2));
        
        this.add(this.createLabel("Variabilities: "), this.getConstraints(1, 1, 0, 3));
        this.add(this.createTextFieldNoEditable("variabilitiesTextField", "", 15), this.getConstraints(4, 1, 1, 3));
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