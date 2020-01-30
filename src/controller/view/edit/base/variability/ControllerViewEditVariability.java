package controller.view.edit.base.variability;

import controller.view.edit.ControllerViewEdit;
import view.edit.base.variability.ViewEditVariability;

/**
 * <p>Class of Controller <b>ControllerViewEditVariability</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewEditVariability</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  29/01/2020
 * @see    controller.view.edit.ControllerViewEdit
 * @see    model.structural.base.variability.Variability
 * @see    view.edit.base.variability.ViewEditVariability
 */
public class ControllerViewEditVariability extends ControllerViewEdit  {
    private final ViewEditVariability viewEditVariability;

    /**
     * Default constructor method of Class.
     * @param viewEdit View Edit Variability.
     */
    public ControllerViewEditVariability(ViewEditVariability viewEdit) {
        super(viewEdit);
        this.viewEditVariability = viewEdit;
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