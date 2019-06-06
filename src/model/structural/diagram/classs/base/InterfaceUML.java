package model.structural.diagram.classs.base;

import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classs.Entity;
import org.w3c.dom.Element;

/**
 * <p>Class of Model <b>InterfaceUML</b>.</p>
 * <p>Class responsible for representing <b>Interface UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  03/06/2019
 * @see    model.structural.diagram.classs.Entity
 */
public class InterfaceUML extends Entity {
    
    /**
     * Default constructor method of Class.
     * @param diagram Class Diagram.
     */
    public InterfaceUML(ClassDiagram diagram) {
        super(diagram);
        this.name = "Interface";
        this.type = "interface";
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public InterfaceUML(Element element) {
        super(element);
        this.type = "interface";
    }
    
    @Override
    public void addAttribute(AttributeUML attribute) {
        attribute.setFinal(true);
        attribute.setStatic(true);
        super.addAttribute(attribute);
    }
    
    @Override
    public void addMethod(MethodUML method) {
        method.setAbstract(true);
        super.addMethod(method);
    }
    
    @Override
    public String getIcon() {
        return "src/images/icons/diagram/classs/interface.png";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleInterfaceUML";
    }
    
    @Override
    public String exportHeader() {
        String export  = "    <"         + this.type;
               export += " id=\""        + this.id          + "\"";
               export += " name=\""      + this.name        + "\"";
               export += " mandatory=\"" + this.mandatory   + "\"";
               export += " x=\""         + this.getX()      + "\"";
               export += " y=\""         + this.getY()      + "\"";
               export += " height=\""    + this.getHeight() + "\"";
               export += " width=\""     + this.getWidth()  + "\"";
               export += ">\n";
        return export;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}