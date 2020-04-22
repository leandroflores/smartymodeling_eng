package controller.view.modal.new_.base.product;

import controller.view.modal.new_.ControllerViewNew;
import view.modal.new_.base.product.ViewNewProduct;

/**
 * <p>Class of Controller <b>ControllerViewNewProduct</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewNewProduct</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-09
 * @see    controller.view.modal.new_.ControllerViewNew
 * @see    model.structural.base.product.Product
 * @see    view.modal.new_.base.product.ViewNewProduct
 */
public class ControllerViewNewProduct extends ControllerViewNew {

    /**
     * Default constructor method of Class.
     * @param viewNew View New Product.
     */
    public ControllerViewNewProduct(ViewNewProduct viewNew) {
        super(viewNew);
    }
    
    @Override
    public boolean check() {
        return this.check(this.getView().getPanelBaseProduct().getNameTextField(), "Name is required!")
            && this.check(this.getView().getPanelBaseProduct().getVersionTextField(), "Version is required!");
    }

    @Override
    public void new_() {
        this.getView().getProject().addProduct(this.getView().getProduct());
        this.getView().getViewMenu().setTabIndex(4);
    }
    
    @Override
    public ViewNewProduct getView() {
        return (ViewNewProduct) this.viewModal;
    }
}