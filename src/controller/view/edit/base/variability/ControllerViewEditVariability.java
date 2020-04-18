package controller.view.edit.base.variability;

import controller.view.edit.ControllerViewEdit;
import view.edit.base.variability.ViewEditVariability;

/**
 * <p>Class of Controller <b>ControllerViewEditVariability</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEditVariability</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-01-29
 * @see    controller.view.edit.ControllerViewEdit
 * @see    model.structural.base.variability.Variability
 * @see    view.edit.base.variability.ViewEditVariability
 */
public class ControllerViewEditVariability extends ControllerViewEdit  {

    /**
     * Default constructor method of Class.
     * @param viewEdit View Edit Variability.
     */
    public ControllerViewEditVariability(ViewEditVariability viewEdit) {
        super(viewEdit);
    }
    
    @Override
    public boolean check() {
        return true;
    }

    @Override
    public void save() {
        this.close();
    }
}