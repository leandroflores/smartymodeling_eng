package model.controller.structural.base;

import java.util.ArrayList;
import java.util.List;
import model.structural.base.Diagram;
import model.structural.base.Project;
import model.structural.base.Stereotype;
import model.structural.base.product.Product;

/**
 * <p>Class of Controller <b>ControllerProject</b>.</p>
 * <p>Class responsible for defining the <b>Project Controller</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  31/05/2019
 * @see    model.structural.base.Project
 */
public class ControllerProject {
    private final Project project;
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     */
    public ControllerProject(Project project) {
        this.project = project;
    }
    
    /**
     * Method responsible for returning the Diagrams Array.
     * @return Diagrams Array.
     */
    public Diagram[] getDiagrams() {
        List<Diagram> list  = this.project.getDiagramsList();
        Diagram[]     array = new Diagram[list.size()];
        for (int i = 0; i < list.size(); i++)
                array[i] = list.get(i);
        return  array;
    }
    
    /**
     * Method responsible for returning the Targets Array by Type.
     * @param  type Target Type.
     * @return Targets Array.
     */
    public Object[] getTargets(String type) {
        Object[] diagrams   = this.getDiagrams(type);
        Object[] targets    = new Object[diagrams.length + 1];
                 targets[0] = "Project";
        System.arraycopy(diagrams, 0, targets, 1, diagrams.length);
        return   targets;
    }
    
    /**
     * Method responsible for returning the Diagrams Array by Type.
     * @param  type Diagram Type.
     * @return Diagrams Array.
     */
    public Object[] getDiagrams(String type) {
        List<Diagram> filter = new ArrayList<>();
        for (Diagram diagram : this.project.getDiagramsList()) {
            if (diagram.getType().equals(type))
                filter.add(diagram);
        }
        return filter.toArray();
    }
    
    /**
     * Method responsible for returning the Stereotypes Array.
     * @return Stereotypes Array.
     */
    public Stereotype[] getAllStereotypes() {
        List<Stereotype> list  = this.project.getStereotypesList();
        Stereotype[]     array = new Stereotype[list.size()];
        for (int i = 0; i < list.size(); i++)
                 array[i] = list.get(i);
        return   array;
    }
    
    /**
     * Method responsible for returning the Stereotypes Array.
     * @return Stereotypes Array.
     */
    public Stereotype[] getStereotypes() {
        List<Stereotype> list  = this.project.getStereotypesList(false);
        Stereotype[]     array = new Stereotype[list.size()];
        for (int i = 0; i < list.size(); i++)
                 array[i] = list.get(i);
        return   array;
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
}