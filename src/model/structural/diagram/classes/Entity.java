package model.structural.diagram.classes;

import model.structural.diagram.classes.base.MethodUML;
import model.structural.diagram.classes.base.AttributeUML;
import com.mxgraph.util.mxConstants;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.structural.base.Element;
import model.structural.base.Stereotype;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.PackageUML;
import model.structural.diagram.classes.base.TypeUML;

/**
 * <p>Class of Model <b>Entity</b>.</p>
 * <p>Class responsible for representing the <b>Entity</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  20/05/2019
 * @see    model.structural.base.Element
 */
public abstract class Entity extends Element {
    private ClassDiagram diagram;
    private PackageUML packageUML;
    private TypeUML    typeUML;
    private final LinkedHashMap attributes;
    private final LinkedHashMap methods;
    
    /**
     * Default constructor method of Class.
     * @param diagram Class Diagram.
     */
    public Entity(ClassDiagram diagram) {
        this.diagram    = diagram;
        this.packageUML = null;
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
        this.packageUML = null;
        this.attributes = new LinkedHashMap();
        this.methods    = new LinkedHashMap();
    }
    
    /**
     * Method responsible for updating the Entity Size.
     */
    public void updateSize() {
        this.setMinHeight();
        this.setMinWidth();
        this.updatePackageSize();
    }
    
    /**
     * Method responsible for returning the Min Height.
     * @return Min Height.
     */
    public Integer getMinHeight() {
        return    5
               + 21 * this.getStereotypesList().size()
               +  5
               + (this.type.equals("interface") ? 22 : 0)
               + 25
               +  3
               +  5
               + 16 * this.getAttributesList().size()
               +  3
               +  5
               + 16 * this.getMethodsList().size()
               + 5;
    }
    
    /**
     * Method responsible for setting the Min Height.
     */
    public void setMinHeight() {
        Integer height = this.getMinHeight();
        this.setHeight(height > this.getHeight() ? height : this.getHeight());
    }
    
    /**
     * Method responsible for returning the Min Width.
     * @return Min Width.
     */
    public Integer getMinWidth() {
        Integer[]   sizes = new Integer[]{this.getStereotypeSize(), this.getNameSize(), this.getAttributeSize(), this.getMethodSize(), 200};
        Arrays.sort(sizes);
        return sizes[4];
    }
    
    /**
     * Method responsible for setting the Min Width.
     */
    public void setMinWidth() {
        Integer width = this.getMinWidth();
        this.setWidth(width  >  this.getWidth() ?  width : this.getWidth());
    }

    /**
     * Method responsible for returning the Absolute Height.
     * @return Absolute Height.
     */
    public Integer getAbsoluteHeight() {
        return this.getY() + this.getHeight() + 10;
    }
    
    /**
     * Method responsible for returning the Absolute Width.
     * @return Absolute Width.
     */
    public Integer getAbsoluteWidth() {
        return this.getX() + this.getWidth()  + 10;
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
        this.setMinWidth();
    }

    @Override
    public void dx(Integer distance) {
        super.dx(distance);
        this.updatePackageSize();
    }

    @Override
    public void dy(Integer distance) {
        super.dy(distance);
        this.updatePackageSize();
    }
    
    /**
     * Method responsible for updating the Package Size.
     */
    public void updatePackageSize() {
        if (this.packageUML != null)
            this.packageUML.updateSize();
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
     * Method responsible for returning the Package UML.
     * @return Package UML.
     */
    public PackageUML getPackageUML() {
        return this.packageUML;
    }
    
    /**
     * Method responsible for reseting the Package UML.
     */
    public void resetPackageUML() {
        if (this.packageUML != null)
            this.packageUML.removeEntity(this);
        this.packageUML = null;
    }
    
    /**
     * Method responsible for changing the Entity Package UML.
     * @param newPackageUML New Package.
     */
    public void changePackageUML(PackageUML newPackageUML) {
        if (this.packageUML != null)
            this.packageUML.removeEntity(this);
        if (newPackageUML   != null)
            newPackageUML.addEntity(this);
        this.setPackageUML(newPackageUML);
    }
    
    /**
     * Method responsible for setting the Package UML.
     * @param packageUML Package UML.
     */
    public void setPackageUML(PackageUML packageUML) {
        this.packageUML = packageUML;
    }
    
    /**
     * Method responsible for returning the Stereotypes List.
     * @return Stereotypes List.
     */
    public List<Stereotype> getStereotypesList() {
        return this.diagram.getStereotypesList(this);
    }
    
    /**
     * Method responsible for returning the Stereotype Size.
     * @return Stereotype Size.
     */
    public Integer getStereotypeSize() {
        Integer lenght  = 0;
        Integer current;
        for (Stereotype stereotype : this.getStereotypesList()) {
                current = stereotype.toString().length() * 8;
                lenght  = lenght > current ? lenght : current;
        }
        return  lenght;
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
     * Method responsible for returning the Interface Position.
     * @return Interface Position.
     */
    public Integer getInterfacePosition() {
        return this.getStereotypesList().size() * 21 + 5;
    }
    
    /**
     * Method responsible for returning the Name Position.
     * @return Name Position.
     */
    public Integer getNamePosition() {
        return this.getStereotypesList().size() * 21 + ((this.getType().equals("interface")) ? 20 : 0) + 5;
    }
    
    /**
     * Method responsible for returning the Name Size.
     * @return Name Size.
     */
    private Integer getNameSize() {
        return 10 * this.name.length();
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
        return this.getNamePosition() + 30;
    }
    
    /**
     * Method responsible for returning the Attribute Size.
     * @return Attribute Size.
     */
    public Integer getAttributeSize() {
        Integer lenght  = 0;
        Integer current;
        for (AttributeUML attribute : this.getAttributesList()) {
                current = attribute.getCompleteSignature().length() * 7;
                lenght  = lenght > current ? lenght : current;
        }
        return  lenght;
    }
    
    /**
     * Method responsible for adding a Attribute UML.
     * @param attribute Attribute UML.
     */
    public void addAttribute(AttributeUML attribute) {
        this.attributes.put(attribute.getId(), attribute);
//        this.updateSize();
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
        return this.getAttributesPosition() 
             +  5 
             + 16 * this.getAttributesList().size()
             +  3;
    }
    
    /**
     * Method responsible for returning the Method Size.
     * @return Method Size.
     */
    public Integer getMethodSize() {
        Integer lenght  = 0;
        Integer current;
        for (MethodUML method : this.getMethodsList()) {
                current = method.getCompleteSignature().length() * 7;
                lenght  = lenght > current ? lenght : current;
        }
        return  lenght;
    }
    
    /**
     * Method responsible for adding a Method UML.
     * @param method Method UML.
     */
    public void addMethod(MethodUML method) {
        this.methods.put(method.getId(), method);
//        this.updateSize();
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
     * Method responsible for returning New Attribute Style.
     * @return New Attribute Style.
     */
    public Map getNewAttributeStyle() {
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
     * Method responsible for returning New Method Style.
     * @return New Method Style.
     */
    public Map getNewMethodStyle() {
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
     * Method responsible for exporting the Parent.
     * @return Parent.
     */
    protected String exportParent() {
        if (this.packageUML == null)
            return " parent=\"\"";
        return " parent=\"" + this.getPackageUML().getId() + "\"";
    }
    
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