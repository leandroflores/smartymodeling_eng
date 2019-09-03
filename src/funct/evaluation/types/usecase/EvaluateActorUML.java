package funct.evaluation.types.usecase;

import funct.evaluation.base.EvaluateElement;
import java.util.List;
import model.structural.diagram.UseCaseDiagram;
import model.structural.diagram.usecase.base.ActorUML;

/**
 * <p>Class of Metric <b>EvaluateActorUML</b>.</p>
 * <p>Class responsible por <b>Evaluate</b> the <b>Actors UML</b>.</p>
 * @author Leandro
 * @since  02/09/2019
 * @see    funct.evaluation.base.EvaluateElement
 * @see    model.structural.diagram.UseCaseDiagram
 * @see    model.structural.diagram.usecase.base.ActorUML
 */
public class EvaluateActorUML extends EvaluateElement {
    private final UseCaseDiagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param diagram Use Case Diagram.
     */
    public EvaluateActorUML(UseCaseDiagram diagram) {
        super(diagram);
        this.diagram = diagram;
    }
    
    @Override
    public List<ActorUML> filter(Object[] parameters) {
           List filter = this.diagram.getActorsList();
                filter = this.filterNames(filter, (List<String>) parameters[1]);
                filter = this.filterStereotypes(filter, (List<String>) parameters[2]);
                filter = this.filterMandatory(filter, (Boolean) parameters[3]);
        return  filter;
    }
}