package funct.evaluation.base;

import funct.evaluation.Evaluation;
import java.util.ArrayList;
import java.util.List;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;

/**
 * <p>Class of Evaluation <b>EvaluationElement</b>.</p>
 * <p>Class responsible for <b>Evaluate</b> the <b>Elements</b>.</p>
 * @author Leandro
 * @since  30/03/2020
 * @see    funct.evaluation.Evaluation
 * @see    model.structural.base.Diagram
 * @see    model.structural.base.Element
 * @see    model.structural.base.Project
 */
public class EvaluationElement extends Evaluation {
    private final Diagram diagram;
    private final String  type;
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     * @param type Element Type.
     */
    public EvaluationElement(Project project, String type) {
        super(project);
        this.diagram = null;
        this.type    = type;
    }
    
    /**
     * Alternative constructor method of Class.
     * @param diagram Diagram.
     * @param type Element Type.
     */
    public EvaluationElement(Diagram diagram, String type) {
        super(diagram.getProject());
        this.diagram = diagram;
        this.type    = type;
    }
    
    @Override
    protected Double getClauseValue(String keyword, String filter) {
        List   list = this.filter(this.getAssociationFilters(filter));
        String size = Integer.toString(list.size());
        return Double.parseDouble(size);
    }
    
    /**
     * Method responsible for filtering the Elements by Parameters.
     * @param  parameters Parameters List.
     * @return Elements filtered.
     */
    public List filter(Object[] parameters) {
           List filter = this.filterContext();
           System.out.println("List 0: " + filter);
           System.out.println("Size 0: " + filter.size());
//                filter = this.filterSource(filter, (List<String>) parameters[0]);
//                filter = this.filterTarget(filter, (List<String>) parameters[1]);
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
            || this.type.equalsIgnoreCase("element")
            || this.type.equalsIgnoreCase("elements");
    }
    
    /**
     * Method responsible for returning the Elements List by Context.
     * @return Elements List by Context.
     */
    protected List<Element> getElementsList() {
        return this.diagram != null ?
               this.diagram.getElementsList() : 
               this.project.getElementsList();
    }
    
    /**
     * Method responsible for filtering the Elements List.
     * @return Context List.
     */
    protected List<Element> filterContext() {
        return this.allTypes() ?
               this.getElementsList() :
               this.filterType(this.getElementsList());
    }
    
    /**
     * Method responsible for filtering the Elements by Type.
     * @param  list Elements List.
     * @return Elements filtered by Type
     */
    protected List<Element> filterType(List<Element> list) {
        List filter = new ArrayList<>();
        for (Element element : list) {
            if (element.getType().equalsIgnoreCase(this.type))
               filter.add(element);
        }
        return filter;
    }
}