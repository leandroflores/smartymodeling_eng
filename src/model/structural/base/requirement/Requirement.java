package model.structural.base.requirement;

import model.controller.structural.base.requirement.ControllerRequirement;
import model.structural.base.interfaces.Exportable;

/**
 * <p>Class of Model <b>Requirement</b>.</p>
 * <p>Class responsible for representing the <b>Requirement</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-15
 * @see    model.structural.base.interfaces.Exportable
 */
public class Requirement implements Exportable {
    private String id;
    private String code;
    private String type;
    private String name;
    private String description;
    
     /**
     * Default constructor method of Class.
     */
    public Requirement() {
        this.type        = ControllerRequirement.TYPES[0];
        this.description = "";
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
     * Method responsible for returning the Requirement Icon.
     * @return Requirement Icon.
     */
    public String getIcon() {
        return "icons/requirement/" + this.type.toLowerCase().trim() + ".png";
    }
    
    @Override
    public String export() {
        String export  = "  <requirement";
               export += " id=\""   + this.id   + "\"";
               export += " code=\"" + this.code + "\"";
               export += " type=\"" + this.type + "\"";
               export += " name=\"" + this.name + "\">\n";
               export += "    <description>" + this.description + "</description>\n";
               export += "  </requirement>\n";
        return export;
    }
    
    @Override
    public String toString() {
        return this.id + " - " + this.name;
    }
}