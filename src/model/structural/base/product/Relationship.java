package model.structural.base.product;

import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxPoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import model.structural.base.association.Association;
import model.structural.base.interfaces.Exportable;
import model.structural.base.interfaces.Modelable;

/**
 * <p>Class of Model <b>Relationship</b>.</p>
 * <p>Class responsible for representing the <b>Relationship</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  08/11/2019
 * @see    model.structural.base.interfaces.Exportable
 * @see    model.structural.base.interfaces.Modelable
 */
public class Relationship implements Exportable, Modelable {
    private String id;
    private Instance instance;
    private Association association;
    private List<mxPoint> points;
    
    /**
     * Default constructor method of Class.
     */
    public Relationship() {
        this.association  = null;
        this.points       = new ArrayList<>();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param instance Instance.
     * @param association Association.
     */
    public Relationship(Instance instance, Association association) {
        this.instance     = instance;
        this.association  = association;
        this.points       = new ArrayList<>(association.getPoints());
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public Relationship(org.w3c.dom.Element element) {
        this.id     = element.getAttribute("id");
        this.points = new ArrayList<>();
    }
    
    /**
     * Method responsible for returning the Relationship Id.
     * @return Relationship Id.
     */
    public String getId() {
        return this.id;
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
        return this.instance;
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
        return this.association;
    }

    /**
     * Method responsible for defining the Relationship Association.
     * @param association Relationship Association.
     */
    public void setAssociation(Association association) {
        this.association = association;
    }
    
    /**
     * Method responsible for returning the Relationship Points.
     * @return Relationship Points.
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
               export += "          <point x=\"" + point.getX() + "\" y=\"" + point.getY() + "\"/>\n";
        return export;
    }
    
    @Override
    public String getTitle() {
        return this.association.getTitle();
    }
    
    @Override
    public String getStyleLabel() {
        return this.association.getStyleLabel();
    }

    @Override
    public Map getStyle() {
        Map    style = new HashMap(this.association.getStyle());
               style.put(mxConstants.STYLE_EDITABLE, "0");
        return style;
    }
    
    @Override
    public String export() {
        String export  = "        <relationship ";
               export += " id=\""          + this.id                  + "\"";
               export += " association=\"" + this.association.getId() + "\"";
               export += ">\n";
               export += this.exportPoints();
               export += "        </relationship>\n";
        return export;
    }
    
    @Override
    public boolean equals(Object object) {
        if (object instanceof Relationship == false)
            return false;
        return Objects.equals(this.id, ((Relationship) object).getId());
    }

    @Override
    public int hashCode() {
        int    hash = 5;
               hash = 61 * hash + Objects.hashCode(this.id);
        return hash;
    }
    
    @Override
    public String toString() {
        return this.association.toString();
    }
}