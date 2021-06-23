package view.modal.new_.base.evaluation;

import controller.view.modal.new_.base.evaluation.ControllerViewNewMeasure;
import java.awt.Dimension;
import model.structural.base.evaluation.Measure;
import view.panel.new_.base.evaluation.measure.PanelBaseEvaluation;
import view.panel.new_.base.evaluation.measure.PanelBaseMeasure;
import view.panel.new_.base.evaluation.measure.PanelBaseTarget;
import view.modal.new_.ViewNew;
import view.main.structural.ViewMenu;
import view.panel.new_.base.evaluation.PanelNewMeasure;

/**
 * <p>Class of View <b>ViewNewMeasure</b>.</p>
 * <p>Class responsible for defining the <b>New Measure View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-09-03
 * @see    controller.view.modal.new_.base.evaluation.ControllerViewNewMeasure
 * @see    model.structural.base.evaluation.Measure
 * @see    view.modal.new_.ViewNew
 */
public final class ViewNewMeasure extends ViewNew {
    private final Measure measure;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewNewMeasure(ViewMenu view) {
        super(view);
        measure    = new Measure();
        controller = new ControllerViewNewMeasure(this);
        title      = "New Measure";
        initComponents();
    }
    
    @Override
    public void initComponents() {
        super.initComponents();
        getInsertButton().setEnabled(false);
    }
    
    @Override
    protected Dimension getViewDimension() {
        return new Dimension(600, 525);
    }
    
    @Override
    protected PanelNewMeasure createPanelNew() {
        return new PanelNewMeasure(this, measure);
    }
    
    @Override
    protected Dimension getPanelDimension() {
        return new Dimension(500, 400);
    }
    
    @Override
    protected PanelNewMeasure getPanelNew() {
        return (PanelNewMeasure) super.getPanelNew();
    }
    
    /**
     * Method responsible for returning the Panel Base Measure.
     * @return Panel Base Measure.
     */
    public PanelBaseMeasure getPanelBaseMeasure() {
        return getPanelNew().getPanelBaseMeasure();
    }
    
    /**
     * Method responsible for returning the Panel Base Target.
     * @return Panel Base Target.
     */
    public PanelBaseTarget getPanelBaseTarget() {
        return getPanelNew().getPanelBaseTarget();
    }
    
    /**
     * Method responsible for returning the Panel Base Evaluation.
     * @return Panel Base Evaluation.
     */
    public PanelBaseEvaluation getPanelBaseEvaluation() {
        return getPanelNew().getPanelBaseEvaluation();
    }
    
    /**
     * Method responsible for returning the Controller.
     * @return Controller.
     */
    public ControllerViewNewMeasure getController() {
        return (ControllerViewNewMeasure) controller;
    }
    
    /**
     * Method responsible for returning the Measure.
     * @return Measure.
     */
    public Measure getMeasure() {
        return measure;
    }
}