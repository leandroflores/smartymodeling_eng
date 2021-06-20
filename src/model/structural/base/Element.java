package model.structural.base;

import funct.FunctString;
import java.awt.Point;
import java.util.Objects;
import model.structural.base.interfaces.Exportable;
import model.structural.base.interfaces.Modelable;

/**
 * <p>Class of Model <b>Element</b>.</p>
 * <p>Class responsible for representing the <b>Element</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-20
 * @see    model.structural.base.interfaces.Exportable
 * @see    model.structural.base.interfaces.Modelable
 */
public abstract class Element implements Exportable, Modelable {
    protected Diagram diagram;
    protected String  id;
    protected String  name;
    protected String  type;
    protected boolean mandatory;
    protected Point   position;
    protected Point   global;
    protected Point   size;
    
    /**
     * Default constructor method of Class.
     * @param diagram Diagram.
     */
    public Element(Diagram diagram) {
        super();
        this.diagram   = diagram;
        this.mandatory = true;
        this.position  = new Point(0, 0);
        this.global    = new Point(0, 0);
        this.size      = new Point(0, 0);
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     * @param diagram Diagram.
     */
    public Element(org.w3c.dom.Element element, Diagram diagram) {
        this(diagram);
        this.id        = element.getAttribute("id");
        this.name      = element.getAttribute("name");
        this.type      = element.getAttribute("type");
        this.mandatory = element.getAttribute("mandatory").contains("true");
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     * @param diagram Diagram.
     * @param position Reading Coordinates.
     */
    public Element(org.w3c.dom.Element element, Diagram diagram, boolean position) {
        this(element, diagram);
        setPosition(element);
        setGlobalPosition(element);
        setSize(element);
    }
    
    /**
     * Method responsible for returning the Diagram Element.
     * @return Diagram Element.
     */
    public Diagram getDiagram() {
        return diagram;
    }

    /**
     * Method responsible for returning the Diagram Type.
     * @return Diagram Type.
     */
    public String getDiagramType() {
        return diagram.getType();
    }
    
    /**
     * Method responsible for setting the Diagram Element.
     * @param diagram Diagram Element.
     */
    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }
    
    /**
     * Method responsible for returning the Element Id.
     * @return Element Id.
     */
    public String getId() {
        return id;
    }

    /**
     * Method responsible for defining the Element Id.
     * @param id Element Id.
     */
    public void setId(String id) {
        this.id = this.id == null ? id : this.id;
    }

    /**
     * Method responsible for returning the Element Name.
     * @return Element Name.
     */
    public String getName() {
        return name;
    }

    /**
     * Method responsible for returning the HTML Name.
     * @return HTML Name.
     */
    public String getHTMLCode() {
        String html  = "<html>";
        for (String string : name.split(""))
               html += string + "<br>";
        return html += "</html>";
    }
    
    /**
     * Method responsible for defining Default Name.
     */
    public void setDefaultName() {
        if ((id != null) && (id.contains("#")))
            setName(new FunctString().getInitUpperCase(id.replaceAll("#", "").replaceAll("-", "")));
    }
    
    /**
     * Method responsible for defining the Element Name.
     * @param name Element Name.
     */
    public void setName(String name) {
        String string = new FunctString().getString(name);
        this.name     = string.isEmpty() ? this.name : string;
    }
    
    /**
     * Method responsible for returning the Element Type.
     * @return Element Type.
     */
    public String getType() {
        return type;
    }
    
    /**
     * Method responsible for defining the Element Type.
     * @param type Element Type.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Method responsible for returning the Element Mandatory.
     * @return Element Mandatory.
     */
    public boolean isMandatory() {
        return mandatory;
    }

    /**
     * Method responsible for returning Stereotype Flag.
     * @return Stereotype Flag.
     */
    public boolean allowStereotype() {
        return true;
    }
    
    /**
     * Method responsible for defining the Element Mandatory.
     * @param mandatory Element Mandatory.
     */
    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }
    
    /**
     * Method responsible for returning the Element Default Flag.
     * @return Element Default Flag.
     */
    public boolean isDefault() {
        return !getType().toLowerCase().trim().equals("attribute")
            && !getType().toLowerCase().trim().equals("method")
            && !getType().toLowerCase().trim().equals("package")
            && !getType().toLowerCase().trim().equals("initial")
            && !getType().toLowerCase().trim().equals("final");
    }
    
    /**
     * Method responsible for returning the Element Position.
     * @return Element Position.
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Method responsible for returning the Element X Position.
     * @return Element X Position.
     */
    public Integer getX() {
        return position.x;
    }
    
    /**
     * Method responsible for calculating Position Shift X.
     * @param distance Distance.
     */
    public void dx(Integer distance) {
        position.x = Math.max(0, position.x + distance);
    }
    
    /**
     * Method responsible for returning the Element Y Position.
     * @return Element Y Position.
     */
    public Integer getY() {
        return position.y;
    }
    
    /**
     * Method responsible for calculating Position Shift Y.
     * @param distance Distance.
     */
    public void dy(Integer distance) {
        position.y = Math.max(0, position.y + distance);
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
        setPosition(new Point(x.intValue(), y.intValue()));
    }
    
    /**
     * Method responsible for defining the Element Position.
     * @param x X Position.
     * @param y Y Position.
     */
    public void setPosition(Integer x, Integer y) {
        position = new Point(x, y);
    }
    
