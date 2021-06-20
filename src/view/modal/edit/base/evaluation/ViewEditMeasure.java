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
        initComponents();
    }
    
    @Override
    protected Dimension getViewDimension() {
        return new Dimension(650, 430);
    }
    
    @Override
    protected PanelEditMeasure createPanelEdit() {
        return new PanelEditMeasure(view, measure);
    }
    
    @Override
    protected Dimension getPanelDimension() {
        return new Dimension(500, 300);
    }
    
    @Override
    public PanelEditMeasure getPanelEdit() {
        return (PanelEditMeasure) super.getPanelEdit();
    }
    
    /**
     * Method responsible for returning the Panel Base Measure.
     * @return Panel Base Measure.
     */
    public PanelBaseMeasure getPanelBaseMeasure() {
        return getPanelEdit().getPanelBaseMeasure();
    }
    
    /**
     * Method responsible for returning the Measure.
     * @return Measure.
     */
    public Measure getMeasure() {
        return measure;
    }
}