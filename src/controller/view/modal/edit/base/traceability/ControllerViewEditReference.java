package controller.view.modal.edit.base.traceability;

import controller.view.modal.edit.ControllerViewEdit;
import view.modal.edit.base.traceability.ViewEditReference;

/**
 * <p>Class of Controller <b>ControllerViewEditReference</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEditReference</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-18
 * @see    controller.view.modal.edit.ControllerViewEdit
 * @see    model.structural.base.traceability.Reference
 * @see    view.modal.edit.base.traceability.ViewEditReference
 */
public class ControllerViewEditReference extends ControllerViewEdit {

    /**
     * Default constructor method of Class.
     * @param view View Edit Reference.
     */
    public ControllerViewEditReference(ViewEditReference view) {
        super(view);
    }
    
    @Override
    public boolean check() {
        return check(getView().getPanelBaseReference().getNameTextField(), "Name is required!");
    }

    @Override
    public void save() {
        close();
    }
    
    @Override
    public ViewEditReference getView() {
        return (ViewEditReference) super.getView();
    }
}