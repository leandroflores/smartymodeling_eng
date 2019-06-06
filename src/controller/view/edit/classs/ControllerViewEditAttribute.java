package controller.view.edit.classs;

import controller.view.edit.ControllerViewEdit;
import view.edit.classs.ViewEditAttribute;

/**
 * <p>Class of Controller <b>ControllerViewEditAttribute</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewEditAttribute</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  06/06/2019
 * @see    controller.view.edit.ControllerViewEdit
 * @see    view.edit.classs.ViewEditAttribute
 */
public class ControllerViewEditAttribute extends ControllerViewEdit  {
    private final ViewEditAttribute viewEditAttribute;

    /**
     * Default constructor method of Class.
     * @param viewEdit View Edit Attribute.
     */
    public ControllerViewEditAttribute(ViewEditAttribute viewEdit) {
        super(viewEdit);
        this.viewEditAttribute = viewEdit;
    }

    @Override
    public boolean check() {
        return true;
    }

    @Override
    public void save() {
        this.viewEditAttribute.getViewMenu().update();
        this.viewEditAttribute.dispose();
    }
}