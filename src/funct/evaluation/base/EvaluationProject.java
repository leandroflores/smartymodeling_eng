package funct.evaluation.base;

import funct.evaluation.Evaluation;
import model.structural.base.Project;

/**
 * <p>Class of Evaluation <b>EvaluationDiagram</b>.</p>
 * <p>Class responsible for <b>Evaluate</b> the <b>Diagram</b>.</p>
 * @author Leandro
 * @since  23/10/2019
 * @see    funct.evaluation.Evaluation
 * @see    model.structural.base.Diagram
 */
public class EvaluationProject extends Evaluation {
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     */
    public EvaluationProject(Project project) {
        super(project);
    }

    @Override
    protected Double getClauseValue(String keyword, String filter) {
        if (this.isElement(keyword))
            return new EvaluationElement(this.project, keyword).getClauseValue(keyword, filter);
        else if (this.isAssociation(keyword))
            return new EvaluationAssociation(this.project, keyword).getClauseValue(keyword, filter);
        else if (this.isProductLine(keyword))
            System.out.println("Evaluation Product Line");
        return 0.0d;
    }
}