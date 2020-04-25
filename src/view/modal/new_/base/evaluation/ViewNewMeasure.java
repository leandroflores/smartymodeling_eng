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
        this.measure    = new Measure();
        this.controller = new ControllerViewNewMeasure(this);
        this.title      = "New Measure";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(new Dimension(600, 525));
        this.addHeader();
        this.addComponents();
        this.addFooter();
        this.getInsertButton().setEnabled(false);
    }
    
    @Override
    public void addComponents() {
        this.addPanelNewMeasure();
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel New Measure.
     */
    private void addPanelNewMeasure() {
        this.addPanel("panelNewMeasure", new PanelNewMeasure(this, this.measure));
        this.getPanelNewMeasure().setPreferredSize(new Dimension(500, 400));
        this.add(this.getPanelNewMeasure());
    }
    
    /**
     * Method responsible for returning the Panel New Measure.
     * @return Panel New Measure.
     */
    private PanelNewMeasure getPanelNewMeasure() {
        return (PanelNewMeasure) this.getPanel("panelNewMeasure");
    }
    
    /**
     * Method responsible for returning the Panel Base Measure.
     * @return Panel Base Measure.
     */
    public PanelBaseMeasure getPanelBaseMeasure() {
        return this.getPanelNewMeasure().getPanelBaseMeasure();
    }
    
    /**
     * Method responsible for returning the Panel Base Target.
     * @return Panel Base Target.
     */
    public PanelBaseTarget getPanelBaseTarget() {
        return this.getPanelNewMeasure().getPanelBaseTarget();
    }
    
    /**
     * Method responsible for returning the Panel Base Evaluation.
     * @return Panel Base Evaluation.
     */
    public PanelBaseEvaluation getPanelBaseEvaluation() {
        return this.getPanelNewMeasure().getPanelBaseEvaluation();
    }
    
    /**
     * Method responsible for returning the Controller.
     * @return Controller.
     */
    public ControllerViewNewMeasure getController() {
        return (ControllerViewNewMeasure) this.controller;
    }
    
    /**
     * Method responsible for returning the Measure.
     * @return Measure.
     */
    public Measure getMeasure() {
        return this.measure;
    }
}