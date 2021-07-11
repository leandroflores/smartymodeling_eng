package model.structural.diagram.feature.base.association;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.association.Association;
import model.structural.diagram.feature.base.Feature;
import org.w3c.dom.Element;

/**
 * <p>Class of Model <b>Connection</b>.</p>
 * <p>Class responsible for representing <b>Connection</b> in SMartyModeling.</p>
 * @author Henrique
 * @since  2020-02-10
 * @see    model.structural.base.association.Association
 * @see    model.structural.diagram.feature.base.Feature
 */
public class Connection extends Association {
    private String category;
    
    /**
     * Default constructor method of Class.
     * @param source Connection Source.
     * @param target Connection Target.
     * @param category Connection Category.
     */
    public Connection(Feature source, Feature target, String category) {
        super();
        this.source   = source;
        this.target   = target;
        this.category = category;
        this.type     = "connection";
    }

    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public Connection(Element element) {
        super(element);
        category = element.getAttribute("category");
        type     = "connection";
    }
    
    @Override
    public Feature getSource() {
        return (Feature) source;
    }

    /**
     * Method responsible for setting the Source.
     * @param source Feature.
     */
    public void setSource(Feature source) {
        this.source = source;
    }

    @Override
    public Feature getTarget() {
        return (Feature) target;
    }

    /**
     * Method responsible for setting the Target.
     * @param target Feature.
     */
    public void setTarget(Feature target) {
        this.target = target;
    }

    /**
     * Method responsible for returning the Connection Category.
     * @return Connection Category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Method responsible for setting the Connection Category.
     * @param category Connection Category.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String getTitle() {
        return "";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleConnection" + id;
    }
    
    /**
     * Method responsible for returning the Connection End Size.
     * @return Connection End Size.
     */
    private Object getEndSize() {
        return (category.equalsIgnoreCase("mandatory") 
             || category.equalsIgnoreCase("optional")) ?
                "10" : "20";
    }
    
    /**
     * Method responsible for returning the Connection End Arrow.
     * @return Connection End Arrow.
     */
    private Object getEndArrow() {
        return (category.equalsIgnoreCase("mandatory") 
             || category.equalsIgnoreCase("optional")) ?
                mxConstants.ARROW_OVAL :
                mxConstants.ARROW_BLOCK;
    }
    
    /**
     * Method responsible for returning the Connection End Fill.
     * @return Connection End Fill.
     */
    private Object getEndFill() {
        return (category.equalsIgnoreCase("mandatory") 
             || category.equalsIgnoreCase("exclusive")) ?
                "1" : "0";
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_DASHED,   "0");
               style.put(mxConstants.STYLE_EDITABLE, "0");
               style.put(mxConstants.STYLE_ENDSIZE,  getEndSize());
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_SPACING);
               style.put(mxConstants.STYLE_ENDARROW,   getEndArrow());
               style.put(mxConstants.STYLE_ENDFILL,    getEndFill());
               style.put(mxConstants.STYLE_SHAPE,      mxConstants.SHAPE_CONNECTOR);
        return style;
    }
    
    @Override
    protected String exportHeader() {
        String header  = "    <"        + type;
               header += " id=\""       + id              + "\"";
               header += " source=\""   + source.getId()  + "\"";
               header += " target=\""   + target.getId()  + "\"";
               header += " category=\"" + category.trim() + "\"";
               header += ">\n";
        return header;
    }
}