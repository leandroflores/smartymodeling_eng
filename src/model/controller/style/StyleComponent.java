package model.controller.style;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Class of Style <b>StyleComponent</b>.</p>
 * <p>Class responsible for defining the <b>Component Style</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-10-04
 * @see    model.structural.diagram.component.base.ComponentUML
 */
public class StyleComponent {
    
    /**
     * Method responsible for returning the Image Component Style.
     * @return Image Component Style.
     */
    public Map getImageComponentStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_MOVABLE,   0);
               style.put(mxConstants.STYLE_EDITABLE,  0);
               style.put(mxConstants.STYLE_RESIZABLE, 0);
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_IMAGE);
               style.put(mxConstants.STYLE_IMAGE, "/images/diagram/component/component.png");
        return style;
    }
}