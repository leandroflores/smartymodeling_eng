package funct.evaluation.base;

import funct.evaluation.Evaluation;
import java.util.ArrayList;
import model.structural.base.Diagram;
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
        System.out.println("KeyWord: " + keyword);
        System.out.println("Filter.: " + filter);
        System.out.println("");
        return 0.0d;
    }
}