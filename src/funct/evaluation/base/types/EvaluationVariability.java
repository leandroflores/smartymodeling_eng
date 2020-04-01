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
                filter = this.filterVariationPoint(filter, (List<String>) parameters[0]);
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
     * Method responsible for filtering the Variabilities by Variation Points List.
     * @param  list Variabilities List.
     * @param  varPoints Variation Points List.
     * @return Variabilities filtered by Variation Points.
     */
    protected List<Variability> filterVariationPoint(List<Variability> list, List<String> varPoints) {
        return this.isVoid(varPoints) ? list : this.selectVariationPoints(list, varPoints);
    }
    
    /**
     * Method responsible for selecting the Variabilities by Variation Points List.
     * @param  list Variabilities List.
     * @param  varPoints Variation Points List.
     * @return Variabilities selected by Variation Points.
     */
    protected List<Variability> selectVariationPoints(List<Variability> list, List<String> varPoints) {
        List filter = new ArrayList<>();
        for (Variability variability : list) {
            if (varPoints.contains(variability.getVariationPoint().getName()))
                filter.add(variability);
        }
        return  filter;
    }
    
    /**
     * Method responsible for filtering the Variabilities by Variants List.
     * @param  list Variabilities List.
     * @param  variants Variants List.
     * @return Variabilities filtered by Variants.
     */
    protected List<Variability> filterVariants(List<Variability> list, List<String> variants) {
        return this.isVoid(variants) ? list : this.selectVariants(list, variants);
    }
    
    /**
     * Method responsible for selecting the Variabilities by Variants List.
     * @param  list Variabilities List.
     * @param  variants Variants List.
     * @return Variabilities selected by Variants.
     */
    protected List<Variability> selectVariants(List<Variability> list, List<String> variants) {
        List   filter = new ArrayList<>();
        for (Variability variability : list) {
            for (String variant : variants) {
                if (variability.isVariant(variant))
                    filter.add(variability);
            }
        }
        return filter;
    }
    
    /**
     * Method responsible for filtering the Variabilities by Constraint.
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