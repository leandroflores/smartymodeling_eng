package model.controller.structural.base.evaluation;

import model.controller.Controller;
import model.structural.base.Project;

/**
 * <p>Class of Controller <b>ControllerMetric</b>.</p>
 * <p>Class responsible for defining the <b>Metric Controller</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-23
 * @see    model.controller.Controller
 * @see    model.structural.base.evaluation.Metric
 */
public class ControllerMetric extends Controller {
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     */
    public ControllerMetric(Project project) {
        super(project);
    }
    
    /**
     * Method responsible for returning the Metric Targets Array.
     * @return Metric Targets Array.
     */
    public String[] getTargets() {
        return new String[]{"Project", "Activity", "Class", "Component", "Sequence", "UseCase", "Product Line", "Variability"};
    }
}