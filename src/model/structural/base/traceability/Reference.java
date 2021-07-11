package model.structural.base.traceability;

import java.util.LinkedList;
import java.util.List;
import model.structural.base.Element;
import model.structural.base.interfaces.Exportable;

/**
 * <p>Class of Model <b>Reference</b>.</p>
 * <p>Class responsible for representing the <b>Reference</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-22
 * @see    model.structural.base.Element
 * @see    model.structural.base.interfaces.Exportable
 */
public class Reference implements Exportable {
    private String id;
    private String name;
    private String description;
    private List<Element> elements;
    
     /**
     * Default constructor method of Class.
     */
    public Reference() {
        description = "";
        elements    = new LinkedList<>();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public Reference(org.w3c.dom.Element element) {
        this();
        id   = element.getAttribute("id");
        name = element.getAttribute("name");
    }
    
    /**
     * Method responsible for returning the Reference Id.
     * @return Reference Id.
     */
    public String getId() {
        return id;
    }

    /**
     * Method responsible for defining the Reference Id.
     * @param id Reference Id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method responsible for returning the Reference Name.
     * @return Reference Name.
     */
    public String getName() {
        return name;
    }

    /**
     * Method responsible for defining the Reference Name.
     * @param name Reference Name.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Method responsible for returning the Reference Description.
     * @return Reference Description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method responsible for defining the Reference Description.
     * @param description Reference Description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Method responsible for returning the Reference Elements.
     * @return Reference Elements.
     */
    public List<Element> getElements() {
        return elements;
    }
    
    /**
     * Method responsible for returning the Elements List.
     * @return Elements List.
     */
    public String getElementsList() {
        if (elements.size() == 1)
            return "{" + elements.get(0).getName() + "}";
        String toReturn  = "{" + elements.get(0) + ", \n";
        for (int i = 1; i < elements.size() - 1; i++)
               toReturn += elements.get(i).getName() + ", \n";
        toReturn += elements.get(elements.size() - 1).getName() + "}";
        return toReturn;
    }
    
    /**
     * Method responsible for adding a Element.
     * @param element Element.
     */
    public void addElement(Element element) {
        if (!elements.contains(element))
             elements.add(element);
    }
    
    /**
     * Method responsible for checking if Reference contains an Element.
     * @param  element Element.
     * @return Element is Reference.
     */
    public boolean contains(Element element) {
        return elements.contains(element);
    }
    
    /**
     * Method responsible for removing a Element.
     * @param element Element.
     */
    public void removeElement(Element element) {
        if (elements.contains(element))
            elements.remove(element);
    }
    
    /**
     * Method responsible for checking if Tracebility is Empty.
     * @return Reference is Empty.
     */
    public boolean isEmpty() {
        return elements.isEmpty();
    }
    
    /**
     * Method responsible for defining the Reference Elements.
     * @param elements Reference Elements.
     */
    public void setElements(List<Element> elements) {
        this.elements = elements;
    }
    
    /**
     * Method responsible for returning the Reference Icon.
     * @return Reference Icon.
     */
    public String getIcon() {
        return "icons/traceability/reference.png";
    }
    
    /**
     * Method responsible for exporting the Elements String.
     * @return Elements String.
     */
    private String exportElements() {
        String export  = "";
        for (Element element : elements)
               export += "    <element id=\"" + element.getId() + "\"/>\n";
        return export;
    }
    
    @Override
    public String export() {
        String export  = "  <reference";
               export += " id=\""   + id   + "\"";
               export += " name=\"" + name + "\">\n";
               export += "    <description>" + description + "</description>\n";
               export += exportElements();
               export += "  </reference>\n";
        return export;
    }
    
    @Override
    public String toString() {
        return id + " - " + name;
    }
}