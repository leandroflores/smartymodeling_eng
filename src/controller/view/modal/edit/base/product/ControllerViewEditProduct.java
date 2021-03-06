package controller.view.modal.edit.base.product;

import controller.view.modal.edit.ControllerViewEdit;
import view.modal.edit.base.product.ViewEditProduct;

/**
 * <p>Class of Controller <b>ControllerViewEditProduct</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEditProduct</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-18
 * @see    controller.view.modal.edit.ControllerViewEdit
 * @see    model.structural.base.product.Product
 * @see    view.modal.edit.base.product.ViewEditProduct
 */
public class ControllerViewEditProduct extends ControllerViewEdit {

    /**
     * Default constructor method of Class.
     * @param view View Edit Product.
     */
    public ControllerViewEditProduct(ViewEditProduct view) {
        super(view);
    }
    
    @Override
    public boolean check() {
        return check(getView().getPanelBaseProduct().getNameTextField(), "Name is required!")
            && check(getView().getPanelBaseProduct().getVersionTextField(), "Version is required!");
    }

    @Override
    public void save() {
        close();
    }
    
    @Override
    public ViewEditProduct getView() {
        return (ViewEditProduct) super.getView();
    }
}