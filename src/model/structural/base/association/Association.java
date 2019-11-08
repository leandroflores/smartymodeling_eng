package model.structural.base.association;

import com.mxgraph.util.mxPoint;
import java.util.ArrayList;
import java.util.List;
import model.structural.base.Element;
import model.structural.base.interfaces.Exportable;
import model.structural.base.interfaces.Modelable;

/**
 * <p>Class of Model <b>Association</b>.</p>
 * <p>Class responsible for representing <b>Association</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  20/05/2019
 * @see    model.structural.base.Element
 * @see    model.structural.base.interfaces.Exportable
 * @see    model.structural.base.interfaces.Modelable
 */
public abstract class Association implements Exportable, Modelable {
    protected Element source;
    protected Element target;
    protected String  type;
    protected List    points;
    
    /**
     * Default constructor method of Class.
     */
    public Association() {
        this.points = new ArrayList();
    }
    
    /**
     * Method responsible for returning the Association Id.
     * @return Association Id.
     */
    public String getId() {
        return this.type.toUpperCase().trim() + "#" + this.source.getId() + "-" + this.target.getId();
    }
    
    /**
     * Method responsible for checking if a Element is Source.
     * @param  element Element.
     * @return Element is Source.
     */
    public boolean isSource(Element element) {
        return this.source.equals(element);
    }
    
    /**
     * Method responsible for checking if a Element is Target.
     * @param  element Element.
     * @return Element is Target.
     */
    public boolean isTarget(Element element) {
        return this.target.equals(element);
    }
    
    /**
     * Method responsible for checking if a Element is part of Association.
     * @param  element Element.
     * @return Element is part of Association.
     */
    public boolean contains(Element element) {
        return    this.isSource(element)
               || this.isTarget(element);
    }
            
    /**
     * Method responsible for returning the Source Element.
     * @return Source Element.
     */
    public Element getSource() {
        return this.source;
    }
    
    /**
     * Method responsible for defining the Source Element.
     * @param source Source Element.
     */
    public void setSource(Element source) {
        this.source = source;
    }
    
    /**
     * Method responsible for returning the Target Element.
     * @return Target Element.
     */
    public Element getTarget() {
        return this.target;
    }
    
    /**
     * Method responsible for defining the Target Element.
     * @param target Target Element.
     */
    public void setTarget(Element target) {
        this.target = target;
    }
    
    /**
     * Method responsible for returning the Association Type.
     * @return Association Type.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Method responsible for returning the Association Points.
     * @return Association Points.
     */
    public List<mxPoint> getPoints() {
        return this.points;
    }
    
    /**
     * Method responsible for adding a New Point to Association.
     * @param point New Point.
     */
    public void addPoint(mxPoint point) {
        if (!this.points.contains(point))
            this.points.add(point);
    }
    
    /**
     * Method responsible for removing a Point from Association.
     * @param point Point.
     */
    public void removePoint(mxPoint point) {
        this.points.remove(point);
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
        for (mxPoint current : this.getPoints()) {
            Double value = this.distance(point, current);
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
        for (mxPoint point : this.getPoints())
               export += "      <point x=\"" + point.getX() + "\" y=\"" + point.getY() + "\"/>\n";
        return export;
    }
    
    @Override
    public String export() {
        String export  = "    <"      + this.type;
               export += " source=\"" + this.source.getId() + "\"";
               export += " target=\"" + this.target.getId() + "\"";
               export += ">";
               export += this.exportPoints();
               export += "    </" + this.type + ">";
        return export;
    }
    
    @Override
    public String toString() {
        return this.source.getName() + " - " + this.target.getName();
    }
}