package view.modal.delete.base.evaluation;

import controller.view.modal.delete.base.evaluation.ControllerViewDeleteMeasure;
import view.modal.delete.ViewDelete;
import model.structural.base.evaluation.Measure;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewDeleteMeasure</b>.</p>
 * <p>Class responsible for defining the <b>Measure Delete View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-18
 * @see    controller.view.modal.delete.base.evaluation.ControllerViewDeleteMeasure
 * @see    model.structural.base.evaluation.Measure
 * @see    view.modal.delete.ViewDelete
 */
public final class ViewDeleteMeasure extends ViewDelete {
    private final Measure measure;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param measure Measure.
     */
    public ViewDeleteMeasure(PanelModeling panel, Measure measure) {
        super(panel);
        this.measure    = measure;
        this.controller = new ControllerViewDeleteMeasure(this);
        this.title      = "Delete Measure";
        this.initComponents();
        this.addComponents();
    }

    @Override
    public void addComponents() {
        super.addComponents(this.measure.getName());
    }
    
    /**
     * Method responsible for returning the Measure.
     * @return Measure.
     */
    public Measure getMeasure() {
        return this.measure;
    }
}