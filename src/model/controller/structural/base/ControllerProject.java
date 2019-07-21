package model.controller.structural.base;

import java.util.List;
import model.structural.base.Project;
import model.structural.base.Stereotype;

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
}