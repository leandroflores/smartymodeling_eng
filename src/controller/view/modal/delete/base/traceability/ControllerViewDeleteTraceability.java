package controller.view.modal.delete.base.traceability;

import controller.view.modal.delete.ControllerViewDelete;
import model.structural.base.traceability.Traceability;
import view.modal.delete.base.traceability.ViewDeleteTraceability;

/**
 * <p>Class of Controller <b>ControllerViewDeleteTraceability</b>.</p>
 * <p>Class responsible for controlling the <b>ViewDeleteRequirement</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-23
 * @see    controller.view.modal.delete.ControllerViewDelete
 * @see    model.structural.base.traceability.Traceability
 * @see    view.modal.delete.base.traceability.ViewDeleteTraceability
 */
public final class ControllerViewDeleteTraceability extends ControllerViewDelete {
    private final Traceability traceability;
    
    /**
     * Default constructor method of Class.
     * @param view View Delete Traceability.
     */
    public ControllerViewDeleteTraceability(ViewDeleteTraceability view) {
        super(view);
        traceability = getView().getTraceability();
    }
    
    @Override
    public void delete() {
        getView().getProject().removeTraceability(traceability);
        close();
    }
    
    @Override
    public ViewDeleteTraceability getView() {
        return (ViewDeleteTraceability) super.getView();
    }
}