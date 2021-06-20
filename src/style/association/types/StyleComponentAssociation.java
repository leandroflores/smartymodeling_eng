package style.association.types;

import com.mxgraph.util.mxConstants;
import java.util.Map;
import style.association.StyleAssociation;

/**
 * <p>Class of Style <b>StyleComponentAssociation</b>.</p>
 * <p>Class responsible for defining the <b>Component Association Style</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-13
 * @see    model.structural.diagram.component.base.association.ComunicationUML
 * @see    style.association.StyleAssociation
 */
public class StyleComponentAssociation extends StyleAssociation {
    
    /**
     * Method responsible for setting the Provide Style.
     * @param style Edge Style.
     */
    public void setProvideStyle(Map style) {
        setDefault(style);
        style.put(mxConstants.STYLE_DASHED, "0");
    }
    
    /**
     * Method responsible for setting the Require Style.
     * @param style Edge Style.
     */
    public void setRequireStyle(Map style) {
        setDefault(style);
        style.put(mxConstants.STYLE_DASHED, "1");
        style.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_OPEN);
    }
}