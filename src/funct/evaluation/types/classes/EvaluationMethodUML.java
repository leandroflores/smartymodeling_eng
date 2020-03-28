package funct.evaluation.types.classes;

import funct.evaluation.base.EvaluationElement;
import java.util.List;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.MethodUML;

/**
 * <p>Class of Evaluation <b>EvaluationMethodUML</b>.</p>
 * <p>Class responsible por <b>Evaluate</b> the <b>Methods UML</b>.</p>
 * @author Leandro
 * @since  28/03/2020
 * @see    funct.evaluation.base.EvaluationElement
 * @see    model.structural.diagram.ClassDiagram
 * @see    model.structural.diagram.classes.base.MethodUML
 */
public class EvaluationMethodUML extends EvaluationElement {
    private final ClassDiagram classDiagram;
    
    /**
     * Default constructor method of Class.
     * @param diagram Class Diagram.
     */
    public EvaluationMethodUML(ClassDiagram diagram) {
        super(diagram);
        this.classDiagram = diagram;
    }
    
    @Override
    public List<MethodUML> filter(Object[] parameters) {
        List   filter = this.classDiagram.getMethodsList();
               filter = this.filterNames(filter, (List<String>) parameters[0]);
               filter = this.filterStereotypes(filter, (List<String>) parameters[1]);
               filter = this.filterMandatory(filter, (Boolean) parameters[2]);
               filter = this.filterAbstract(filter,  (Boolean) parameters[3]);
               filter = this.filterFinal(filter,     (Boolean) parameters[4]);
               filter = this.filterStatic(filter,    (Boolean) parameters[5]);
               filter = this.filterVisibility(filter, (String) parameters[6]);
        return filter;
    }
}