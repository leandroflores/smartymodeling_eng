package model.structural.diagram.classes.base.association;

import com.mxgraph.util.mxConstants;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.association.Association;
import model.structural.diagram.classes.Encodable;
import model.structural.diagram.classes.Entity;
import org.w3c.dom.Element;

/**
 * <p>Class of Model <b>AssociationUML</b>.</p>
 * <p>Class responsible for representing <b>Association UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  03/06/2019
 * @see    model.structural.base.association.Association
 * @see    model.structural.diagram.classes.Encodable
 * @see    model.structural.diagram.classes.Entity
 */
public class AssociationUML extends Association implements Encodable {
    private String  name;
    private String  category;
    private boolean direction;
    private String  sourceName;
    private Integer sourceMin;
    private Integer sourceMax;
    private Point   sourcePos;
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

    /**
     * Method responsible for returning the Complete Id.
     * @return Complete Id.
     */
    public String getCompleteId() {
        return this.category.toUpperCase().trim() + "#" + this.source.getId() + "-" + this.target.getId();
    }
    
    /**
     * Method responsible for setting the Default Parameters.
     */
    private void setDefault() {
        this.setDefaultSource();
        this.setDefaultTarget();
        this.setDefaultPoints();
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
        this.setSourceName(element.getAttribute("sourceName"));
        this.setSourceMin(element);
        this.setSourceMax(element);
        this.setSourcePosition(element);
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
        String value   = element.getAttribute("sourceMin").trim();
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
        String value   = element.getAttribute("sourceMax").trim();
        this.sourceMax = (value.equals("*")) ? Integer.MAX_VALUE : Integer.parseInt(value);
    }
    
    /**
     * Method responsible for returning the Source Position.
     * @return Source Position.
     */
    public Point getSourcePosition() {
        return this.sourcePos;
    }

    /**
     * Method responsible for returning the Source X Position.
     * @return Source X Position.
     */
    public Integer getSourceX() {
        return this.sourcePos.x;
    }
    
    /**
     * Method responsible for calculating Position Shift Source X.
     * @param distance Distance.
     */
    public void dxSource(Integer distance) {
        this.dx(this.sourcePos, distance);
    }
    
    /**
     * Method responsible for returning the Source Y Position.
     * @return Source Y Position.
     */
    public Integer getSourceY() {
        return this.sourcePos.y;
    }
    
    /**
     * Method responsible for calculating Position Shift Source Y.
     * @param distance Distance.
     */
    public void dySource(Integer distance) {
        this.dy(this.sourcePos, distance);
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
        this.setSourcePosition(new Point(x.intValue(), y.intValue()));
    }
    
    /**
     * Method responsible for defining the Source Position.
     * @param x X Position.
     * @param y Y Position.
     */
    public void setSourcePosition(Integer x, Integer y) {
        this.sourcePos = new Point(x, y);
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
        this.sourceName = "";
        this.sourceMin  = 1;
        this.sourceMax  = 1;
        this.sourcePos  = new Point(this.source.getXCenter(), this.source.getYCenter());
    }
    
    /**
     * Method responsible for returning the Source Label.
     * @return Source Label.
     */
    public String getSourceLabel() {
        if (this.sourceMin.equals(0) && this.sourceMax.equals(Integer.MAX_VALUE))
            return "*";
        if (this.sourceMin.equals(this.sourceMax))
            return Integer.toString(this.sourceMin);
        return this.sourceMin + ".." + (this.sourceMax.equals(Integer.MAX_VALUE) ? "*" : this.sourceMax);
    }
    
    /**
     * Method responsible for setting the Target by W3C Element.
     * @param element W3C Element.
     */
    public void setTarget(Element element) {
        this.setTargetName(element.getAttribute("targetName"));
        this.setTargetMin(element);
        this.setTargetMax(element);
        this.setTargetPosition(element);
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
        String value   = element.getAttribute("targetMin").trim();
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
        String value   = element.getAttribute("targetMax").trim();
        this.targetMax = (value.equals("*")) ? Integer.MAX_VALUE : Integer.parseInt(value);
    }
    
    /**
     * Method responsible for returning the Target Position.
     * @return Target Position.
     */
    public Point getTargetPosition() {
        return this.targetPos;
    }

    /**
     * Method responsible for returning the Target X Position.
     * @return Target X Position.
     */
    public Integer getTargetX() {
        return this.targetPos.x;
    }
    
    /**
     * Method responsible for calculating Position Shift Target X.
     * @param distance Distance.
     */
    public void dxTarget(Integer distance) {
        this.dx(this.targetPos, distance);
    }
    
    /**
     * Method responsible for returning the Target Y Position.
     * @return Target Y Position.
     */
    public Integer getTargetY() {
        return this.targetPos.y;
    }
    
