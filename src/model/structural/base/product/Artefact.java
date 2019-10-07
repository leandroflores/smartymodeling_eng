package model.structural.base.product;

import java.awt.Point;
import java.util.Map;
import java.util.Objects;
import model.structural.base.Element;
import model.structural.base.interfaces.Exportable;
import model.structural.base.interfaces.Modelable;

/**
 * <p>Class of Model <b>Artefact</b>.</p>
 * <p>Class responsible for representing the <b>Artefact</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  06/10/2019
 * @see    model.structural.base.interfaces.Exportable
 * @see    model.structural.base.interfaces.Modelable
 */
public class Artefact implements Exportable, Modelable {
    private String  id;
    private Element element;
    private Point   position;
    private Point   global;
    private Point   size;
    
    /**
     * Default constructor method of Class.
     */
    public Artefact() {
        this.element  = null;
        this.position = new Point(0, 0);
        this.global   = new Point(0, 0);
        this.size     = new Point(0, 0);
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element Element.
     */
    public Artefact(Element element) {
        this.element  = element;
        this.position = new Point(element.getPosition());
        this.global   = new Point(element.getGlobal());
        this.size     = new Point(element.getSize());
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public Artefact(org.w3c.dom.Element element) {
        this();
        this.id = element.getAttribute("id");
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     * @param position Reading Coordinates.
     */
    public Artefact(org.w3c.dom.Element element, boolean position) {
        this(element);
        this.setPosition(element);
        this.setSize(element);
    }
    
    /**
     * Method responsible for returning the Artefact Id.
     * @return Artefact Id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Method responsible for defining the Artefact Id.
     * @param id Artefact Id.
     */
    public void setId(String id) {
        this.id = (this.id == null) ? id : this.id;
    }

    /**
     * Method responsible for returning the Artefact Element.
     * @return Artefact Element.
     */
    public Element getElement() {
        return this.element;
    }

    /**
     * Method responsible for defining the Artefact Element.
     * @param element Artefact Element.
     */
    public void setElement(Element element) {
        this.element = element;
    }
    
    /**
     * Method responsible for returning the Artefact Position.
     * @return Artefact Position.
     */
    public Point getPosition() {
        return this.position;
    }

    /**
     * Method responsible for returning the Element X Position.
     * @return Element X Position.
     */
    public Integer getX() {
        return this.position.x;
    }
    
    /**
     * Method responsible for calculating Position Shift X.
     * @param distance Distance.
     */
    public void dx(Integer distance) {
        if (this.position.x + distance < 0)
            this.position.x  = 0;
        else
            this.position.x += distance;
    }
    
    /**
     * Method responsible for returning the Artefact Y Position.
     * @return Artefact Y Position.
     */
    public Integer getY() {
        return this.position.y;
    }
    
    /**
     * Method responsible for calculating Position Shift Y.
     * @param distance Distance.
     */
    public void dy(Integer distance) {
        if (this.position.y + distance < 0)
            this.position.y  = 0;
        else
            this.position.y += distance;
    }
    
    /**
     * Method responsible for defining the Position Point.
     * @param  element W3C Element.
     */
    private void setPosition(org.w3c.dom.Element element) {
        Double x = 0.0d;
        Double y = 0.0d;
        try {
            x = Double.parseDouble(element.getAttribute("x"));
            y = Double.parseDouble(element.getAttribute("y"));
        }catch (NumberFormatException exception) {}
        this.setPosition(new Point(x.intValue(), y.intValue()));
    }
    
    /**
     * Method responsible for defining the Artefact Position.
     * @param x X Position.
     * @param y Y Position.
     */
    public void setPosition(Integer x, Integer y) {
        this.position = new Point(x, y);
    }
    
    /**
     * Method responsible for defining the Artefact Position.
     * @param position Element Position.
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * Method responsible for returning the Artefact Global Position.
     * @return Artefact Global Position.
     */
    public Point getGlobal() {
        return this.global;
    }
    
    /**
     * Method responsible for setting the Artefact Global Position.
     * @param global Artefact Global Position.
     */
    public void setGlobal(Point global) {
        this.global = global;
    }
    
    /**
     * Method responsible for returning the Artefact X Global Position.
     * @return Artefact X Global Position.
     */
    public Integer getGlobalX() {
        return this.global.x;
    }
    
    /**
     * Method responsible for setting the Artefact X Global Position.
     * @param x Artefact X Global Position.
     */
    public void setGlobalX(Integer x) {
        this.global.x = x;
    }
    
    /**
     * Method responsible for returning the Artefact Y Global Position.
     * @return Artefact Y Global Position.
     */
    public Integer getGlobalY() {
        return this.global.y;
    }
    
    /**
     * Method responsible for setting the Artefact Y Global Position.
     * @param y Artefact Y Global Position.
     */
    public void setGlobalY(Integer y) {
        this.global.y = y;
    }

    /**
     * Method responsible for returning the X Center.
     * @return X Center.
     */
    public Integer getXCenter() {
        return this.getX() + (this.getWidth() / 2);
    }
    
    /**
     * Method responsible for returning the Y Center.
     * @return Y Center.
     */
    public Integer getYCenter() {
        return this.getY() + (this.getHeight() / 2);
    }
    
    /**
     * Method responsible for returning the Artefact Size.
     * @return Artefact Size.
     */
    public Point getSize() {
        return this.size;
    }

    /**
     * Method responsible for returning the Artefact Height.
     * @return Artefact Height.
     */
    public Integer getHeight() {
        return this.size.y;
    }
    
    /**
     * Method responsible for updating the Artefact Height.
     * @param height Artefact Height.
     */
    public void setHeight(Integer height) {
        this.size.y = height;
    }
    
    /**
     * Method responsible for returning the Artefact Width.
     * @return Artefact Width.
     */
    public Integer getWidth() {
        return this.size.x;
    }
    
    /**
     * Method responsible for updating the Artefact Width.
     * @param width Artefact Width.
     */
    public void setWidth(Integer width) {
        this.size.x = width;
    }
    
    /**
     * Method responsible for defining the Size Point.
     * @param  element W3C Element.
     */
    private void setSize(org.w3c.dom.Element element) {
        Double height = 0.0d;
        Double width  = 0.0d;
        try {
            height = Double.parseDouble(element.getAttribute("height"));
            width  = Double.parseDouble(element.getAttribute("width"));
        }catch (NumberFormatException exception) {}
        this.setSize(new Point(width.intValue(), height.intValue()));
    }
    
    /**
     * Method responsible for defining the Artefact Position.
     * @param width Artefact Width.
     * @param height Artefact Height.
     */
    public void setSize(Integer width, Integer height) {
        this.size = new Point(height, width);
    }
    
    /**
     * Method responsible for returning the Artefact Size.
     * @param size Artefact Size.
     */
    public void setSize(Point size) {
        this.size = size;
    }
    
    @Override
    public String getTitle() {
        return this.element.getName();
    }
    
    /**
     * Method responsible for returning the Artefact Abstract.
     * @return Artefact Abstract.
     */
    public String getAbstract() {
        return "[" + this.getId() + "] " + this.element.getName();
    }
    
    /**
     * Method responsible for returning the Artefact Icon.
     * @return Artefact Icon.
     */
    public String getIcon() {
        return this.element.getIcon();
    }
    
    @Override
    public String getStyleLabel() {
        return this.element.getStyleLabel();
    }

    @Override
    public Map getStyle() {
        return this.element.getStyle();
    }
    
    @Override
    public String export() {
        String export  = "        "; 
               export += "<artifact"; 
               export += " id=\""        + this.id          + "\"";
               export += " element=\""   + this.element     + "\"";
               export += " x=\""         + this.getX()      + "\"";
               export += " y=\""         + this.getY()      + "\"";
               export += " height=\""    + this.getHeight() + "\"";
               export += " width=\""     + this.getWidth()  + "\"";
               export += "/>\n";
        return export;
    }
    
    @Override
    public boolean equals(Object object) {
        if (object instanceof Artefact == false)
            return false;
        return Objects.equals(this.id, ((Artefact) object).getId());
    }

    @Override
    public int hashCode() {
        int    hash = 5;
               hash = 61 * hash + Objects.hashCode(this.id);
        return hash;
    }
    
    @Override
    public String toString() {
        return this.element.toString();
    }
}