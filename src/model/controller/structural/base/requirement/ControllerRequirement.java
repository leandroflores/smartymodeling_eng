package model.controller.structural.base.requirement;

import model.controller.Controller;
import model.structural.base.Project;

/**
 * <p>Class of Controller <b>ControllerRequirement</b>.</p>
 * <p>Class responsible for defining the <b>Requirement Controller</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-15
 * @see    model.controller.Controller
 * @see    model.structural.base.requirement.Requirement
 */
public class ControllerRequirement extends Controller {
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     */
    public ControllerRequirement(Project project) {
        super(project);
    }
    
    /**
     * Method responsible for returning the Metric Targets Array.
     * @return Metric Targets Array.
     */
    public String[] getTargets() {
        return new String[]{"Business", "Functional", "Non-Functional", "User Interface"};
    }
    
    /**
     * Method responsible for returning the Requirements Target.
     * @return Requirements Target.
     */
    public Object[] getRequirementsTarget() {
        Object[] objects  = getRequirements();
        Object[] array    = new Object[objects.length + 1];
                 array[0] = "All Requirements";
        System.arraycopy(objects, 0, array, 1, objects.length);
        return   array;
    }
}