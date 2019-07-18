package model.structural.diagram.activity.base;

import com.mxgraph.util.mxConstants;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.Element;

/**
 * <p>Class of Model <b>JoinUML</b>.</p>
 * <p>Class responsible for representing the <b>Join UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  17/07/2019
 * @see    model.structural.base.Element
 */
public class JoinUML extends Element {
    
    /**
     * Default constructor method of Class.
     */
    public JoinUML() {
        this.name      = "";
        this.type      = "join";
        this.mandatory = true;
        this.size      = new Point(100, 20);
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public JoinUML(org.w3c.dom.Element element) {
        super(element, true);
        this.type = "join";
    }

    @Override
    public String getIcon() {
        return "src/images/icons/diagram/activity/join.png";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleJoinUML";
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
               style.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_BOTTOM);
               style.put(mxConstants.STYLE_FONTSIZE, "15");
               style.put(mxConstants.STYLE_EDITABLE, "0");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_FILLCOLOR,   "#9999ff");
               style.put(mxConstants.STYLE_STROKECOLOR, "#FFFFFF");
        return style;
    }
}