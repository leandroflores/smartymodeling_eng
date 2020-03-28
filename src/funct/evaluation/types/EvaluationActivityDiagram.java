package funct.evaluation.types;

import funct.evaluation.base.EvaluationDiagram;
import funct.evaluation.types.activity.EvaluationActivityUML;
import funct.evaluation.types.activity.EvaluationDecisionUML;
import model.structural.diagram.ActivityDiagram;

/**
 * <p>Class of Evaluation <b>EvaluationActivityDiagram</b>.</p>
 * <p>Class responsible for <b>Evaluate</b> the <b>Activity Diagram</b>.</p>
 * @author Leandro
 * @since  27/03/2020
 * @see    funct.evaluation.base.EvaluationDiagram
 * @see    funct.evaluation.types.activity.EvaluationActivityUML
 * @see    funct.evaluation.types.activity.EvaluationDecisionUML
 * @see    model.structural.diagram.ActivityDiagram
 */
public class EvaluationActivityDiagram extends EvaluationDiagram {
    private final ActivityDiagram diagram;
    private final EvaluationActivityUML evaluationActivityUML;
    private final EvaluationDecisionUML evaluationDecisionUML;
    
    /**
     * Default constructor method of Class.
     * @param diagram Activity Diagram.
     */
    public EvaluationActivityDiagram(ActivityDiagram diagram) {
        super(diagram);
        this.diagram = diagram;
        this.evaluationActivityUML = new EvaluationActivityUML(diagram);
        this.evaluationDecisionUML = new EvaluationDecisionUML(diagram);
    }
    
    @Override
    public Double getClauseValue(String keyword, String filter) {
        if (keyword.toLowerCase().equals("activity"))
            return this.getActivityMetric(this.getDefaultFilters(filter));
        else if (keyword.toLowerCase().equals("decision"))
            return this.getDecisionMetric(this.getDefaultFilters(filter));
        return null;
    }
    
    /**
     * Method responsible for returning the Activity Metric Value.
     * @param  parameters Parameters List.
     * @return Activity Metric Value.
     */
    public Double getActivityMetric(Object[] parameters) {
               this.addObjects(this.evaluationActivityUML.filter(parameters));
        return this.evaluationActivityUML.getMetricValue(parameters);
    }
    
    /**
     * Method responsible for returning the Decision Metric Value.
     * @param  parameters Parameters List.
     * @return Decision Metric Value.
     */
    public Double getDecisionMetric(Object[] parameters) {
               this.addObjects(this.evaluationDecisionUML.filter(parameters));
        return this.evaluationDecisionUML.getMetricValue(parameters);
    }
    
    @Override
    public String[] getValues() {
        String[] values    = new String[2];
                 values[0] = Integer.toString(this.diagram.getActivitiesList().size());
                 values[1] = Integer.toString(this.diagram.getDecisionsList().size());
        return   values;
    }
}