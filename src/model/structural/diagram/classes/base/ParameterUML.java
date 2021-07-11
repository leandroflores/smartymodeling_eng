package model.structural.diagram.classes.base;

import model.structural.base.interfaces.Exportable;
import model.structural.diagram.classes.Encodable;
import org.w3c.dom.Element;

/**
 * <p>Class of Model <b>ParameterUML</b>.</p>
 * <p>Class responsible for representing <b>Parameter UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-20
 * @see    model.structural.base.interfaces.Exportable
 * @see    model.structural.diagram.classes.Encodable
 * @see    model.structural.diagram.classes.base.TypeUML
 */
public class ParameterUML implements Exportable, Encodable {
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
        name = element.getAttribute("name");
    }

    @Override
    public Boolean isAbstract() {
        return false;
    }

    @Override
    public Boolean isFinal() {
        return false;
    }
    
    @Override
    public Boolean isStatic() {
        return false;
    }
    
    @Override
    public String getVisibility() {
        return "";
    }
    
    /**
     * Method responsible for returning Parameter Type UML.
     * @return Parameter Type UML.
     */
    public TypeUML getType() {
        return type;
    }

    /**
     * Method responsible for changing the Type UML.
     * @param oldType Old Type.
     * @param newType New Type.
     */
    public void changeTypeUML(TypeUML oldType, TypeUML newType) {
        if (type.equals(oldType))
            type = newType;
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
        return name;
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
        return name + " : " + type.getName();
    }
    
    /**
     * Method responsible for returning the Parameter Values.
     * @return Parameter Values.
     */
    public Object[] getValues() {
        return new Object[] {name, type};
    }
    
    @Override
    public String exportCode() {
        return type.getName() + " " + name;
    }

    @Override
    public String export() {
        String export  = "        <parameter";
               export += " type=\"" + type.getId() + "\"";
               export += " name=\"" + name         + "\"";
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
        if (!(object instanceof ParameterUML))
            return false;
        return name.equals(((ParameterUML) object).getName());
    }

    @Override
    public String toString() {
        return type + " " + name;
    }
}