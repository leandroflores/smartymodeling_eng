package model.structural.diagram.classs.base.association;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import model.structural.base.association.Association;
import model.structural.diagram.classs.Entity;
import org.w3c.dom.Element;

/**
 * <p>Class of Model <b>AssociationUML</b>.</p>
 * <p>Class responsible for representing <b>Association UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  03/06/2019
 * @see    model.structural.base.association.Association
 * @see    model.structural.diagram.classs.Entity
 */
public class AssociationUML extends Association {
    private String  name;
    private String  category;
    private boolean direction;
    private String  sourceName;
    private Integer sourceMin;
    private Integer sourceMax;
    private String  targetName;
    private Integer targetMin;
    private Integer targetMax;
    
    /**
     * Default constructor method of Class.
     * @param source Source Entity.
     * @param target Target Entity.
     */
    public AssociationUML(Entity source, Entity target) {
        this.source    = source;
        this.target    = target;
        this.type      = "association";
        this.name      = "";
        this.category  = "normal";
        this.direction = false;
        this.setDefault();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public AssociationUML(Element element) {
        this.type      = "association";
        this.name      = element.getAttribute("name");
        this.category  = element.getAttribute("category");
        this.direction = element.getAttribute("direction").contains("true");
        this.setDefault();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param source Source Entity.
     * @param target Target Entity.
     * @param category Association Category.
     * @param direction Association Direction Flag.
     */
    public AssociationUML(Entity source, Entity target, String category, boolean direction) {
        this.source    = source;
        this.target    = target;
        this.type      = "association";
        this.name      = "";
        this.category  = category;
        this.direction = direction;
        this.setDefault();
    }
    
    @Override
    public String getId() {
        return this.category.toUpperCase().trim() + "#" + this.source.getId() + "-" + this.target.getId();
    }

    /**
     * Method responsible for setting the Default Parameters.
     */
    private void setDefault() {
        this.setDefaultSource();
        this.setDefaultTarget();
    }
    
    @Override
    public Entity getSource() {
        return (Entity) this.source;
    }

    /**
     * Method responsible for setting the Source.
     * @param source Source.
     */
    public void setSource(Entity source) {
        this.source = source;
    }

    @Override
    public Entity getTarget() {
        return (Entity) this.target;
    }

    /**
     * Method responsible for setting the Target.
     * @param target Target.
     */
    public void setTarget(Entity target) {
        this.target = target;
    }

    /**
     * Method responsible for returning the Name.
     * @return Name.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Method responsible for setting the Name.
     * @param name Name.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Method responsible for returning the Category.
     * @return Category.
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Method responsible for setting the Category.
     * @param category Category.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Method responsible for returning the Direction Flag.
     * @return Direction Flag.
     */
    public boolean isDirection() {
        return this.direction;
    }

    /**
     * Method responsible for setting the Direction Flag.
     * @param direction Direction Flag.
     */
    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    /**
     * Method responsible for setting the Source by W3C Element.
     * @param element W3C Element.
     */
    public void setSource(Element element) {
        this.setName(element.getAttribute("name"));
        
    }
    
    /**
     * Method responsible for returning the Source Name.
     * @return Source Name.
     */
    public String getSourceName() {
        return this.sourceName;
    }

    /**
     * Method responsible for setting the Source Name.
     * @param sourceName Source Name.
     */
    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    /**
     * Method responsible for returning the Source Min.
     * @return Source Min.
     */
    public Integer getSourceMin() {
        return this.sourceMin;
    }

    /**
     * Method responsible for setting the Source Min.
     * @param sourceMin Source Min.
     */
    public void setSourceMin(Integer sourceMin) {
        this.sourceMin = sourceMin;
    }
    
    /**
     * Method responsible for setting the Source Min by W3C Element.
     * @param element W3C Element.
     */
    public void setSourceMin(Element element) {
        String value   = element.getAttribute("min").trim();
        this.sourceMin = (value.equals("*")) ? 0 : Integer.parseInt(value);
    }

    /**
     * Method responsible for returning the Source Max.
     * @return Source Max.
     */
    public Integer getSourceMax() {
        return this.sourceMax;
    }

    /**
     * Method responsible for setting the Source Max.
     * @param sourceMax Source Max.
     */
    public void setSourceMax(Integer sourceMax) {
        this.sourceMax = sourceMax;
    }
    
    /**
     * Method responsible for setting the Source Max by W3C Element.
     * @param element W3C Element.
     */
    public void setSourceMax(Element element) {
        String value   = element.getAttribute("max").trim();
        this.sourceMax = (value.equals("*")) ? Integer.MAX_VALUE : Integer.parseInt(value);
    }

    /**
     * Method responsible for setting the Default Source.
     */
    public void setDefaultSource() {
        this.sourceName = "";
        this.sourceMin  = 1;
        this.sourceMax  = 1;
    }
    
    /**
     * Method responsible for returning the Target Name.
     * @return Target Name.
     */
    public String getTargetName() {
        return this.targetName;
    }

    /**
     * Method responsible for setting the Target Name.
     * @param targetName Target Name.
     */
    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    /**
     * Method responsible for returning the Target Min.
     * @return Target Min.
     */
    public Integer getTargetMin() {
        return this.targetMin;
    }

    /**
     * Method responsible for setting the Target Min.
     * @param targetMin Target Min.
     */
    public void setTargetMin(Integer targetMin) {
        this.targetMin = targetMin;
    }
    
    /**
     * Method responsible for setting the Target Min by W3C Element.
     * @param element W3C Element.
     */
    public void setTargetMin(Element element) {
        String value   = element.getAttribute("min").trim();
        this.targetMin = (value.equals("*")) ? 0 : Integer.parseInt(value);
    }

    /**
     * Method responsible for returning the Target Max.
     * @return Target Max.
     */
    public Integer getTargetMax() {
        return this.targetMax;
    }

    /**
     * Method responsible for setting the Target Max.
     * @param targetMax Target Max.
     */
    public void setTargetMax(Integer targetMax) {
        this.targetMax = targetMax;
    }
    
    /**
     * Method responsible for setting the Target Max by W3C Element.
     * @param element W3C Element.
     */
    public void setTargetMax(Element element) {
        String value   = element.getAttribute("max").trim();
        this.targetMax = (value.equals("*")) ? Integer.MAX_VALUE : Integer.parseInt(value);
    }
    
    /**
     * Method responsible for setting the Default Target.
     */
    public void setDefaultTarget() {
        this.targetName = "";
        this.targetMin  = 1;
        this.targetMax  = 1;
    }
    
    /**
     * Method responsible for returning the Stroke Color.
     * @return Stroke Color.
     */
    private String getStrokeColor() {
        return (this.category.toLowerCase().trim().equals("aggregation")) ? "#A9A9A9" : "#000000";
    }
    
    /**
     * Method responsible for returning the Start Arrow.
     * @return Start Arrow.
     */
    private Object getStartArrow() {
        return (this.category.toLowerCase().trim().equals("normal")) ? mxConstants.ARROW_SPACING : mxConstants.ARROW_DIAMOND;
    }
    
    /**
     * Method responsible for returning the End Arrow.
     * @return End Arrow.
     */
    private Object getEndArrow() {
        return (this.direction) ? mxConstants.ARROW_OPEN : mxConstants.ARROW_SPACING;
    }
    
    @Override
    public String getTitle() {
        return this.name;
    }
    
    @Override
    public String getStyleLabel() {
        return "styleAssociationUML" + this.getId();
    }
    
    @Override
    public String export() {
        String export  = "    <" + this.type;
               export += " source=\""    + this.source.getId()  + "\"";
               export += " target=\""    + this.target.getId()  + "\"";
               export += " name=\""      + this.name.trim()     + "\"";
               export += " category=\""  + this.category.trim() + "\"";
               export += " direction=\"" + this.direction       + "\"";
               export += "/>\n";
        return export;
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_DASHED,    "0");
               style.put(mxConstants.STYLE_MOVABLE,   "0");
               style.put(mxConstants.STYLE_EDITABLE,  "1");
               style.put(mxConstants.STYLE_FONTCOLOR, "#000000");
               style.put(mxConstants.STYLE_STARTSIZE, "15");
               style.put(mxConstants.STYLE_ENDSIZE,   "15");
               style.put(mxConstants.STYLE_ENDARROW,    this.getEndArrow());
               style.put(mxConstants.STYLE_STARTARROW,  this.getStartArrow());
               style.put(mxConstants.STYLE_STROKECOLOR, this.getStrokeColor());
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CONNECTOR);
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
        if (objeto instanceof AssociationUML == false)
            return false;
        return this.source.equals(((AssociationUML) objeto).getSource())
            && this.target.equals(((AssociationUML) objeto).getTarget());
    }
}