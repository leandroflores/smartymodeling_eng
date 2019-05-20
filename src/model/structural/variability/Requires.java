package model.structural.variability;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import model.structural.Association;
import model.structural.Element;

/**
 * <p>Class of Model <b>Requires</b>.</p>
 * <p>Class responsible for representing <b>Requires</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  20/05/2019
 * @see    model.structural.Association
 * @see    model.structural.Element
 */
public class Requires extends Association {
    
    /**
     * Default constructor method of Class.
     * @param source Source Element.
     * @param target Target Element.
     */
    public Requires(Element source, Element target) {
        this.source  = source;
        this.target = target;
        this.type    = "requires";
    }

    @Override
    public String getTitle() {
        return "<<requires>>";
    }

    @Override
    public String getStyleLabel() {
        return "styleRequires";
    }

    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_DASHED, "1");
               style.put(mxConstants.STYLE_EDITABLE, "1");
               style.put(mxConstants.STYLE_MOVABLE, "0");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_FONTCOLOR, "#000000");
               style.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_OPEN);
               style.put(mxConstants.STYLE_EDITABLE, "0");
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
        if (objeto instanceof Requires == false)
            return false;
        return this.source.equals(((Requires)  objeto).getSource())
            && this.target.equals(((Requires) objeto).getTarget());
    }
}