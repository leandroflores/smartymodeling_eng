package controller.view.modal.new_.base.product;

import controller.view.modal.new_.ControllerViewNew;
import view.modal.new_.base.product.ViewNewProduct;

/**
 * <p>Class of Controller <b>ControllerViewNewProduct</b>.</p>
 * <p>Class responsible for controlling the <b>ViewNewProduct</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-09
 * @see    controller.view.modal.new_.ControllerViewNew
 * @see    model.structural.base.product.Product
 * @see    view.modal.new_.base.product.ViewNewProduct
 */
public class ControllerViewNewProduct extends ControllerViewNew {

    /**
     * Default constructor method of Class.
     * @param view View New Product.
     */
    public ControllerViewNewProduct(ViewNewProduct view) {
        super(view);
    }
    
    @Override
    public boolean check() {
        return check(getView().getPanelBaseProduct().getNameTextField(), "Name is required!")
            && check(getView().getPanelBaseProduct().getVersionTextField(), "Version is required!");
    }

    @Override
    public void new_() {
        getView().getProject().addProduct(getView().getProduct());
        getView().getViewMenu().setTabIndex(4);
    }
    
    @Override
    public ViewNewProduct getView() {
        return (ViewNewProduct) super.getView();
    }
}