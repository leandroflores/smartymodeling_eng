package model.structural.diagram.feature.base;

import com.mxgraph.util.mxConstants;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.Element;

/**
 * <p>Class of Model <b>Feature</b>.</p>
 * <p>Class responsible for representing <b>Feature</b> in SMartyModeling.</p>
 * @author Henrique
 * @since  10/02/2020
 * @see    model.structural.base.Element
 */
public class Feature extends Element {
    
    /**
     * Default constructor method of Class.
     */
    public Feature() {
        this.name      = "Feature";
        this.type      = "feature";
        this.mandatory = false;
        this.size      = new Point(200, 50);
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public Feature(org.w3c.dom.Element element) {
        super(element, true);
        this.type = "feature";
    }
    
    @Override
    public void setName(String name) {
        super.setName(name);
        this.setMinWidth();
    }
    
    /**
     * Method responsible for returning the Min Size.
     * @return Min Size.
     */
    public Integer getMinWidth() {
        return Math.max(30, this.name.length() * 10 + 50);
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
        Integer width = this.getMinWidth();
        this.setWidth(width > this.getWidth() ? width : this.getWidth());
    }
    
    @Override
    public String getIcon() {
        return super.getFolder() + "feature/feature.png";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleFeature" + this.id;
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_FONTSIZE, "15");
               style.put(mxConstants.STYLE_EDITABLE, "1");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_FONTSTYLE,   this.mandatory ? mxConstants.FONT_ITALIC : "0");
               style.put(mxConstants.STYLE_FILLCOLOR,   mxConstants.NONE);
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
               style.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_CENTER);
        return style;
    }
    
    @Override
    public String toString() {
        return "Feature: " + this.name;
    }
}