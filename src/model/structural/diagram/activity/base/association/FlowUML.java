package model.structural.diagram.activity.base.association;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.Element;
import model.structural.base.association.Association;

/**
 * <p>Class of Model <b>FlowUML</b>.</p>
 * <p>Class responsible for representing the <b>Flow UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-17
 * @see    model.structural.base.association.Association
 * @see    model.structural.base.Element
 */
public class FlowUML extends Association {
    private String guard;
    private String action;
    private String weight;
    
    /**
     * Default constructor method of Class.
     * @param source Source Element.
     * @param target Target Element.
     */
    public FlowUML(Element source, Element target) {
        this.source = source;
        this.target = target;
        this.guard  = "";
        this.action = "";
        this.weight = "";
        this.type   = "flow";
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public FlowUML(org.w3c.dom.Element element) {
        super(element);
        guard  = element.getAttribute("guard");
        action = element.getAttribute("action");
        weight = element.getAttribute("weight");
        type   = "flow";
    }

    /**
     * Method responsible for returning the Guard.
     * @return Guard.
     */
    public String getGuard() {
        return guard;
    }

    /**
     * Method responsible for returning the Formatted Guard.
     * @return Formatted Guard.
     */
    public String getFormattedGuard() {
        if (!guard.trim().equals(""))
            return "[" + guard + "]";
        return "";
    }
    
    /**
     * Method responsible for setting the Guard.
     * @param guard Guard.
     */
    public void setGuard(String guard) {
        this.guard = guard.replaceAll("\\[", "").replaceAll("\\]", "").trim();
    }

    /**
     * Method responsible for returning the Action.
     * @return Action.
     */
    public String getAction() {
        return action;
    }

    /**
     * Method responsible for returning the Formatted Action.
     * @return Formatted Action.
     */
    public String getFormattedAction() {
        if (!action.trim().equals(""))
            return " / " + action;
        return "";
    }
    
    /**
     * Method responsible for setting the Action.
     * @param action Action.
     */
    public void setAction(String action) {
        this.action = action.replace("\\/", "").trim();
    }

    /**
     * Method responsible for returning the Weight.
     * @return Weight.
     */
    public String getWeight() {
        return weight;
    }
    
    /**
     * Method responsible for returning the Formatted Weight.
     * @return Formatted Weight.
     */
    public String getFormattedWeight() {
        if (!weight.trim().equals(""))
            return " {" + weight + "}";
        return "";
    }

    /**
     * Method responsible for setting the Weight.
     * @param weight Weight.
     */
    public void setWeight(String weight) {
        this.weight = weight.replaceAll("\\{", "").replaceAll("\\}", "").trim();
    }
    
    @Override
    public String getTitle() {
        return (getFormattedGuard() + getFormattedAction() + getFormattedWeight()).trim();
    }
    
    @Override
    public String getStyleLabel() {
        return "styleFlowUML";
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_DASHED,   "0");
               style.put(mxConstants.STYLE_MOVABLE,  "0");
               style.put(mxConstants.STYLE_EDITABLE, "1");
               style.put(mxConstants.STYLE_ENDSIZE,  "15");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_SHAPE,      mxConstants.SHAPE_CONNECTOR);
               style.put(mxConstants.STYLE_ENDARROW,   mxConstants.ARROW_OPEN);
               style.put(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_SPACING);
        return style;
    }
    
    @Override
    protected String exportHeader() {
        String export  = "    <"      + type;
               export += " id=\""     + id.trim()      + "\"";
               export += " source=\"" + source.getId() + "\"";
               export += " target=\"" + target.getId() + "\"";
               export += " guard=\""  + getGuard()     + "\"";
               export += " action=\"" + getAction()    + "\"";
               export += " weight=\"" + getWeight()    + "\"";
               export += ">\n";
        return export;
    }
}