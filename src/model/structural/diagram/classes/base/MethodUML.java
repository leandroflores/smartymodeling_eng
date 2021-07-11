package model.structural.diagram.classes.base;

import com.mxgraph.util.mxConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.diagram.classes.Encodable;
import model.structural.diagram.classes.Entity;

/**
 * <p>Classe de Modelo <b>MethodUML</b>.</p>
 * <p>Class responsible for representing <b>Method UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-20
 * @see    model.structural.base.Element
 * @see    model.structural.diagram.classes.Encodable
 * @see    model.structural.diagram.classes.base.ParameterUML
 * @see    model.structural.diagram.classes.base.TypeUML
 */
public class MethodUML extends Element implements Encodable {
    private Entity  entity;
    private TypeUML return_;
    private String  visibility;
    private boolean constructor;
    private boolean static_;
    private boolean final_;
    private boolean abstract_;
    private List<ParameterUML> parameters;
    
    /**
     * Default constructor method of Class.
     * @param diagram Class Diagram.
     */
    public MethodUML(Diagram diagram) {
        super(diagram);
        id          = null;
        name        = "method";
        entity      = null;
        return_     = null;
        visibility  = "public";
        constructor = false;
        static_     = false;
        final_      = false;
        abstract_   = false;
        parameters  = new ArrayList<>();
        type        = "method";
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     * @param diagram Class Diagram.
     */
    public MethodUML(org.w3c.dom.Element element, Diagram diagram) {
        super(element, diagram);
        id          = element.getAttribute("id");
        name        = element.getAttribute("name");
        entity      = null;
        return_     = null;
        visibility  = element.getAttribute("visibility");
        constructor = element.getAttribute("constructor").equals("true");
        static_     = element.getAttribute("static").equals("true");
        final_      = element.getAttribute("final").equals("true");
        abstract_   = element.getAttribute("abstract").equals("true");
        parameters  = new ArrayList<>();
        setReturn(element);
        type        = "method";
    }

    @Override
    public void setDefaultName() {
        super.setDefaultName();
        name = name.toLowerCase().trim();
        entity.setMinWidth();
    }
    
    @Override
    public String getName() {
        return constructor ? entity.getName() : name;
    }
    
    @Override
    public void setName(String name) {
        if (!constructor) {
            super.setName(name);
            this.name = getName().trim();
            entity.setMinWidth();
        }
    }
    
    /**
     * Method responsible for returning if the Method is Getter.
     * @return Method is Getter.
     */
    public boolean isGetter() {
        return name.toLowerCase().startsWith("get");
    }
    
    /**
     * Method responsible for returning if the Method is Setter.
     * @return Method is Setter.
     */
    public boolean isSetter() {
        return name.toLowerCase().startsWith("set");
    }
    
    /**
     * Method responsible for returning if the Method is Overwritten.
     * @return Method is Overwritten.
     */
    public boolean isOverwritten() {
        if (entity.getSuper() == null && entity.getImplementsMethods().isEmpty())
            return false;
        for (MethodUML method : entity.getInheritedMethods()) {
            if (getCompleteSignature().equalsIgnoreCase(method.getCompleteSignature()))
                return true;
        }
        return false;
    }
    
    /**
     * Method responsible for returning if the Method is Specific.
     * @return Method is Specific.
     */
    public boolean isSpecific() {
        return !isGetter() &&
               !isSetter() &&
               !isOverwritten() &&
               !constructor; 
    }
    
    /**
     * Method responsible for changing the Type UML.
     * @param oldType Old Type.
     * @param newType New Type.
     */
    public void changeTypeUML(TypeUML oldType, TypeUML newType) {
        changeReturn(oldType, newType);
        changeParameterTypes(oldType, newType);
    }
    
    /**
     * Method responsible for returning Entity.
     * @return Entity.
     */
    public Entity getEntity() {
        return entity;
    }

    /**
     * Method responsible for defining Entity.
     * @param entity Entity.
     */
    public void setEntity(Entity entity) {
        this.entity = entity;
    }
    
    /**
     * Method responsible for returning Method Return.
     * @return Method Return.
     */
    public TypeUML getReturn() {
        return return_;
    }
    
    /**
     * Method responsible for defining Method Return.
     * @param return_ Method Return.
     */
    public void setReturn(TypeUML return_) {
        this.return_ = return_;
    }
    
    /**
     * Method responsible for defining Method Return.
     * @param element W3C Element.
     */
    private void setReturn(org.w3c.dom.Element element) {
        if (constructor)
            return_ = null;
    }
    
    /**
     * Method responsible for changing the Return.
     * @param oldType Old Type.
     * @param newType New Type.
     */
    private void changeReturn(TypeUML oldType, TypeUML newType) {
        if (return_.equals(oldType))
            return_ = newType;
    }
    
    @Override
    public String getVisibility() {
        return visibility;
    }

    /**
     * Method responsible for defining Method Visibility.
     * @param visibility Method Visibility.
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
    
    /**
     * Method responsible for returning Method Constructor Flag.
     * @return Method Constructor Flag.
     */
    public boolean isConstructor() {
        return constructor;
    }

    /**
     * Method responsible for defining Method Constructor Flag.
     * @param constructor Method Constructor Flag.
     */
    public void setConstructor(boolean constructor) {
        this.constructor = constructor;
    }
    
    @Override
    public Boolean isStatic() {
        return static_;
    }

    /**
     * Method responsible for defining Method Static Flag.
     * @param static_ Method Static Flag.
     */
    public void setStatic(boolean static_) {
        this.static_ = static_;
    }
    
    @Override
    public Boolean isFinal() {
        return final_;
    }
    
    /**
     * Method responsible for defining Method Final Flag.
     * @param final_ Method Final Flag.
     */
    public void setFinal(boolean final_) {
        this.final_ = final_;
    }

    @Override
    public Boolean isAbstract() {
        return abstract_;
    }
    
    /**
     * Method responsible for defining Method Abstract Flag.
     * @param abstract_ Method Abstract Flag.
     */
    public void setAbstract(boolean abstract_) {
        this.abstract_  = abstract_;
        updateVisibility();
        updateAbstractClass();
    }
    
    /**
     * Method responsible for updating the Visibility.
     */
    private void updateVisibility() {
        if (abstract_ && visibility.equalsIgnoreCase("private"))
            setVisibility("public");
    }
    
    /**
     * Method responsible for updating the Abstract Class.
     */
    private void updateAbstractClass() {
        if (entity.isClass() && abstract_)
           ((ClassUML) entity).setAbstract(abstract_);
    }
    
    /**
     * Method responsible for returning Parameters List.
     * @return Parameters List.
     */
    public List<ParameterUML> getParameters() {
        return parameters;
    }
    
    /**
     * Method responsible for adding a Parameter UML.
     * @param parameter Parameter UML.
     */
    public void addParameter(ParameterUML parameter) {
        if (!parameters.contains(parameter))
             parameters.add(parameter);
    }
    
    /**
     * Method responsible for defining Parameters List.
     * @param parameters Parameters List.
     */
    public void setParameters(List<ParameterUML> parameters) {
        this.parameters = parameters;
    }
    
    /**
     * Method responsible for changing the Paremeter Types.
     * @param oldType Old Type.
     * @param newType New Type.
     */
    private void changeParameterTypes(TypeUML oldType, TypeUML newType) {
        for (ParameterUML parameter : getParameters())
            parameter.changeTypeUML(oldType, newType);
    }
    
    /**
     * Method responsible for returning the Parameters.
     * @return Parameters.
     */
    private String printParameters() {
        if (parameters.isEmpty())   
            return "()";
        if (parameters.size() == 1)
            return "(" + parameters.get(0).getTitle() + ")";
        String toReturn  = "(" + parameters.get(0).getTitle();
        for (int i = 1; i < parameters.size(); ++i)
               toReturn += ", " + parameters.get(i).getTitle();
        return toReturn + ")";
    }
    
    /**
     * Method responsible for returning the Parameters Code.
     * @return Parameters Code.
     */
    private String getParametersCode() {
        if (parameters.isEmpty())   
            return "()";
        if (parameters.size() == 1)
            return "(" + parameters.get(0).exportCode() + ")";
        String code  = "("  + parameters.get(0).exportCode();
        for (int  i  = 1; i < parameters.size(); ++i)
               code += ", " + parameters.get(i).exportCode();
        return code +  ")";
    }
    
    /**
     * Method responsible for adding the Parameters Packages.
     * @param set Packages Set.
     */
    public void addParametersPackages(Set<String> set) {
        for (ParameterUML parameter : getParameters())
            set.add(parameter.getType().getSignature());
    }
    
    @Override
    public String exportCode() {
        String code  =  getSignatureCode();
               code += !constructor ? getReturnCode() : "";
               code +=  getNameCode();
               code +=  getParametersCode();
               code +=  abstract_   ? ";\n" : " {" + getDefaultBodyCode();
        return code;
    }
    
    /**
     * Method responsible for returning the Default Code.
     * @return Default Code.
     */
    public String exportDefaultCode() {
        String code  = getDefaultSignatureCode();
               code += getReturnCode();
               code += " "   + name;
               code += getParametersCode();
               code += "{\n" + getBodyCode();
        return code;
    }
    
    /**
     * Method responsible for returning the Signature Code.
     * @return Signature Code.
     */
    public String getSignatureCode() {
        String code  = visibility;
               code += static_   ? " static"   : "";
               code += abstract_ ? " abstract" : "";
               code += final_    ? " final"    : "";
        return code;
    }
    
    /**
     * Method responsible for returning the Default Signature Code.
     * @return Default Signature Code.
     */
    public String getDefaultSignatureCode() {
        String code  = visibility;
               code += static_ ? " static" : "";
               code += final_  ? " final"  : "";
        return code;
    }
    
    /**
     * Method responsible for returning the Return Code.
     * @return Return Code.
     */
    private String getReturnCode() {
        return " " + return_.getName();
    }
    
    /**
     * Method responsible for returning the Name Code.
     * @return Name Code.
     */
    public String getNameCode() {
        return " " + getName();
    }
    
    /**
     * Method responsible for returning the Default Body Code.
     * @return Default Body Code.
     */
    private String getDefaultBodyCode() {
        if (constructor)
            return "}\n";
        if (return_.isVoid())
            return "\n    }\n";
        return getBodyCode();
    }
    
    /**
     * Method responsible for returning the Body Code.
     * @return Body Code.
     */
    private String getBodyCode() {
        return "\n        " + return_.getBodyCode() + "\n    }\n";
    }
    
    /**
     * Method responsible for returning the Complete Signature.
     * @return Complete Signature.
     */
    public String getCompleteSignature() {
        return entity.getStereotypes(this) + getSignature();
    }
    
    /**
     * Method responsible for returning the Short Signature.
     * @return Short Signature.
     */
    public String getShortSignature() {
        return name + printParameters();
    }
    
    /**
     * Method responsible for returning the Signature.
     * @return Signature.
     */
    public String getSignature() {
        return getVisibilitySymbol() + " " + getShortSignature() + printReturnType();
    }
    
    /**
     * Method responsible for returning Return Type.
     * @return Return Type.
     */
    private String printReturnType() {
        if (constructor)
            return "";
        if (return_ == null)
            return "";
        return " : " + return_.getName();
    }
    
    /**
     * Method responsible for returning Visibility Symbol.
     * @return Visibility Symbol.
     */
    private String getVisibilitySymbol() {
        if (visibility.equals("public"))
            return "+";
        if (visibility.equals("protected"))
            return "#";
        if (visibility.equals("private"))
            return "-";
        return "~";
    }
    
    /**
     * Method responsible for returning Method Description.
     * @return Method Description.
     */
    public String getDescription() {
        return getVisibilitySymbol() + " " + name + getParametersCode() + getReturnSignature();
    }
    
    /**
     * Method responsible for returning the Return Signature.
     * @return Return Signature.
     */
    private String getReturnSignature() {
        if (constructor)
            return "";
        if (return_ == null)
            return " : void";
        return " : " + return_.getName();
    }
    
    @Override
    public String getIcon() {
        return getFolder() + "classes/method.png";
    }
    
    @Override
    public String getTitle() {
        return getSignature();
    }
    
    @Override
    public String getStyleLabel() {
        return "styleMethodUML" + id;
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
               style.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_LEFT);
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_FILLCOLOR,   mxConstants.NONE);
               style.put(mxConstants.STYLE_STROKECOLOR, mxConstants.NONE);
               style.put(mxConstants.STYLE_FONTSTYLE, getFontCode());
               style.put(mxConstants.STYLE_EDITABLE,  "1");
               style.put(mxConstants.STYLE_FONTSIZE,  "12");
               style.put(mxConstants.STYLE_RESIZABLE, "0");
               style.put(mxConstants.STYLE_MOVABLE,   "0");
               style.put(mxConstants.STYLE_FOLDABLE,  "0");
        return style;
    }
    
    /**
     * Method responsible for returning Font Code.
     * @return Font Code.
     */
    private int getFontCode() {
        if (abstract_ && static_)
            return 3;
        if (abstract_)
            return 2;
        if (static_)
            return 1;
        return 4;
    }
    
    /**
     * responsible for exporting Method Return.
     * @return Method Return.
     */
    private String exportReturn() {
        if (return_ == null)
            return "return=\"TIPO#42\"";
        return "return=\"" + return_.getId() + "\"";
    }
    
    @Override
    public String export() {
        String    export  = "      <"  + type + "";
                  export += " id=\""          + id          + "\"";
                  export += " name=\""        + name        + "\"";
                  export += " "               + exportReturn();
                  export += " visibility=\""  + visibility  + "\"";
                  export += " constructor=\"" + constructor + "\"";
                  export += " static=\""      + static_     + "\"";
                  export += " final=\""       + final_      + "\"";
                  export += " abstract=\""    + abstract_   + "\"";
                  export += ">\n";
                  export += exportParameters();
                  export += "      </" + type + ">\n";
        return    export;
    }
    
    /**
     * Method responsible for exporting the Method Parameters.
     * @return Method Parameters.
     */
    private String exportParameters() {
        String export  = "";
        for (ParameterUML parameter : parameters)
               export +=  parameter.export();
        return export;
    }
    
    @Override
    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (!(object instanceof MethodUML))
            return false;
        return getShortSignature().equals(((MethodUML) object).getShortSignature());
    }

    @Override
    public int hashCode() {
        int    hash = 7;
               hash = 73 * hash + Objects.hashCode(name);
        return hash;
    }
    
    @Override
    public String toString() {
        return getDescription();
    }
}