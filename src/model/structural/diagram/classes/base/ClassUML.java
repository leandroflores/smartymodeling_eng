package model.structural.diagram.classes.base;

import com.mxgraph.util.mxConstants;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.structural.base.Diagram;
import model.structural.base.association.Association;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.association.RealizationUML;
import model.structural.base.Element;

/**
 * <p>Class of Model <b>ClassUML</b>.</p>
 * <p>Class responsible for representing <b>Class UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-03
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
        name      = "Class";
        type      = "class";
        abstract_ = false;
        final_    = false;
        super.updateSize();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     * @param diagram Class Diagram.
     */
    public ClassUML(org.w3c.dom.Element element, Diagram diagram) {
        super(element, diagram);
        type      = "class";
        abstract_ = element.getAttribute("abstract").equals("true");
        final_    = element.getAttribute("final").equals("true");
    }

    @Override
    public Boolean isAbstract() {
        return abstract_;
    }

    /**
     * Method responsible for setting the Abstract Flag.
     * @param abstract_ Abstract Flag.
     */
    public void setAbstract(boolean abstract_) {
        this.abstract_  = abstract_;
        this.final_     = abstract_ ? false : final_;
    }

    @Override
    public Boolean isFinal() {
        return final_;
    }

    /**
     * Method responsible for setting the Final Flag.
     * @param final_ Final Flag.
     */
    public void setFinal(boolean final_) {
        this.final_    = final_;
        this.abstract_ = final_ ? false : abstract_;
    }
    
    @Override
    public boolean isFirstConcrete() {
        if (abstract_)
            return false;
        for (Element element : diagram.getSupers(this)) {
            if (!((ClassUML) element).isAbstract())
                return false;
        }
        return true;
    }
    
    @Override
    public String getIcon() {
        return getFolder() + "classes/class.png";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleClassUML" + id;
    }
    
    @Override
    public Map getNameStyle() {
        Map    style = super.getNameStyle();
               style.put(mxConstants.STYLE_FONTSTYLE, abstract_ ? 3 : 1);
        return style;
    }
    
    @Override
    public String getSignatureCode() {
        String signature  = "public ";
               signature += abstract_ ? "abstract " : "";
               signature += "class ";
               signature += name + " ";
               signature += getExtendsCode();
               signature += getImplementsCode();
        return signature;
    }
    
    /**
     * Method responsible for returning the Realizations List.
     * @return Realizations List.
     */
    public List<InterfaceUML> getRealizations() {
        List   interfaces = new ArrayList<>();
        for (Association association : getDiagram().getRealizations(this))
               interfaces.add(((RealizationUML) association).getTarget());
        return interfaces;
    }
    
    /**
     * Method responsible for adding the Interfaces Packages.
     * @param set Packages Set.
     */
    public void addInterfacesPackages(Set<String> set) {
        for (InterfaceUML interface_ : getRealizations())
            set.add(setPath(interface_.getFullPath()));
    }
    
    /**
     * Method responsible for returning the Implements Code.
     * @return Implements Code.
     */
    public String getImplementsCode() {
        String  names  =  "";
        for (InterfaceUML interface_ : getRealizations())
                names +=  interface_.getName() + ", ";
        String  code   =  names.contains(", ") ? names.substring(0, names.lastIndexOf(",")) : "";
        return !code.isEmpty() ? "implements " + code + " " : "";
    }
    
    /**
     * Method responsible for adding the Realizations Packages.
     * @param set Packages Set.
     */
    protected void addRealizationsPackages(Set<String> set) {
        for (InterfaceUML interface_ : getRealizations())
            set.add(setPath(interface_.getFullPath()));
    }
    
    @Override
    public Set<MethodUML> getImplementsMethods() {
        Set    set = new HashSet();
        for (InterfaceUML  interfaceUML_ : getRealizations())
               set.addAll(interfaceUML_.getAllMethods());
        return set;
    }
    
    @Override
    public Set<MethodUML> getAllMethods() {
        Set    set = new HashSet();
               set.addAll(getMethodsList());
               set.addAll(getInheritedMethods());
               set.addAll(getImplementsMethods());
        return set;
    }
    
    @Override
    public Set<MethodUML> getExportableMethods() {
        Set    set = new HashSet();
               set.addAll(getMethodsList());
               set.addAll(getAbstractInheritedMethods());
               set.addAll(getImplementsMethods());
        return set;
    }
    
    /**
     * Method responsible for returning the Abstract Methods.
     * @return Abstract Methods.
     */
    public List<MethodUML> getAbstractMethods() {
        List   list = new ArrayList<>();
        for (MethodUML method : getAllMethods()) {
            if (method.isAbstract())
                list.add(method);
        }
        return list;
    }
    
    @Override
    public Double getExtensValue() {
        return Double.parseDouble(Integer.toString(getAbstractMethods().size())) /
               Double.parseDouble(Integer.toString(getAllMethods().size()));
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
               export += " abstract=\""  + abstract_    + "\"";
               export += " final=\""     + final_       + "\"";
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