package controller.view.new_.product.test;

import controller.view.new_.ControllerViewNew;
import view.new_.product.test.ViewNewProduct_Final;

/**
 * <p>Class of Controller <b>ControllerViewNewProduct_Final</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewNewProduct_Final</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  22/08/2019
 * @see    controller.view.new_.ControllerViewNew
 * @see    view.new_.product.test.ViewNewProduct_Final
 */
public class ControllerViewNewProduct_Final extends ControllerViewNew {
    private final ViewNewProduct_Final viewNewProduct;
    
    /**
     * Default constructor method of Class.
     * @param view View New Product.
     */
    public ControllerViewNewProduct_Final(ViewNewProduct_Final view) {
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