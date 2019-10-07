package model.structural.base.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.structural.base.interfaces.Exportable;

/**
 * <p>Class of Model <b>Product</b>.</p>
 * <p>Class responsible for representing the <b>Product</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  06/10/2019
 * @see    model.structural.base.interfaces.Exportable
 */
public class Product implements Exportable {
    private String  id;
    private String  name;
    private HashMap instances;
    
    /**
     * Default constructor method of Class.
     */
    public Product() {
        this.instances = new HashMap<>();
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
     * @param id Product Id.
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
     * @return Product is Empty.
     */
    public boolean isEmpty() {
        return this.getInstances().isEmpty();
    }
    
    /**
     * Method responsible for returning the Instances List.
     * @return Instances List.
     */
    public HashMap<String, Instance> getInstances() {
        return this.instances;
    }
    
    /**
     * Method responsible for returning the Instances List.
     * @return Instances List.
     */
    public List<Instance> getInstancesList() {
        return new ArrayList<>(this.instances.values());
    }
    
    /**
     * Method responsible for setting the Product Instances.
     * @param instances Product Instances.
     */
    public void setInstances(HashMap<String, Instance> instances) {
        this.instances = instances;
    }
    
    /**
     * Method responsible for exporting the Instances.
     * @return Instances.
     */
    private String exportInstances() {
        String export  = "";
        for (Instance instance : this.getInstancesList())
               export += instance.export();
        return export;
    }
        
    @Override
    public String export() {
        String export  = "    <product id=\"" + this.id + "\" name=\"" + this.name + "\">\n";
               export += this.exportInstances();
               export += "    </product>\n";
        return export;
    }
    
    @Override
    public String toString() {
        return this.id + " - " + this.name;
    }
}