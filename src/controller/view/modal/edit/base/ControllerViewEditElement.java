package controller.view.modal.edit.base;

import controller.view.modal.edit.ControllerViewEdit;
import view.modal.edit.base.ViewEditElement;

/**
 * <p>Class of Controller <b>ControllerViewEditElement</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEditElement</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-30
 * @see    controller.view.modal.edit.ControllerViewEdit
 * @see    model.structural.base.Element
 * @see    view.modal.edit.base.ViewEditElement
 */
public class ControllerViewEditElement extends ControllerViewEdit  {

    /**
     * Default constructor method of Class.
     * @param view View Edit Element.
     */
    public ControllerViewEditElement(ViewEditElement view) {
        super(view);
    }
    
    @Override
    public boolean check() {
        return check(getView().getPanelBaseElement().getNameTextField(), "Name is a required!");
    }

    @Override
    public void save() {
        close();
    }
    
    @Override
    public ViewEditElement getView() {
        return (ViewEditElement) super.getView();
    }
}