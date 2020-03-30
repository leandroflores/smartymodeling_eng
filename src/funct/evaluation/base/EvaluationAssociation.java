package funct.evaluation.base;

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
 * @see    model.structural.base.Diagram
 * @see    model.structural.base.Element
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
    protected Double getClauseValue(String keyword, String filter) {
        System.out.println("Keyword: " + keyword);
        System.out.println("Filter.: " + filter);
        System.out.println("");
        
        return -50.0d;
    }
    
    /**
     * Method responsible for returning the Evaluation Value.
     * @param  parameters Metric Parameters.
     * @return Evaluation Value.
     */
    public Double getEvaluationValue(Object[] parameters) {
        List   list = this.filter(parameters);
        String size = Integer.toString(list.size());
        return Double.parseDouble(size);
    }
    
    /**
     * Method responsible for filtering the Elements by Parameters.
     * @param  parameters Parameters List.
     * @return Elements filtered.
     */
    public List filter(Object[] parameters) {
           List filter = this.getInitialList();
           System.out.println("List 0: " + filter);
           System.out.println("Size 0: " + filter.size());
                filter = this.filterSource(filter, (List<String>) parameters[0]);
           System.out.println("List 1: " + filter);
           System.out.println("Size 1: " + filter.size());
                filter = this.filterTarget(filter, (List<String>) parameters[1]);
           System.out.println("List 2: " + filter);
           System.out.println("Size 2: " + filter.size());
//                filter = this.filterStereotypes(filter, (List<String>) parameters[2]);
//                filter = this.filterMandatory(filter, (Boolean) parameters[3]);
           System.out.println("");
        return  filter;
    }
    
    /**
     * Method responsible for returning if List is Void.
     * @param  list List.
     * @return List is Void.
     */
    protected boolean isVoid(List<String> list) {
        return list == null   
            || list.isEmpty()  
            || list.get(0).trim().equalsIgnoreCase("*");
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
    protected List<Association> getAssociationsList() {
        return this.diagram != null ?
               this.diagram.getAssociationsList() : 
               this.project.getAssociationsList();
    }
    
    /**
     * Method responsible for returning the Initial List.
     * @return Initial List.
     */
    protected List<Association> getInitialList() {
        return this.allTypes() ?
               this.getAssociationsList() :
               this.filterType(this.getAssociationsList());
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
     * Method responsible for returning the Associations List by Source List.
     * @param  list Associations List.
     * @param  names Source List.
     * @return Associations filtered.
     */
    protected List<Association> filterSource(List<Association> list, List<String> names) {
        return this.isVoid(names) ? list : this.getSource(list, names);
    }
    
    /**
     * Method responsible for returning the Associations List by Source List.
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
     * Method responsible for returning the Associations List by Target List.
     * @param  list Associations List.
     * @param  names Target List.
     * @return Associations filtered.
     */
    protected List<Association> filterTarget(List<Association> list, List<String> names) {
        return this.isVoid(names) ? list : this.getTarget(list, names);
    }
    
    /**
     * Method responsible for returning the Associations List by Target List.
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
     * Method responsible for filtering the Associations by Contains.
     * @param  list Associations List.
     * @param  names Names List.
     * @return Associations filtered.
     */
    protected List<Association> filterContains(List<Association> list, List<String> names) {
        List filter = new ArrayList<>();
        if (this.isVoid(names))
            return list;
        for (Association association : list) {
            for (String name : names) {
                if (association.contains(name))
                    filter.add(association);
            }
        }
        return filter;
    }
}