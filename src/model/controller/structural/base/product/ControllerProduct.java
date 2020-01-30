package model.controller.structural.base.product;

import java.util.List;
import model.structural.base.Project;
import model.structural.base.product.Instance;
import model.structural.base.product.Product;

/**
 * <p>Class of Controller <b>ControllerProduct</b>.</p>
 * <p>Class responsible for defining the <b>Product Controller</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  29/01/2020
 * @see    model.structural.base.product.Product
 */
public class ControllerProduct {
    private final Project project;
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     */
    public ControllerProduct(Project project) {
        this.project = project;
    }
    
    /**
     * Method responsible for returning the Products Array.
     * @return Products Array.
     */
    public Product[] getProducts() {
        List<Product> list = this.project.getProductsList();
        Product[] products = new Product[list.size()];
        for (int i = 0; i < list.size(); i++)
                  products[i] = list.get(i);
        return    products;
    }
    
    /**
     * Method responsible for returning the Instances Array by Type.
     * @param  type Instance Type.
     * @return Instances Array.
     */
    public Instance[] getInstances(String type) {
        List<Instance> list  = this.project.getInstances(type);
        Instance[] instances = new Instance[list.size()];
        for (int i = 0; i < list.size(); i++)
               instances[i] = list.get(i);
        return instances;
    }
}