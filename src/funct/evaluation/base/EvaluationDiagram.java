package funct.evaluation.base;

import funct.evaluation.base.association.EvaluationAssociation;
import funct.evaluation.base.variability.EvaluationVariability;
import model.structural.base.Diagram;

/**
 * <p>Class of Funct <b>EvaluationDiagram</b>.</p>
 * <p>Class responsible for <b>Evaluate</b> the <b>Diagram</b> in the SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-23
 * @see    funct.evaluation.base.EvaluationBase
 * @see    funct.evaluation.base.EvaluationElement
 * @see    funct.evaluation.base.association.EvaluationAssociation
 * @see    funct.evaluation.base.variability.EvaluationVariability
 * @see    model.structural.base.Diagram
 */
public class EvaluationDiagram extends EvaluationBase {
    private final Diagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param diagram Diagram.
     */
    public EvaluationDiagram(Diagram diagram) {
        super(diagram.getProject());
        this.diagram = diagram;
    }

    @Override
    public Double getClauseValue(String keyword, String filter) {
        if (isElement(keyword))
            return getValue(new EvaluationElement(diagram, keyword), keyword, filter);
        else if (isAssociation(keyword))
            return getValue(new EvaluationAssociation(diagram, keyword), keyword, filter);
        else if (isVariability(keyword))
            return getValue(new EvaluationVariability(diagram, keyword), keyword, filter);
        return 0.0d;
    }
}