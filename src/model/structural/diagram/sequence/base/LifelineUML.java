package model.structural.diagram.sequence.base;

import com.mxgraph.util.mxConstants;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.Element;

/**
 * <p>Class of Model <b>LifelineUML</b>.</p>
 * <p>Class responsible for representing the <b>Lifeline UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  24/07/2019
 * @see    model.structural.base.Element
 */
public class LifelineUML extends Element {
    private Element element;
    
    /**
     * Default constructor method of Class.
     */
    public LifelineUML() {
        this.name      = "Lifeline";
        this.type      = "lifeline";
        this.mandatory = true;
        this.size      = new Point(200, 350);
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public LifelineUML(org.w3c.dom.Element element) {
        super(element, true);
        this.type = "lifeline";
    }

    /**
     * Method responsible for returning the Element.
     * @return Element.
     */
    public Element getElement() {
        return this.element;
    }

    /**
     * Method responsible for setting the Element.
     * @param element Element.
     */
    public void setElement(Element element) {
        this.element = element;
    }

    @Override
    public String getIcon() {
        return "src/images/icons/diagram/usecase/use-case.png";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleUseCaseUML";
    }
    
    /**
     * Method responsible for returning the Name Style.
     * @return Name Style.
     */
    public Map getNameStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_FILLCOLOR,   "#9999FF");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
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
//               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_LINE);
////               style.put(mxConstants.STYLE_ORTHOGONAL, "1");
//               style.put(mxConstants.STYLE_ROTATION,  90);
//               style.put(mxConstants.STYLE_EDITABLE,  "0");
//               style.put(mxConstants.STYLE_RESIZABLE, "0");
//               style.put(mxConstants.STYLE_MOVABLE,   "0");
//               style.put(mxConstants.STYLE_FOLDABLE,  "0");
//               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_DASHED,   "0");
               style.put(mxConstants.STYLE_EDITABLE, "0");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_ENDARROW,   mxConstants.ARROW_SPACING);
               style.put(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_SPACING);
        return style;
    }
    
    /**
     * Method responsible for returning the End Point Style.
     * @return End Point Style.
     */
    public Map getEndPointStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
               style.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_BOTTOM);
               style.put(mxConstants.STYLE_FONTSIZE, "15");
               style.put(mxConstants.STYLE_EDITABLE, "0");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_FILLCOLOR,   "#000000");
               style.put(mxConstants.STYLE_STROKECOLOR, "#FFFFFF");
        return style;
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_FONTSIZE, "15");
               style.put(mxConstants.STYLE_EDITABLE, "1");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_FILLCOLOR,   "#E6E6FA");
               style.put(mxConstants.STYLE_STROKECOLOR, "#E6E6FA");
               style.put(mxConstants.STYLE_EDITABLE, "1");
               style.put(mxConstants.STYLE_FOLDABLE, "0");
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
               style.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_CENTER);
        return style;
    }
}