package model.structural.base;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import model.structural.base.interfaces.Exportable;
import org.w3c.dom.Element;

/**
 * <p>Class of Model <b>Stereotype</b>.</p>
 * <p>Class responsible for representing <b>Stereotype</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-20
 * @see    model.structural.base.interfaces.Exportable
 */
public final class Stereotype implements Exportable {
    private String  id;
    private String  name;
    private boolean primitive;
    
    /**
     * Default constructor method of Class.
     */
    public Stereotype() {
        id        = "";
        primitive = false;
        name      = "stereotype";
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public Stereotype(Element element) {
        id        = element.getAttribute("id").trim();
        primitive = element.getAttribute("primitive").trim().toLowerCase().equals("true");
        setName(element.getAttribute("name"));
    }
    
    /**
     * Alternative constructor method of Class.
     * @param id Stereotype Id.
     * @param name Stereotype Name.
     * @param primitive Stereotype Primitive.
     */
    public Stereotype(String id, String name, boolean primitive) {
        this.id        = id.trim();
        this.primitive = primitive;
        setName(name);
    }
    
    /**
     * Alternative constructor method of Class.
     * @param name Stereotype Name.
     */
    public Stereotype(String name) {
        id        = null;
        primitive = false;
        setName(name);
    }

    /**
     * Method responsible for returning the Stereotype Id.
     * @return Stereotype Id.
     */
    public String getId() {
        return id;
    }

    /**
     * Method responsible for defining the Stereotype Id.
     * @param id Stereotype Id.
     */
    public void setId(String id) {
        this.id = ((this.id == null) || (this.id.trim().equals(""))) ? id : this.id;
    }

    /**
     * Method responsible for returning the Stereotype Name.
     * @return Stereotype Name.
     */
    public String getName() {
        return name;
    }

    /**
     * Method responsible for defining the Stereotype Name.
     * @param name Stereotype Name.
     */
    public void setName(String name) {
        this.name = primitive ? name.trim() : name.toLowerCase().trim();
    }

    /**
     * Method responsible for returning the Stereotype Primitive Flag.
     * @return Stereotype Primitive Flag.
     */
    public boolean isPrimitive() {
        return primitive;
    }

    /**
     * Method responsible for defining the Stereotype Primitive Flag.
     * @param primitive Stereotype Primitive Flag.
     */
    public void setPrimitive(boolean primitive) {
        this.primitive = primitive;
    }
    
    /**
     * Method responsible for returning the Style Map.
     * @return Style Map.
     */
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_MOVABLE,   "0");
               style.put(mxConstants.STYLE_EDITABLE,  "0");
               style.put(mxConstants.STYLE_FOLDABLE,  "0");
               style.put(mxConstants.STYLE_FONTSIZE,  "15");
               style.put(mxConstants.STYLE_RESIZABLE, "0");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
//               style.put(mxConstants.STYLE_FILLCOLOR,   "#9999FF");
//               style.put(mxConstants.STYLE_STROKECOLOR, "#9999FF");
               style.put(mxConstants.STYLE_FILLCOLOR,   mxConstants.NONE);
               style.put(mxConstants.STYLE_STROKECOLOR, mxConstants.NONE);
               style.put(mxConstants.STYLE_DASH_PATTERN,  "0");
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
               style.put(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_SHADOW);
        return style;
    }

    @Override
    public String export() {
        return "    <stereotype id=\"" + id + "\" name=\"" + name + "\"" + " primitive=\"" + primitive + "\"/>\n";
    }
    
    @Override
    public boolean equals(Object object) {
        if (object instanceof Stereotype == false)
            return false;
        return Objects.equals(id, ((Stereotype) object).getId());
    }

    @Override
    public int hashCode() {
        int    hash = 7;
               hash = 97 * hash + Objects.hashCode(id);
        return hash;
    }
    
    @Override
    public String toString() {
        return "<<" + name + ">>";
    }
}