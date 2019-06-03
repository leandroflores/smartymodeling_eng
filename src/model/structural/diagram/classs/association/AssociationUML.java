package model.structural.diagram.classs.association;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import model.structural.base.association.Association;
import model.structural.diagram.classs.Entity;

/**
 * <p>Class of Model <b>AssociationUML</b>.</p>
 * <p>Class responsible for representing <b>Association UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  03/06/2019
 * @see    model.structural.base.association.Association
 * @see    model.structural.diagram.classs.Entity
 */
public class AssociationUML extends Association {
    private String  category;
    private boolean direction;
    
    /**
     * Default constructor method of Class.
     * @param source Source Entity.
     * @param target Target Entity.
     */
    public AssociationUML(Entity source, Entity target) {
        this.source    = source;
        this.target    = target;
        this.type      = "association";
        this.category  = "normal";
        this.direction = false;
    }
    
    /**
     * Alternative constructor method of Class.
     * @param source Source Entity.
     * @param target Target Entity.
     * @param category Association Category.
     */
    public AssociationUML(Entity source, Entity target, String category) {
        this.source    = source;
        this.target    = target;
        this.type      = "association";
        this.category  = category;
        this.direction = false;
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
    private String getStartArrow() {
        return (this.category.toLowerCase().trim().equals("normal")) ? mxConstants.ARROW_OPEN : mxConstants.ARROW_DIAMOND;
    }
    
    /**
     * Method responsible for returning the End Arrow.
     * @return End Arrow.
     */
    private String getEndArrow() {
        return (this.direction) ? mxConstants.ARROW_OPEN : mxConstants.ARROW_CLASSIC;
    }
    
    @Override
    public String getTitle() {
        return "";
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
               export += " category=\""  + this.category.trim() + "\"";
               export += " direction=\"" + this.direction       + "\"";
               export += "/>\n";
        return export;
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_DASHED,    "1");
               style.put(mxConstants.STYLE_MOVABLE,   "0");
               style.put(mxConstants.STYLE_EDITABLE,  "0");
               style.put(mxConstants.STYLE_FONTCOLOR, "#000000");
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