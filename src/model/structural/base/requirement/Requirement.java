package model.structural.base.requirement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.controller.structural.base.requirement.ControllerRequirement;
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
        this.code        = "";
        this.type        = ControllerRequirement.TYPES[0];
        this.name        = "";
        this.description = "";
        this.initMaps();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public Requirement(org.w3c.dom.Element element) {
        this.id          = element.getAttribute("id");
        this.code        = element.getAttribute("code");
        this.type        = element.getAttribute("type");
        this.name        = element.getAttribute("name");
        this.description = "";
        this.initMaps();
    }
    
    /**
     * Method responsible for initializing the Traceability Maps.
     */
    private void initMaps() {
        this.features  = new HashMap();
        this.use_case  = new HashMap();
        this.classes   = new HashMap();
        this.component = new HashMap();
        this.sequence  = new HashMap();
        this.activity  = new HashMap();
    }
    
    /**
     * Method responsible for returning the Requirement Id.
     * @return Requirement Id.
     */
    public String getId() {
        return this.id;
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
        return this.code;
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
        return this.type;
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
        return this.name;
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
        return this.description;
    }

    /**
     * Method responsible for setting the Requirement Description.
     * @param description Requirement Description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Method responsible for adding the Element.
     * @param type Diagram Type.
     * @param element Element.
     */
    public void addElement(String type, Element element) {
        if (type.equalsIgnoreCase("Feature"))
            this.features.put(element.getId(), element);
        else if (type.equalsIgnoreCase("UseCase"))
            this.use_case.put(element.getId(), element);
        else if (type.equalsIgnoreCase("Class"))
            this.classes.put(element.getId(), element);
        else if (type.equalsIgnoreCase("Component"))
            this.component.put(element.getId(), element);
        else if (type.equalsIgnoreCase("Sequence"))
            this.sequence.put(element.getId(), element);
        else if (type.equalsIgnoreCase("Activity"))
            this.activity.put(element.getId(), element);
    }
    
    /**
     * Method responsible for removing a Element.
     * @param element Element.
     */
    public void removeElement(Element element) {
        this.features.remove(element.getId());
        this.use_case.remove(element.getId());
        this.classes.remove(element.getId());
        this.component.remove(element.getId());
        this.sequence.remove(element.getId());
        this.activity.remove(element.getId());
    }
    
    /**
     * Method responsible for returning the Elements List by Type Diagram.
     * @param  type Type Diagram.
     * @return Elements List by Type Diagram.
     */
    public List<Element> getElements(String type) {
        if (type.equalsIgnoreCase("Feature"))
            return new ArrayList<>(this.features.values());
        else if (type.equalsIgnoreCase("UseCase"))
            return new ArrayList<>(this.use_case.values());
        else if (type.equalsIgnoreCase("Class"))
            return new ArrayList<>(this.classes.values());
        else if (type.equalsIgnoreCase("Component"))
            return new ArrayList<>(this.component.values());
        else if (type.equalsIgnoreCase("Sequence"))
            return new ArrayList<>(this.sequence.values());
        else if (type.equalsIgnoreCase("Activity"))
            return new ArrayList<>(this.activity.values());
        return new ArrayList<>();
    }
    
    /**
     * Method responsible for returning the All Elements List.
     * @return All Elements List.
     */
    public List<Element> getAllElements() {
        List   elements = new ArrayList<>();
               elements.addAll(this.features.values());
               elements.addAll(this.use_case.values());
               elements.addAll(this.classes.values());
               elements.addAll(this.component.values());
               elements.addAll(this.sequence.values());
               elements.addAll(this.activity.values());
        return elements;
    }
    
    /**
     * Method responsible for returning the Requirement Icon.
     * @return Requirement Icon.
     */
    public String getIcon() {
        return "icons/requirement/types/" + this.type.toLowerCase().trim() + ".png";
    }
    
    /**
     * Method responsible for exporting the Traceability Requirement.
     * @return Traceability Requirement.
     */
    private String exportTraceability() {
        String export  = "";
               export += this.exportTraceability("Feature");
               export += this.exportTraceability("UseCase");
               export += this.exportTraceability("Class");
               export += this.exportTraceability("Component");
               export += this.exportTraceability("Sequence");
               export += this.exportTraceability("Activity");
        return export;
    }
    
    /**
     * Method responsible for exporting the Traceability Requirement by Diagram Type.
     * @param  type Diagram Type.
     * @return Traceability Requirement.
     */
    private String exportTraceability(String type) {
        String export  = "";
        for (Element element : this.getElements(type))
               export += "    <"       + type.toLowerCase().trim() 
                      +  " element=\"" + element.getId() +  "\"/>\n";
        return export;
    }
    
    @Override
    public String export() {
        String export  = "  <requirement";
               export += " id=\""   + this.id   + "\"";
               export += " code=\"" + this.code + "\"";
               export += " type=\"" + this.type + "\"";
               export += " name=\"" + this.name + "\">\n";
               export += "    <description>" + this.description + "</description>\n";
               export += this.exportTraceability();
               export += "  </requirement>\n";
        return export;
    }
    
    @Override
    public String toString() {
        return this.code + " - " + this.name;
    }
}