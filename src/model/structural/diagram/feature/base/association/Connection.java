package model.structural.diagram.feature.base.association;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.association.Association;
import model.structural.diagram.feature.base.Feature;

/**
 * <p>Class of Model <b>Connection</b>.</p>
 * <p>Class responsible for representing <b>Connection</b> in SMartyModeling.</p>
 * @author Henrique
 * @since  10/02/2020
 * @see    model.structural.base.association.Association
 * @see    model.structural.diagram.feature.base.Feature
 */
public class Connection extends Association {
    private String category;
    
    /**
     * Default constructor method of Class.
     * @param source Feature.
     * @param target Feature.
     */
    public Connection(Feature source, Feature target) {
        this.source = source;
        this.target = target;
        this.type   = "feature";
    }

    @Override
    public Feature getSource() {
        return (Feature) this.source;
    }

    /**
     * Method responsible for setting the Source.
     * @param source Feature.
     */
    public void setSource(Feature source) {
        this.source = source;
    }

    @Override
    public Feature getTarget() {
        return (Feature) this.target;
    }

    /**
     * Method responsible for setting the Target.
     * @param target Feature.
     */
    public void setTarget(Feature target) {
        this.target = target;
    }

    /**
     * Method responsible for returning the Connection Category.
     * @return Connection Category.
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Method responsible for setting the Connection Category.
     * @param category Connection Category.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String getTitle() {
        return this.getCategory();
    }
    
    @Override
    public String getStyleLabel() {
        return "styleFeature";
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_DASHED,   "0");
               style.put(mxConstants.STYLE_EDITABLE, "0");
               style.put(mxConstants.STYLE_ENDSIZE,  "15");
               style.put(mxConstants.STYLE_FONTSIZE, "15");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_ENDARROW,   mxConstants.ARROW_OPEN);
               style.put(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_SPACING);
               style.put(mxConstants.STYLE_SHAPE,      mxConstants.SHAPE_CONNECTOR);
        return style;
    }
}