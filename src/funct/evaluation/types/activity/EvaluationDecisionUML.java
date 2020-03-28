package funct.evaluation.types.activity;

import funct.evaluation.base.EvaluationElement;
import java.util.List;
import model.structural.diagram.ActivityDiagram;
import model.structural.diagram.activity.base.DecisionUML;

/**
 * <p>Class of Evaluation <b>EvaluationDecisionUML</b>.</p>
 * <p>Class responsible por <b>Evaluate</b> the <b>Decisions UML</b>.</p>
 * @author Leandro
 * @since  27/03/2020
 * @see    funct.evaluation.base.EvaluationElement
 * @see    model.structural.diagram.ActivityDiagram
 * @see    model.structural.diagram.activity.base.DecisionUML
 */
public class EvaluationDecisionUML extends EvaluationElement {
    private final ActivityDiagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param diagram Activity Diagram.
     */
    public EvaluationDecisionUML(ActivityDiagram diagram) {
        super(diagram);
        this.diagram = diagram;
    }
    
    @Override
    public List<DecisionUML> filter(Object[] parameters) {
           List filter = this.diagram.getActivitiesList();
                filter = this.filterNames(filter, (List<String>) parameters[0]);
                filter = this.filterStereotypes(filter, (List<String>) parameters[1]);
                filter = this.filterMandatory(filter, (Boolean) parameters[2]);
        return  filter;
    }
}