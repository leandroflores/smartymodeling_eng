package model.structural.base.product;

import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxPoint;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import model.structural.base.association.Association;
import model.structural.base.interfaces.Exportable;
import model.structural.base.interfaces.Modelable;
import model.structural.diagram.classes.base.association.AssociationUML;
import org.w3c.dom.Element;

/**
 * <p>Class of Model <b>Relationship</b>.</p>
 * <p>Class responsible for representing the <b>Relationship</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-08
 * @see    model.structural.base.interfaces.Exportable
 * @see    model.structural.base.interfaces.Modelable
 */
public class Relationship implements Exportable, Modelable {
    private String id;
    private Instance instance;
    private Association association;
    private Point sourcePos;
    private Point targetPos;
    private List<mxPoint> points;
    
    /**
     * Default constructor method of Class.
     */
    public Relationship() {
        this.association  = null;
        this.sourcePos    = new Point(0, 0);
        this.targetPos    = new Point(0, 0);
        this.points       = new ArrayList<>();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param instance Instance.
     * @param association Association.
     */
    public Relationship(Instance instance, Association association) {
        this.instance    = instance;
        this.association = association;
        updatePositions(association);
        points = new ArrayList<>(association.getPoints());
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public Relationship(org.w3c.dom.Element element) {
        id = element.getAttribute("id");
        setSourcePosition(element);
        setTargetPosition(element);
        points = new ArrayList<>();
    }
    
    /**
     * Method responsible for updating the Positions.
     * @param association Association.
     */
    private void updatePositions(Association association) {
        if (association instanceof AssociationUML) {
            setSourcePosition(new Point(((AssociationUML) association).getSourceX(), 
                                        ((AssociationUML) association).getSourceY()));
            setTargetPosition(new Point(((AssociationUML) association).getTargetX(), 
                                        ((AssociationUML) association).getTargetY()));
        }else {
            sourcePos = new Point(0, 0);
            targetPos = new Point(0, 0);
        }
    }
    
    /**
     * Method responsible for returning the Relationship Id.
     * @return Relationship Id.
     */
    public String getId() {
        return id;
    }

    /**
     * Method responsible for defining the Relationship Id.
     * @param id Relationship Id.
     */
    public void setId(String id) {
        this.id = ((this.id == null) || (this.id.trim().equals(""))) ? id : this.id;
    }

    /**
     * Method responsible for returning the Relationship Instance.
     * @return Relationship Instance.
     */
    public Instance getInstance() {
        return instance;
    }

    /**
     * Method responsible for setting the Relationship Instance.
     * @param instance Relationship Instance.
     */
    public void setInstance(Instance instance) {
        this.instance = instance;
    }
    
    /**
     * Method responsible for returning the Relationship Association.
     * @return Relationship Association.
     */
    public Association getAssociation() {
        return association;
    }

    /**
     * Method responsible for updating the Positions.
     */
    public void updatePositions() {
        if (association instanceof AssociationUML) {
            if ((getSourceX().equals(0) && (getSourceY().equals(0))))
                setSourcePosition(new Point(((AssociationUML) association).getSourcePosition()));
            if ((getTargetX().equals(0) && (getTargetY().equals(0))))
                setTargetPosition(new Point(((AssociationUML) association).getTargetPosition()));
        }
    }
    
    /**
     * Method responsible for defining the Relationship Association.
     * @param association Relationship Association.
     */
    public void setAssociation(Association association) {
        this.association = association;
        updatePositions();
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
     * Method responsible for calculating Position Shift X.
     * @param position Position.
     * @param distance Distance.
     */
    public void dx(Point position, Integer distance) {
        position.x = (position.x + distance < 0) ? 0 : position.x + distance;
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
     * Method responsible for calculating Position Shift Y.
     * @param position Position.
     * @param distance Distance.
     */
    public void dy(Point position, Integer distance) {
        position.y = (position.y + distance < 0) ? 0 : position.y + distance;
    }
    
    /**
     * Method responsible for returning the Relationship Points.
     * @return Relationship Points.
     */
    public List<mxPoint> getPoints() {
        return points;
    }

    /**
     * Method responsible for adding a New Point to Association.
     * @param point New Point.
     */
    public void addPoint(mxPoint point) {
        if (!points.contains(point))
             points.add(point);
    }
    
    /**
     * Method responsible for removing a Point from Association.
     * @param point Point.
     */
    public void removePoint(mxPoint point) {
        points.remove(point);
    }
    
    /**
     * Method responsible for returning the Distance between two Points.
     * @param  pointA A Point.
     * @param  pointB B Point.
     * @return Distance between two Points.
     */
    private Double distance(mxPoint pointA, mxPoint pointB) {
         return Math.sqrt((pointB.getX() - pointA.getX()) 
                        * (pointB.getX() - pointA.getX()) +
                          (pointB.getY() - pointA.getY())
                        * (pointB.getY() - pointA.getY()));
    }
    
    /**
     * Method responsible for returning the Nearest Point between the Association Points.
     * @param  point Point.
     * @return Nearest Point.
     */
    public mxPoint getNearestPoint(mxPoint point) {
        mxPoint nearest  = null;
        Double  distance = Double.MAX_VALUE;
        for (mxPoint current : getPoints()) {
            Double value = distance(point, current);
            if (value    < distance) {
                distance = value;
                nearest  = current;
            }
        }
        return nearest;
    }

    /**
     * Method responsible for setting the Association Points.
     * @param points Association Points.
     */
    public void setPoints(List<mxPoint> points) {
        this.points = (points != null) ? points : new ArrayList();
    }
    
    /**
     * Method responsible for exporting the Association Points.
     * @return Association Points.
     */
    public String exportPoints() {
        String export  = "";
        for (mxPoint point : getPoints())
               export += "          <point x=\"" + point.getX() + "\" y=\"" + point.getY() + "\"/>\n";
        return export;
    }
    
    @Override
    public String getTitle() {
        return association.getTitle();
    }
    
    @Override
    public String getStyleLabel() {
        return association.getStyleLabel();
    }

    @Override
    public Map getStyle() {
        Map    style = new HashMap(association.getStyle());
               style.put(mxConstants.STYLE_EDITABLE, "0");
        return style;
    }
    
    @Override
    public String export() {
        String export  = "        <relationship ";
               export += " id=\""          + id                  + "\"";
               export += " association=\"" + association.getId() + "\"";
               export += " sourceX=\""     + sourcePos.getX()    + "\"";
               export += " sourceY=\""     + sourcePos.getY()    + "\"";
               export += " targetX=\""     + targetPos.getX()    + "\"";
               export += " targetY=\""     + targetPos.getY()    + "\"";
               export += ">\n";
               export += exportPoints();
               export += "        </relationship>\n";
        return export;
    }
    
    @Override
    public boolean equals(Object object) {
        if (object instanceof Relationship == false)
            return false;
        return Objects.equals(id, ((Relationship) object).getId());
    }

    @Override
    public int hashCode() {
        int    hash = 5;
               hash = 61 * hash + Objects.hashCode(id);
        return hash;
    }
    
    @Override
    public String toString() {
        return association.toString();
    }
}