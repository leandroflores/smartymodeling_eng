package model.structural.diagram.activity.base.association;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import model.structural.base.Element;
import model.structural.base.association.Association;

/**
 * <p>Class of Model <b>FlowUML</b>.</p>
 * <p>Class responsible for representing the <b>Flow UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  17/07/2019
 * @see    model.structural.base.association.Association
 * @see    model.structural.base.Element
 */
public class FlowUML extends Association {
    
    /**
     * Default constructor method of Class.
     * @param source Source Element.
     * @param target Target Element.
     */
    public FlowUML(Element source, Element target) {
        this.source = source;
        this.target = target;
        this.type   = "flow";
    }

    @Override
    public String getTitle() {
        return "";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleFlowUML";
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_DASHED,   "0");
               style.put(mxConstants.STYLE_MOVABLE,  "0");
               style.put(mxConstants.STYLE_EDITABLE, "0");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_SHAPE,      mxConstants.SHAPE_CONNECTOR);
               style.put(mxConstants.STYLE_ENDARROW,   mxConstants.ARROW_OPEN);
               style.put(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_SPACING);
        return style;
    }
    
    @Override
    public int hashCode() {
        int    hash = 3;
               hash = 19 * hash + Objects.hashCode(this.source);
               hash = 19 * hash + Objects.hashCode(this.target);
        return hash;
    }
    
    @Override
    public boolean equals(Object objeto) {
        if (objeto instanceof FlowUML == false)
            return false;
        return this.source.equals(( (FlowUML) objeto).getSource())
            && this.target.equals(((FlowUML) objeto).getTarget());
    }
}