    /**
     * Method responsible for defining the Element Position.
     * @param position Element Position.
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * Method responsible for returning the Element Global Position.
     * @return Element Global Position.
     */
    public Point getGlobalPosition() {
        return global;
    }
    
    /**
     * Method responsible for defining the Global Position Point.
     * @param  element W3C Element.
     */
    private void setGlobalPosition(org.w3c.dom.Element element) {
        Double x = 0.0d;
        Double y = 0.0d;
        try {
            x = Double.parseDouble(element.getAttribute("globalX"));
            y = Double.parseDouble(element.getAttribute("globalY"));
        }catch (NumberFormatException exception) {}
        setGlobalPosition(new Point(x.intValue(), y.intValue()));
    }
    
    /**
     * Method responsible for defining the Element Global Position.
     * @param x X Position.
     * @param y Y Position.
     */
    public void setGlobalPosition(Integer x, Integer y) {
        global = new Point(x, y);
    }
    
    /**
     * Method responsible for setting the Element Global Position.
     * @param global Element Global Position.
     */
    public void setGlobalPosition(Point global) {
        this.global = global;
    }
    
    /**
     * Method responsible for returning the Element X Global Position.
     * @return Element X Global Position.
     */
    public Integer getGlobalX() {
        return global.x;
    }
    
    /**
     * Method responsible for calculating Global Position Shift X.
     * @param distance Distance.
     */
    public void dxGlobal(Integer distance) {
        global.x = Math.max(0, global.x + distance);
    }
    
    /**
     * Method responsible for setting the Element X Global Position.
     * @param x Element X Global Position.
     */
    public void setGlobalX(Integer x) {
        global.x = x;
    }
    
    /**
     * Method responsible for returning the Element Y Global Position.
     * @return Element Y Global Position.
     */
    public Integer getGlobalY() {
        return global.y;
    }
    
    /**
     * Method responsible for calculating Global Position Shift Y.
     * @param distance Distance.
     */
    public void dyGlobal(Integer distance) {
        global.y = Math.max(0, global.y + distance);
    }
    
    /**
     * Method responsible for setting the Element Y Global Position.
     * @param y Element Y Global Position.
     */
    public void setGlobalY(Integer y) {
        global.y = y;
    }
    
    /**
     * Method responsible for returning the X Center.
     * @return X Center.
     */
    public Integer getXCenter() {
        return getX() + (getWidth() / 2);
    }
    
    /**
     * Method responsible for returning the Y Center.
     * @return Y Center.
     */
    public Integer getYCenter() {
        return getY() + (getHeight() / 2);
    }
    
    /**
     * Method responsible for returning the Element Size.
     * @return Element Size.
     */
    public Point getSize() {
        return size;
    }

    /**
     * Method responsible for returning the Element Height.
     * @return Element Height.
     */
    public Integer getHeight() {
        return size.y;
    }
    
    /**
     * Method responsible for updating the Element Height.
     * @param height Element Height.
     */
    public void setHeight(Integer height) {
        size.y = height;
    }
    
    /**
     * Method responsible for returning the Element Width.
     * @return Element Width.
     */
    public Integer getWidth() {
        return size.x;
    }
    
    /**
     * Method responsible for updating the Element Width.
     * @param width Element Width.
     */
    public void setWidth(Integer width) {
        size.x = width;
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
        setSize(new Point(width.intValue(), height.intValue()));
    }
    
    /**
     * Method responsible for defining the Element Position.
     * @param width Element Width.
     * @param height Element Height.
     */
    public void setSize(Integer width, Integer height) {
        size = new Point(height, width);
    }
    
    /**
     * Method responsible for returning the Element Size.
     * @param size Element Size.
     */
    public void setSize(Point size) {
        this.size = size;
    }
    
    @Override
    public String getTitle() {
        return name;
    }
    
    /**
     * Method responsible for returning the Element Abstract.
     * @return Element Abstract.
     */
    public String getAbstract() {
        return "[" + getId() + "] " + getName();
    }
    
    /**
     * Method responsibel for returning the Image Default Folder.
     * @return Image Default Folder.
     */
    protected String getFolder() {
        return "icons/diagram/";
    }
    
    /**
     * Method responsible for returning the Element Icon.
     * @return Element Icon.
     */
    public abstract String getIcon();
    
    /**
     * Method responsible for returning the Element Summary.
     * @return Element Summary.
     */
    public String getSummary() {
        return new FunctString().getInitUpperCase(type) + ": " + name;
    }
    
    @Override
    public String export() {
        String export  = "    <"         + type;
               export += " id=\""        + id           + "\"";
               export += " name=\""      + name         + "\"";
               export += " mandatory=\"" + mandatory    + "\"";
               export += " x=\""         + getX()       + "\"";
               export += " y=\""         + getY()       + "\"";
               export += " globalX=\""   + getGlobalX() + "\"";
               export += " globalY=\""   + getGlobalY() + "\"";
               export += " height=\""    + getHeight()  + "\"";
               export += " width=\""     + getWidth()   + "\"";
               export += "/>\n";
        return export;
    }
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Element))
            return false;
        return Objects.equals(id, ((Element) object).getId());
    }

    @Override
    public int hashCode() {
        int    hash = 5;
               hash = 61 * hash + Objects.hashCode(id);
               hash = 61 * hash + Objects.hashCode(type);
        return hash;
    }
    
    @Override
    public String toString() {
        return getSummary();
    }
}