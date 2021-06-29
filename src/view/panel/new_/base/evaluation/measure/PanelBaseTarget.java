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
        controller = new ControllerPanelBaseTarget(this);
        setDefaultProperties();
        addComponents();
        addFooter();
        setValues();
    }
    
    @Override
    protected void setDefaultProperties() {
        setLayout(new GridBagLayout());
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Target: "), createConstraints(1, 1, 0, 0));
        add(createComboBox("target", getTargets(), 15), createConstraints(3, 1, 1, 0));
        
        add(createLabel("Elements: "), createConstraints(1, 1, 0, 1));
        add(createTextFieldNoEditable("elements", "", 15), createConstraints(3, 1, 1, 1));
        
        add(createLabel("Associations: "), createConstraints(1, 1, 0, 2));
        add(createTextFieldNoEditable("associations", "", 15), createConstraints(3, 1, 1, 2));
        
        add(createLabel("Variabilities: "), createConstraints(1, 1, 0, 3));
        add(createTextFieldNoEditable("variabilities", "", 15), createConstraints(3, 1, 1, 3));
    }
    
    /**
     * Method responsible for adding the Panel Footer.
     */
    @Override
    public void addFooter() {
        add(getFooter(), createConstraints(4, 1, 0, 4));
    }
    
    /**
     * Method responsible for setting the Target Values.
     */
    public void setValues() {
        getController().update();
    }
    
    /**
     * Method responsible for returning the Target Combo Box.
     * @return Target Combo Box.
     */
    public JComboBox getTargetComboBox() {
        return getComboBox("target");
    }
    
    /**
     * Method responsible for returning the Elements Text Field.
     * @return Elements Text Field.
     */
    public JTextField getElementsTextField() {
        return getTextField("elements");
    }
    
    /**
     * Method responsible for returning the Associations Text Field.
     * @return Associations Text Field.
     */
    public JTextField getAssociationsTextField() {
        return getTextField("associations");
    }
    
    /**
     * Method responsible for returning the Variabilities Text Field.
     * @return Variabilities Text Field.
     */
    public JTextField getVariabilitiesTextField() {
        return getTextField("variabilities");
    }
    
    @Override
    public ControllerPanelBaseTarget getController() {
        return (ControllerPanelBaseTarget) controller;
    }
}