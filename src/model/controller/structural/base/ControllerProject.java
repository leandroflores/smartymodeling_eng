package model.controller.structural.base;

import java.util.ArrayList;
import java.util.List;
import model.structural.base.Diagram;
import model.structural.base.Project;
import model.structural.base.Stereotype;
import model.structural.base.requirement.Requirement;

/**
 * <p>Class of Controller <b>ControllerProject</b>.</p>
 * <p>Class responsible for defining the <b>Project Controller</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-31
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
     * Method responsible for returning the Project Targets Array.
     * @return Project Targets Array.
     */
    public Object[] getProjectTargets() {
        Object[] diagrams   = this.getDiagrams();
        Object[] targets    = new Object[diagrams.length + 1];
                 targets[0] = "Project";
        System.arraycopy(diagrams, 0, targets, 1, diagrams.length);
        return   targets;
    }
    
    /**
     * Method responsible for returning the Product Targets Array.
     * @return Product Targets Array.
     */
    public Object[] getProductTargets() {
        Object[] products   = this.getProducts();
        Object[] targets    = new Object[products.length + 1];
                 targets[0] = "Project";
        System.arraycopy(products, 0, targets, 1, products.length);
        return   targets;
    }
    
    /**
     * Method responsible for returning the Diagram Targets Array by Type.
     * @param  type Target Type.
     * @return Diagram Targets Array.
     */
    public Object[] getDiagramTargets(String type) {
        Object[] diagrams   = this.getDiagrams(type);
        Object[] targets    = new Object[diagrams.length + 1];
                 targets[0] = "Project";
        System.arraycopy(diagrams, 0, targets, 1, diagrams.length);
        return   targets;
    }
    
    /**
     * Method responsible for returning the General Context Array.
     * @return General Context Array.
     */
    public Object[] getGeneralContext() {
        Object[] diagrams   = this.getDiagrams();
        Object[] context    = new Object[diagrams.length + 1];
                 context[0] = "Project";
        System.arraycopy(diagrams, 0, context, 1, diagrams.length);
        return   context;
    }
    
    /**
     * Method responsible for returning the Diagrams Array by Type.
     * @param  type Diagram Type.
     * @return Diagrams Array.
     */
    public Object[] getDiagrams(String type) {
        List<Diagram> filter = new ArrayList<>();
        for (Diagram diagram : this.project.getDiagramsList()) {
            if (diagram.getType().equalsIgnoreCase(type))
                filter.add(diagram);
        }
        return filter.toArray();
    }
    
    /**
     * Method responsible for returning the Products Array.
     * @return Products Array.
     */
    public Object[] getProducts() {
        return this.project.getProductsList().toArray();
    }
    
    /**
     * Method responsible for returning the Elements Array.
     * @return Elements Array.
     */
    public Object[] getElements() {
        return this.project.getElementsList().toArray();
    }
    
    /**
     * Method responsible for returning the Associations Array.
     * @return Associations Array.
     */
    public Object[] getAssociations() {
        return this.project.getAssociationsList().toArray();
    }
    
    /**
     * Method responsible for returning the Variabilities Array.
     * @return Variabilities Array.
     */
    public Object[] getVariabilities() {
        return this.project.getVariabilitiesList().toArray();
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
     * Method responsible for returning the Requirements Array.
     * @return Requirements Array.
     */
    public Requirement[] getRequirements() {
        return (Requirement[]) this.project.getRequirementsList().toArray();
    }
}