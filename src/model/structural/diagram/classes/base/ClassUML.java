package model.structural.diagram.classes.base;

import com.mxgraph.util.mxConstants;
import java.util.Map;
import java.util.Set;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.Entity;
import org.w3c.dom.Element;

/**
 * <p>Class of Model <b>ClassUML</b>.</p>
 * <p>Class responsible for representing <b>Class UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  03/06/2019
 * @see    model.structural.diagram.classes.Entity
 */
public class ClassUML extends Entity {
    private boolean abstract_;
    private boolean final_;
    
    /**
     * Default constructor method of Class.
     * @param diagram Class Diagram.
     */
    public ClassUML(ClassDiagram diagram) {
        super(diagram);
        this.name      = "Class";
        this.type      = "class";
        this.abstract_ = false;
        this.final_    = false;
        super.updateSize();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public ClassUML(Element element) {
        super(element);
        this.type      = "class";
        this.abstract_ = element.getAttribute("abstract").equals("true");
        this.final_    = element.getAttribute("final").equals("true");
    }

    /**
     * Method responsible for returning the Abstract Flag.
     * @return Abstract Flag.
     */
    public boolean isAbstract() {
        return this.abstract_;
    }

    /**
     * Method responsible for setting the Abstract Flag.
     * @param abstract_ Abstract Flag.
     */
    public void setAbstract(boolean abstract_) {
        this.abstract_  = abstract_;
        this.final_     = this.abstract_ ? false : this.final_;
    }

    /**
     * Method responsible for returning the Final Flag.
     * @return Final Flag.
     */
    public boolean isFinal() {
        return this.final_;
    }

    /**
     * Method responsible for setting the Final Flag.
     * @param final_ Final Flag.
     */
    public void setFinal(boolean final_) {
        this.final_    = final_;
        this.abstract_ = this.final_ ? false : this.abstract_;
    }
    
    @Override
    public String getIcon() {
        return super.getFolder() + "classes/class.png";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleClassUML" + this.id;
    }
    
    @Override
    public Map getNameStyle() {
        Map    style = super.getNameStyle();
               style.put(mxConstants.STYLE_FONTSTYLE, this.abstract_ ? 3 : 1);
        return style;
    }
    
    @Override
    public String getSignatureCode() {
        String signature  = "public class " + this.name + " ";
               signature += this.getExtendsCode();
               signature += this.getImplementsCode();
        return signature;
    }
    
    /**
     * Method responsible for returning the Implements Code.
     * @return Implements Code.
     */
    public String getImplementsCode() {
        String  names  = "";
        for (InterfaceUML interfaceUML : this.diagram.getRealizations(this))
                names +=  interfaceUML.getName() + ", ";
        String  code   = names.contains(", ") ? names.substring(0, names.lastIndexOf(",")) : "";
        return !code.isEmpty() ? "implements " + code + " " : "";
    }
    
    /**
     * Method responsible for adding the Realizations Packages.
     * @param set Packages Set.
     */
    protected void addRealizationsPackages(Set<String> set) {
        for (InterfaceUML interfaceUML : this.diagram.getRealizations(this))
            set.add(this.setPath(interfaceUML.getFullPath()));
    }
    
    @Override
    public String exportHeader() {
        String export  = "    <"         + this.type;
               export += " id=\""        + this.id                      + "\"";
               export += " name=\""      + this.name                    + "\"";
               export += " mandatory=\"" + this.mandatory               + "\"";
               export += " x=\""         + this.getX()                  + "\"";
               export += " y=\""         + this.getY()                  + "\"";
               export += " abstract=\""  + this.abstract_               + "\"";
               export += " final=\""     + this.final_                  + "\"";
               export += " height=\""    + this.getHeight()             + "\"";
               export += " width=\""     + this.getWidth()              + "\"";
               export += this.exportParent();
               export += ">\n";
        return export;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}