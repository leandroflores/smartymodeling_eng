package controller.view.modal.requirement.base;

import controller.view.modal.requirement.ControllerViewRequirement;
import view.modal.requirement.base.ViewRequirementTraceability;

/**
 * <p>Class of Controller <b>ControllerViewRequirementMatrix</b>.</p>
 * <p>Class responsible for controlling the <b>ViewRequirementMatrix</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-27
 * @see    controller.view.modal.requirement.ControllerViewRequirement
 * @see    view.modal.requirement.base.ViewRequirementTraceability
 */
public class ControllerViewRequirementTraceability extends ControllerViewRequirement {
    
    /**
     * Default constructor method of Class.
     * @param view View Requirement Traceability.
     */
    public ControllerViewRequirementTraceability(ViewRequirementTraceability view) {
        super(view);
    }
}