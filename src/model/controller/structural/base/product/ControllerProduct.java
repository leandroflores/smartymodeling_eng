package model.controller.structural.base.product;

import model.controller.Controller;
import model.structural.base.Project;

/**
 * <p>Class of Controller <b>ControllerProduct</b>.</p>
 * <p>Class responsible for defining the <b>Product Controller</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-01-29
 * @see    model.controller.Controller
 * @see    model.structural.base.product.Product
 */
public class ControllerProduct extends Controller {
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     */
    public ControllerProduct(Project project) {
        super(project);
    }
}