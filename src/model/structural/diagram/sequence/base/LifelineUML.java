package model.structural.diagram.sequence.base;

import com.mxgraph.util.mxConstants;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.Element;
import model.structural.diagram.usecase.base.ActorUML;

/**
 * <p>Class of Model <b>LifelineUML</b>.</p>
 * <p>Class responsible for representing the <b>Lifeline UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  24/07/2019
 * @see    model.structural.base.Element
 */
public class LifelineUML extends Element {
    private ActorUML actor;
    
    /**
     * Default constructor method of Class.
     */
    public LifelineUML() {
        this.name      = "Lifeline";
        this.type      = "lifeline";
        this.mandatory = true;
        this.size      = new Point(200, 350);
        this.actor     = null;
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
     * Method responsible for returning the Actor UML.
     * @return Actor UML.
     */
    public ActorUML getActor() {
        return this.actor;
    }

    /**
     * Method responsible for setting the Actor UML.
     * @param actor Actor UML.
     */
    public void setActor(ActorUML actor) {
        this.actor = actor;
    }

    /**
     * Method responsible for returning the Name Size.
     * @return Name Size.
     */
    public Integer getNameSize() {
        return 10 * this.name.length();
    }
    
    /**
     * Method responsible for returning the Signature.
     * @return Signature.
     */
    public String getSignature() {
        return this.name + " : " + this.getActorName();
    }
    
    /**
     * Method responsible for returning the Actor Name.
     * @return Actor Name.
     */
    public String getActorName() {
        if (this.actor != null)
            return this.actor.getName();
        return "Actor";
    }
    
    @Override
    public String getIcon() {
        return "src/images/icons/diagram/sequence/lifeline.png";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleLifelineUML";
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