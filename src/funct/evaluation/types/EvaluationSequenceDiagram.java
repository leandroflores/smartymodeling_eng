package funct.evaluation.types;

import funct.evaluation.base.EvaluationDiagram;
import funct.evaluation.types.sequence.EvaluationInstanceUML;
import funct.evaluation.types.sequence.EvaluationLifelineUML;
import model.structural.diagram.SequenceDiagram;

/**
 * <p>Class of Evaluation <b>EvaluationSequenceDiagram</b>.</p>
 * <p>Class responsible for <b>Evaluate</b> the <b>Sequence Diagram</b>.</p>
 * @author Leandro
 * @since  27/03/2020
 * @see    funct.evaluation.base.EvaluationDiagram
 * @see    funct.evaluation.types.sequence.EvaluationInstanceUML
 * @see    funct.evaluation.types.sequence.EvaluationLifelineUML
 * @see    model.structural.diagram.SequenceDiagram
 */
public class EvaluationSequenceDiagram extends EvaluationDiagram {
    private final SequenceDiagram diagram;
    private final EvaluationInstanceUML evaluationInstanceUML;
    private final EvaluationLifelineUML evaluationLifelineUML;
    
    /**
     * Default constructor method of Class.
     * @param diagram Sequence Diagram.
     */
    public EvaluationSequenceDiagram(SequenceDiagram diagram) {
        super(diagram);
        this.diagram = diagram;
        this.evaluationInstanceUML = new EvaluationInstanceUML(diagram);
        this.evaluationLifelineUML = new EvaluationLifelineUML(diagram);
    }
    
    @Override
    public Double getClauseValue(String keyword, String filter) {
        if (keyword.toLowerCase().equals("instance"))
            return this.getInstanceMetric(this.getDefaultFilters(filter));
        else if (keyword.toLowerCase().equals("lifeline"))
            return this.getLifelineMetric(this.getDefaultFilters(filter));
        return null;
    }
    
    /**
     * Method responsible for returning the Instance Metric Value.
     * @param  parameters Parameters List.
     * @return Instance Metric Value.
     */
    public Double getInstanceMetric(Object[] parameters) {
               this.addObjects(this.evaluationInstanceUML.filter(parameters));
        return this.evaluationInstanceUML.getMetricValue(parameters);
    }
    
    /**
     * Method responsible for returning the Lifeline Metric Value.
     * @param  parameters Parameters List.
     * @return Lifeline Metric Value.
     */
    public Double getLifelineMetric(Object[] parameters) {
               this.addObjects(this.evaluationLifelineUML.filter(parameters));
        return this.evaluationLifelineUML.getMetricValue(parameters);
    }
    
    @Override
    public String[] getValues() {
        String[] values    = new String[2];
                 values[0] = Integer.toString(this.diagram.getInstancesList().size());
                 values[1] = Integer.toString(this.diagram.getLifelinesList().size());
        return   values;
    }
}