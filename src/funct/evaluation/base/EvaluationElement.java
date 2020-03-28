package funct.evaluation.base;

import java.util.ArrayList;
import java.util.List;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.diagram.classes.Encodable;

/**
 * <p>Class of Evaluation <b>EvaluationElement</b>.</p>
 * <p>Class responsible for <b>Evaluate</b> the <b>Elements</b>.</p>
 * @author Leandro
 * @since  02/09/2019
 * @see    model.structural.base.Diagram
 * @see    model.structural.base.Element
 */
public abstract class EvaluationElement {
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
    
    /**
     * Method responsible for filtering the Objects by Abstract Flag.
     * @param  objects  Objects List.
     * @param  abstract_ Abstract Flag.
     * @return Objects filtered.
     */
    protected List<Encodable> filterAbstract(List objects, Boolean abstract_) {
        List<Encodable> filter = new ArrayList<>();
        if (abstract_ == null)
            return objects;
        for (Object object : objects) {
            if (abstract_.equals(((Encodable) object).isAbstract()))
                filter.add((Encodable) object);
        }
        return filter;
    }
    
    /**
     * Method responsible for filtering the Objects by Final Flag.
     * @param  objects Objects List.
     * @param  final_ Final Flag.
     * @return Objects filtered.
     */
    protected List<Encodable> filterFinal(List objects, Boolean final_) {
        List<Encodable> filter = new ArrayList<>();
        if (final_ == null)
            return objects;
        for (Object object : objects) {
            if (final_.equals(((Encodable) object).isFinal()))
                filter.add((Encodable) object);
        }
        return filter;
    }
    
    /**
     * Method responsible for filtering the Objects by Static Flag.
     * @param  objects Objects List.
     * @param  static_ Static Flag.
     * @return Objects filtered.
     */
    protected List<Encodable> filterStatic(List objects, Boolean static_) {
        List<Encodable> filter = new ArrayList<>();
        if (static_ == null)
            return objects;
        for (Object object : objects) {
            if (static_.equals(((Encodable) object).isFinal()))
                filter.add((Encodable) object);
        }
        return filter;
    }
    
    /**
     * Method responsible for filtering the Objects by Visibility.
     * @param  objects Objects List.
     * @param visibility Visibility.
     * @return Objects filtered.
     */
    protected List<Encodable> filterVisibility(List objects, String visibility) {
        List<Encodable> filter = new ArrayList<>();
        if (visibility.equalsIgnoreCase(""))
            return objects;
        for (Object object : objects) {
            if (visibility.equals(((Encodable) object).getVisibility()))
                filter.add((Encodable) object);
        }
        return filter;
    }
}