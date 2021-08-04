package model.structural.base.traceability;

import java.util.LinkedList;
import java.util.List;
import model.structural.base.interfaces.Exportable;
import model.structural.base.requirement.Requirement;

/**
 * <p>Class of Model <b>Traceability</b>.</p>
 * <p>Class responsible for representing the <b>Traceability</b> in SMartyModeling.</p>
 * @author Renan
 * @since  2021-06-21
 * @see    model.structural.base.Element
 * @see    model.structural.base.interfaces.Exportable
 */
public class Traceability implements Exportable {
    private String id;
    private Requirement owner;
    private List<Requirement> destination;
    
    /**
     * Default constructor method of Class.
     */
    public Traceability() {
        destination = new LinkedList<>();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param owner Owner Requirement.
     */
    public Traceability(Requirement owner) {
        this();
        this.owner = owner;
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public Traceability(org.w3c.dom.Element element) {
        this();
        id = element.getAttribute("id");
    }
    
    /**
     * Method responsible for returning the Traceability Id.
     * @return Traceability Id.
     */
    public String getId() {
        return id;
    }

    /**
     * Method responsible for defining the Traceability Id.
     * @param id Traceability Id.
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * Method responsible for returning the Traceability Owner.
     * @return Traceability Owner.
     */
    public Requirement getOwner() {
        return owner;
    }

    /**
     * Method responsible for setting the Traceability Owner.
     * @param owner Traceability Owner.
     */
    public void setOwner(Requirement owner) {
        this.owner = owner;
    }
    
    /**
     * Method responsible for returning the Traceability Elements.
     * @return Traceability Elements.
     */
    public List<Requirement> getDestination() {
        return destination;
    }
    
    /**
     * Method responsible for returning the Elements List.
     * @return Elements List.
     */
    public String getDestinationList() {
        if (destination.size() == 1)
            return "{" + destination.get(0).getName() + "}";
        String toReturn  = "{" + destination.get(0) + ", \n";
        for (int i = 1; i < destination.size() - 1; i++)
               toReturn += destination.get(i).getName() + ", \n";
        toReturn += this.destination.get(destination.size() - 1).getName() + "}";
        return toReturn;
    }
    
    /**
     * Method responsible for adding a Element.
     * @param destination Element.
     */
    public void addDestination(Requirement destination) {
        if (!this.destination.contains(destination))
             this.destination.add(destination);
    }
    
    /**
     * Method responsible for checking if Traceability contains an Element.
     * @param  destination Element.
     * @return Element is Traceability.
     */
    public boolean contains(Requirement destination) {
        return this.destination.contains(destination);
    }
    
    /**
     * Method responsible for removing a Element.
     * @param destination Element.
     */
    public void removeDestination(Requirement destination) {
        if (this.destination.contains(destination))
            this.destination.remove(destination);
    }
    
    /**
     * Method responsible for checking if Tracebility is Empty.
     * @return Traceability is Empty.
     */
    public boolean isEmpty() {
        return destination.isEmpty();
    }
    
    /**
     * Method responsible for defining the Traceability Elements.
     * @param destination Traceability Elements.
     */
    public void setDestination(List<Requirement> destination) {
        this.destination = destination;
    }
    
    /**
     * Method responsible for returning the Traceability Icon.
     * @return Traceability Icon.
     */
    public String getIcon() {
        return "icons/traceability/traceability.png";
    }
    
    /**
     * Method responsible for exporting the Elements String.
     * @return Elements String.
     */
    private String exportDestination() {
        String export  = "";
        for (Requirement requirement : this.destination)
               export += "    <destination id=\"" + requirement.getId() + "\"/>\n";
        return export;
    }
    
    @Override
    public String export() {
        String export  = "  <traceability";
               export += " id=\""   + this.id   + "\"";
               export += " owner=\""   + this.owner.getId()   + "\">\n";
               export += exportDestination();
               export += "  </traceability>\n";
        return export;
    }
    
    @Override
    public String toString() {
        return this.id + " - " + this.owner + " " + this.destination;
    }
}
