package funct.evaluation.types;

import funct.evaluation.Evaluation;
import funct.evaluation.types.usecase.EvaluationActorUML;
import funct.evaluation.types.usecase.EvaluationUseCaseUML;
import model.structural.diagram.UseCaseDiagram;

/**
 * <p>Class of Metric <b>EvaluationUseCaseDiagram</b>.</p>
 * <p>Class responsible por <b>Evaluate</b> of <b>Use Case Diagram</b>.</p>
 * @author Leandro
 * @since  02/09/2019
 * @see    funct.evaluation.Evaluation
 * @see    funct.evaluation.types.usecase.EvaluationActorUML
 * @see    funct.evaluation.types.usecase.EvaluationUseCaseUML
 * @see    model.structural.diagram.UseCaseDiagram
 */
public class EvaluationUseCaseDiagram extends Evaluation {
    private final UseCaseDiagram   diagram;
    private final EvaluationActorUML   metricActorUML;
    private final EvaluationUseCaseUML metricUseCaseUML;
    
    /**
     * Default constructor method of Class.
     * @param diagram Use Case Diagram.
     */
    public EvaluationUseCaseDiagram(UseCaseDiagram diagram) {
        super(diagram.getProject());
        this.diagram          = diagram;
        this.metricActorUML   = new EvaluationActorUML(diagram);
        this.metricUseCaseUML = new EvaluationUseCaseUML(diagram);
    }
    
    @Override
    public Double getClauseValue(String keyword, String filter) {
        if (keyword.equalsIgnoreCase("actor"))
            return this.metricActorUML.getMetricValue(this.getActorFilters(filter));
        else if (keyword.equalsIgnoreCase("usecase"))
            return this.metricUseCaseUML.getMetricValue(this.getUseCaseFilters(filter));
        return null;
    }
    
    /**
     * Method responsible for returning the Actor Filters.
     * @param  filter Clause Filter.
     * @return Actor Filters.
     */
    private Object[] getActorFilters(String filter) {
        Object[] filters    = new Object[2];
                 filters[0] = this.getNames(filter);
                     filter = this.clearNames(filter);
                 filters[1] = this.getMandatory(filter);
        return   filters;
    }
    
    /**
     * Method responsible for returning the Use Case Filters.
     * @param  filter Clause Filter.
     * @return Use Case Filters.
     */
    private Object[] getUseCaseFilters(String filter) {
        Object[] filters    = new Object[2];
                 filters[0] = this.getNames(filter);
                     filter = this.clearNames(filter);
                 filters[1] = this.getMandatory(filter);
        return   filters;
    }
    
    /**
     * Method responsible for returning the Actor Metric Value.
     * @param  parameters Parameters List.
     * @return Actor Metric Value.
     */
    public Double getActorMetric(Object[] parameters) {
        return this.metricActorUML.getMetricValue(parameters);
    }
    
    /**
     * Method responsible for returning the Use Case Metric Value.
     * @param  parameters Parameters List.
     * @return Use Case Metric Value.
     */
    public Double getUseCaseMetric(Object[] parameters) {
        return this.metricUseCaseUML.getMetricValue(parameters);
    }
    
    /**
     * Method responsible for returning the Diagram Values.
     * @return Diagram Values.
     */
    public String[] getValues() {
        String[] values    = new String[2];
                 values[0] = Integer.toString(this.diagram.getActorsList().size());
                 values[1] = Integer.toString(this.diagram.getUseCasesList().size());
        return   values;
    }
}