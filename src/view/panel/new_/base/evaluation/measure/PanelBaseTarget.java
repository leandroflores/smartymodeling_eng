package view.panel.new_.base.evaluation.measure;

import controller.view.panel.new_.base.evaluation.measure.ControllerPanelBaseTarget;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.structural.base.evaluation.Measure;
import view.panel.new_.base.evaluation.PanelNewMeasure;

/**
 * <p>Class of View <b>PanelBaseTarget</b>.</p> 
 * <p>Class responsible for defining a <b>Measure Target Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-23
 * @see    controller.view.panel.new_.base.evaluation.measure.ControllerPanelBaseTarget
 * @see    model.structural.base.evaluation.Measure
 * @see    view.panel.new_.base.evaluation.measure.PanelBase
 */
public final class PanelBaseTarget extends PanelBase {
    
    /**
     * Default constructor method of Class.
     * @param panel Panel New Measure.
     * @param measure Measure.
     */
    public PanelBaseTarget(PanelNewMeasure panel, Measure measure) {
        super(panel, measure);
        this.controller = new ControllerPanelBaseTarget(this);
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
        this.add(this.createLabel("Target: "), this.createConstraints(1, 1, 0, 0));
        this.add(this.createComboBox("targetComboBox", this.getTargets(), 15), this.createConstraints(3, 1, 1, 0));
        
        this.add(this.createLabel("Elements: "), this.createConstraints(1, 1, 0, 1));
        this.add(this.createTextFieldNoEditable("elementsTextField", "", 15), this.createConstraints(3, 1, 1, 1));
        
        this.add(this.createLabel("Associations: "), this.createConstraints(1, 1, 0, 2));
        this.add(this.createTextFieldNoEditable("associationsTextField", "", 15), this.createConstraints(3, 1, 1, 2));
        
        this.add(this.createLabel("Variabilities: "), this.createConstraints(1, 1, 0, 3));
        this.add(this.createTextFieldNoEditable("variabilitiesTextField", "", 15), this.createConstraints(3, 1, 1, 3));
    }
    
    /**
     * Method responsible for adding the Panel Footer.
     */
    @Override
    public void addFooter() {
        this.add(this.getFooter(), this.createConstraints(4, 1, 0, 4));
    }
    
    /**
     * Method responsible for setting the Target Values.
     */
    public void setValues() {
        this.getController().update();
    }
    
    /**
     * Method responsible for returning the Target Combo Box.
     * @return Target Combo Box.
     */
    public JComboBox getTargetComboBox() {
        return this.getComboBox("targetComboBox");
    }
    
    /**
     * Method responsible for returning the Elements Text Field.
     * @return Elements Text Field.
     */
    public JTextField getElementsTextField() {
        return this.getTextField("elementsTextField");
    }
    
    /**
     * Method responsible for returning the Associations Text Field.
     * @return Associations Text Field.
     */
    public JTextField getAssociationsTextField() {
        return this.getTextField("associationsTextField");
    }
    
    /**
     * Method responsible for returning the Variabilities Text Field.
     * @return Variabilities Text Field.
     */
    public JTextField getVariabilitiesTextField() {
        return this.getTextField("variabilitiesTextField");
    }
    
    @Override
    public ControllerPanelBaseTarget getController() {
        return (ControllerPanelBaseTarget) this.controller;
    }
}