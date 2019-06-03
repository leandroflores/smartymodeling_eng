package model.structural.diagram.classs;

import model.structural.diagram.classs.base.MethodUML;
import model.structural.diagram.classs.base.AttributeUML;
import com.mxgraph.util.mxConstants;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.structural.base.Element;

/**
 * <p>Class of Model <b>Entity</b>.</p>
 * <p>Class responsible for representing <b>Entity</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  20/05/2019
 * @see    model.structural.base.Element
 */
public abstract class Entity extends Element {
    private final HashMap attributes;
    private final HashMap methods;
    
    /**
     * Default constructor method of Class.
     */
    public Entity() {
        this.mandatory  = true;
        this.size       = new Point(200, 120);
        this.attributes = new HashMap();
        this.methods    = new HashMap();
    }

    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public Entity(org.w3c.dom.Element element) {
        super(element, true);
        this.attributes = new HashMap();
        this.methods    = new HashMap();
    }
    
    /**
     * Method responsible for returning Attributes HashMap.
     * @return Attributes HashMap.
     */
    public HashMap getAttributes() {
        return this.attributes;
    }
    
    /**
     * Method responsible for returning Attributes List.
     * @return Attributes List.
     */
    public List<AttributeUML> getAttributesList() {
        return new ArrayList<>(this.attributes.values());
    }
    
    /**
     * Method responsible for returning Attributes Position.
     * @return Attributes Position.
     */
    public Integer getAttributesPosition() {
        return 70 + this.attributes.size() * 16 + 5;
    }

    /**
     * Method responsible for updating the Size.
     */
    private void updateSize() {
        if (this.getHeight() + 20 < this.getHeight())
            this.setHeight(this.getHeight() + 20);
    }
    
    /**
     * Method responsible for adding a Attribute UML.
     * @param attribute Attribute UML.
     */
    public void addAttribute(AttributeUML attribute) {
        this.attributes.put(attribute.getId(), attribute);
        this.updateSize();
    }
    
    /**
     * Method responsible for removing a Attribute UML.
     * @param attribute Attribute UML.
     */
    public void removeAttribute(AttributeUML attribute) {
        this.attributes.remove(attribute.getId());
    }

    /**
     * Method responsible for returning Methods HashMap.
     * @return Methods HashMap.
     */
    public HashMap getMethods() {
        return this.methods;
    }
    
    /**
     * Method responsible for returning Methods List.
     * @return Methods List.
     */
    public List<MethodUML> getMethodsList() {
        return new ArrayList<>(this.methods.values());
    }

    /**
     * Method responsible for returning Methods Position.
     * @return Methods Position.
     */
    public Integer getMethodsPosition() {
        return this.getAttributesPosition() + 5;
    }
    
    /**
     * Method responsible for adding a Method UML.
     * @param method Method UML.
     */
    public void addMethod(MethodUML method) {
        this.methods.put(method.getId(), method);
        this.updateSize();
    }
    
    /**
     * Method responsible for removing a Method UML.
     * @param method Method UML.
     */
    public void removeMethod(MethodUML method) {
        this.methods.remove(method.getId());
    }

    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
               style.put(mxConstants.STYLE_FILLCOLOR,   "#9999FF");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_EDITABLE, "0");
               style.put(mxConstants.STYLE_FOLDABLE, "0");
        return style;
    }
    
    /**
     * Method responsible for returning Stereotype Style.
     * @return Stereotype Style.
     */
    public Map getStereotypeStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_FILLCOLOR,   "#9999FF");
               style.put(mxConstants.STYLE_STROKECOLOR, "#9999FF");
               style.put(mxConstants.STYLE_EDITABLE,  "0");
               style.put(mxConstants.STYLE_RESIZABLE, "0");
               style.put(mxConstants.STYLE_MOVABLE,   "0");
               style.put(mxConstants.STYLE_FOLDABLE,  "0");
               style.put(mxConstants.STYLE_FONTSIZE,  "15");
               style.put(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_SHADOW);
        return style;
    }
    
    /**
     * Method responsible for returning Name Style.
     * @return Name Style.
     */
    public Map getNameStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_FILLCOLOR,   "#9999FF");
               style.put(mxConstants.STYLE_STROKECOLOR, "#9999FF");
               style.put(mxConstants.STYLE_EDITABLE,  "1");
               style.put(mxConstants.STYLE_RESIZABLE, "0");
               style.put(mxConstants.STYLE_MOVABLE,   "0");
               style.put(mxConstants.STYLE_FOLDABLE,  "0");
               style.put(mxConstants.STYLE_FONTSIZE,  "15");
               style.put(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_BOLD);
        return style;
    }
    
    /**
     * Method responsible for returning Line Style.
     * @return Line Style.
     */
    public Map getLineStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_LINE);
               style.put(mxConstants.STYLE_EDITABLE,  "0");
               style.put(mxConstants.STYLE_RESIZABLE, "0");
               style.put(mxConstants.STYLE_MOVABLE,   "0");
               style.put(mxConstants.STYLE_FOLDABLE,  "0");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
        return style;
    }
    
    /**
     * Method responsible for returning Add Attribute Style.
     * @return Add Attribute Style.
     */
    public Map getAddAttributeStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
               style.put(mxConstants.STYLE_FONTCOLOR,   "#FFFF1A");
               style.put(mxConstants.STYLE_FILLCOLOR,   "#FFFF1A");
               style.put(mxConstants.STYLE_STROKECOLOR, "#FFFF1A");
               style.put(mxConstants.STYLE_EDITABLE, "0");
               style.put(mxConstants.STYLE_RESIZABLE,"0");
               style.put(mxConstants.STYLE_MOVABLE,  "0");
               style.put(mxConstants.STYLE_FOLDABLE, "0");
               style.put(mxConstants.STYLE_FONTSIZE, "0");
        return style;
    }
    
    /**
     * Method responsible for returning Add Method Style.
     * @return Add Method Style.
     */
    public Map getAddMethodStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
               style.put(mxConstants.STYLE_FONTCOLOR,   "#4AAD7D");
               style.put(mxConstants.STYLE_FILLCOLOR,   "#4AAD7D");
               style.put(mxConstants.STYLE_STROKECOLOR, "#4AAD7D");
               style.put(mxConstants.STYLE_EDITABLE, "0");
               style.put(mxConstants.STYLE_RESIZABLE,"0");
               style.put(mxConstants.STYLE_MOVABLE,  "0");
               style.put(mxConstants.STYLE_FOLDABLE, "0");
               style.put(mxConstants.STYLE_FONTSIZE, "0");
        return style;
    }
    
    /**
     * Method responsible for exporting Header.
     * @return Header.
     */
    public abstract String exportHeader();
    
    /**
     * Method responsible for exporting Attributes.
     * @return Attributes.
     */
    public String exportAttributes() {
        String export  = "";
        for (AttributeUML attribute : this.getAttributesList())
               export +=  attribute.export();
        return export;
    }
    
    /**
     * Method responsible for exporting Methods.
     * @return Methods.
     */
    public String exportMethods() {
        String export  = "";
        for (MethodUML   method : this.getMethodsList())
               export += method.export();
        return export;
    }
    
    /**
     * Method responsible for exporting Footer.
     * @return Footer.
     */
    public String exportFooter() {
        return "    </" + this.type + ">\n";
    }
    
    @Override
    public String export() {
        return   this.exportHeader()
               + this.exportAttributes()
               + this.exportMethods()
               + this.exportFooter();
    }
}