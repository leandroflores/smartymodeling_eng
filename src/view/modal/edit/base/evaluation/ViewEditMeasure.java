package view.modal.edit.base.evaluation;

import controller.view.modal.edit.base.evaluation.ControllerViewEditMeasure;
import java.awt.Dimension;
import model.structural.base.evaluation.Measure;
import view.modal.edit.ViewEdit;
import view.panel.base.evaluation.PanelBaseMeasure;
import view.panel.edit.base.evaluation.PanelEditMeasure;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditMeasure</b>.</p>
 * <p>Class responsible for defining the <b>Measure Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-25
 * @see    controller.view.modal.edit.base.evaluation.ControllerViewEditMeasure
 * @see    model.structural.base.evaluation.Measure
 * @see    view.modal.edit.ViewEdit
 */
public final class ViewEditMeasure extends ViewEdit {
    private final Measure measure;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param measure Measure.
     */
    public ViewEditMeasure(PanelModeling panel, Measure measure) {
        super(panel.getViewMenu());
        this.measure    = measure;
        this.controller = new ControllerViewEditMeasure(this);
        this.title      = "Edit Measure Data";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(new Dimension(650, 430));
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.addPanelBaseMeasure();
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Base Measure.
     */
    private void addPanelBaseMeasure() {
        this.addPanel("panelEditMeasure", new PanelEditMeasure(this.view, this.measure));
        this.getPanel("panelEditMeasure").setPreferredSize(new Dimension(500, 300));
        this.add(this.getPanel("panelEditMeasure"));
    }
    
    /**
     * Method responsible for returning the Panel Edit Measure.
     * @return Panel Edit Measure.
     */
    public PanelEditMeasure getPanelEditMeasure() {
        return (PanelEditMeasure) this.getPanel("panelEditMeasure");
    }
    
    /**
     * Method responsible for returning the Panel Base Measure.
     * @return Panel Base Measure.
     */
    public PanelBaseMeasure getPanelBaseMeasure() {
        return this.getPanelEditMeasure().getPanelBaseMeasure();
    }
    
    /**
     * Method responsible for returning the Measure.
     * @return Measure.
     */
    public Measure getMeasure() {
        return this.measure;
    }
}