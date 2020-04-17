package view.panel.base.evaluation.measure;

import controller.view.panel.base.evaluation.measure.ControllerPanelBaseTarget;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.structural.base.evaluation.Measure;
import view.new_.evaluation.ViewNewMeasure;

/**
 * <p>Class of View <b>PanelBaseTarget</b>.</p> 
 * <p>Class responsible for defining a <b>Measure Target Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-23
 * @see    controller.view.edit.panel.base.evaluation.
 * @see    model.structural.base.evaluation.Measure
 * @see   view.panel.base.evaluation.measure.PanelBase
 */
public final class PanelBaseTarget extends PanelBase {
    
    /**
     * Default constructor method of Class.
     * @param view View New Measure.
     * @param measure Measure.
     */
    public PanelBaseTarget(ViewNewMeasure view, Measure measure) {
        super(view, measure);
        this.controller = new ControllerPanelBaseTarget(this);
        this.setDefaultProperties();
        this.addComponents();
        this.addFooter();
        this.setValues();
    }
    
    @Override
    protected void setDefaultProperties() {
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
    
    /**
     * Method responsible for returning the Panel Controller.
     * @return Panel Controller.
     */
    public ControllerPanelBaseTarget getController() {
        return (ControllerPanelBaseTarget) this.controller;
    }
}