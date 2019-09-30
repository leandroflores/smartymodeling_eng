package model.structural.diagram.sequence.base;

import com.mxgraph.util.mxConstants;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.Element;

/**
 * <p>Class of Model <b>InstanceUML</b>.</p>
 * <p>Class responsible for representing the <b>Instance UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  24/07/2019
 * @see    model.structural.base.Element
 */
public class InstanceUML extends Element {
    private Element element;
    
    /**
     * Default constructor method of Class.
     */
    public InstanceUML() {
        this.name      = "Instance";
        this.type      = "instance";
        this.mandatory = true;
        this.size      = new Point(200, 350);
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public InstanceUML(org.w3c.dom.Element element) {
        super(element, true);
        this.type = "instance";
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
    
    /**
     * Method responsible for returning the Signature.
     * @return Signature.
     */
    public String getSignature() {
        return this.name + " : " + this.getClassName();
    }
    
    /**
     * Method responsible for returning the Class Name.
     * @return Class Name.
     */
    public String getClassName() {
        if (this.element != null)
            return this.element.getName();
        return "";
    }

    @Override
    public String getIcon() {
        return "src/images/icons/diagram/sequence/instance.png";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleInstanceUML";
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