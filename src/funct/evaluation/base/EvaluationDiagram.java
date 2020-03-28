package funct.evaluation.base;

import funct.evaluation.Evaluation;
import java.util.ArrayList;
import java.util.List;
import model.structural.base.Diagram;

/**
 * <p>Class of Evaluation <b>EvaluationDiagram</b>.</p>
 * <p>Class responsible for <b>Evaluate</b> the <b>Diagram</b>.</p>
 * @author Leandro
 * @since  23/10/2019
 * @see    funct.evaluation.Evaluation
 * @see    model.structural.base.Diagram
 */
public abstract class EvaluationDiagram extends Evaluation {
    private final Diagram  diagram;
    protected List<Object> objects;
    
    /**
     * Default constructor method of Class.
     * @param diagram Diagram.
     */
    public EvaluationDiagram(Diagram diagram) {
        super(diagram.getProject());
        this.diagram = diagram;
        this.objects = new ArrayList<>();
    }
    
    /**
     * Method responsible for adding the Objects List.
     * @param list Objects List.
     */
    protected void addObjects(List list) {
        this.objects.addAll(new ArrayList<>(list));
        this.objects.add("\n");
    }
    
    /**
     * Method responsible for returning the Default Filters.
     * @param  filter Clause Filter.
     * @return Default Filters.
     */
    protected Object[] getDefaultFilters(String filter) {
        Object[] filters    = new Object[3];
                 filters[0] = this.getNames(filter);
                     filter = this.clearNames(filter);
                 filters[1] = this.getStereotypes(filter);
                     filter = this.clearStereotypes(filter);
                 filters[2] = this.getMandatory(filter);
        return   filters;
    }
    
    /**
     * Method responsible for returning the Diagram Values.
     * @return Diagram Values.
     */
    public abstract Object[] getValues();

    /**
     * Method responsible for returning the Objects List.
     * @return Objects List.
     */
    public List<Object> getObjects() {
        return this.objects;
    }
}