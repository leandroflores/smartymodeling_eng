package model.controller.structural.base;

import model.controller.Controller;
import model.structural.base.Project;
import model.structural.base.Stereotype;

/**
 * <p>Class of Controller <b>ControllerProject</b>.</p>
 * <p>Class responsible for defining the <b>Project Controller</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-31
 * @see    model.controller.Controller
 * @see    model.structural.base.Project
 */
public class ControllerProject extends Controller {
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     */
    public ControllerProject(Project project) {
        super(project);
    }
    
    /**
     * Method responsible for returning the Diagram Types Array.
     * @return Diagram Types Array.
     */
    public String[] getDiagramTypes() {
        return new String[]{"Feature", "UseCase", "Class", "Component", "Sequence", "Activity"};
    }
    
    /**
     * Method responsible for returning the General Context Array.
     * @return General Context Array.
     */
    public Object[] getGeneralContext() {
        Object[] diagrams   = getDiagrams();
        Object[] context    = new Object[diagrams.length + 1];
                 context[0] = "Project";
        System.arraycopy(diagrams, 0, context, 1, diagrams.length);
        return   context;
    }
    
    /**
     * Method responsible for returning the Project Targets Array.
     * @return Project Targets Array.
     */
    public Object[] getProjectTargets() {
        Object[] diagrams   = getDiagrams();
        Object[] targets    = new Object[diagrams.length + 1];
                 targets[0] = "Project";
        System.arraycopy(diagrams, 0, targets, 1, diagrams.length);
        return   targets;
    }
    
    /**
     * Method responsible for returning the Diagram Targets Array by Type.
     * @param  type Target Type.
     * @return Diagram Targets Array.
     */
    public Object[] getDiagramTargets(String type) {
        Object[] diagrams   = getDiagrams(type);
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
        Object[] products   = getProducts();
        Object[] targets    = new Object[products.length + 1];
                 targets[0] = "Project";
        System.arraycopy(products, 0, targets, 1, products.length);
        return   targets;
    }
    
    /**
     * Method responsible for returning the Manual Stereotypes Array.
     * @return Manual Stereotypes Array.
     */
    public Stereotype[] getManualStereotypes() {
        return getStereotypes(false);
    }
}