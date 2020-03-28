package funct.evaluation.types.component;

import funct.evaluation.base.EvaluationElement;
import java.util.List;
import model.structural.diagram.ComponentDiagram;
import model.structural.diagram.component.base.ComponentUML;

/**
 * <p>Class of Evaluation <b>EvaluationComponentUML</b>.</p>
 * <p>Class responsible por <b>Evaluate</b> the <b>Components UML</b>.</p>
 * @author Leandro
 * @since  27/03/2020
 * @see    funct.evaluation.base.EvaluationElement
 * @see    model.structural.diagram.ComponentDiagram
 * @see    model.structural.diagram.component.base.ComponentUML
 */
public class EvaluationComponentUML extends EvaluationElement {
    private final ComponentDiagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param diagram Component Diagram.
     */
    public EvaluationComponentUML(ComponentDiagram diagram) {
        super(diagram);
        this.diagram = diagram;
    }
    
    @Override
    public List<ComponentUML> filter(Object[] parameters) {
           List filter = this.diagram.getComponentsList();
                filter = this.filterNames(filter, (List<String>) parameters[0]);
                filter = this.filterStereotypes(filter, (List<String>) parameters[1]);
                filter = this.filterMandatory(filter, (Boolean) parameters[2]);
        return  filter;
    }
}