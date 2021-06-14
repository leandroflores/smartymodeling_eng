package model.controller.structural.base.variability;

import model.controller.Controller;
import model.structural.base.Project;

/**
 * <p>Class of Controller <b>ControllerVariability</b>.</p>
 * <p>Class responsible for defining the <b>Variability Controller</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-04
 * @see    model.controller.Controller
 * @see    model.structural.base.variability.Variability
 */
public class ControllerVariability extends Controller {
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     */
    public ControllerVariability(Project project) {
        super(project);
    }
    
    /**
     * Method responsible for returning the Variability Constraints Array.
     * @return Variability Constraints Array.
     */
    public String[] getConstraints() {
        return new String[]{"Exclusive", "Inclusive"};
    }
    
    /**
     * Method responsible for returning the Variability Bindings Array.
     * @return Variability Bindings Array.
     */
    public String[] getBindings() {
        return new String[]{"DESIGN_TIME", "LINK_TIME", "COMPILE_TIME", "RUNTIME"};
    }
}