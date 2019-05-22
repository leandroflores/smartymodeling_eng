package model.structural.diagram.usecase;

import com.mxgraph.util.mxConstants;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.Element;

/**
 * <p>Class of Model <b>ActorUML</b>.</p>
 * <p>Class responsible for representing <b>Actor UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  22/05/2019
 * @see    model.structural.base.Element
 */
public class ActorUML extends Element {
    
    /**
     * Default constructor method of Class.
     */
    public ActorUML() {
        this.name      = "Actor";
        this.type      = "actor";
        this.mandatory = true;
        this.size      = new Point(75, 50);
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public ActorUML(org.w3c.dom.Element element) {
        super(element, true);
        this.type = "actor";
    }

    @Override
    public String getIcon() {
        return "src/images/icons/diagram/usecase/actor.png";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleActorUML";
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_BOTTOM);
               style.put(mxConstants.STYLE_IMAGE, "/images/diagram/usecase/actor.jpg");
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_IMAGE);
               style.put(mxConstants.STYLE_FONTCOLOR, "#000000");
               style.put(mxConstants.STYLE_FONTSIZE,  "15");
               style.put(mxConstants.STYLE_EDITABLE,  "1");
        return style;
    }
}