package funct.evaluation.base;

import funct.evaluation.Evaluation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.structural.base.Project;

/**
 * <p>Class of Funct <b>EvaluationBase</b>.</p>
 * <p>Class responsible for defining the <b>Evaluation Base</b> in the SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-28
 * @see    funct.evaluation.Evaluation
 * @see    model.structural.base.Project
 */
public abstract class EvaluationBase extends Evaluation {
    protected final List result;
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     */
    public EvaluationBase(Project project) {
        super(project);
        result = new ArrayList<>();
    }
    
    /**
     * Method responsible for returning the Value.
     * @param  evaluation Evaluation Object.
     * @param  keyword Clause Keyword.
     * @param  filter Clause Filter.
     * @return Value.
     */
    public Double getValue(Evaluation evaluation, String keyword, String filter) {
        Double value = evaluation.getClauseValue(keyword, filter);
        addResult(evaluation.getObjects());
        return value;
    }
    
    /**
     * Method responsible for checking if the Keyword is a Element.
     * @param  keyword Clause Keyword.
     * @return Keyword is a Element.
     */
    protected boolean isElement(String keyword) {
        String[] array = {"element", "elements", 
                          "feature", 
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
                          "connection",
                          "communication", "extend", "include",
                          "association", "realization",
                          "comunication",
                          "flow",
                          "message"};
        return Arrays.asList(array).contains(keyword.toLowerCase());
    }
    
    /**
     * Method responsible for checking if the Keyword is a Variability.
     * @param  keyword Clause Keyword.
     * @return Keyword is a Product Line.
     */
    protected boolean isVariability(String keyword) {
        String[] array = {"variability", "variants", "variationpoint"};
        return Arrays.asList(array).contains(keyword.toLowerCase());
    }
    
    /**
     * Method responsible for checking if the Keyword is a Product Line.
     * @param  keyword Clause Keyword.
     * @return Keyword is a Product Line.
     */
    protected boolean isProduct(String keyword) {
        String[] array = {"product", "instance", "artifact"};
        return Arrays.asList(array).contains(keyword.toLowerCase());
    }
    
    /**
     * Method responsible for adding the Objects List to Result.
     * @param list Objects List.
     */
    protected void addResult(List list) {
        result.addAll(new ArrayList<>(list));
    }
    
    /**
     * Method responsible for returning the Objects List.
     * @return Objects List.
     */
    public List<Object> getResult() {
        return result;
    }
}