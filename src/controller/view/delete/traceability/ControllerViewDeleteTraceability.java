package controller.view.delete.traceability;

import controller.view.delete.ControllerViewDelete;
import model.structural.base.traceability.Traceability;
import view.delete.traceability.ViewDeleteTraceability;

/**
 * <p>Class of Controller <b>ControllerViewDeleteTraceability</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewDeleteTraceability</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  23/07/2019
 * @see    controller.view.delete.ControllerViewDelete
 * @see    model.structural.base.traceability.Traceability
 * @see    view.delete.traceability.ViewDeleteTraceability
 */
public class ControllerViewDeleteTraceability extends ControllerViewDelete {
    private final ViewDeleteTraceability viewDeleteTraceability;
    private final Traceability traceability;
    
    /**
     * Default constructor method of Class.
     * @param viewDelete View Delete Traceability.
     */
    public ControllerViewDeleteTraceability(ViewDeleteTraceability viewDelete) {
        super(viewDelete);
        this.viewDeleteTraceability = viewDelete;
        this.traceability           = viewDelete.getTraceability();
    }
    
    @Override
    public void delete() {
        this.viewDeleteTraceability.getProject().removeTraceability(this.traceability);
        this.close();
    }
}