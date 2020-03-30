package funct.evaluation.types;

import funct.evaluation.base.EvaluationDiagram;
import funct.evaluation.base.EvaluationElement;
import funct.evaluation.types.usecase.EvaluationActorUML;
import funct.evaluation.types.usecase.EvaluationUseCaseUML;
import model.structural.diagram.UseCaseDiagram;

/**
 * <p>Class of Evaluation <b>EvaluationUseCaseDiagram</b>.</p>
 * <p>Class responsible for <b>Evaluate</b> the <b>Use Case Diagram</b>.</p>
 * @author Leandro
 * @since  02/09/2019
 * @see    funct.evaluation.base.EvaluationDiagram
 * @see    funct.evaluation.types.usecase.EvaluationActorUML
 * @see    funct.evaluation.types.usecase.EvaluationUseCaseUML
 * @see    model.structural.diagram.UseCaseDiagram
 */
public class EvaluationUseCaseDiagram extends EvaluationDiagram {
    private final UseCaseDiagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param diagram Use Case Diagram.
     */
    public EvaluationUseCaseDiagram(UseCaseDiagram diagram) {
        super(diagram);
        this.diagram = diagram;
    }
    
    @Override
    public Double getClauseValue(String keyword, String filter) {
        if (keyword.toLowerCase().equals("actor"))
            return this.getActorMetric(this.getDefaultFilters(filter));
        else if (keyword.toLowerCase().equals("usecase"))
            return this.getUseCaseMetric(this.getDefaultFilters(filter));
        return null;
    }
    
    /**
     * Method responsible for returning the Actor Metric Value.
     * @param  parameters Parameters List.
     * @return Actor Metric Value.
     */
    public Double getActorMetric(Object[] parameters) {
        EvaluationActorUML evaluation = new EvaluationActorUML(this.diagram);
               this.addObjects(evaluation.filter(parameters));
        return evaluation.getMetricValue(parameters);
    }
    
    /**
     * Method responsible for returning the Use Case Metric Value.
     * @param  parameters Parameters List.
     * @return Use Case Metric Value.
     */
    public Double getUseCaseMetric(Object[] parameters) {
        EvaluationElement evaluation = new EvaluationUseCaseUML(this.diagram);
               this.addObjects(evaluation.filter(parameters));
        return evaluation.getMetricValue(parameters);
    }
    
    @Override
    public String[] getValues() {
        String[] values    = new String[2];
                 values[0] = Integer.toString(this.diagram.getActorsList().size());
                 values[1] = Integer.toString(this.diagram.getUseCasesList().size());
        return   values;
    }
}