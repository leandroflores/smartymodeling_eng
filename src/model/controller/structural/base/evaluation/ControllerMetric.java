package model.controller.structural.base.evaluation;

import java.util.List;
import model.structural.base.Project;
import model.structural.base.evaluation.Metric;

/**
 * <p>Class of Controller <b>ControllerMetric</b>.</p>
 * <p>Class responsible for defining the <b>Metric Controller</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  23/07/2019
 * @see    model.structural.base.evaluation.Metric
 */
public class ControllerMetric {
    public static final String[] TARGETS  = {"Project", "Activity", "Class", "Component", "Sequence", "UseCase", "Product Line", "Variability"};
    private final Project project;
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     */
    public ControllerMetric(Project project) {
        this.project = project;
    }
    
    /**
     * Method responsible for returning the Metrics Array.
     * @return Metrics Array.
     */
    public Metric[] getMetrics() {
        List<Metric> list   = this.project.getMetricsList();
        Metric[] metrics    = new Metric[list.size()];
        for (int i = 0; i < list.size(); i++)
                 metrics[i] = list.get(i);
        return   metrics;
    }
}