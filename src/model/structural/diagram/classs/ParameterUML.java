package model.structural.diagram.classs;

import model.structural.base.interfaces.Exportable;
import org.w3c.dom.Element;

/**
 * <p>Class of Model <b>ParameterUML</b>.</p>
 * <p>Class responsible for representing <b>Parameter UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  20/05/2019
 * @see    model.structural.base.interfaces.Exportable
 * @see    model.structural.diagram.classs.TypeUML
 */
public class ParameterUML implements Exportable {
    private TypeUML type;
    private String  name;
    
    /**
     * Default constructor method of Class.
     */
    public ParameterUML() {}
    
    /**
     * Alternative constructor method of Class.
     * @param type Parameter Type.
     * @param name Parameter Name.
     */
    public ParameterUML(TypeUML type, String name) {
        this.type = type;
        this.name = name;
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element W3C.
     */
    public ParameterUML(Element element) {
        this.name = element.getAttribute("name");
    }

    /**
     * Method responsible for returning Parameter Type UML.
     * @return Parameter Type UML.
     */
    public TypeUML getType() {
        return this.type;
    }

    /**
     * Method responsible for defining Parameter Type UML.
     * @param type Parameter Type UML.
     */
    public void setType(TypeUML type) {
        this.type = type;
    }

    /**
     * Method responsible for returning Parameter Name.
     * @return Parameter Name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method responsible for defining Parameter Name.
     * @param name Parameter Name.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Method responsible for returning Parameter Title.
     * @return Parameter Title.
     */
    public String getTitle() {
        return this.name + " : " + this.type.getName();
    }
    
    /**
     * Method responsible for printing Parameter UML.
     * @return Parameter UML.
     */
    public String print() {
        return this.type.getName() + " " + this.name;
    }

    @Override
    public String export() {
        String export  = "        <parameter";
               export += " type=\"" + this.type.getId() + "\"";
               export += " name=\"" + this.name         + "\"";
               export += "/>\n";
        return export;
    }

    @Override
    public int hashCode() {
        int    hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (object instanceof ParameterUML == false)
            return false;
        return this.name.equals(((ParameterUML) object).getName());
    }

    @Override
    public String toString() {
        return this.type + " " + this.name;
    }
}