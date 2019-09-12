package model.structural.diagram.component.base;

import com.mxgraph.util.mxConstants;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.Element;

/**
 * <p>Class of Model <b>InterfaceUML</b>.</p>
 * <p>Class responsible for representing the <b>Interface UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  20/07/2019
 * @see    model.structural.base.Element
 */
public class InterfaceUML extends Element {
    
    /**
     * Default constructor method of Class.
     */
    public InterfaceUML() {
        this.name      = "Interface";
        this.type      = "interface";
        this.mandatory = true;
        this.size      = new Point(40, 40);
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public InterfaceUML(org.w3c.dom.Element element) {
        super(element, true);
        this.type = "interface";
    }
    
    @Override
    public String getIcon() {
        return "src/images/icons/diagram/component/interface.png";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleInterfaceUML";
    }

    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
               style.put(mxConstants.STYLE_FONTSIZE, "15");
               style.put(mxConstants.STYLE_FONTCOLOR, "#000000");
               style.put(mxConstants.STYLE_FILLCOLOR, "#9999ff");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_BOTTOM);
        return style;
    }
}