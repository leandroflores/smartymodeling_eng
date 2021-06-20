package style.association.types;

import com.mxgraph.util.mxConstants;
import java.util.Map;
import style.association.StyleAssociation;

/**
 * <p>Class of Style <b>StyleActivityAssociation</b>.</p>
 * <p>Class responsible for defining the <b>Activity Association Style</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-13
 * @see    model.structural.diagram.activity.base.association.FlowUML
 * @see    style.association.StyleAssociation
 */
public class StyleActivityAssociation extends StyleAssociation {
    
    /**
     * Method responsible for setting the Flow Style.
     * @param style Edge Style.
     */
    public void setFlowStyle(Map style) {
        setDefault(style);
        style.put(mxConstants.STYLE_DASHED,   "0");
        style.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_OPEN);
    }
}