package style.association;

import com.mxgraph.util.mxConstants;
import java.util.Map;

/**
 * <p>Class of Style <b>StyleAssociation</b>.</p>
 * <p>Class responsible for defining the <b>Association Style</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-13
 * @see    model.structural.base.association.Dependency
 * @see    model.structural.base.association.Generalization
 */
public class StyleAssociation {
    
    /**
     * Method responsible for setting the Default Style.
     * @param style Edge Style.
     */
    protected void setDefault(Map style) {
        style.put(mxConstants.STYLE_MOVABLE,  "0");
        style.put(mxConstants.STYLE_EDITABLE, "0");
        style.put(mxConstants.STYLE_ENDFILL,   "1");
        style.put(mxConstants.STYLE_FONTSIZE,  "15");
        style.put(mxConstants.STYLE_ENDSIZE,   "15");
        style.put(mxConstants.STYLE_STARTSIZE, "15");
        style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
        style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
        style.put(mxConstants.STYLE_SHAPE,       mxConstants.SHAPE_CONNECTOR);
        style.put(mxConstants.STYLE_ENDARROW,    mxConstants.ARROW_SPACING);
        style.put(mxConstants.STYLE_STARTARROW,  mxConstants.ARROW_SPACING);
    }
    
    /**
     * Method responsible for setting the Default Style.
     * @param style Edge Style.
     */
    public void setDefaultStyle(Map style) {
        setDefault(style);
        style.put(mxConstants.STYLE_DASHED, "0");
    }
    
    /**
     * Method responsible for setting the Dependency Style.
     * @param style Edge Style.
     */
    public void setDependencyStyle(Map style) {
        setDefault(style);
        style.put(mxConstants.STYLE_DASHED, "1");
        style.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_OPEN);
    }
    
    /**
     * Method responsible for setting the Generalization Style.
     * @param style Edge Style.
     */
    public void setGeneralizationStyle(Map style) {
        setDefault(style);
        style.put(mxConstants.STYLE_DASHED,   "0");
        style.put(mxConstants.STYLE_ENDFILL,  "0");
        style.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_BLOCK);
    }
}