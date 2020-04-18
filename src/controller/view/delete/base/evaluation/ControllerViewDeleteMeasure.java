package controller.view.delete.base.evaluation;

import controller.view.delete.ControllerViewDelete;
import model.structural.base.evaluation.Measure;
import view.delete.base.evaluation.ViewDeleteMeasure;

/**
 * <p>Class of Controller <b>ControllerViewDeleteMeasure</b>.</p>
 * <p>Class responsible for controlling the <b>ViewDeleteMeasure</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-18
 * @see    controller.view.delete.ControllerViewDelete
 * @see    model.structural.base.evaluation.Measure
 * @see    view.delete.base.evaluation.ViewDeleteMeasure
 */
public final class ControllerViewDeleteMeasure extends ControllerViewDelete {
    private final Measure measure;
    
    /**
     * Default constructor method of Class.
     * @param viewDelete View Delete Measure.
     */
    public ControllerViewDeleteMeasure(ViewDeleteMeasure viewDelete) {
        super(viewDelete);
        this.measure = this.getView().getMeasure();
    }
    
    @Override
    public void delete() {
        this.getView().getProject().removeMeasure(this.measure);
        this.close();
    }
    
    @Override
    public ViewDeleteMeasure getView() {
        return (ViewDeleteMeasure) this.viewModal;
    }
}