package controller.view.delete.product;

import controller.view.delete.ControllerViewDelete;
import model.structural.base.product.Instance;
import view.delete.product.ViewDeleteInstance;

/**
 * <p>Class of Controller <b>ControllerViewDeleteInstance</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewDeleteInstance</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  10/10/2019
 * @see    controller.view.delete.ControllerViewDelete
 * @see    model.structural.base.product.Instance
 * @see    view.delete.product.ViewDeleteInstance
 */
public class ControllerViewDeleteInstance extends ControllerViewDelete {
    private final ViewDeleteInstance viewDeleteInstance;
    private final Instance instance;
    
    /**
     * Default constructor method of Class.
     * @param viewDelete View Delete Instance.
     */
    public ControllerViewDeleteInstance(ViewDeleteInstance viewDelete) {
        super(viewDelete);
        this.viewDeleteInstance = viewDelete;
        this.instance           = viewDelete.getInstance();
    }
    
    @Override
    public void delete() {
        this.viewDeleteInstance.getProduct().removeInstance(this.instance);
        this.viewDeleteInstance.getPanelModeling().removeInstance(this.instance);
        this.close();
    }
}