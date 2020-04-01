package model.structural.base.association;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.Element;

/**
 * <p>Class of Model <b>Generalization</b>.</p>
 * <p>Class responsible for representing the <b>Generalization</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  20/05/2019
 * @see    model.structural.base.association.Association
 * @see    model.structural.base.Element
 */
public class Generalization extends Association {
    
    /**
     * Default constructor method of Class.
     * @param source Source Element.
     * @param target Target Element.
     */
    public Generalization(Element source, Element target) {
        this.source = source;
        this.target = target;
        this.type   = "generalization";
    }

    /**
     * Method responsible for returning the Generalization Source.
     * @param  element Element.
     * @return Generalization Source.
     */
    public Element getSource(Element element) {
        return this.target.equals(element) ? this.source : null;
    }
    
    /**
     * Method responsible for returning the Generalization Target.
     * @param  element Element.
     * @return Generalization Target.
     */
    public Element getTarget(Element element) {
        return this.source.equals(element) ? this.target : null;
    }
    
    @Override
    public String getTitle() {
        return "Test";
    }

    @Override
    public String getStyleLabel() {
        return "styleGeneralization";
    }

    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_DASHED,   "0");
               style.put(mxConstants.STYLE_EDITABLE, "0");
               style.put(mxConstants.STYLE_STARTSIZE, "15");
               style.put(mxConstants.STYLE_ENDSIZE,   "20");
//               style.put(mxConstants.STYLE_FILLCOLOR,   "#FFFFFF");
//               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");

//               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
//               style.put(mxConstants.STYLE_FILLCOLOR,   "#8B008B");
//               style.put(mxConstants.STYLE_STROKECOLOR, "#8B008B");
//               style.put(mxConstants.STYLE_FOLDABLE,  0);
//               style.put(mxConstants.STYLE_SWIMLANE_FILLCOLOR,   "#00FF00");

               
//               style.put(mxConstants.STYLE_ENDARROW,   mxConstants.ARROW_BLOCK); // Triangle
//               style.put(mxConstants.STYLE_ENDARROW,   mxConstants.ARROW_CLASSIC); // Classic Arrow
//               style.put(mxConstants.STYLE_ENDARROW,   mxConstants.ARROW_DIAMOND); // Losangle
//               style.put(mxConstants.STYLE_ENDARROW,   mxConstants.ARROW_OPEN); // Open Arrow
//               style.put(mxConstants.STYLE_ENDARROW,   mxConstants.ARROW_OVAL); // Circle
//               style.put(mxConstants.STYLE_ENDARROW,   mxConstants.ARROW_); // Triangle
               
               style.put(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_SPACING);
//               style.put(mxConstants.STYLE_EDGE, mxConstants.EDGESTYLE_LOOP);
        return style;
    }
}