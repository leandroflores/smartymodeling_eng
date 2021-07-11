package controller.view.modal.delete.base.traceability;

import controller.view.modal.delete.ControllerViewDelete;
import model.structural.base.traceability.Reference;
import view.modal.delete.base.traceability.ViewDeleteReference;

/**
 * <p>Class of Controller <b>ControllerViewDeleteReference</b>.</p>
 * <p>Class responsible for controlling the <b>ViewDeleteReference</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-23
 * @see    controller.view.modal.delete.ControllerViewDelete
 * @see    model.structural.base.traceability.Reference
 * @see    view.modal.delete.base.traceability.ViewDeleteReference
 */
public final class ControllerViewDeleteReference extends ControllerViewDelete {
    private final Reference reference;
    
    /**
     * Default constructor method of Class.
     * @param view View Delete Reference.
     */
    public ControllerViewDeleteReference(ViewDeleteReference view) {
        super(view);
        reference = getView().getReference();
    }
    
    @Override
    public void delete() {
        getView().getProject().removeReference(reference);
        close();
    }
    
    @Override
    public ViewDeleteReference getView() {
        return (ViewDeleteReference) super.getView();
    }
}