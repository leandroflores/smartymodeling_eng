package controller.view.edit.base.product;

import controller.view.edit.ControllerViewEdit;
import view.edit.base.product.ViewEditInstance;

/**
 * <p>Class of Controller <b>ControllerViewEditInstance</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEditInstance</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-18
 * @see    controller.view.edit.ControllerViewEdit
 * @see    model.structural.base.product.Instance
 * @see    view.edit.base.product.ViewEditInstance
 */
public class ControllerViewEditInstance extends ControllerViewEdit {

    /**
     * Default constructor method of Class.
     * @param viewEdit View Edit Instance.
     */
    public ControllerViewEditInstance(ViewEditInstance viewEdit) {
        super(viewEdit);
    }
    
    @Override
    public boolean check() {
        return this.check(this.getView().getPanelBaseInstance().getNameTextField(), "Name is required!");
    }

    @Override
    public void save() {
        this.close();
    }
    
    @Override
    public ViewEditInstance getView() {
        return (ViewEditInstance) this.viewModal;
    }
}