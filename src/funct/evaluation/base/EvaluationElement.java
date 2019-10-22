package funct.evaluation.base;

import java.util.ArrayList;
import java.util.List;
import model.structural.base.Diagram;
import model.structural.base.Element;

/**
 * <p>Class of Metric <b>EvaluationElement</b>.</p>
 * <p>Class responsible por <b>Evaluate</b> the <b>Elements</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  02/09/2019
 * @see    model.structural.base.Diagram
 * @see    model.structural.base.Element
 */
public class EvaluationElement {
    private final Diagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param diagram Diagram.
     */
    public EvaluationElement(Diagram diagram) {
        this.diagram = diagram;
    }
    
    /**
     * Method responsible for returning the Metric Value.
     * @param  parameters Metric Parameters.
     * @return Metric Value.
     */
    public Double getMetricValue(Object[] parameters) {
        return Double.parseDouble(Integer.toString(this.filter(parameters).size()));
    }
    
    /**
     * Method responsible for filtering the Elements by Parameters.
     * @param  parameters Parameters List.
     * @return Actors UML filtered.
     */
    public List filter(Object[] parameters) {
           List filter = this.diagram.getElementsList();
                filter = this.filterNames(filter, (List<String>) parameters[1]);
                filter = this.filterStereotypes(filter, (List<String>) parameters[2]);
        return  filter;
    }
    
    /**
     * Method responsible for returning if List is Void.
     * @param  list List.
     * @return List is Void.
     */
    protected boolean isVoid(List<String> list) {
        return (list == null) || (list.isEmpty()) || (list.get(0).trim().equals("*"));
    }
    
    /**
     * Method responsible for filtering the Elements by Name.
     * @param  elements Elements List.
     * @param  names Names List.
     * @return Elements filtered.
     */
    protected List<Element> filterNames(List elements, List<String> names) {
        List<Element> filter = new ArrayList<>();
        if (this.isVoid(names))
            return elements;
        for (Object object : elements) {
            if (names.contains(((Element) object).getName()))
                filter.add((Element) object);
        }
        return filter;
    }
    
    /**
     * Method responsible for filtering the Elements by Stereotypes.
     * @param  elements Elements List.
     * @param  stereotypes Stereotypes List.
     * @return Elements filtered.
     */
    protected List<Element> filterStereotypes(List elements, List<String> stereotypes) {
        List<Element> filter = new ArrayList<>();
        if (this.isVoid(stereotypes))
            return elements;
        for (Object object : elements) {
            if (stereotypes.contains(this.diagram.getStereotypes((Element) object, "|")))
                filter.add((Element) object);
        }
        return filter;
    }
    
    /**
     * Method responsible for filtering the Elements by Mandatory Flag.
     * @param  elements Elements List.
     * @param  mandatory Mandatory Flag.
     * @return Elements filtered.
     */
    protected List<Element> filterMandatory(List elements, Boolean mandatory) {
        List<Element> filter = new ArrayList<>();
        if (mandatory == null)
            return elements;
        for (Object object : elements) {
            if (mandatory == ((Element) object).isMandatory())
                filter.add((Element) object);
        }
        return filter;
    }
}