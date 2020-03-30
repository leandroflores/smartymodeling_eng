package funct.evaluation.base;

import funct.evaluation.Evaluation;
import java.util.ArrayList;
import java.util.Arrays;
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
        this.objects = new ArrayList<>();
    }

    @Override
    protected Double getClauseValue(String keyword, String filter) {
        if (this.isElement(keyword))
            this.getElementMetric(this.getDefaultFilters(filter));
        else if (this.isAssociation(keyword))
            this.evaluateAssociation(keyword, filter);
        else if (this.isProductLine(keyword))
            System.out.println("Evaluation Product Line");
        return 1.0d;
    }
    
    /**
     * Method responsible for checking if the Keyword is a Element.
     * @param  keyword Clause Keyword.
     * @return Keyword is a Element.
     */
    protected boolean isElement(String keyword) {
        String[] array = {"elements", 
                          "actor", "usecase", 
                          "package", "class", "interface", "attribute", "method",
                          "component",
                          "activity", "decision",
                          "lifeline", "instance"};
        return Arrays.asList(array).contains(keyword.toLowerCase());
    }
    
    /**
     * Method responsible for checking if the Keyword is a Association.
     * @param  keyword Clause Keyword.
     * @return Keyword is a Association.
     */
    protected boolean isAssociation(String keyword) {
        String[] array = {"associations", "dependency", "generalization", 
                          "communication", "extend", "include",
                          "association", "realization",
                          "comunication",
                          "flow",
                          "message"};
        return Arrays.asList(array).contains(keyword.toLowerCase());
    }
    
    /**
     * Method responsible for checking if the Keyword is a Product Line.
     * @param  keyword Clause Keyword.
     * @return Keyword is a Product Line.
     */
    protected boolean isProductLine(String keyword) {
        String[] array = {"product", "instance", "artifact"};
        return Arrays.asList(array).contains(keyword.toLowerCase());
    }
    
    /**
     * Method responsible for returning the Element Metric Value.
     * @param  parameters Parameters List.
     * @return Element Metric Value.
     */
    public Double getElementMetric(Object[] parameters) {
        EvaluationElement evaluation = new EvaluationElement(this.project);
               this.addObjects(evaluation.filter(parameters));
        return evaluation.getMetricValue(parameters);
    }
    
    /**
     * Method responsible for evaluate the Association.
     * @param  keyword Clause Keyword.
     * @param  filter Clause Filter.
     * @return Evaluation Value.
     */
    public Double evaluateAssociation(String keyword, String filter) {
        EvaluationAssociation evaluation = new EvaluationAssociation(this.project, keyword);
//               this.addObjects(evaluation.filter(this.getAssociationFilters(filter)));
        return evaluation.getEvaluationValue(this.getAssociationFilters(filter));
    }
}