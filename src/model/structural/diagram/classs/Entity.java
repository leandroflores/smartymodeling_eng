package model.structural.diagram.classs;

import model.structural.diagram.classs.base.MethodUML;
import model.structural.diagram.classs.base.AttributeUML;
import com.mxgraph.util.mxConstants;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.structural.base.Element;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classs.base.TypeUML;

/**
 * <p>Class of Model <b>Entity</b>.</p>
 * <p>Class responsible for representing <b>Entity</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  20/05/2019
 * @see    model.structural.base.Element
 */
public abstract class Entity extends Element {
    private ClassDiagram diagram;
    private TypeUML typeUML;
    private final LinkedHashMap attributes;
    private final LinkedHashMap methods;
    
    /**
     * Default constructor method of Class.
     * @param diagram Class Diagram.
     */
    public Entity(ClassDiagram diagram) {
        this.diagram    = diagram;
        this.mandatory  = true;
        this.size       = new Point(200, 120);
        this.attributes = new LinkedHashMap();
        this.methods    = new LinkedHashMap();
    }

    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public Entity(org.w3c.dom.Element element) {
        super(element, true);
        this.attributes = new LinkedHashMap();
        this.methods    = new LinkedHashMap();
    }

    /**
     * Method responsible for changing the Types of Attributes and Methods.
     * @param oldType Old Type.
     * @param newType New Type.
     */
    public void changeType(TypeUML oldType, TypeUML newType) {
         this.changeAttributeTypes(oldType, newType);
         this.changeMethodTypes(oldType, newType);
    }
    
    @Override
    public void setName(String name) {
        super.setName(name);
        this.typeUML.setName(this.getName());
    }

    /**
     * Method responsible for returning the Class Diagram.
     * @return Class Diagram.
     */
    public ClassDiagram getDiagram() {
        return this.diagram;
    }

    /**
     * Method responsible for setting the Class Diagram.
     * @param diagram Class Diagram.
     */
    public void setDiagram(ClassDiagram diagram) {
        this.diagram = diagram;
    }
    
    /**
     * Method responsible for returning the Stereotypes by Element.
     * @param  element Element.
     * @return Stereotypes.
     */
    public String getStereotypes(Element element) {
        return this.diagram.getStereotypes(element, " ");
    }
    
    /**
     * Method responsible for returning the Type UML.
     * @return Type UML.
     */
    public TypeUML getTypeUML() {
        return this.typeUML;
    }

    /**
     * Method responsible for setting the Type UML.
     * @param typeUML Type UML.
     */
    public void setTypeUML(TypeUML typeUML) {
        this.typeUML = typeUML;
    }
    
    /**
     * Method responsible for returning the Attributes HashMap.
     * @return Attributes HashMap.
     */
    public HashMap getAttributes() {
        return this.attributes;
    }
    
    /**
     * Method responsible for returning the Attributes List.
     * @return Attributes List.
     */
    public List<AttributeUML> getAttributesList() {
        return new ArrayList<>(this.attributes.values());
    }
    
    /**
     * Method responsible for changing the Attribute Types.
     * @param oldType Old Type.
     * @param newType New Type.
     */
    private void changeAttributeTypes(TypeUML oldType, TypeUML newType) {
        List<AttributeUML>  list = this.getAttributesList();
        for (int i = 0; i < list.size(); i++)
            list.get(i).changeTypeUML(oldType, newType);
    }
    
    /**
     * Method responsible for returning the Attributes Position.
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
     * Method responsible for changing the Method Types.
     * @param oldType Old Type.
     * @param newType New Type.
     */
    private void changeMethodTypes(TypeUML oldType, TypeUML newType) {
        List<MethodUML>     list = this.getMethodsList();
        for (int i = 0; i < list.size(); i++)
            list.get(i).changeTypeUML(oldType, newType);
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
    
    /**
     * Method responsible for returning the Comparator.
     * @return Comparator.
     */
    private Comparator<Element> getComparator() {
        return new Comparator<Element>() {
            @Override
            public int compare(Element element1, Element element2) {
                return element1.getId().compareTo(element2.getId());
            }
        };
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