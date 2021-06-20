package style.association.types;

import com.mxgraph.util.mxConstants;
import java.util.Map;
import style.association.StyleAssociation;

/**
 * <p>Class of Style <b>StyleClassAssociation</b>.</p>
 * <p>Class responsible for defining the <b>Class Association Style</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-13
 * @see    model.structural.diagram.classes.base.association.AssociationUML
 * @see    model.structural.diagram.classes.base.association.RealizationUML
 * @see    style.association.StyleAssociation
 */
public class StyleClassAssociation extends StyleAssociation {
    
    /**
     * Method responsible for setting the Association Style.
     * @param style Edge Style.
     * @param direction Direction Flag.
     */
    public void setAssociationStyle(Map style, boolean direction) {
        setDefault(style);
        style.put(mxConstants.STYLE_DASHED,   "0");
        style.put(mxConstants.STYLE_ENDARROW, direction ? mxConstants.ARROW_OPEN : mxConstants.ARROW_SPACING);
    }
    
    /**
     * Method responsible for setting the Aggregation Style.
     * @param style Edge Style.
     * @param direction Direction Flag.
     */
    public void setAggregationStyle(Map style, boolean direction) {
        setDefault(style);
        style.put(mxConstants.STYLE_DASHED, "0");
        style.put(mxConstants.STYLE_STARTFILL,  "0");
        style.put(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_DIAMOND);
        style.put(mxConstants.STYLE_ENDARROW, direction ? mxConstants.ARROW_OPEN : mxConstants.ARROW_SPACING);
    }
    
    /**
     * Method responsible for setting the Composition Style.
     * @param style Edge Style.
     * @param direction Direction Flag.
     */
    public void setCompositionStyle(Map style, boolean direction) {
        setAggregationStyle(style, direction);
        style.put(mxConstants.STYLE_STARTFILL, "1");
    }
    
    /**
     * Method responsible for setting the Realization Style.
     * @param style Edge Style.
     */
    public void setRealizationStyle(Map style) {
        setDefault(style);
        style.put(mxConstants.STYLE_DASHED, "1");
        style.put(mxConstants.STYLE_ENDFILL,  "0");
        style.put(mxConstants.STYLE_ENDSIZE,  "10");
        style.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_BLOCK);
    }
}