package model.structural.base.requirement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.structural.base.Element;
import model.structural.base.interfaces.Exportable;

/**
 * <p>Class of Model <b>Requirement</b>.</p>
 * <p>Class responsible for representing the <b>Requirement</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-15
 * @see    model.structural.base.interfaces.Exportable
 */
public class Requirement implements Exportable {
    private String  id;
    private String  code;
    private String  type;
    private String  name;
    private String  description;
    private String  priority;
    private String  history;
    private HashMap features;
    private HashMap use_case;
    private HashMap classes;
    private HashMap component;
    private HashMap sequence;
    private HashMap activity;
    
     /**
     * Default constructor method of Class.
     */
    public Requirement() {
        code        = "";
        type        = "Business";
        name        = "";
        description = "";
        priority    = "Essential";
        initMaps();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public Requirement(org.w3c.dom.Element element) {
        id           = element.getAttribute("id");
        code         = element.getAttribute("code");
        type         = element.getAttribute("type");
        name         = element.getAttribute("name");
        priority     = element.getAttribute("priority");
        description  = "";
        initMaps();
    }
    
    /**
     * Method responsible for initializing the Traceability Maps.
     */
    private void initMaps() {
        features  = new HashMap();
        use_case  = new HashMap();
        classes   = new HashMap();
        component = new HashMap();
        sequence  = new HashMap();
        activity  = new HashMap();
    }
    
    /**
     * Method responsible for returning the Requirement Id.
     * @return Requirement Id.
     */
    public String getId() {
        return id;
    }

    /**
     * Method responsible for setting the Requirement Id.
     * @param id Requirement Id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method responsible for returning the Requirement Code.
     * @return Requirement Code.
     */
    public String getCode() {
        return code;
    }
    
    public String getHtmlCode() {
        String html  = "<html>";
        for (String string : code.split(""))
               html += string + "<br>";
        return html += "</html>";
    }

    /**
     * Method responsible for setting the Requirement Code.
     * @param code Requirement Code.
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * Method responsible for returning the Requirement Type.
     * @return Requirement Type.
     */
    public String getType() {
        return type;
    }

    /**
     * Method responsible for setting the Requirement Type.
     * @param type Requirement Type.
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * Method responsible for returning the Requirement Name.
     * @return Requirement Name.
     */
    public String getName() {
        return name;
    }

    /**
     * Method responsible for setting the Requirement Name.
     * @param name Requirement Name.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Method responsible for returning the Requirement Description.
     * @return Requirement Description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method responsible for setting the Requirement Description.
     * @param description Requirement Description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Method responsible for returning the Requirement Priority.
     * @return Requirement Priority.
     */
    public String getPriority() {
        return priority;
    }
    
    /**
     * Method responsible for setting the Requirement Priority.
     * @param priority Requirement Priority.
     */
    public void setPriority(String priority){
        this.priority = priority;
    }
    
    /**
     * Method responsible for returning the Requirement History.
     * @return Requirement History.
     */
    public String getHistory(){
        return history;
    }
    
    /**
     * Method responsible for setting the Requirement History.
     * @param history Requirement History.
     */
    public void setHistory(String history){
        this.history = history;
    }
    
    /**
     * Method responsible for adding the Element.
     * @param type Diagram Type.
     * @param element Element.
     */
    public void addElement(String type, Element element) {
        if (type.equalsIgnoreCase("Feature"))
            features.put(element.getId(), element);
        else if (type.equalsIgnoreCase("UseCase"))
            use_case.put(element.getId(), element);
        else if (type.equalsIgnoreCase("Class"))
            classes.put(element.getId(), element);
        else if (type.equalsIgnoreCase("Component"))
            component.put(element.getId(), element);
        else if (type.equalsIgnoreCase("Sequence"))
            sequence.put(element.getId(), element);
        else if (type.equalsIgnoreCase("Activity"))
            activity.put(element.getId(), element);
    }
    
    /**
     * Method responsible for returning if Requirement contains a Element.
     * @param  element Element.
     * @return Requirement contains a Element.
     */
    public boolean contains(Element element) {
        return getAllElements().contains(element);
    }
    
    /**
     * Method responsible for removing a Element.
     * @param element Element.
     */
    public void removeElement(Element element) {
        features.remove(element.getId());
        use_case.remove(element.getId());
        classes.remove(element.getId());
        component.remove(element.getId());
        sequence.remove(element.getId());
        activity.remove(element.getId());
    }
    
    /**
     * Method responsible for returning the Elements List by Type Diagram.
     * @param  type Type Diagram.
     * @return Elements List by Type Diagram.
     */
    public List<Element> getElements(String type) {
        if (type.equalsIgnoreCase("Feature"))
            return new ArrayList<>(features.values());
        else if (type.equalsIgnoreCase("UseCase"))
            return new ArrayList<>(use_case.values());
        else if (type.equalsIgnoreCase("Class"))
            return new ArrayList<>(classes.values());
        else if (type.equalsIgnoreCase("Component"))
            return new ArrayList<>(component.values());
        else if (type.equalsIgnoreCase("Sequence"))
            return new ArrayList<>(sequence.values());
        else if (type.equalsIgnoreCase("Activity"))
            return new ArrayList<>(activity.values());
        return new ArrayList<>();
    }
    
    /**
     * Method responsible for returning the All Elements List.
     * @return All Elements List.
     */
    public List<Element> getAllElements() {
        List   elements = new ArrayList<>();
               elements.addAll(features.values());
               elements.addAll(use_case.values());
               elements.addAll(classes.values());
               elements.addAll(component.values());
               elements.addAll(sequence.values());
               elements.addAll(activity.values());
        return elements;
    }
    
    /**
     * Method responsible for returning the Requirement Icon.
     * @return Requirement Icon.
     */
    public String getIcon() {
        return "icons/requirement/types/" + type.toLowerCase().trim() + ".png";
    }
    
    /**
     * Method responsible for exporting the Traceability Requirement.
     * @return Traceability Requirement.
     */
    private String exportTraceability() {
        String export  = "";
               export += exportTraceability("Feature");
               export += exportTraceability("UseCase");
               export += exportTraceability("Class");
               export += exportTraceability("Component");
               export += exportTraceability("Sequence");
               export += exportTraceability("Activity");
        return export;
    }
    
    /**
     * Method responsible for exporting the Traceability Requirement by Diagram Type.
     * @param  type Diagram Type.
     * @return Traceability Requirement.
     */
    private String exportTraceability(String type) {
        String export  = "";
        for (Element element : getElements(type))
               export += "    <"       + type.toLowerCase().trim() 
                      +  " element=\"" + element.getId() +  "\"/>\n";
        return export;
    }
    
    @Override
    public String export() {
        String export  = "  <requirement";
               export += " id=\""       + id       + "\"";
               export += " code=\""     + code     + "\"";
               export += " type=\""     + type     + "\"";
               export += " name=\""     + name     + "\"";
               export += " priority=\"" + priority + "\"";
               export += " history=\""  + history  + "\">\n";
               export += "    <description>" + description  + "</description>\n";
               export += exportTraceability();
               export += "  </requirement>\n";
        return export;
    }
    
    @Override
    public String toString() {
        return code + " - " + name;
    }
}