    /**
     * Method responsible for calculating Position Shift Target Y.
     * @param distance Distance.
     */
    public void dyTarget(Integer distance) {
        this.dy(this.targetPos, distance);
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
        this.setTargetPosition(new Point(x.intValue(), y.intValue()));
    }
    
    /**
     * Method responsible for defining the Target Position.
     * @param x X Position.
     * @param y Y Position.
     */
    public void setTargetPosition(Integer x, Integer y) {
        this.targetPos = new Point(x, y);
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
        this.targetName = "";
        this.targetMin  = 1;
        this.targetMax  = 1;
        this.targetPos  = new Point(this.target.getXCenter(), this.target.getYCenter());
    }
    
    /**
     * Method responsible for returning the Target Label.
     * @return Target Label.
     */
    public String getTargetLabel() {
        if (this.targetMin.equals(0) && this.targetMax.equals(Integer.MAX_VALUE))
            return "*";
        if (this.targetMin.equals(this.targetMax))
            return Integer.toString(this.targetMin);
        return this.targetMin + ".." + (this.targetMax.equals(Integer.MAX_VALUE) ? "*" : this.targetMax);
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
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_FILLCOLOR,   "#EEEEEE");
               style.put(mxConstants.STYLE_STROKECOLOR, "#EEEEEE");
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
               style.put(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_SHADOW);
        return style;
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
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_DASHED,    "0");
               style.put(mxConstants.STYLE_MOVABLE,   "0");
               style.put(mxConstants.STYLE_EDITABLE,  "0");
               style.put(mxConstants.STYLE_FONTCOLOR, "#000000");
               style.put(mxConstants.STYLE_STARTSIZE, "15");
               style.put(mxConstants.STYLE_ENDSIZE,   "15");
               style.put(mxConstants.STYLE_ENDARROW,    this.getEndArrow());
               style.put(mxConstants.STYLE_STARTARROW,  this.getStartArrow());
               style.put(mxConstants.STYLE_STROKECOLOR, this.getStrokeColor());
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CONNECTOR);
        return style;
    }
    
    /**
     * Method responsible for returning the Cardinality Code.
     * @return Cardinality Code.
     */
    private String getCardinalityCode() {
        return this.targetMax == 1 ? this.target.getName() : "List<" + this.target.getName() + ">";
    }
    
    /**
     * Method responsible for returning the Target Name Code.
     * @return Target Name Code.
     */
    private String getTargetNameCode() {
        if (this.targetName.trim().isEmpty())
            return this.targetMax == 1 ? this.getName().toLowerCase() : this.targetName.toLowerCase() + "s";
        return this.targetName;
    }
    
    /**
     * Method responsible for returning the Source Code.
     * @return Source Code.
     */
    public String getSourceCode() {
        String code = this.exportCode();
        return code;
    }
    
    /**
     * Method responsible for returning the Source Cardinality Code.
     * @return Source Cardinality Code.
     */
    private String getSourceCardinalityCode() {
        return this.targetMax == 1 ? this.target.getName() : "List<" + this.target.getName() + ">";
    }
    
    @Override
    public String exportCode() {
        String code  = "private ";
               code += this.getCardinalityCode() + " ";
//               code += this.getTargetNameCode() + ";";
        return code;
    }
    
    /**
     * Method responsible for exporting the Association Source.
     * @return Association Source.
     */
    public String exportSource() {
        String export  = "      <source";
               export += " entity=\""     + this.source.getId() + "\"";
               export += " sourceName=\"" + this.sourceName     + "\"";
               export += " sourceMin=\""  + this.sourceMin      + "\"";
               export += " sourceMax=\""  + this.sourceMax      + "\"";
               export += " sourceX=\""    + this.getSourceX()   + "\"";
               export += " sourceY=\""    + this.getSourceY()   + "\"/>\n";
        return export;
    }
    
    /**
     * Method responsible for returning the Target.
     * @return Target.
     */
    public String exportTarget() {
        String export  = "      <target";
               export += " entity=\""     + this.target.getId() + "\"";
               export += " targetName=\"" + this.targetName     + "\"";
               export += " targetMin=\""  + this.targetMin      + "\"";
               export += " targetMax=\""  + this.targetMax      + "\"";
               export += " targetX=\""    + this.getTargetX()   + "\"";
               export += " targetY=\""    + this.getTargetY()   + "\"/>\n";
        return export;
    }
    
    @Override
    protected String exportHeader() {
        String export  = "    <"         + this.type;
               export += " id=\""        + this.id.trim()       + "\"";
               export += " name=\""      + this.name.trim()     + "\"";
               export += " category=\""  + this.category.trim() + "\"";
               export += " direction=\"" + this.direction       + "\"";
               export += ">\n";
               export += this.exportSource();
               export += this.exportTarget();
        return export;
    }
}