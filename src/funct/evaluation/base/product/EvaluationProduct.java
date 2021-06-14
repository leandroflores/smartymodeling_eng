package funct.evaluation.base.product;

import funct.evaluation.base.EvaluationBase;
import model.structural.base.Project;
import model.structural.base.product.Product;

/**
 * <p>Class of Funct <b>EvaluationProduct</b>.</p>
 * <p>Class responsible for <b>Evaluate</b> the <b>Product</b> in the SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-01
 * @see    funct.evaluation.base.EvaluationBase
 * @see    funct.evaluation.base.product.EvaluationArtifact
 * @see    funct.evaluation.base.product.EvaluationInstance
 * @see    model.structural.base.product.Product
 */
public class EvaluationProduct extends EvaluationBase {
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
    public Double getClauseValue(String keyword, String filter) {
        if (keyword.equalsIgnoreCase("instance"))
            return getValue(new EvaluationInstance(project, product), keyword, filter);
        else if (keyword.equalsIgnoreCase("artifact"))
            return getValue(new EvaluationArtifact(project, product), keyword, filter);
        return 0.0d;
    }
}