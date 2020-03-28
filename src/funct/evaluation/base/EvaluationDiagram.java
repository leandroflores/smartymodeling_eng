package funct.evaluation.base;

import funct.evaluation.Evaluation;
import java.util.ArrayList;
import model.structural.base.Diagram;

/**
 * <p>Class of Evaluation <b>EvaluationDiagram</b>.</p>
 * <p>Class responsible for <b>Evaluate</b> the <b>Diagram</b>.</p>
 * @author Leandro
 * @since  23/10/2019
 * @see    funct.evaluation.Evaluation
 * @see    model.structural.base.Diagram
 */
public abstract class EvaluationDiagram extends Evaluation {
    private final Diagram  diagram;
    
    /**
     * Default constructor method of Class.
     * @param diagram Diagram.
     */
    public EvaluationDiagram(Diagram diagram) {
        super(diagram.getProject());
        this.diagram = diagram;
        this.objects = new ArrayList<>();
    }
    
    /**
     * Method responsible for returning the Diagram Values.
     * @return Diagram Values.
     */
    public abstract Object[] getValues();
}