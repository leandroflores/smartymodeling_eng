package model.controller;

import java.util.Arrays;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.Stereotype;
import model.structural.base.evaluation.Metric;
import model.structural.base.product.Instance;
import model.structural.base.product.Product;
import model.structural.base.requirement.Requirement;
import model.structural.base.variability.Variability;
import model.structural.diagram.classes.base.TypeUML;

/**
 * <p>Class of Controller <b>Controller</b>.</p>
 * <p>Class responsible for defining the <b>Model Controller</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2021-06-13
 * @see    model.structural.base.Project
 */
public abstract class Controller {
    protected final Project project;
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     */
    public Controller(Project project) {
        this.project = project;
    }
    
    /**
     * Method responsible for returning the Elements Array.
     * @return Elements Array.
     */
    public Element[] getElements() {
        Object[] array = project.getElementsList().toArray();
        return Arrays.copyOf(array, array.length, Element[].class);
    }
    
    /**
     * Method responsible for returning the Default Elements Array.
     * @return Default Elements Array.
     */
    public Element[] getDefaultElements() {
        Object[] array = project.getDefaultElements().toArray();
        return Arrays.copyOf(array, array.length, Element[].class);
    }
    
    /**
     * Method responsible for returning the Associations Array.
     * @return Associations Array.
     */
    public Object[] getAssociations() {
        return project.getAssociationsList().toArray();
    }
    
    /**
     * Method responsible for returning the Types Array.
     * @return Types Array.
     */
    public TypeUML[] getTypes() {
        Object[] array = project.getTypesList().toArray();
        return Arrays.copyOf(array, array.length, TypeUML[].class);
    }
    
    /**
     * Method responsible for returning the Diagrams Array.
     * @return Diagrams Array.
     */
    public Diagram[] getDiagrams() {
        Object[] array = project.getDiagramsList().toArray();
        return Arrays.copyOf(array, array.length, Diagram[].class);
    }
    
    /**
     * Method responsible for returning the Diagrams Array by Type.
     * @param  type Diagram Type.
     * @return Diagrams Array.
     */
    public Object[] getDiagrams(String type) {
        Object[] array = project.getDiagrams(type).toArray();
        return Arrays.copyOf(array, array.length, Diagram[].class);
    }
    
    /**
     * Method responsible for returning the Variabilities Array.
     * @return Variabilities Array.
     */
    public Variability[] getVariabilities() {
        Object[] array = project.getVariabilitiesList().toArray();
        return Arrays.copyOf(array, array.length, Variability[].class);
    }
    
    /**
     * Method responsible for returning the Products Array.
     * @return Products Array.
     */
    public Product[] getProducts() {
        Object[] array = project.getProductsList().toArray();
        return Arrays.copyOf(array, array.length, Product[].class);
    }
    
    /**
     * Method responsible for returning the Instances Array.
     * @return Instances Array.
     */
    public Instance[] getInstances() {
        Object[] array = project.getInstancesList().toArray();
        return Arrays.copyOf(array, array.length, Instance[].class);
    }
    
    /**
     * Method responsible for returning the Instances Array by Type.
     * @param  type Instance Type.
     * @return Instances Array by Type.
     */
    public Instance[] getInstances(String type) {
        Object[] array = project.getInstances(type).toArray();
        return Arrays.copyOf(array, array.length, Instance[].class);
    }
    
    /**
     * Method responsible for returning the Requirements Array.
     * @return Requirements Array.
     */
    public Requirement[] getRequirements() {
        Object[] array = project.getRequirementsList().toArray();
        return Arrays.copyOf(array, array.length, Requirement[].class);
    }
    
    /**
     * Method responsible for returning the Metrics Array.
     * @return Metrics Array.
     */
    public Metric[] getMetrics() {
        Object[] array = project.getMetricsList().toArray();
        return Arrays.copyOf(array, array.length, Metric[].class);
    }
    
    /**
     * Method responsible for returning the Stereotypes Array.
     * @return Stereotypes Array.
     */
    public Stereotype[] getStereotypes() {
        Object[] array = project.getStereotypesList().toArray();
        return Arrays.copyOf(array, array.length, Stereotype[].class);
    }
    
    /**
     * Method responsible for returning the Stereotypes Array by Primitive Flag.
     * @param  primitive Primitive Flag.
     * @return Stereotypes Array.
     */
    public Stereotype[] getStereotypes(boolean primitive) {
        Object[] array = project.getStereotypesList(primitive).toArray();
        return Arrays.copyOf(array, array.length, Stereotype[].class);
    }
}