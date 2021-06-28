package view.panel.base.evaluation;

import controller.view.panel.base.evaluation.ControllerPanelBaseMeasure;
import java.awt.GridLayout;
import javax.swing.JTextField;
import model.structural.base.evaluation.Measure;
import view.panel.base.PanelBase;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseMeasure</b>.</p>
 * <p>Class responsible for defining a <b>Measure Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-25
 * @see    controller.view.panel.base.evaluation.ControllerPanelBaseMeasure
 * @see    model.structural.base.evaluation.Measure
 * @see    view.panel.base.PanelBase
 */
public final class PanelBaseMeasure extends PanelBase {
    private final Measure measure;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param measure Measure.
     */
    public PanelBaseMeasure(ViewMenu view, Measure measure) {
        super(view);
        this.measure    = measure;
        this.controller = new ControllerPanelBaseMeasure(this);
        setDefaultProperties();
        addComponents();
        getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        setLayout(new GridLayout(5, 2));
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Name*: "));
        add(createTextField("name", getMeasure().getName(), 15));
        
        add(createLabel("Date: "));
        add(createTextFieldNoEditable("date", getMeasure().getDate(), 15));
        
        add(createLabel("Metric: "));
        add(createTextFieldNoEditable("metric", getMeasure().getMetric().toString(), 15));
        
        add(createLabel("Target: "));
        add(createTextFieldNoEditable("target", getMeasure().getTarget(), 15));
        
        add(createLabel("Value: "));
        add(createTextFieldNoEditable("value", getMeasure().getValue().toString(), 15));
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return getTextField("name");
    }
    
    /**
     * Method responsible for returning the Measure.
     * @return Measure.
     */
    public Measure getMeasure() {
        return measure;
    }
}