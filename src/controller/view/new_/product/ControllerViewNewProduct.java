package controller.view.new_.product;

import controller.view.new_.ControllerViewNew;
import view.new_.product.ViewNewProduct;

/**
 * <p>Class of Controller <b>ControllerViewNewProduct</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewNewProduct</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  22/08/2019
 * @see    controller.view.new_.ControllerViewNew
 * @see    view.new_.product.ViewNewProduct
 */
public class ControllerViewNewProduct extends ControllerViewNew {
    private final ViewNewProduct viewNewProduct;
    
    /**
     * Default constructor method of Class.
     * @param view View New Product.
     */
    public ControllerViewNewProduct(ViewNewProduct view) {
        super(view);
        this.viewNewProduct = view;
    }

    @Override
    public boolean check() {
        return this.viewNewProduct.getPanelBaseVariationPoints() != null;
    }

    @Override
    public void insert() {
        this.viewNewProduct.getPanelBaseVariationPoints().getController().newProduct();
    }
}