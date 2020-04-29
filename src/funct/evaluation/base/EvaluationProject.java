package funct.evaluation.base;

import funct.evaluation.base.association.EvaluationAssociation;
import funct.evaluation.base.variability.EvaluationVariability;
import model.structural.base.Project;

/**
 * <p>Class of EvaluationBase <b>EvaluationProject</b>.</p>
 * <p>Class responsible for <b>Evaluate</b> the <b>Project</b> in the SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-23
 * @see    funct.evaluation.base.EvaluationBase
 * @see    funct.evaluation.base.EvaluationElement
 * @see    funct.evaluation.base.association.EvaluationAssociation
 * @see    funct.evaluation.base.variability.EvaluationVariability
 * @see    model.structural.base.Project
 */
public class EvaluationProject extends EvaluationBase {
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     */
    public EvaluationProject(Project project) {
        super(project);
    }

    @Override
    public Double getClauseValue(String keyword, String filter) {
        if (this.isElement(keyword))
            return super.getValue(new EvaluationElement(this.project, keyword), keyword, filter);
        else if (this.isAssociation(keyword))
            return super.getValue(new EvaluationAssociation(this.project, keyword), keyword, filter);
        else if (this.isVariability(keyword))
            return super.getValue(new EvaluationVariability(this.project, keyword), keyword, filter);
        else if (this.isProduct(keyword))
            System.out.println("Evaluation Product Line");
        return 0.0d;
    }
}