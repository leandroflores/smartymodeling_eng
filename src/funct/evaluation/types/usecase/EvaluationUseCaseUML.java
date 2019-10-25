package funct.evaluation.types.usecase;

import funct.evaluation.base.EvaluationElement;
import java.util.List;
import model.structural.diagram.UseCaseDiagram;
import model.structural.diagram.usecase.base.UseCaseUML;

/**
 * <p>Class of Metric <b>EvaluationUseCaseUML</b>.</p>
 * <p>Class responsible por <b>Evaluate</b> the <b>Use Cases UML</b>.</p>
 * @author Leandro
 * @since  02/09/2019
 * @see    funct.evaluation.base.EvaluationElement
 * @see    model.structural.diagram.UseCaseDiagram
 * @see    model.structural.diagram.usecase.base.UseCaseUML
 */
public class EvaluationUseCaseUML extends EvaluationElement {
    private final UseCaseDiagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param diagram Use Case Diagram.
     */
    public EvaluationUseCaseUML(UseCaseDiagram diagram) {
        super(diagram);
        this.diagram = diagram;
    }
    
    @Override
    public List<UseCaseUML> filter(Object[] parameters) {
           List filter = this.diagram.getUseCasesList();
                filter = this.filterNames(filter, (List<String>) parameters[0]);
//                filter = this.filterStereotypes(filter, (List<String>) parameters[1]);
                filter = this.filterMandatory(filter, (Boolean) parameters[1]);
        return  filter;
    }
}