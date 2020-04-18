package controller.view.new_.base.product;

import controller.view.new_.ControllerViewNew;
import view.new_.base.product.ViewNewProduct;

/**
 * <p>Class of Controller <b>ControllerViewNewProduct</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewNewProduct</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-09
 * @see    controller.view.new_.ControllerViewNew
 * @see    model.structural.base.product.Product
 * @see    view.new_.base.product.ViewNewProduct
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
    public void insert() {
        this.getView().getProject().addProduct(this.getView().getProduct());
    }
    
    @Override
    public ViewNewProduct getView() {
        return (ViewNewProduct) this.viewModal;
    }
}