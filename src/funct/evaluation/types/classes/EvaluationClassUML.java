package funct.evaluation.types.classes;

import funct.evaluation.base.EvaluationElement;
import java.util.List;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.ClassUML;

/**
 * <p>Class of Evaluation <b>EvaluationClassUML</b>.</p>
 * <p>Class responsible por <b>Evaluate</b> the <b>Classes UML</b>.</p>
 * @author Leandro
 * @since  27/03/2020
 * @see    funct.evaluation.base.EvaluationElement
 * @see    model.structural.diagram.ClassDiagram
 * @see    model.structural.diagram.classes.base.ClassUML
 */
public class EvaluationClassUML extends EvaluationElement {
    private final ClassDiagram classDiagram;
    
    /**
     * Default constructor method of Class.
     * @param diagram Class Diagram.
     */
    public EvaluationClassUML(ClassDiagram diagram) {
        super(diagram);
        this.classDiagram = diagram;
    }
    
    @Override
    public List<ClassUML> filter(Object[] parameters) {
        List   filter = this.classDiagram.getClassesList();
               filter = this.filterNames(filter, (List<String>) parameters[0]);
               filter = this.filterStereotypes(filter, (List<String>) parameters[1]);
               filter = this.filterMandatory(filter, (Boolean) parameters[2]);
               filter = this.filterAbstract(filter, (Boolean) parameters[3]);
               filter = this.filterFinal(filter, (Boolean) parameters[4]);
        return filter;
    }
}