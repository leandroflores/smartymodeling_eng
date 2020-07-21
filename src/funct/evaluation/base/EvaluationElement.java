package funct.evaluation.base;

import funct.evaluation.Evaluation;
import java.util.ArrayList;
import java.util.List;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.diagram.classes.Encodable;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.MethodUML;
import model.structural.diagram.feature.base.Feature;

/**
 * <p>Class of Evaluation <b>EvaluationElement</b>.</p>
 * <p>Class responsible for <b>Evaluate</b> the <b>Element</b> in the SMartyModeling.</p>
 * @author Leandro
 * @since  2020-03-30
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
    public Double getClauseValue(String keyword, String filter) {
        List   list = this.filter(this.getElementFilters(filter));
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
                filter = this.filterParent(filter, this.getParent((List<String>) parameters[2]));
                filter = this.filterName(filter, (List<String>) parameters[0]);
                filter = this.filterStereotype(filter, (List<String>) parameters[1]);
                filter = this.filterMandatory(filter,  (Boolean) parameters[3]);
                filter = this.filterAbstract(filter,   (Boolean) parameters[4]);
                filter = this.filterFinal(filter,  (Boolean) parameters[5]);
                filter = this.filterStatic(filter, (Boolean) parameters[6]);
                filter = this.filterVisibility(filter, (String) parameters[7]);
                filter = this.filterGetter(filter, (Boolean) parameters[8]);
                filter = this.filterSetter(filter, (Boolean) parameters[9]);
                filter = this.filterOverwritten(filter, (Boolean) parameters[10]);
                filter = this.filterSpecific(filter, (Boolean) parameters[11]);
                super.addObjects(this.getList(filter));
        return  filter;
    }
    
    /**
     * Method responsible for returning the Parent Name.
     * @param  parameter Parameter List.
     * @return Parent Name.
     */
    private String getParent(List<String> parameter) {
        return parameter.isEmpty() ? "" : parameter.get(0);
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
    protected List<Element> getElements() {
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
               this.getElements() :
               this.filterType(this.getElements());
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
    
    /**
     * Method responsible for filtering the Parent List.
     * @param  list Initial List.
     * @param  parent Parent.
     * @return Parent List.
     */
    protected List<Element> filterParent(List<Element> list, String parent) {
        if (this.type.equalsIgnoreCase("method"))
            return this.getMethods(list, parent);
        if (this.type.equalsIgnoreCase("attribute"))
            return this.getAttributes(list, parent);
        return list;
    }
    
    /**
     * Method resposible for returning the filtered Methods.
     * @param  list Initial List.
     * @param  parent Parent Name.
     * @return Methods List.
     */
    protected List<Element> getMethods(List<Element> list, String parent) {
        Entity entity = this.diagram.filterEntityByName(parent);
        if (parent.trim().equals(""))
            return list;
        if (entity == null)
            return new ArrayList<>();
        return (List<Element>) new ArrayList(entity.getAllMethods());
    }
    
    /**
     * Method resposible for returning the filtered Attributes.
     * @param  list Initial List.
     * @param  parent Parent Name.
     * @return Attributes List.
     */
    protected List<Element> getAttributes(List<Element> list, String parent) {
        Entity entity = this.diagram.filterEntityByName(parent);
        if (parent.trim().equals(""))
            return list;
        if (entity == null)
            return new ArrayList<>();
        return (List<Element>) new ArrayList(entity.getAttributesList());
    }
    
    /**
     * Method responsible for returning the Elements List by Names.
     * @param  list Elements List.
     * @param  names Names List.
     * @return Elements filtered.
     */
    protected List<Element> filterName(List<Element> list, List<String> names) {
        return this.isVoid(names) ? list : this.getNames(list, names);
    }
    
    /**
     * Method responsible for returning the Elements List by Names.
     * @param  list Elements List.
     * @param  names Names List.
     * @return Elements Name filtered.
     */
    private List<Element> getNames(List<Element> list, List<String> names) {
        List filter = new ArrayList<>();
        for (Element element : list) {
            if (names.contains(element.getName()))
                filter.add(element);
        }
        return  filter;
    }
    
    /**
     * Method responsible for returning the Elements List by Stereotypes.
     * @param  list Elements List.
     * @param  stereotypes Stereotypes List.
     * @return Elements filtered.
     */
    protected List<Element> filterStereotype(List<Element> list, List<String> stereotypes) {
        return this.isVoid(stereotypes) ? list : this.getStereotypes(list, stereotypes);
    }
    
    /**
     * Method responsible for returning the Elements List by Stereotypes.
     * @param  list Elements List.
     * @param  stereotypes Stereotypes List.
     * @return Elements Name filtered.
     */
    private List<Element> getStereotypes(List<Element> list, List<String> stereotypes) {
        List filter = new ArrayList<>();
        for (Element element : list) {
            String links = this.project.getStereotypesString(element);
            for (String stereotype : stereotypes)
                if (links.contains(stereotype)) {
                    filter.add(element);
                    break;
                }
        }
        return  filter;
    }
    
    /**
     * Method responsible for returning the Elements List by Mandatory Flag.
     * @param  list Elements List.
     * @param  mandatory Mandatory Flag.
     * @return Elements Name filtered by Mandatory Flag.
     */
    protected List<Element> filterMandatory(List<Element> list, Boolean mandatory) {
        List filter = new ArrayList<>();
        if (mandatory == null)
            return list;
        for (Element element : list) {
            if (mandatory.equals(element.isMandatory()))
                filter.add(element);
        }
        return filter;
    }
    
    /**
     * Method responsible for returning the Elements List by Abstract Flag.
     * @param  list Elements List.
     * @param  abstract_ Abstract Flag.
     * @return Elements Name filtered by Abstract Flag.
     */
    protected List<Element> filterAbstract(List<Element> list, Boolean abstract_) {
        List filter = new ArrayList<>();
        if (abstract_ == null)
            return list;
        for (Element element : list) {
            if (this.checkAbstract(element, abstract_))
                filter.add(element);
        }
        return  filter;
    }
    
    /**
     * Method responsible for checking if a Element is Abstract.
     * @param  element Element.
     * @param  abstract_ Abstract Flag.
     * @return Element is Abstract.
     */
    private boolean checkAbstract(Element element, Boolean abstract_) {
        if (element instanceof Encodable)
            return abstract_.equals(((Encodable) element).isAbstract());
        else if (element instanceof Feature)
            return abstract_.equals(((Feature)   element).isAbstract());
        return true;
    }
    
    /**
     * Method responsible for returning the Elements List by Final Flag.
     * @param  list Elements List.
     * @param  final_ Final Flag.
     * @return Elements Name filtered by Final Flag.
     */
    protected List<Element> filterFinal(List<Element> list, Boolean final_) {
        List filter = new ArrayList<>();
        if (final_ == null)
            return list;
        for (Element element : list) {
            if (this.checkFinal(element, final_))
                filter.add(element);
        }
        return filter;
    }
    
    /**
     * Method responsible for checking if a Element is Final.
     * @param  element Element.
     * @param  final_ Final Flag.
     * @return Element is Final.
     */
    private boolean checkFinal(Element element, Boolean final_) {
        if (element instanceof Encodable)
            return final_.equals(((Encodable) element).isFinal());
        return true;
    }
    
    /**
     * Method responsible for returning the Elements List by Static Flag.
     * @param  list Elements List.
     * @param  static_ Static Flag.
     * @return Elements filtered by Static Flag.
     */
    protected List<Element> filterStatic(List<Element> list, Boolean static_) {
        List filter = new ArrayList<>();
        if (static_ == null)
            return list;
        for (Element element : list) {
            if (this.checkStatic(element, static_))
                filter.add(element);
        }
        return filter;
    }
    
    /**
     * Method responsible for checking if a Element is Static.
     * @param  element Element.
     * @param  static_ Static Flag.
     * @return Element is Static.
     */
    private boolean checkStatic(Element element, Boolean static_) {
        if (element instanceof Encodable)
            return static_.equals(((Encodable) element).isStatic());
        return true;
    }
    
    /**
     * Method responsible for returning the Elements List by Visibility.
     * @param  list Elements List.
     * @param  visibility Visibility.
     * @return Elements filtered by Visibility.
     */
    protected List<Element> filterVisibility(List<Element> list, String visibility) {
        List filter = new ArrayList<>();
        if (visibility.equals(""))
            return list;
        for (Element element : list) {
            if (this.checkVisibility(element, visibility))
                filter.add(element);
        }
        return filter;
    }
    
    /**
     * Method responsible for checking the Element by Visibility.
     * @param  element Element.
     * @param  visibility Visibility.
     * @return Element checked by Visibility.
     */
    private boolean checkVisibility(Element element, String visibility) {
        if (element instanceof Encodable)
            return visibility.equalsIgnoreCase(((Encodable) element).getVisibility());
        return true;
    }
    
    /**
     * Method responsible for returning the Elements List by Getter Flag.
     * @param  list Elements List.
     * @param  getter Getter Flag.
     * @return Elements filtered by Getter Flag.
     */
    protected List<Element> filterGetter(List<Element> list, Boolean getter) {
        List filter = new ArrayList<>();
        if (getter == null)
            return list;
        for (Element element : list) {
            if (this.checkGetter(element, getter))
                filter.add(element);
        }
        return filter;
    }
    
    /**
     * Method responsible for checking if a Element is Getter.
     * @param  element Element.
     * @param  getter Getter Flag.
     * @return Element is Getter.
     */
    private boolean checkGetter(Element element, Boolean getter) {
        if (element instanceof MethodUML)
            return getter.equals(((MethodUML) element).isGetter());
        return true;
    }
    
    /**
     * Method responsible for returning the Elements List by Setter Flag.
     * @param  list Elements List.
     * @param  setter Setter Flag.
     * @return Elements filtered by Setter Flag.
     */
    protected List<Element> filterSetter(List<Element> list, Boolean setter) {
        List filter = new ArrayList<>();
        if (setter == null)
            return list;
        for (Element element : list) {
            if (this.checkSetter(element, setter))
                filter.add(element);
        }
        return filter;
    }
    
    /**
     * Method responsible for checking if a Element is Setter.
     * @param  element Element.
     * @param  setter Setter Flag.
     * @return Element is Setter.
     */
    private boolean checkSetter(Element element, Boolean setter) {
        if (element instanceof MethodUML)
            return setter.equals(((MethodUML) element).isSetter());
        return true;
    }
    
    /**
     * Method responsible for returning the Elements List by Overwritten Flag.
     * @param  list Elements List.
     * @param  overwritten Overwritten Flag.
     * @return Elements filtered by Overwritten Flag.
     */
    protected List<Element> filterOverwritten(List<Element> list, Boolean overwritten) {
        List filter = new ArrayList<>();
        if (overwritten == null)
            return list;
        for (Element element : list) {
            if (this.checkOverwritten(element, overwritten))
                filter.add(element);
        }
        return filter;
    }
    
    /**
     * Method responsible for checking if a Element is Overwritten.
     * @param  element Element.
     * @param  overwritten Overwritten Flag.
     * @return Element is Overwritten.
     */
    private boolean checkOverwritten(Element element, Boolean overwritten) {
        if (element instanceof MethodUML)
            return overwritten.equals(((MethodUML) element).isOverwritten());
        return true;
    }
    
    /**
     * Method responsible for returning the Elements List by Specific Flag.
     * @param  list Elements List.
     * @param  specific Specific Flag.
     * @return Elements filtered by Specific Flag.
     */
    protected List<Element> filterSpecific(List<Element> list, Boolean specific) {
        List filter = new ArrayList<>();
        if (specific == null)
            return list;
        for (Element element : list) {
            if (this.checkSpecific(element, specific))
                filter.add(element);
        }
        return filter;
    }
    
    /**
     * Method responsible for checking if a Element is Specific.
     * @param  element Element.
     * @param  specific Specific Flag.
     * @return Element is Specific.
     */
    private boolean checkSpecific(Element element, Boolean specific) {
        if (element instanceof MethodUML)
            return specific.equals(((MethodUML) element).isSpecific());
        return true;
    }
    
    /**
     * Method responsible for returning the Summary List of Elements List.
     * @param  filter Elements List.
     * @return Summary List.
     */
    private List getList(List<Element> filter) {
        List   list = new ArrayList();
        for (Element element : filter)
               list.add(element.getSummary());
        return list;
    }
}