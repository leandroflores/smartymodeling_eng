package funct.evaluation.types.classes;

import funct.evaluation.base.EvaluationElement;
import java.util.List;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.PackageUML;

/**
 * <p>Class of Evaluation <b>EvaluationPackageUML</b>.</p>
 * <p>Class responsible por <b>Evaluate</b> the <b>Packages UML</b>.</p>
 * @author Leandro
 * @since  27/03/2020
 * @see    funct.evaluation.base.EvaluationElement
 * @see    model.structural.diagram.ClassDiagram
 * @see    model.structural.diagram.classes.base.PackageUML
 */
public class EvaluationPackageUML extends EvaluationElement {
    private final ClassDiagram classDiagram;
    
    /**
     * Default constructor method of Class.
     * @param diagram Class Diagram.
     */
    public EvaluationPackageUML(ClassDiagram diagram) {
        super(diagram);
        this.classDiagram = diagram;
    }
    
    @Override
    public List<PackageUML> filter(Object[] parameters) {
        List   filter = this.classDiagram.getPackagesList();
               filter = this.filterNames(filter, (List<String>) parameters[0]);
               filter = this.filterStereotypes(filter, (List<String>) parameters[1]);
               filter = this.filterMandatory(filter, (Boolean) parameters[2]);
        return filter;
    }
}