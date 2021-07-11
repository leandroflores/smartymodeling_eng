package model.structural.diagram.feature.base;

import com.mxgraph.util.mxConstants;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.Diagram;
import model.structural.base.Element;

/**
 * <p>Class of Model <b>Feature</b>.</p>
 * <p>Class responsible for representing <b>Feature</b> in SMartyModeling.</p>
 * @author Henrique
 * @since  2020-02-10
 * @see    model.structural.base.Element
 */
public class Feature extends Element {
    private boolean abstract_;
    
    /**
     * Default constructor method of Class.
     * @param diagram Feature Diagram.
     */
    public Feature(Diagram diagram) {
        super(diagram);
        name      = "Feature";
        mandatory = false;
        abstract_ = false;
        size      = new Point(150, 50);
        type      = "feature";
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     * @param diagram Feature Diagram.
     */
    public Feature(org.w3c.dom.Element element, Diagram diagram) {
        super(element, diagram, true);
        abstract_ = element.getAttribute("abstract").equals("true");
        type      = "feature";
    }
    
    @Override
    public void setName(String name) {
        super.setName(name);
        setMinWidth();
    }
    
    /**
     * Method responsible for returning the Min Size.
     * @return Min Size.
     */
    public Integer getMinWidth() {
        return Math.max(40, name.length() * 10 + 50);
    }
    
    /**
     * Method responsible for returning the Min Height.
     * @return Min Height.
     */
    public Integer getMinHeight() {
        return 30;
    }
    
    /**
     * Method responsible for setting the Min Width.
     */
    public void setMinWidth() {
        Integer width = getMinWidth();
        setWidth(width > getWidth() ? width : getWidth());
    }
    
    /**
     * Method responsible for returning the Abstract Flag.
     * @return Abstract Flag.
     */
    public boolean isAbstract() {
        return abstract_;
    }

    /**
     * Method responsible for setting the Abstract Flag.
     * @param abstract_ Abstract Flag.
     */
    public void setAbstract(boolean abstract_) {
        this.abstract_ = abstract_;
    }
    
    @Override
    public String getIcon() {
        return getFolder() + "feature/feature.png";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleFeature" + id;
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_FONTSIZE, "15");
               style.put(mxConstants.STYLE_EDITABLE, "1");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_FILLCOLOR,   mxConstants.NONE);
               style.put(mxConstants.STYLE_DASHED,      abstract_ ? "1": "0");
               style.put(mxConstants.STYLE_FONTSTYLE,   abstract_ ? mxConstants.FONT_ITALIC : "0");
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
               style.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_CENTER);
        return style;
    }
    
    @Override
    public String export() {
        String export  = "    <" + type;
               export += " id=\""        + id           + "\"";
               export += " name=\""      + name         + "\"";
               export += " mandatory=\"" + mandatory    + "\"";
               export += " abstract=\""  + abstract_    + "\"";
               export += " x=\""         + getX()       + "\"";
               export += " y=\""         + getY()       + "\"";
               export += " globalX=\""   + getGlobalX() + "\"";
               export += " globalY=\""   + getGlobalY() + "\"";
               export += " height=\""    + getHeight()  + "\"";
               export += " width=\""     + getWidth()   + "\"";
               export += "/>\n";
        return export;
    }
    
    @Override
    public String toString() {
        return name;
    }
}