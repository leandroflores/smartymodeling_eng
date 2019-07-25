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
        this.size      = new Point(200, 100);
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
    
    @Override
    public Map getStyle() {
        Map    estilo = new HashMap<>();
               estilo.put(mxConstants.STYLE_FONTSIZE, "15");
               estilo.put(mxConstants.STYLE_EDITABLE, "1");
               estilo.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               estilo.put(mxConstants.STYLE_FILLCOLOR,   "#9999ff");
               estilo.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               estilo.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
               estilo.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_CENTER);
        return estilo;
    }
}