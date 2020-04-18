package controller.view.edit.base.product;

import controller.view.edit.ControllerViewEdit;
import view.edit.base.product.ViewEditProduct;

/**
 * <p>Class of Controller <b>ControllerViewEditProduct</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEditProduct</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-18
 * @see    controller.view.edit.ControllerViewEdit
 * @see    model.structural.base.product.Product
 * @see    view.edit.base.product.ViewEditProduct
 */
public class ControllerViewEditProduct extends ControllerViewEdit {

    /**
     * Default constructor method of Class.
     * @param viewEdit View Edit Product.
     */
    public ControllerViewEditProduct(ViewEditProduct viewEdit) {
        super(viewEdit);
    }
    
    @Override
    public boolean check() {
        return this.check(this.getView().getPanelBaseProduct().getNameTextField(), "Name is required!")
            && this.check(this.getView().getPanelBaseProduct().getVersionTextField(), "Version is required!");
    }

    @Override
    public void save() {
        this.close();
    }
    
    @Override
    public ViewEditProduct getView() {
        return (ViewEditProduct) this.viewModal;
    }
}