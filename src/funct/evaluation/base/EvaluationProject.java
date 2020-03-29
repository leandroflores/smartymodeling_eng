package funct.evaluation.base;

import funct.evaluation.Evaluation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.association.Association;

/**
 * <p>Class of Evaluation <b>EvaluationDiagram</b>.</p>
 * <p>Class responsible for <b>Evaluate</b> the <b>Diagram</b>.</p>
 * @author Leandro
 * @since  23/10/2019
 * @see    funct.evaluation.Evaluation
 * @see    model.structural.base.Diagram
 */
public class EvaluationProject extends Evaluation {
    private final Project project;
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     */
    public EvaluationProject(Project project) {
        super(project);
        this.project = project;
        this.objects = new ArrayList<>();
    }

    @Override
    protected Double getClauseValue(String keyword, String filter) {
        if (this.isElement(keyword))
            System.out.println("Evaluation Element");
        else if (this.isAssociation(keyword))
            System.out.println("Evaluation Association");
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
        String[] array = {"element", 
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
        String[] array = {"association", "dependency", "generalization", 
                          "communication", 
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
     * Method responsible for returning the Elements List by Keyword.
     * @param  keyword Clause Keyword.
     * @return Elements List found.
     */
    protected List<Element> getElements(String keyword) {
        return keyword.equalsIgnoreCase("element") ?
                this.project.getElements() : 
                this.project.getElements(keyword);
    }
    
    /**
     * Method responsible for returning the Associations List by Keyword.
     * @param  keyword Clause Keyword.
     * @return Associations List found.
     */
    protected List<Association> getAssociations(String keyword) {
        return keyword.equalsIgnoreCase("association") ?
                this.project.getAssociations(): 
                this.project.getAssociations(keyword);
    }
}