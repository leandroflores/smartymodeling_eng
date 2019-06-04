package model.structural.base.association;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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

    @Override
    public String getTitle() {
        return "";
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
               style.put(mxConstants.STYLE_ENDSIZE,   "15");
               style.put(mxConstants.STYLE_FILLCOLOR,   "#FFFFFF");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_ENDARROW,   mxConstants.ARROW_BLOCK);
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
    public boolean equals(Object object) {
        if (object instanceof Generalization == false)
            return false;
        return this.source.equals(((Generalization) object).getSource())
            && this.target.equals(((Generalization) object).getTarget());
    }
}