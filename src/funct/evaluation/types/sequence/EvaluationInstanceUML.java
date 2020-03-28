package funct.evaluation.types.sequence;

import funct.evaluation.base.EvaluationElement;
import java.util.List;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.sequence.base.InstanceUML;

/**
 * <p>Class of Evaluation <b>EvaluationInstanceUML</b>.</p>
 * <p>Class responsible por <b>Evaluate</b> the <b>Instances UML</b>.</p>
 * @author Leandro
 * @since  27/03/2020
 * @see    funct.evaluation.base.EvaluationElement
 * @see    model.structural.diagram.SequenceDiagram
 * @see    model.structural.diagram.sequence.base.InstanceUML
 */
public class EvaluationInstanceUML extends EvaluationElement {
    private final SequenceDiagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param diagram Sequence Diagram.
     */
    public EvaluationInstanceUML(SequenceDiagram diagram) {
        super(diagram);
        this.diagram = diagram;
    }
    
    @Override
    public List<InstanceUML> filter(Object[] parameters) {
           List filter = this.diagram.getLifelinesList();
                filter = this.filterNames(filter, (List<String>) parameters[0]);
                filter = this.filterStereotypes(filter, (List<String>) parameters[1]);
                filter = this.filterMandatory(filter, (Boolean) parameters[2]);
        return  filter;
    }
}