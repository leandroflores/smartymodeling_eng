package funct.evaluation.base.types;

import funct.evaluation.Evaluation;
import java.util.ArrayList;
import java.util.List;
import model.structural.base.Diagram;
import model.structural.base.Project;
import model.structural.base.association.Association;

/**
 * <p>Class of Evaluation <b>EvaluationAssociation</b>.</p>
 * <p>Class responsible for <b>Evaluate</b> the <b>Associations</b>.</p>
 * @author Leandro
 * @since  30/03/2020
 * @see    funct.evaluation.Evaluation
 * @see    model.structural.base.Diagram
 * @see    model.structural.base.Project
 * @see    model.structural.base.association.Association
 */
public class EvaluationAssociation extends Evaluation {
    private final Diagram diagram;
    private final String  type;
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     * @param type Association Type.
     */
    public EvaluationAssociation(Project project, String type) {
        super(project);
        this.diagram = null;
        this.type    = type;
    }
    
    /**
     * Alternative constructor method of Class.
     * @param diagram Diagram.
     * @param type Association Type.
     */
    public EvaluationAssociation(Diagram diagram, String type) {
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
                filter = this.filterSource(filter, (List<String>) parameters[0]);
                filter = this.filterTarget(filter, (List<String>) parameters[1]);
                filter = this.filterContains(filter, (List<String>) parameters[2]);
                this.addObjects(this.getList(filter));
        return  filter;
    }
    
    /**
     * Method responsible for returning if Flag is for All Types.
     * @return Flag is for All Types.
     */
    protected boolean allTypes() {
        return this.type.equalsIgnoreCase("") 
            || this.type.equalsIgnoreCase("associations");
    }
    
    /**
     * Method responsible for returning the Associations List by Context.
     * @return Associations List by Context.
     */
    protected List<Association> getAssociations() {
        return this.diagram != null ?
               this.diagram.getAssociationsList() : 
               this.project.getAssociationsList();
    }
    
    /**
     * Method responsible for filtering the Associations Context.
     * @return Associations Context.
     */
    protected List<Association> filterContext() {
        return this.allTypes() ?
               this.getAssociations() :
               this.filterType(this.getAssociations());
    }
    
    /**
     * Method responsible for filtering the Associations by Type.
     * @param  list Associations List.
     * @return Associations filtered by Type
     */
    protected List<Association> filterType(List<Association> list) {
        List filter = new ArrayList<>();
        for (Association association : list) {
            if (association.getType().equalsIgnoreCase(this.type))
               filter.add(association);
        }
        return filter;
    }
    
    /**
     * Method responsible for returning the Associations List by Source.
     * @param  list Associations List.
     * @param  names Source List.
     * @return Associations filtered.
     */
    protected List<Association> filterSource(List<Association> list, List<String> names) {
        return this.isVoid(names) ? list : this.getSource(list, names);
    }
    
    /**
     * Method responsible for returning the Associations List by Source.
     * @param  list Associations List.
     * @param  names Names List.
     * @return Associations filtered.
     */
    protected List<Association> getSource(List<Association> list, List<String> names) {
        List filter = new ArrayList<>();
        for (Association association : list) {
            if (names.contains(association.getSource().getName()))
                filter.add(association);
        }
        return  filter;
    }
    
    /**
     * Method responsible for returning the Associations List by Target.
     * @param  list Associations List.
     * @param  names Target List.
     * @return Associations filtered.
     */
    protected List<Association> filterTarget(List<Association> list, List<String> names) {
        return this.isVoid(names) ? list : this.getTarget(list, names);
    }
    
    /**
     * Method responsible for returning the Associations List by Target.
     * @param  list Associations List.
     * @param  names Names List.
     * @return Associations filtered.
     */
    protected List<Association> getTarget(List<Association> list, List<String> names) {
        List filter = new ArrayList<>();
        for (Association association : list) {
            if (names.contains(association.getTarget().getName()))
                filter.add(association);
        }
        return  filter;
    }
    
    /**
     * Method responsible for returning the Associations List by Contains.
     * @param  list Associations List.
     * @param  names Contains List.
     * @return Associations filtered.
     */
    protected List<Association> filterContains(List<Association> list, List<String> names) {
        return this.isVoid(names) ? list : this.getContains(list, names);
    }
    
    /**
     * Method responsible for returning the Associations List by Contains.
     * @param  list Associations List.
     * @param  names Names List.
     * @return Associations filtered.
     */
    protected List<Association> getContains(List<Association> list, List<String> names) {
        List filter = new ArrayList<>();
        for (Association association : list) {
            if (names.contains(association.getSource().getName())
             || names.contains(association.getTarget().getName()))
                filter.add(association);
        }
        return  filter;
    }
    
    /**
     * Method responsible for returning the Summary List of Associations List.
     * @param  filter Associations List.
     * @return Summary List.
     */
    private List getList(List<Association> filter) {
        List   list = new ArrayList();
        for (Association association : filter)
               list.add(association.getSummary());
        return list;
    }
}