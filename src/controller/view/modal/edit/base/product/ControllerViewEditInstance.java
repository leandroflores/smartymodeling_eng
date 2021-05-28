package controller.view.modal.edit.base.product;

import controller.view.modal.edit.ControllerViewEdit;
import view.modal.edit.base.product.ViewEditInstance;

/**
 * <p>Class of Controller <b>ControllerViewEditInstance</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEditInstance</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-18
 * @see    controller.view.modal.edit.ControllerViewEdit
 * @see    model.structural.base.product.Instance
 * @see    view.modal.edit.base.product.ViewEditInstance
 */
public class ControllerViewEditInstance extends ControllerViewEdit {

    /**
     * Default constructor method of Class.
     * @param view View Edit Instance.
     */
    public ControllerViewEditInstance(ViewEditInstance view) {
        super(view);
    }
    
    @Override
    public boolean check() {
        return check(getView().getPanelBaseInstance().getNameTextField(), "Name is required!");
    }

    @Override
    public void save() {
        close();
    }
    
    @Override
    public ViewEditInstance getView() {
        return (ViewEditInstance) super.getView();
    }
}