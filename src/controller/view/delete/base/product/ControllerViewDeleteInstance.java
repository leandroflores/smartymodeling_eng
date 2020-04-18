package controller.view.delete.base.product;

import controller.view.delete.ControllerViewDelete;
import model.structural.base.product.Instance;
import view.delete.base.product.ViewDeleteInstance;

/**
 * <p>Class of Controller <b>ControllerViewDeleteInstance</b>.</p>
 * <p>Class responsible for controlling the <b>ViewDeleteInstance</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-10
 * @see    controller.view.delete.ControllerViewDelete
 * @see    model.structural.base.product.Instance
 * @see    view.delete.base.product.ViewDeleteInstance
 */
public class ControllerViewDeleteInstance extends ControllerViewDelete {
    private final Instance instance;
    
    /**
     * Default constructor method of Class.
     * @param viewDelete View Delete Instance.
     */
    public ControllerViewDeleteInstance(ViewDeleteInstance viewDelete) {
        super(viewDelete);
        this.instance = viewDelete.getInstance();
    }
    
    @Override
    public void delete() {
        this.getView().getProduct().removeInstance(this.instance);
        this.getView().getPanelModeling().removeInstance(this.instance);
        this.close();
    }
    
    @Override
    public ViewDeleteInstance getView() {
        return (ViewDeleteInstance) this.viewModal;
    }
}