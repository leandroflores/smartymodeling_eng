package funct.evaluation.base.types;

import funct.evaluation.Evaluation;
import java.util.ArrayList;
import java.util.List;
import model.structural.base.Diagram;
import model.structural.base.Project;
import model.structural.base.variability.Variability;

/**
 * <p>Class of Evaluation <b>EvaluationVariability</b>.</p>
 * <p>Class responsible for <b>Evaluate</b> the <b>Variabilities</b>.</p>
 * @author Leandro
 * @since  31/03/2020
 * @see    funct.evaluation.Evaluation
 * @see    model.structural.base.Diagram
 * @see    model.structural.base.Project
 * @see    model.structural.base.variability.Variability
 */
public class EvaluationVariability extends Evaluation {
    private final Diagram diagram;
    private final String  type;
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     * @param type Variabilty Type.
     */
    public EvaluationVariability(Project project, String type) {
        super(project);
        this.diagram = null;
        this.type    = type;
    }
    
    /**
     * Alternative constructor method of Class.
     * @param diagram Diagram.
     * @param type Variabilty Type.
     */
    public EvaluationVariability(Diagram diagram, String type) {
        super(diagram.getProject());
        this.diagram = diagram;
        this.type    = type;
    }
    
    @Override
    public Double getClauseValue(String keyword, String filter) {
        List   list = this.filter(this.getAssociationFilters(filter));
        String size = Integer.toString(list.size());
        return Double.parseDouble(size);
    }
    
    /**
     * Method responsible for filtering the Associations by Parameters.
     * @param  parameters Parameters List.
     * @return Associations filtered.
     */
    public List filter(Object[] parameters) {
           List filter = this.filterContext();
                filter = this.filterVariationPoints(filter, (List<String>) parameters[0]);
                filter = this.filterVariants(filter, (List<String>) parameters[1]);
                filter = this.filterConstraint(filter, (String) parameters[3]);
        return  filter;
    }
    
    /**
     * Method responsible for returning if Flag is for All Types.
     * @return Flag is for All Types.
     */
    protected boolean allTypes() {
        return this.type.equalsIgnoreCase("") 
            || this.type.equalsIgnoreCase("variability")
            || this.type.equalsIgnoreCase("variabilities");
    }
    
    /**
     * Method responsible for returning the Variabilities List by Context.
     * @return Variabilities List by Context.
     */
    protected List<Variability> getVariabilities() {
        return this.diagram != null ?
               this.diagram.getVariabilitiesList(): 
               this.project.getVariabilitiesList();
    }
    
    /**
     * Method responsible for filtering the Variabilities Context.
     * @return Variabilities Context.
     */
    protected List<Variability> filterContext() {
        return this.getVariabilities();
    }
    
    /**
     * Method responsible for returning the Variabilities List by Variation Points.
     * @param  list Variabilities List.
     * @param  names Variation Point List.
     * @return Variabilities filtered by Variation Points.
     */
    protected List<Variability> filterVariationPoints(List<Variability> list, List<String> names) {
        return this.isVoid(names) ? list : this.getVariationPoints(list, names);
    }
    
    /**
     * Method responsible for returning the Variabilities List by Variation Points.
     * @param  list Variabilities List.
     * @param  names Names List.
     * @return Variabilities filtered by Variation Points.
     */
    protected List<Variability> getVariationPoints(List<Variability> list, List<String> names) {
        List filter = new ArrayList<>();
        for (Variability variability : list) {
            if (names.contains(variability.getVariationPoint().getName()))
                filter.add(variability);
        }
        return  filter;
    }
    
    /**
     * Method responsible for returning the Variabilities List by Variants.
     * @param  list Variabilities List.
     * @param  names Variants List.
     * @return Variabilities filtered by Variants.
     */
    protected List<Variability> filterVariants(List<Variability> list, List<String> names) {
        return this.isVoid(names) ? list : this.getVariants(list, names);
    }
    
    /**
     * Method responsible for returning the Variabilities List by Variants.
     * @param  list Variabilities List.
     * @param  names Variants List.
     * @return Variabilities filtered by Variants.
     */
    protected List<Variability> getVariants(List<Variability> list, List<String> names) {
        List filter = new ArrayList<>();
        for (Variability variability : list) {
            for (String variant : names) {
                if (variability.isVariant(variant))
                    filter.add(variability);
            }
        }
        return  filter;
    }
    
    /**
     * Method responsible for returning the Variabilities List by Constraint.
     * @param  list Variabilities List.
     * @param  constraint Constraint.
     * @return Variabilities filtered by Constraint.
     */
    protected List<Variability> filterConstraint(List<Variability> list, String constraint) {
        List filter = new ArrayList<>();
        if (constraint.equals(""))
            return list;
        for (Variability variability : list) {
            if (variability.getConstraint().equalsIgnoreCase(constraint))
                filter.add(variability);
        }
        return filter;
    }
}