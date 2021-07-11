package model.structural.diagram.classes.base;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.diagram.classes.Encodable;
import model.structural.diagram.classes.Entity;

/**
 * <p>Class of Model <b>AttributeUML</b>.</p>
 * <p>Class responsible for representing <b>Attribute UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-20
 * @see    model.structural.base.Element
 * @see    model.structural.diagram.classes.Encodable
 * @see    model.structural.diagram.classes.Entity
 * @see    model.structural.diagram.classes.base.TypeUML
 */
public class AttributeUML extends Element implements Encodable {
    private Entity  entity;
    private TypeUML typeUML;
    private String  visibility;
    private boolean static_;
    private boolean final_;
    
    /**
     * Default constructor method of Class.
     * @param diagram Class Diagram.
     */
    public AttributeUML(Diagram diagram) {
        super(diagram);
        id         = null;
        name       = "attribute";
        entity     = null;
        typeUML    = null;
        visibility = "private";
        static_    = false;
        final_     = false;
        type       = "attribute";
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     * @param diagram Class Diagram.
     */
    public AttributeUML(org.w3c.dom.Element element, Diagram diagram) {
        super(element, diagram);
        id         = element.getAttribute("id");
        name       = element.getAttribute("name");
        entity     = null;
        typeUML    = null;
        visibility = element.getAttribute("visibility");
        static_    = element.getAttribute("static").trim().equals("true");
        final_     = element.getAttribute("final").trim().equals("true");
        type       = "attribute";
    }

    @Override
    public void setDefaultName() {
        super.setDefaultName();
        name = name.toLowerCase().trim();
        entity.setMinWidth();
    }
    
    @Override
    public void setName(String name) {
        super.setName(name);
        this.name = getName().replaceAll(" ", "");
        entity.setMinWidth();
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
     * Method responsible for returning Type UML.
     * @return Type UML.
     */
    public TypeUML getTypeUML() {
        return typeUML;
    }

    /**
     * Method responsible for defining Type UML.
     * @param typeUML Type UML.
     */
    public void setTypeUML(TypeUML typeUML) {
        this.typeUML = typeUML;
    }
    
    /**
     * Method responsible for changing the Type UML.
     * @param oldType Old Type.
     * @param newType New Type.
     */
    public void changeTypeUML(TypeUML oldType, TypeUML newType) {
        if (typeUML.equals(oldType))
            typeUML = newType;
    }

    @Override
    public String getVisibility() {
        return visibility;
    }

    /**
     * Method responsible for returning Visibility.
     * @param visibility Visibility.
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    @Override
    public Boolean isStatic() {
        return static_;
    }

    /**
     * Method responsible for defining Static Flag.
     * @param static_ Static Flag.
     */
    public void setStatic(boolean static_) {
        this.static_ = static_;
    }
    
    @Override
    public Boolean isFinal() {
        return final_;
    }

    /**
     * Method responsible for defining Final Flag.
     * @param final_ Final Flag.
     */
    public void setFinal(boolean final_) {
        this.final_ = final_;
    }
    
    @Override
    public Boolean isAbstract() {
        return false;
    }
    
    @Override
    public String exportCode() {
        String code  = printVisibility();
               code += (static_)  ? " static" : "";
               code += (final_)   ? " final"  : "";
               code += " " + printTypeUML();
               code += " " + name + ";";
        return code;
    }
    
    /**
     * Method responsible for printing Visibility.
     * @return Visibility.
     */
    private String printVisibility() {
        if (visibility.equals("package"))
            return "";
        if (visibility.equals("default"))
            return "";
        return visibility;
    }
    
    /**
     * Method responsible for printing Import.
     * @return Import.
     */
    public String printImport() {
        if (typeUML.getPath().contains("."))
            return typeUML.getPath();
        return "";
    }
    
    /**
     * Method responsible for printing Type UML.
     * @return Type UML.
     */
    private String printTypeUML() {
        if (typeUML == null)
            return "Object";
        return typeUML.getName();
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
     * Method responsible for returning the Complete Signature.
     * @return Complete Signature.
     */
    public String getCompleteSignature() {
        return (entity.getStereotypes(this) + getSignature()).trim();
    }
    
    /**
     * Method responsible for returning the Signature.
     * @return Signature.
     */
    public String getSignature() {
        return getVisibilitySymbol() + " " + name + " : " + printTypeUML();
    }
    
    @Override
    public String getIcon() {
        return getFolder() + "classes/attribute.png";
    }
    
    @Override
    public String getTitle() {
        return getSignature();
    }
    
    @Override
    public String getStyleLabel() {
        return "styleAttributeUML" + id;
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
               style.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_LEFT);
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
//               style.put(mxConstants.STYLE_FILLCOLOR,   "#9999FF");
               style.put(mxConstants.STYLE_FILLCOLOR,   mxConstants.NONE);
//               style.put(mxConstants.STYLE_STROKECOLOR, "#9999FF");
               style.put(mxConstants.STYLE_STROKECOLOR,mxConstants.NONE);
               style.put(mxConstants.STYLE_FONTSTYLE, static_ ? 1 : mxConstants.FONT_UNDERLINE);
               style.put(mxConstants.STYLE_EDITABLE,  "1");
               style.put(mxConstants.STYLE_FONTSIZE,  "12");
               style.put(mxConstants.STYLE_RESIZABLE, "0");
               style.put(mxConstants.STYLE_MOVABLE,   "0");
               style.put(mxConstants.STYLE_FOLDABLE,  "0");
        return style;
    }
    
    /**
     * Method responsible for exporting the Type UML.
     * @return Type UML.
     */
    private String exportType() {
        if (typeUML == null)
            return " type=\"TYPE#21\"";
        return " type=\"" + typeUML.getId() + "\"";
    }
    
    @Override
    public String export() {
        String export  = "      <"        + type;
               export += " id=\""         + id          + "\"";
               export += " name=\""       + name        + "\"";
               export += exportType();
               export += " visibility=\"" + visibility + "\"";
               export += " static=\""     + static_    + "\"";
               export += " final=\""      + final_     + "\"";
               export += "/>\n";
        return export;
    }
    
    @Override
    public String toString() {
        return getSignature();
    }
}