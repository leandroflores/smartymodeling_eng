package funct.evaluation.base;

import funct.evaluation.Evaluation;
import model.structural.base.Diagram;

/**
 * <p>Class of Evaluation <b>EvaluationDiagram</b>.</p>
 * <p>Class responsible for <b>Evaluate</b> the <b>Diagram</b>.</p>
 * @author Leandro
 * @since  23/10/2019
 * @see    funct.evaluation.Evaluation
 * @see    model.structural.base.Diagram
 */
public class EvaluationDiagram extends Evaluation {
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
    protected Double getClauseValue(String keyword, String filter) {
        if (this.isElement(keyword))
            return new EvaluationElement(this.diagram, keyword).getClauseValue(keyword, filter);
        else if (this.isAssociation(keyword))
            return new EvaluationAssociation(this.diagram, keyword).getClauseValue(keyword, filter);
        else if (this.isProductLine(keyword))
            System.out.println("Evaluation Product Line");
        return 0.0d;
    }
}