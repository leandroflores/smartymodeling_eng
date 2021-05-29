package funct.evaluation.base;

import funct.evaluation.base.association.EvaluationAssociation;
import funct.evaluation.base.product.EvaluationProduct;
import funct.evaluation.base.variability.EvaluationVariability;
import model.structural.base.Project;

/**
 * <p>Class of Funct <b>EvaluationProject</b>.</p>
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
        if (isElement(keyword))
            return getValue(new EvaluationElement(project, keyword), keyword, filter);
        else if (isAssociation(keyword))
            return getValue(new EvaluationAssociation(project, keyword), keyword, filter);
        else if (isVariability(keyword))
            return getValue(new EvaluationVariability(project, keyword), keyword, filter);
        else if (isProduct(keyword))
            return getValue(new EvaluationProduct(project, null), keyword, filter);
        return 0.0d;
    }
}