package model.structural.diagram.classes.base;

import java.util.HashSet;
import java.util.Set;
import model.structural.base.Diagram;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.Entity;
import org.w3c.dom.Element;

/**
 * <p>Class of Model <b>InterfaceUML</b>.</p>
 * <p>Class responsible for representing <b>Interface UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-03
 * @see    model.structural.diagram.classes.Entity
 */
public class InterfaceUML extends Entity {
    
    /**
     * Default constructor method of Class.
     * @param diagram Class Diagram.
     */
    public InterfaceUML(ClassDiagram diagram) {
        super(diagram);
        name = "Interface";
        type = "interface";
        super.updateSize();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     * @param diagram Class Diagram.
     */
    public InterfaceUML(Element element, Diagram diagram) {
        super(element, diagram);
        type = "interface";
    }
    
    @Override
    public boolean isFirstConcrete() {
        return false;
    }
    
    @Override
    public Set<MethodUML> getAllMethods() {
        Set    set = new HashSet();
               set.addAll(getMethodsList());
               set.addAll(getInheritedMethods());
        return set;
    }
    
    @Override
    public Set<MethodUML> getExportableMethods() {
        return new HashSet<>(getMethodsList());
    }
    
    @Override
    public Set<MethodUML> getImplementsMethods() {
        return new HashSet<>();
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
        return super.getFolder() + "classes/interface.png";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleInterfaceUML";
    }
    
    @Override
    public String getSignatureCode() {
        String signature  = "public interface " + name + " ";
               signature += getExtendsCode();
        return signature;
    }
    
    @Override
    public Double getExtensValue() {
        return 1.0d;
    }
    
    @Override
    public String exportHeader() {
        String export  = "    <"         + type;
               export += " id=\""        + id           + "\"";
               export += " name=\""      + name         + "\"";
               export += " mandatory=\"" + mandatory    + "\"";
               export += " x=\""         + getX()       + "\"";
               export += " y=\""         + getY()       + "\"";
               export += " globalX=\""   + getGlobalX() + "\"";
               export += " globalY=\""   + getGlobalY() + "\"";
               export += " height=\""    + getHeight()  + "\"";
               export += " width=\""     + getWidth()   + "\"";
               export += exportParent();
               export += ">\n";
        return export;
    }
    
    @Override
    public String toString() {
        return name;
    }
}