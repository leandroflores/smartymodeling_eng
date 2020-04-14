package style.association.types;

import com.mxgraph.util.mxConstants;
import java.util.Map;
import model.structural.base.Element;
import style.association.StyleAssociation;

/**
 * <p>Class of Style <b>StyleSequenceAssociation</b>.</p>
 * <p>Class responsible for defining the <b>Sequence Association Style</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-13
 * @see    model.structural.diagram.sequence.base.association.MessageUML
 * @see    style.association.StyleAssociation
 */
public class StyleSequenceAssociation extends StyleAssociation {
    
    /**
     * Method responsible for returning the Icon Style.
     * @param  element Element.
     * @return Icon Style.
     */
    public String getIconStyle(Element element) {
        return element.getType().equalsIgnoreCase("instance") ? 
               "classIconStyle" : 
               "actorIconStyle";
    }
    
    /**
     * Method responsible for setting the Message Style.
     * @param style Edge Style.
     * @param synchronous Synchronous Flag.
     */
    public void setMessageStyle(Map style, boolean synchronous) {
        this.setDefault(style);
        style.put(mxConstants.STYLE_DASHED,   "0");
        style.put(mxConstants.STYLE_ENDARROW, synchronous ? mxConstants.ARROW_OPEN : mxConstants.ARROW_SPACING);
    }
}