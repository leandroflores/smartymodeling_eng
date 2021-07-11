package model.structural.diagram.classes.base.association;

import com.mxgraph.util.mxConstants;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.association.Association;
import model.structural.diagram.classes.Entity;
import org.w3c.dom.Element;

/**
 * <p>Class of Model <b>AssociationUML</b>.</p>
 * <p>Class responsible for representing <b>Association UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-03
 * @see    model.structural.base.association.Association
 * @see    model.structural.diagram.classes.Entity
 */
public class AssociationUML extends Association {
    private String  name;
    private String  category;
    private boolean direction;
    private String  sourceVisibility;
    private String  sourceName;
    private Integer sourceMin;
    private Integer sourceMax;
    private Point   sourcePos;
    private String  targetVisibility;
    private String  targetName;
    private Integer targetMin;
    private Integer targetMax;
    private Point   targetPos;
    
    /**
     * Default constructor method of Class.
     * @param source Source Entity.
     * @param target Target Entity.
     */
    public AssociationUML(Entity source, Entity target) {
        super();
        this.source    = source;
        this.target    = target;
        this.name      = "";
        this.category  = "normal";
        this.direction = false;
        this.type      = "association";
        setDefault();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public AssociationUML(Element element) {
        super(element);
        name      = element.getAttribute("name");
        category  = element.getAttribute("category");
        direction = element.getAttribute("direction").contains("true");
        type      = "association";
    }
    
    /**
     * Alternative constructor method of Class.
     * @param source Source Entity.
     * @param target Target Entity.
     * @param category Association Category.
     * @param direction Association Direction Flag.
     */
    public AssociationUML(Entity source, Entity target, String category, boolean direction) {
        super();
        this.source    = source;
        this.target    = target;
        this.type      = "association";
        this.name      = "";
        this.category  = category;
        this.direction = direction;
        setDefault();
    }

    /**
     * Method responsible for returning the Complete Id.
     * @return Complete Id.
     */
    public String getCompleteId() {
        return category.toUpperCase().trim() + "#" + source.getId() + "-" + target.getId();
    }
    
    /**
     * Method responsible for setting the Default Parameters.
     */
    private void setDefault() {
        setDefaultSource();
        setDefaultTarget();
        setDefaultPoints();
    }
    
    @Override
    public Entity getSource() {
        return (Entity) source;
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
        return (Entity) target;
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
        return name;
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
        return category;
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
        return direction;
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
        setSourceVisibility(element.getAttribute("sourceVisibility"));
        setSourceName(element.getAttribute("sourceName"));
        setSourceMin(element);
        setSourceMax(element);
        setSourcePosition(element);
    }
    
    /**
     * Method responsible for returning the Source Visibility.
     * @return Source Visibility.
     */
    public String getSourceVisibility() {
        return sourceVisibility;
    }

    /**
     * Method responsible for setting the Source Visibility.
     * @param sourceVisibility Source Visibility.
     */
    public void setSourceVisibility(String sourceVisibility) {
        this.sourceVisibility = sourceVisibility;
    }
    
    /**
     * Method responsible for returning the Default Source Name.
     * @return Default Source Name.
     */
    public String getDefaultSourceName() {
        return target.getName().toLowerCase() + (targetMax == 1 ? "" : "s");
    }
    
    /**
     * Method responsible for returning the Source Name.
     * @return Source Name.
     */
    public String getSourceName() {
        return sourceName;
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
        return sourceMin;
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
        String value = element.getAttribute("sourceMin").trim();
        sourceMin    = (value.equals("*")) ? 0 : Integer.parseInt(value);
    }

    /**
     * Method responsible for returning the Source Max.
     * @return Source Max.
     */
    public Integer getSourceMax() {
        return sourceMax;
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
        String value = element.getAttribute("sourceMax").trim();
        sourceMax    = (value.equals("*")) ? Integer.MAX_VALUE : Integer.parseInt(value);
    }
    
    /**
     * Method responsible for returning the Source Position.
     * @return Source Position.
     */
    public Point getSourcePosition() {
        return sourcePos;
    }

    /**
     * Method responsible for returning the Source X Position.
     * @return Source X Position.
     */
    public Integer getSourceX() {
        return sourcePos.x;
    }
    
    /**
     * Method responsible for calculating Position Shift Source X.
     * @param distance Distance.
     */
    public void dxSource(Integer distance) {
        dx(sourcePos, distance);
    }
    
    /**
     * Method responsible for returning the Source Y Position.
     * @return Source Y Position.
     */
    public Integer getSourceY() {
        return sourcePos.y;
    }
    
    /**
     * Method responsible for calculating Position Shift Source Y.
     * @param distance Distance.
     */
    public void dySource(Integer distance) {
        dy(sourcePos, distance);
    }
    
    /**
     * Method responsible for defining the Source Position Point.
     * @param  element W3C Element.
     */
    private void setSourcePosition(Element element) {
        Double x = 0.0d;
        Double y = 0.0d;
        try {
            x = Double.parseDouble(element.getAttribute("sourceX"));
            y = Double.parseDouble(element.getAttribute("sourceY"));
        }catch (NumberFormatException exception) {}
        setSourcePosition(new Point(x.intValue(), y.intValue()));
    }
    
    /**
     * Method responsible for defining the Source Position.
     * @param x X Position.
     * @param y Y Position.
     */
    public void setSourcePosition(Integer x, Integer y) {
        sourcePos = new Point(x, y);
    }
    
    /**
     * Method responsible for defining the Source Position.
     * @param sourcePosition Source Position.
     */
    public void setSourcePosition(Point sourcePosition) {
        this.sourcePos = sourcePosition;
    }

    /**
     * Method responsible for setting the Default Source.
     */
    public void setDefaultSource() {
        sourceVisibility = "private";
        sourceName = "";
        sourceMin  = 1;
        sourceMax  = 1;
        sourcePos  = new Point(source.getXCenter(), source.getYCenter());
    }
    
    /**
     * Method responsible for returning the Source Label.
     * @return Source Label.
     */
    public String getSourceLabel() {
        return getCardinalitySourceLabel() + getNameSourceLabel();
    }
    
    /**
     * Method responsible for returning the Cardinality Source Label.
     * @return Cardinality Source Label.
     */
    public String getCardinalitySourceLabel() {
        if (sourceMin.equals(0) && sourceMax.equals(Integer.MAX_VALUE))
            return "*";
        if (sourceMin.equals(sourceMax))
            return Integer.toString(sourceMin);
        return sourceMin + ".." + (sourceMax.equals(Integer.MAX_VALUE) ? "*" : sourceMax);
    }
    
    /**
     * Method responsible for returning the Name Source Label.
     * @return Name Source Label.
     */
    public String getNameSourceLabel() {
        return sourceName.equals("") ? sourceName : " (" + getSourceSignature() + ")";
    }
    
    /**
     * Method responsible for returning the Source Signature.
     * @return Source Signature.
     */
    public String getSourceSignature() {
        return getVisibilitySymbol(sourceVisibility) + " " + sourceName;
    }
    
    /**
     * Method responsible for setting the Target by W3C Element.
     * @param element W3C Element.
     */
    public void setTarget(Element element) {
        setTargetVisibility(element.getAttribute("targetVisibility"));
        setTargetName(element.getAttribute("targetName"));
        setTargetMin(element);
        setTargetMax(element);
        setTargetPosition(element);
    }
    
    /**
     * Method responsible for returning the Target Visibility.
     * @return Target Visibility.
     */
    public String getTargetVisibility() {
        return targetVisibility;
    }

    /**
     * Method responsible for setting the Target Visibility.
     * @param targetVisibility Target Visibility.
     */
    public void setTargetVisibility(String targetVisibility) {
        this.targetVisibility = targetVisibility;
    }
    
    /**
     * Method responsible for returning the Default Target Name.
     * @return Default Target Name.
     */
    public String getDefaultTargetName() {
        return source.getName().toLowerCase() + (sourceMax == 1 ? "" : "s");
    }
    
    /**
     * Method responsible for returning the Target Name.
     * @return Target Name.
     */
    public String getTargetName() {
        return targetName;
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
        return targetMin;
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
        String value = element.getAttribute("targetMin").trim();
        targetMin    = (value.equals("*")) ? 0 : Integer.parseInt(value);
    }

    /**
     * Method responsible for returning the Target Max.
     * @return Target Max.
     */
    public Integer getTargetMax() {
        return targetMax;
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
        String value = element.getAttribute("targetMax").trim();
        targetMax    = (value.equals("*")) ? Integer.MAX_VALUE : Integer.parseInt(value);
    }
    
    /**
     * Method responsible for returning the Target Position.
     * @return Target Position.
     */
    public Point getTargetPosition() {
        return targetPos;
    }

    /**
     * Method responsible for returning the Target X Position.
     * @return Target X Position.
     */
    public Integer getTargetX() {
        return targetPos.x;
    }
    
    /**
     * Method responsible for calculating Position Shift Target X.
     * @param distance Distance.
     */
    public void dxTarget(Integer distance) {
        dx(targetPos, distance);
    }
    
    /**
     * Method responsible for returning the Target Y Position.
     * @return Target Y Position.
     */
    public Integer getTargetY() {
        return targetPos.y;
    }
    
    /**
     * Method responsible for calculating Position Shift Target Y.
     * @param distance Distance.
     */
    public void dyTarget(Integer distance) {
        dy(targetPos, distance);
    }
    
    /**
     * Method responsible for defining the Target Position Point.
     * @param  element W3C Element.
     */
    private void setTargetPosition(org.w3c.dom.Element element) {
        Double x = 0.0d;
        Double y = 0.0d;
        try {
            x = Double.parseDouble(element.getAttribute("targetX"));
            y = Double.parseDouble(element.getAttribute("targetY"));
        }catch (NumberFormatException exception) {}
        setTargetPosition(new Point(x.intValue(), y.intValue()));
    }
    
    /**
     * Method responsible for defining the Target Position.
     * @param x X Position.
     * @param y Y Position.
     */
    public void setTargetPosition(Integer x, Integer y) {
        targetPos = new Point(x, y);
    }
    
    /**
     * Method responsible for defining the Target Position.
     * @param targetPosition Target Position.
     */
    public void setTargetPosition(Point targetPosition) {
        this.targetPos = targetPosition;
    }
    
    /**
     * Method responsible for setting the Default Target.
     */
    public void setDefaultTarget() {
        targetName = "";
        targetMin  = 1;
        targetMax  = 1;
        targetPos  = new Point(target.getXCenter(), target.getYCenter());
        targetVisibility = "private";
    }
    
    /**
     * Method responsible for returning the Target Label.
     * @return Target Label.
     */
    public String getTargetLabel() {
        return getCardinalityTargetLabel() + getNameTargetLabel();
    }
    
    /**
     * Method responsible for returning the Cardinality Target Label.
     * @return Cardinality Target Label.
     */
    public String getCardinalityTargetLabel() {
        if (targetMin.equals(0) && targetMax.equals(Integer.MAX_VALUE))
            return "*";
        if (targetMin.equals(targetMax))
            return Integer.toString(targetMin);
        return targetMin + ".." + (targetMax.equals(Integer.MAX_VALUE) ? "*" : targetMax);
    }
    
    /**
     * Method responsible for returning the Target Source Label.
     * @return Name Target Label.
     */
    public String getNameTargetLabel() {
        return targetName.equals("") ? targetName : " (" + getTargetSignature() + ")";
    }
    
    /**
     * Method responsible for returning the Target Signature.
     * @return Target Signature.
     */
    public String getTargetSignature() {
        return getVisibilitySymbol(targetVisibility) + " " + targetName;
    }
    
    /**
     * Method responsible for returning the Visibility Symbol.
     * @param  visibility Visibility.
     * @return Visibility Symbol.
     */
    private String getVisibilitySymbol(String visibility) {
        if (visibility.trim().equalsIgnoreCase("public"))
            return "+";
        if (visibility.trim().equalsIgnoreCase("protected"))
            return "#";
        if (visibility.trim().equalsIgnoreCase("default"))
            return "~";
        return "-";
    }
    
    /**
     * Method responsible for calculating Position Shift X.
     * @param position Position.
     * @param distance Distance.
     */
    public void dx(Point position, Integer distance) {
        position.x = (position.x + distance < 0) ? 0 : position.x + distance;
    }
    
    /**
     * Method responsible for calculating Position Shift Y.
     * @param position Position.
     * @param distance Distance.
     */
    public void dy(Point position, Integer distance) {
        position.y = (position.y + distance < 0) ? 0 : position.y + distance;
    }
    
    /**
     * Method responsible for returning the Start Arrow.
     * @return Start Arrow.
     */
    private Object getStartArrow() {
        return (category.toLowerCase().trim().equals("normal")) ? mxConstants.ARROW_SPACING : mxConstants.ARROW_DIAMOND;
    }
    
    /**
     * Method responsible for returning the Start Fill.
     * @return Start Fill.
     */
    private Object getStartFill() {
        return (category.equalsIgnoreCase("aggregation")) ? "0" : "1";
    }
    
    /**
     * Method responsible for returning the End Arrow.
     * @return End Arrow.
     */
    private Object getEndArrow() {
        return (direction) ? mxConstants.ARROW_OPEN : mxConstants.ARROW_SPACING;
    }
    
    /**
     * Method responsible for returning the Cardinality Style Label.
     * @return Cardinality Style Label.
     */
    public String getCardinalityLabel() {
        return "styleCardinality";
    }
    
    /**
     * Method responsible for returning the Cardinality Style Map.
     * @return Cardinality Style Map.
     */
    public Map getCardinalityStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_MOVABLE,   "1");
               style.put(mxConstants.STYLE_EDITABLE,  "1");
               style.put(mxConstants.STYLE_FOLDABLE,  "0");
               style.put(mxConstants.STYLE_FONTSIZE,  "10");
               style.put(mxConstants.STYLE_RESIZABLE, "0");
               style.put(mxConstants.STYLE_FONTCOLOR, "#000000");
               style.put(mxConstants.STYLE_FILLCOLOR,   mxConstants.NONE);
               style.put(mxConstants.STYLE_STROKECOLOR, mxConstants.NONE);
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
               style.put(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_SHADOW);
        return style;
    }
    
    @Override
    public String getTitle() {
        return name;
    }
    
    @Override
    public String getStyleLabel() {
        return "styleAssociationUML" + getId();
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_DASHED,    "0");
               style.put(mxConstants.STYLE_STARTSIZE, "15");
               style.put(mxConstants.STYLE_ENDSIZE,   "15");
               style.put(mxConstants.STYLE_EDITABLE,  "0");
               style.put(mxConstants.STYLE_MOVABLE,   "0");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_ENDARROW,    getEndArrow());
               style.put(mxConstants.STYLE_STARTARROW,  getStartArrow());
               style.put(mxConstants.STYLE_STARTFILL,   getStartFill());
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CONNECTOR);
        return style;
    }
    
    /**
     * Method responsible for returning the Source Code.
     * @return Source Code.
     */
    public String getSourceCode() {
        String code  = getSourceVisibility() + " ";
               code += getSourceCardinalityCode() + " ";
               code += getSourceNameCode()   + ";";
        return code;
    }
    
    /**
     * Method responsible for returning the Source Cardinality Code.
     * @return Source Cardinality Code.
     */
    private String getSourceCardinalityCode() {
        return targetMax == 1 ? target.getName() : "List<" + target.getName() + ">";
    }
    
    /**
     * Method responsible for returning the Source Name Code.
     * @return Source Name Code.
     */
    private String getSourceNameCode() {
        return targetName.trim().isEmpty() ? getDefaultSourceName() : targetName;
    }
    
    /**
     * Method responsible for returning the Target Code.
     * @return Target Code.
     */
    public String getTargetCode() {
        String code  = getTargetVisibility() + " ";
               code += getTargetCardinalityCode() + " ";
               code += getTargetNameCode()   + ";";
        return code;
    }
    
    /**
     * Method responsible for returning the Target Cardinality Code.
     * @return Target Cardinality Code.
     */
    private String getTargetCardinalityCode() {
        return sourceMax == 1 ? source.getName() : "List<" + source.getName() + ">";
    }
    
    /**
     * Method responsible for returning the Target Name Code.
     * @return Target Name Code.
     */
    private String getTargetNameCode() {
        return sourceName.trim().isEmpty() ? getDefaultTargetName() : sourceName;
    }
    
    /**
     * Method responsible for exporting the Association Source.
     * @return Association Source.
     */
    public String exportSource() {
        String export  = "      <source";
               export += " entity=\""           + source.getId()   + "\"";
               export += " sourceVisibility=\"" + sourceVisibility + "\"";
               export += " sourceName=\""       + sourceName       + "\"";
               export += " sourceMin=\""        + sourceMin        + "\"";
               export += " sourceMax=\""        + sourceMax        + "\"";
               export += " sourceX=\""          + getSourceX()     + "\"";
               export += " sourceY=\""          + getSourceY()     + "\"/>\n";
        return export;
    }
    
    /**
     * Method responsible for returning the Target.
     * @return Target.
     */
    public String exportTarget() {
        String export  = "      <target";
               export += " entity=\""           + target.getId()   + "\"";
               export += " targetVisibility=\"" + targetVisibility + "\"";
               export += " targetName=\""       + targetName       + "\"";
               export += " targetMin=\""        + targetMin        + "\"";
               export += " targetMax=\""        + targetMax        + "\"";
               export += " targetX=\""          + getTargetX()     + "\"";
               export += " targetY=\""          + getTargetY()     + "\"/>\n";
        return export;
    }
    
    @Override
    protected String exportHeader() {
        String export  = "    <"         + type;
               export += " id=\""        + id.trim()       + "\"";
               export += " name=\""      + name.trim()     + "\"";
               export += " category=\""  + category.trim() + "\"";
               export += " direction=\"" + direction       + "\"";
               export += ">\n";
               export += exportSource();
               export += exportTarget();
        return export;
    }
}