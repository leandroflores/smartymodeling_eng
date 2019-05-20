package model.structural;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Element;

/**
 * <p>Class of Model <b>Stereotype</b>.</p>
 * <p>Class responsible for representing <b>Stereotype</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  20/05/2019
 * @see    model.structural.Exportable
 */
public class Stereotype implements Exportable {
    private String id;
    private String name;
    
    /**
     * Default constructor method of Class.
     */
    public Stereotype() {
        this.id   = "";
        this.name = "stereotype";
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public Stereotype(Element element) {
        this.id   = element.getAttribute("id").trim();
        this.name = element.getAttribute("name").toLowerCase().trim();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param id Stereotype Id.
     * @param name Stereotype Name.
     */
    public Stereotype(String id, String name) {
        this.id   = id.trim();
        this.name = name.toLowerCase().trim();
    }

    /**
     * Method responsible for returning the Stereotype Id.
     * @return Stereotype Id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Method responsible for defining the Stereotype Id.
     * @param id Stereotype Id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method responsible for returning the Stereotype Name.
     * @return Stereotype Name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method responsible for defining the Stereotype Name.
     * @param name Stereotype Name.
     */
    public void setName(String name) {
        this.name = name.toLowerCase().trim();
    }
    
    public Map getStyle() {
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

    @Override
    public String export() {
        return "  <stereotype id=\"" + this.id + "\" name=\"" + this.name + "\"/>\n";
    }
    
    @Override
    public String toString() {
        return "<<" + this.name + ">>";
    }
}