package funct.evaluation.types.usecase;

import funct.evaluation.types.MetricElement;
import java.util.List;
import model.structural.diagram.UseCaseDiagram;
import model.structural.diagram.usecase.base.ActorUML;
import model.structural.diagram.usecase.base.UseCaseUML;

/**
 * <p>Class of Metric <b>MetricUseCaseUML</b>.</p>
 * <p>Class responsible por extract the Metrics of <b>Use Cases UML</b>.</p>
 * @author Leandro
 * @since  02/09/2019
 */
public class MetricUseCaseUML extends MetricElement {
    private final UseCaseDiagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param diagram Use Case Diagram.
     */
    public MetricUseCaseUML(UseCaseDiagram diagram) {
        super(diagram);
        this.diagram = diagram;
    }
    
    @Override
    public List<UseCaseUML> filter(Object[] parameters) {
           List filter = this.diagram.getActorsList();
                filter = this.filterNames(filter, (List<String>) parameters[1]);
                filter = this.filterStereotypes(filter, (List<String>) parameters[2]);
                filter = this.filterMandatory(filter, (Boolean) parameters[3]);
        return  filter;
    }
}