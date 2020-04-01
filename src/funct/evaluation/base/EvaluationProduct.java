package funct.evaluation.base;

import funct.evaluation.Evaluation;
import funct.evaluation.base.types.EvaluationArtifact;
import funct.evaluation.base.types.EvaluationInstance;
import model.structural.base.Project;
import model.structural.base.product.Product;

/**
 * <p>Class of Evaluation <b>EvaluationProduct</b>.</p>
 * <p>Class responsible for <b>Evaluate</b> the <b>Product</b>.</p>
 * @author Leandro
 * @since  01/04/2020
 * @see    funct.evaluation.Evaluation
 * @see    model.structural.base.product.Product
 * @see    funct.evaluation.base.types.EvaluationAssociation
 * @see    funct.evaluation.base.types.EvaluationElement
 * @see    funct.evaluation.base.types.EvaluationVariability
 */
public class EvaluationProduct extends Evaluation {
    private final Product product;
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     * @param product Product.
     */
    public EvaluationProduct(Project project, Product product) {
        super(project);
        this.product = product;
    }

    @Override
    protected Double getClauseValue(String keyword, String filter) {
        if (keyword.equalsIgnoreCase("instance"))
            return new EvaluationInstance(this.project, this.product).getClauseValue(keyword, filter);
        else if (keyword.equalsIgnoreCase("artifact"))
            return new EvaluationArtifact(this.project, this.product).getClauseValue(keyword, filter);
        return 0.0d;
    }
}