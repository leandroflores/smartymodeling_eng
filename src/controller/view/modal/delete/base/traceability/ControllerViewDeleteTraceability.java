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
public class ControllerViewDeleteTraceability extends ControllerViewDelete {
    private final Traceability traceability;
    
    /**
     * Default constructor method of Class.
     * @param viewDelete View Delete Traceability.
     */
    public ControllerViewDeleteTraceability(ViewDeleteTraceability viewDelete) {
        super(viewDelete);
        this.traceability = viewDelete.getTraceability();
    }
    
    @Override
    public void delete() {
        this.getView().getProject().removeTraceability(this.traceability);
        this.close();
    }
    
    @Override
    public ViewDeleteTraceability getView() {
        return (ViewDeleteTraceability) this.viewModal;
    }
}