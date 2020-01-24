package controller.view.edit.classes;

import controller.view.edit.ControllerViewEdit;
import view.edit.classs.ViewEditMethod;

/**
 * <p>Class of Controller <b>ControllerViewEditMethod</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewEditMethod</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  06/06/2019
 * @see    controller.view.edit.ControllerViewEdit
 * @see    view.edit.classs.ViewEditMethod
 */
public class ControllerViewEditMethod extends ControllerViewEdit  {
    private final ViewEditMethod viewEditMethod;

    /**
     * Default constructor method of Class.
     * @param viewEdit View Edit Method.
     */
    public ControllerViewEditMethod(ViewEditMethod viewEdit) {
        super(viewEdit);
        this.viewEditMethod = viewEdit;
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