package controller.view.modal.edit.base.variability;

import controller.view.modal.edit.ControllerViewEdit;
import view.modal.edit.base.variability.ViewEditVariability;

/**
 * <p>Class of Controller <b>ControllerViewEditVariability</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEditVariability</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-01-29
 * @see    controller.view.modal.edit.ControllerViewEdit
 * @see    model.structural.base.variability.Variability
 * @see    view.modal.edit.base.variability.ViewEditVariability
 */
public class ControllerViewEditVariability extends ControllerViewEdit  {

    /**
     * Default constructor method of Class.
     * @param view View Edit Variability.
     */
    public ControllerViewEditVariability(ViewEditVariability view) {
        super(view);
    }
    
    @Override
    public boolean check() {
        return true;
    }

    @Override
    public void save() {
        close();
    }
}