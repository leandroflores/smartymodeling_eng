package controller.view.modal.edit.base.evaluation;

import controller.view.modal.edit.ControllerViewEdit;
import view.modal.edit.base.evaluation.ViewEditMeasure;

/**
 * <p>Class of Controller <b>ControllerViewEditMeasure</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEditMeasure</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-25
 * @see    controller.view.modal.edit.ControllerViewEdit
 * @see    model.structural.base.evaluation.Measure
 * @see    view.modal.edit.base.evaluation.ViewEditMeasure
 */
public class ControllerViewEditMeasure extends ControllerViewEdit {

    /**
     * Default constructor method of Class.
     * @param view View Edit Measure.
     */
    public ControllerViewEditMeasure(ViewEditMeasure view) {
        super(view);
    }
    
    @Override
    public boolean check() {
        return check(getView().getPanelBaseMeasure().getNameTextField(), "Name is required!");
    }

    @Override
    public void save() {
        close();
    }
    
    @Override
    public ViewEditMeasure getView() {
        return (ViewEditMeasure) super.getView();
    }
}