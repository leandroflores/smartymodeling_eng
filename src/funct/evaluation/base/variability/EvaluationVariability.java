package funct.evaluation.base.variability;

import funct.evaluation.Evaluation;
import java.util.ArrayList;
import java.util.List;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.variability.Variability;

/**
 * <p>Class of Funct <b>EvaluationVariability</b>.</p>
 * <p>Class responsible for <b>Evaluate</b> the <b>Variability</b> in the SMartyModeling.</p>
 * @author Leandro
 * @since  2020-03-31
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
        List   list = filter(keyword, getVariabilityFilters(filter));
        String size = Integer.toString(list.size());
        return Double.parseDouble(size);
    }
    
    /**
     * Method responsible for filtering the Associations by Parameters.
     * @param  keyword Keyword.
     * @param  parameters Parameters List.
     * @return Variants filtered.
     */
    public List filter(String keyword, Object[] parameters) {
           List filter = filterContext();
                filter = filterName(filter, (List<String>) parameters[0]);
                filter = filterConstraint(filter, (String) parameters[3]);
                filter = filterElements(filter, keyword);
                addObjects(getList(filter, keyword));
        return  filter;
    }
    
    /**
     * Method responsible for returning the Variabilities List by Context.
     * @return Variabilities List by Context.
     */
    protected List getVariabilities() {
        return diagram != null ? diagram.getVariabilitiesList() : project.getVariabilitiesList();
    }
    
    /**
     * Method responsible for filtering the Variabilities Context.
     * @return Variabilities Context.
     */
    protected List filterContext() {
        return getVariabilities();
    }
    
    /**
     * Method responsible for returning the Variants List by Names.
     * @param  list Variants List.
     * @param  names Names List.
     * @return Variants filtered.
     */
    protected List filterName(List list, List names) {
        return isVoid(names) ? list : getNames(list, names);
    }
    
    /**
     * Method responsible for returning the Variability List by Names.
     * @param  list Variability List.
     * @param  names Names List.
     * @return Variability Name filtered.
     */
    private List getNames(List<Variability> list, List<String> names) {
        List filter = new ArrayList<>();
        for (Variability variability : list) {
            if (names.contains(variability.getName()))
                filter.add(variability);
        }
        return  filter;
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
    
    /**
     * Method responsible for filtering the Elements.
     * @param  list Initial List.
     * @param  keyword Type Keyword.
     * @return Elements filtered.
     */
    public List filterElements(List<Variability> list, String keyword) {
        List   filter = new ArrayList<>();
        if (keyword.equalsIgnoreCase("variability"))
            return list;
        for (Variability variability : list) {
            if (keyword.equalsIgnoreCase("variants"))
               filter.addAll(variability.getVariants());
            else if (keyword.equalsIgnoreCase("variationPoint"))
               filter.add(variability.getVariationPoint());
        }
        return filter;
    }
    
    /**
     * Method responsible for returning the Summary List.
     * @param  filter Filter List.
     * @param  keyword Keyword.
     * @return Summary List.
     */
    private List getList(List filter, String keyword) {
        if (keyword.equalsIgnoreCase("variability"))
            return getVariabilitiesList(filter);
        return getElementsList(filter);
    }
    
    /**
     * Method responsible for returning the Summary List of Elements List.
     * @param  filter Elements List.
     * @return Summary List.
     */
    private List getElementsList(List<Element> filter) {
        List   list = new ArrayList();
        for (Element element : filter)
               list.add(element.getSummary());
        return list;
    }
    
    /**
     * Method responsible for returning the Summary List of Variabilities List.
     * @param  filter Variabilities List.
     * @return Summary List.
     */
    private List getVariabilitiesList(List<Variability> filter) {
        List   list = new ArrayList();
        for (Variability variability : filter)
               list.add(variability.getSummary());
        return list;
    }
}