package controller.view.modal.delete.base.evaluation;

import controller.view.modal.delete.ControllerViewDelete;
import model.structural.base.evaluation.Measure;
import view.modal.delete.base.evaluation.ViewDeleteMeasure;

/**
 * <p>Class of Controller <b>ControllerViewDeleteMeasure</b>.</p>
 * <p>Class responsible for controlling the <b>ViewDeleteMeasure</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-18
 * @see    controller.view.modal.delete.ControllerViewDelete
 * @see    model.structural.base.evaluation.Measure
 * @see    view.modal.delete.base.evaluation.ViewDeleteMeasure
 */
public final class ControllerViewDeleteMeasure extends ControllerViewDelete {
    private final Measure measure;
    
    /**
     * Default constructor method of Class.
     * @param view View Delete Measure.
     */
    public ControllerViewDeleteMeasure(ViewDeleteMeasure view) {
        super(view);
        measure = getView().getMeasure();
    }
    
    @Override
    public void delete() {
        getView().getProject().removeMeasure(measure);
        close();
    }
    
    @Override
    public ViewDeleteMeasure getView() {
        return (ViewDeleteMeasure) super.getView();
    }
}