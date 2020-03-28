package funct.evaluation.types.sequence;

import funct.evaluation.base.EvaluationElement;
import java.util.List;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.sequence.base.LifelineUML;

/**
 * <p>Class of Evaluation <b>EvaluationLifelineUML</b>.</p>
 * <p>Class responsible por <b>Evaluate</b> the <b>Lifelines UML</b>.</p>
 * @author Leandro
 * @since  27/03/2020
 * @see    funct.evaluation.base.EvaluationElement
 * @see    model.structural.diagram.SequenceDiagram
 * @see    model.structural.diagram.sequence.base.LifelineUML
 */
public class EvaluationLifelineUML extends EvaluationElement {
    private final SequenceDiagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param diagram Sequence Diagram.
     */
    public EvaluationLifelineUML(SequenceDiagram diagram) {
        super(diagram);
        this.diagram = diagram;
    }
    
    @Override
    public List<LifelineUML> filter(Object[] parameters) {
           List filter = this.diagram.getLifelinesList();
                filter = this.filterNames(filter, (List<String>) parameters[0]);
                filter = this.filterStereotypes(filter, (List<String>) parameters[1]);
                filter = this.filterMandatory(filter, (Boolean) parameters[2]);
        return  filter;
    }
}