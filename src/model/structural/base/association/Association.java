package model.structural.base.association;

import com.mxgraph.util.mxPoint;
import funct.FunctString;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.structural.base.Element;
import model.structural.base.interfaces.Exportable;
import model.structural.base.interfaces.Modelable;

/**
 * <p>Class of Model <b>Association</b>.</p>
 * <p>Class responsible for representing <b>Association</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-20
 * @see    model.structural.base.Element
 * @see    model.structural.base.interfaces.Exportable
 * @see    model.structural.base.interfaces.Modelable
 */
public abstract class Association implements Modelable, Exportable {
    protected String  id;
    protected Element source;
    protected Element target;
    protected String  type;
    protected List    points;
    
    /**
     * Default constructor method of Class.
     */
    public Association() {
        points = new ArrayList();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public Association(org.w3c.dom.Element element) {
        this();
        id = element.getAttribute("id");
    }
    
    /**
     * Method responsible for returning the Association Id.
     * @return Association Id.
     */
    public String getId() {
        return id;
    }

    /**
     * Method responsible for defining the Association Id.
     * @param id Association Id.
     */
    public void setId(String id) {
        this.id = (this.id == null || this.id.trim().equals("")) ? id : this.id;
    }
    
    /**
     * Method responsible for checking if a Element is Source.
     * @param  element Element.
     * @return Element is Source.
     */
    public boolean isSource(Element element) {
        return source.equals(element);
    }
    
    /**
     * Method responsible for checking if a String Name is Source.
     * @param  name String Name.
     * @return String Name is Source.
     */
    public boolean isSource(String name) {
        return source.getName().equalsIgnoreCase(name);
    }
    
    /**
     * Method responsible for checking if a Element is Target.
     * @param  element Element.
     * @return Element is Target.
     */
    public boolean isTarget(Element element) {
        return target.equals(element);
    }
    
    /**
     * Method responsible for checking if a String Name is Target.
     * @param  name String Name.
     * @return String Name is Target.
     */
    public boolean isTarget(String name) {
        return target.getName().equalsIgnoreCase(name);
    }
    
    /**
     * Method responsible for checking if a Element is part of Association.
     * @param  element Element.
     * @return Element is part of Association.
     */
    public boolean contains(Element element) {
        return isSource(element) || isTarget(element);
    }
    
    /**
     * Method responsible for checking if a String Name is part of Association.
     * @param  name String Name.
     * @return String Name is part of Association.
     */
    public boolean contains(String name) {
        return isSource(name) || isTarget(name);
    }
            
    /**
     * Method responsible for returning the Source Element.
     * @return Source Element.
     */
    public Element getSource() {
        return source;
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
        return target;
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
        return type;
    }

    /**
     * Method responsible for returning the Association Points.
     * @return Association Points.
     */
    public List<mxPoint> getPoints() {
        return points;
    }
    
    /**
     * Method responsible for adding the Default Points in a Self Message.
     * @param pointA Point A.
     * @param pointB Point B.
     */
    public void addDefaultPoint(mxPoint pointA, mxPoint pointB) {
        if (points.isEmpty()) {
            addPoint(pointA);
            addPoint(pointB);
        }
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
     * Method responsible for setting the Default Points.
     */
    public void setDefaultPoints() {
        points = new ArrayList();
        if (source.equals(target))
            setLoopPoints();
    }
    
    /**
     * Method responsible for calculating Position Shift X.
     * @param distance Distance.
     */
    public void dx(Integer distance) {
        if (points != null) {
            for (mxPoint point : getPoints())
                point.setX(getValue(new Double(point.getX()).intValue(), distance));
        }
    }
    
    /**
     * Method responsible for calculating Position Shift Y.
     * @param distance Distance.
     */
    public void dy(Integer distance) {
        if (points != null) {
            for (mxPoint point : getPoints())
                point.setY(getValue(new Double(point.getY()).intValue(), distance));
        }
    }
    
    /**
     * Method responsibla for returning the New Coordinate Value.
     * @param  old Old Value.
     * @param  distance Distance.
     * @return New Coordinate Value.
     */
    private Integer getValue(Integer old, Integer distance) {
        Integer value = old + distance;
        return  value > 0 ? value : 0;
    }
    
    /**
     * Method responsible for setting the Loop Points.
     */
    public void setLoopPoints() {
        points.add(new mxPoint(source.getGlobalX() + source.getWidth() + 150, source.getGlobalY() + 20));
        points.add(new mxPoint(source.getGlobalX() + source.getWidth() + 150, source.getGlobalY() + 70));
    }
    
    /**
     * Method responsible for setting the Association Points.
     * @param points Association Points.
     */
    public void setPoints(List<mxPoint> points) {
        this.points = (points != null) ? points : new ArrayList();
    }
    
    /**
     * Method responsible for returning the Association Summary.
     * @return Association Summary.
     */
    public String getSummary() {
        return new FunctString().getInitUpperCase(type) + ": " + source.getName() + " - " + target.getName();
    }
    
    /**
     * Method responsible for exporting the Association Points.
     * @return Association Points.
     */
    public String exportPoints() {
        String export  = "";
        for (mxPoint point : getPoints())
               export += "      <point x=\"" + point.getX() + "\" y=\"" + point.getY() + "\"/>\n";
        return export;
    }
    
    /**
     * Method responsible for exporting the Association Header.
     * @return Association Header.
     */
    protected String exportHeader() {
        String header  = "    <"      + type;
               header += " id=\""     + id             + "\"";
               header += " source=\"" + source.getId() + "\"";
               header += " target=\"" + target.getId() + "\"";
               header += ">\n";
        return header;
    }
    
    /**
     * Method responsible for exporting the Association Body.
     * @return Association Body.
     */
    protected String exportBody() {
        return exportPoints();
    }
    
    /**
     * Method responsible for exporting the Association Footer.
     * @return Association Footer.
     */
    protected String exportFooter() {
        return "    </" + type + ">\n";
    }
    
    @Override
    public String export() {
        String export  = exportHeader();
               export += exportBody();
               export += exportFooter();
        return export;
    }
    
    @Override
    public boolean equals(Object object) {
        if (object instanceof Association == false)
            return false;
        return Objects.equals(id, ((Association) object).getId());
    }

    @Override
    public int hashCode() {
        int    hash = 3;
               hash = 41 * hash + Objects.hashCode(id);
               hash = 41 * hash + Objects.hashCode(type);
        return hash;
    }
    
    @Override
    public String toString() {
        return source.getName() + " - " + target.getName();
    }
}