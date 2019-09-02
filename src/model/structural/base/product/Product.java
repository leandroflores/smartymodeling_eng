package model.structural.base.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.structural.base.Element;
import model.structural.base.association.Association;
import model.structural.base.interfaces.Exportable;
import model.structural.base.variability.Mutex;
import model.structural.base.variability.Requires;

/**
 * <p>Class of Model <b>Product</b>.</p>
 * <p>Class responsible for representing the <b>Product</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  21/08/2019
 * @see    model.structural.base.interfaces.Exportable
 */
public class Product implements Exportable {
    private String  id;
    private String  name;
    private HashMap identifiers;
    private HashMap elements;
    private HashMap associations;
    
    /**
     * Default constructor method of Class.
     */
    public Product() {
        this.identifiers  = new HashMap<>();
        this.elements     = new HashMap<>();
        this.associations = new HashMap<>();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public Product(org.w3c.dom.Element element) {
        this();
        this.id   = element.getAttribute("id");
        this.name = element.getAttribute("name");
    }
    
    /**
     * Method responsible for returning the Product Id.
     * @return Product Id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Method responsible for setting the Product Id.
     * @param id Product Id..
     */
    public void setId(String id) {
        this.id = id;
    }
   
    /**
     * Method responsible for returning the Product Name.
     * @return Product Name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method responsible for setting the Product Name.
     * @param name Product Name.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Method responsible for returning if Product is Empty.
     * @return 
     */
    public boolean isEmpty() {
        return this.getElementsList().isEmpty();
    }
    
    /**
     * Method responsible for returning the Identifiers List.
     * @return Identifiers List.
     */
    public HashMap<String, Integer> getIdentifiers() {
        return this.identifiers;
    }
    
    /**
     * Method responsible for setting the Product Identifiers.
     * @param identifiers Product Identifiers.
     */
    public void setIdentifiers(HashMap<String, Integer> identifiers) {
        this.identifiers = identifiers;
    }
    
    /**
     * Method responsible for returning if Product contains a Element.
     * @param  element Element.
     * @return Product contains Element.
     */
    public boolean contains(Element element) {
        return this.elements.get(element.getId()) != null;
    }
    
    /**
     * Method responsible for removing a Element.
     * @param  element Element.
     */
    public void remove(Element element) {
        this.removeAssociations(element);
        this.elements.remove(element.getId());
    }
    
    /**
     * Method responsible for removing the Association by Element.
     * @param element Element.
     */
    public void removeAssociations(Element element) {
        for (Association association : this.getAssociationsList()) {
            if (association.contains(element))
                this.associations.remove(association.getId());
        }
    }
    
    /**
     * Method responsible for returning the Product Elements.
     * @return Product Elements.
     */
    public HashMap<String, Element> getElements() {
        return this.elements;
    }
    
    /**
     * Method responsible for returning the Elements List.
     * @return Elements List.
     */
    public List<Element> getElementsList() {
        return new ArrayList<>(this.elements.values());
    }
    
    /**
     * Method responsible for setting the Product Elements.
     * @param elements Product Elements.
     */
    public void setElements(HashMap<String, Element> elements) {
        this.elements = (HashMap) elements.clone();
    }
    
    /**
     * Method responsible for returning the Product Associations.
     * @return Product Associations.
     */
    public HashMap<String, Association> getAssociations() {
        return this.associations;
    }
    
    /**
     * Method responsible for removing a Association.
     * @param  association Association.
     */
    public void remove(Association association) {
        this.associations.remove(association.getId());
    }
    
    /**
     * Method responsible for returning the Associations List.
     * @return Associations List.
     */
    public List<Association> getAssociationsList() {
        return new ArrayList<>(this.associations.values());
    }
    
    /**
     * Method responsible for setting the Product Associations.
     * @param associations Product Associations.
     */
    public void setAssociations(HashMap<String, Association> associations) {
        this.associations = (HashMap) associations.clone();
    }
    
    /**
     * Method responsible for updating the Product Elements and Associations.
     */
    public void update() {
        this.updateElements();
        this.removeAssociations();
        this.updateAssociations();
    }
    
    /**
     * Method responsible for updating the Elements.
     */
    private void updateElements() {
        for (Element element : this.getElementsList()) {
            if (this.getIdentifiers().get(element.getId()) <= 0)
                this.elements.remove(element.getId());
        }
    }
    
    /**
     * Method responsible for removing the Mutex and Requires Associations.
     */
    private void removeAssociations() {
        for (Association association : this.getAssociationsList()) {
            if (association instanceof Requires
             || association instanceof Mutex)
                this.associations.remove(association.getId());
        }
    }
    
    /**
     * Method responsible for updating the Associations.
     */
    private void updateAssociations() {
        for (Association association : this.getAssociationsList()) {
            if ((this.elements.get(association.getSource().getId()) == null)
             || (this.elements.get(association.getTarget().getId()) == null))
                this.associations.remove(association.getId());
        }
    }
        
    /**
     * Method responsible for exporting the Elements.
     * @return Elements.
     */
    private String exportElements() {
        String export  = "";
        for (Element element : this.getElementsList())
               export += "      <element id=\"" + element.getId() + "\"/>\n";
        return export;
    }
    
    /**
     * Method responsible for exporting the Associations.
     * @return Associations.
     */
    private String exportAssociations() {
        String export  = "";
        for (Association association : this.getAssociationsList())
               export += "      <association id=\"" + association.getId() + "\"/>\n";
        return export;
    }
        
    @Override
    public String export() {
        String export  = "    <product id=\"" + this.id + "\" name=\"" + this.name + "\">\n";
               export += this.exportElements();
               export += this.exportAssociations();
               export += "    </product>\n";
        return export;
    }
    
    @Override
    public String toString() {
        return this.id + " - " + this.name;
    }
}