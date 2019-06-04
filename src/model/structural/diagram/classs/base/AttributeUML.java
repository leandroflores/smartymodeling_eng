package model.structural.diagram.classs.base;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.Element;
import model.structural.diagram.classs.Entity;

/**
 * <p>Class of Model <b>AttributeUML</b>.</p>
 * <p>Class responsible for representing <b>Attribute UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  20/05/2019
 * @see    model.structural.base.Element
 */
public class AttributeUML extends Element {
    private Entity  entity;
    private TypeUML typeUML;
    private String  visibility;
    private boolean static_;
    private boolean final_;
    
    /**
     * Default constructor method of Class.
     */
    public AttributeUML() {
        this.id         = null;
        this.name       = "attribute";
        this.type       = "attribute";
        this.entity     = null;
        this.typeUML    = null;
        this.visibility = "private";
        this.static_    = false;
        this.final_     = false;
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public AttributeUML(org.w3c.dom.Element element) {
        this.id         = element.getAttribute("id");
        this.name       = element.getAttribute("name");
        this.type       = "attribute";
        this.entity     = null;
        this.typeUML    = null;
        this.visibility = element.getAttribute("visibility");
        this.static_    = element.getAttribute("static_").trim().equals("true");
        this.final_     = element.getAttribute("final_").trim().equals("true");
    }

    @Override
    public void setDefaultName() {
        super.setDefaultName();
        this.name = this.name.toLowerCase().trim();
    }
    
    /**
     * Method responsible for returning Entity.
     * @return Entity.
     */
    public Entity getEntity() {
        return this.entity;
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
        return this.typeUML;
    }

    /**
     * Method responsible for defining Type UML.
     * @param typeUML Type UML.
     */
    public void setTypeUML(TypeUML typeUML) {
        this.typeUML = typeUML;
    }

    /**
     * Method responsible for returning Visibility.
     * @return Visibility.
     */
    public String getVisibility() {
        return this.visibility;
    }

    /**
     * Method responsible for returning Visibility.
     * @param visibility Visibility.
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    /**
     * Method responsible for returning Static Flag.
     * @return Static Flag.
     */
    public boolean isStatic() {
        return this.static_;
    }

    /**
     * Method responsible for defining Static Flag.
     * @param static_ Static Flag.
     */
    public void setStatic(boolean static_) {
        this.static_ = static_;
    }

    /**
     * Method responsible for returning Final Flag.
     * @return Final Flag.
     */
    public boolean isFinal() {
        return this.final_;
    }

    /**
     * Method responsible for defining Final Flag.
     * @param final_ Final Flag.
     */
    public void setFinal(boolean final_) {
        this.final_ = final_;
    }
    
    /**
     * Method responsible for printing Attribute Data.
     * @return Attribute Data.
     */
    public String print() {
        String attribute  = this.printVisibility();
               attribute += (this.static_)  ? " static" : "";
               attribute += (this.final_)   ? " final"  : "";
               attribute += " " + this.printTypeUML();
               attribute += " " + this.name;
        return attribute;
    }
    
    /**
     * Method responsible for printing Visibility.
     * @return Visibility.
     */
    private String printVisibility() {
        if (this.visibility.equals("package"))
            return "";
        if (this.visibility.equals("default"))
            return "";
        return this.visibility;
    }
    
    /**
     * Method responsible for printing Import.
     * @return Import.
     */
    public String printImport() {
        if (this.typeUML.getPath().contains("."))
            return this.typeUML.getPath();
        return "";
    }
    
    /**
     * Method responsible for printing Type UML.
     * @return Type UML.
     */
    private String printTypeUML() {
        if (this.typeUML == null)
            return "Object";
        return this.typeUML.getName();
    }
    
    /**
     * Method responsible for returning Visibility Symbol.
     * @return Visibility Symbol.
     */
    private String getVisibilitySymbol() {
        if (this.visibility.equals("public"))
            return "+";
        if (this.visibility.equals("protected"))
            return "#";
        if (this.visibility.equals("private"))
            return "-";
        return "~";
    }
    
    /**
     * Method responsible for returning Signature.
     * @return Signature.
     */
    public String getSignature() {
        return this.getVisibilitySymbol() + " " + this.name + " : " + this.printTypeUML();
    }
    
    @Override
    public String getIcon() {
        return "src/images/icons/diagram/classs/attribute.png";
    }
    
    @Override
    public String getTitle() {
        return this.getSignature();
    }
    
    @Override
    public String getStyleLabel() {
        return "styleAttributeUML" + this.id;
    }
    
    @Override
    public Map getStyle() {
        Map    estilo = new HashMap<>();
               estilo.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
               estilo.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_LEFT);
               estilo.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               estilo.put(mxConstants.STYLE_FILLCOLOR,   "#9999FF");
               estilo.put(mxConstants.STYLE_STROKECOLOR, "#9999FF");
               estilo.put(mxConstants.STYLE_FONTSTYLE, this.static_ ? 1 : 4);
               estilo.put(mxConstants.STYLE_EDITABLE,  "1");
               estilo.put(mxConstants.STYLE_FONTSIZE,  "12");
               estilo.put(mxConstants.STYLE_RESIZABLE, "0");
               estilo.put(mxConstants.STYLE_MOVABLE,   "0");
               estilo.put(mxConstants.STYLE_FOLDABLE,  "0");
        return estilo;
    }
    
    /**
     * Method responsible for exporting the Type UML.
     * @return Type UML.
     */
    private String exportType() {
        if (this.typeUML == null)
            return " type=\"TYPE#21\"";
        return " type=\"" + this.typeUML.getId() + "\"";
    }
    
    @Override
    public String export() {
        String export  = "      <"        + this.type;
               export += " id=\""         + this.id          + "\"";
               export += " name=\""       + this.name        + "\"";
               export += this.exportType();
               export += " visibility=\"" + this.visibility + "\"";
               export += " static=\""     + this.static_    + "\"";
               export += " final=\""      + this.final_     + "\"";
               export += "/>\n";
        return export;
    }
    
    @Override
    public String toString() {
        return this.getSignature();
    }
}