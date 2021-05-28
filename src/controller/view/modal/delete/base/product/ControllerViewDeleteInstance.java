package controller.view.modal.delete.base.product;

import controller.view.modal.delete.ControllerViewDelete;
import model.structural.base.product.Instance;
import view.modal.delete.base.product.ViewDeleteInstance;

/**
 * <p>Class of Controller <b>ControllerViewDeleteInstance</b>.</p>
 * <p>Class responsible for controlling the <b>ViewDeleteInstance</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-10
 * @see    controller.view.modal.delete.ControllerViewDelete
 * @see    model.structural.base.product.Instance
 * @see    view.modal.delete.base.product.ViewDeleteInstance
 */
public final class ControllerViewDeleteInstance extends ControllerViewDelete {
    private final Instance instance;
    
    /**
     * Default constructor method of Class.
     * @param view View Delete Instance.
     */
    public ControllerViewDeleteInstance(ViewDeleteInstance view) {
        super(view);
        instance = getView().getInstance();
    }
    
    @Override
    public void delete() {
        getView().getProduct().removeInstance(instance);
        getView().getPanelModeling().removeInstance(instance);
        close();
    }
    
    @Override
    public ViewDeleteInstance getView() {
        return (ViewDeleteInstance) super.getView();
    }
}