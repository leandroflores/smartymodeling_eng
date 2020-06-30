package style.association.types;

import com.mxgraph.util.mxConstants;
import java.util.Map;
import style.association.StyleAssociation;

/**
 * <p>Class of Style <b>StyleFeatureAssociation</b>.</p>
 * <p>Class responsible for defining the <b>Feature Association Style</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-13
 * @see    model.structural.diagram.feature.base.association.Connection
 * @see    style.association.StyleAssociation
 */
public class StyleFeatureAssociation extends StyleAssociation {
    
    /**
     * Method responsible for setting the Connection Style.
     * @param style Edge Style.
     * @param oval Oval Flag.
     * @param fill Fill Flag.
     */
    public void setConnectionStyle(Map style, boolean oval, boolean fill) {
        this.setDefault(style);
        style.put(mxConstants.STYLE_DASHED, "0");
        style.put(mxConstants.STYLE_ENDFILL,   fill ? "1" : "0");
        style.put(mxConstants.STYLE_STARTFILL, fill ? "1" : "0");
        style.put(mxConstants.STYLE_ENDSIZE,   oval ? "10" : "15");
        style.put(mxConstants.STYLE_STARTSIZE, oval ? "10" : "15");
        style.put(mxConstants.STYLE_ENDARROW,   oval ? mxConstants.ARROW_OVAL : mxConstants.ARROW_BLOCK);
    }
    
    /**
     * Method responsible for setting the Combination Style.
     * @param style Edge Style.
     */
    public void setCombinationStyle(Map style) {
        this.setDefault(style);
    }
}