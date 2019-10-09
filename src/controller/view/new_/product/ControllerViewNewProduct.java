package controller.view.new_.product;

import controller.view.new_.ControllerViewNew;
import java.awt.event.ActionEvent;
import model.structural.base.product.Product;
import view.new_.product.ViewNewProduct;

/**
 * <p>Class of Controller <b>ControllerViewNewProduct</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewNewProduct</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  09/10/2019
 * @see    controller.view.new_.ControllerViewNew
 * @see    model.structural.base.product.Product
 * @see    view.new_.product.ViewNewProduct
 */
public class ControllerViewNewProduct extends ControllerViewNew {
    private final ViewNewProduct viewNewProduct;

    /**
     * Default constructor method of Class.
     * @param viewNew View New Product.
     */
    public ControllerViewNewProduct(ViewNewProduct viewNew) {
        super(viewNew);
        this.viewNewProduct = viewNew;
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
    }

    /**
     * Method responsible for checking the Product Name.
     * @return Name is checked.
     */
    public boolean checkName() {
        return this.check(this.viewNewProduct.getPanelBaseProduct().getNameTextField(), "Name is required!");
    }
    
    /**
     * Method responsible for checking the Product Version.
     * @return Version is checked.
     */
    public boolean checkVersion() {
        return this.check(this.viewNewProduct.getPanelBaseProduct().getVersionTextField(), "Version is required!");
    }
    
    @Override
    public boolean check() {
        return this.checkName()
            && this.checkVersion();
    }

    @Override
    public void insert() {
        Product product = this.viewNewProduct.getProduct();
        this.viewNewProduct.getProject().addProduct(product);
        this.viewNewProduct.getViewMenu().update();
        this.viewNewProduct.dispose();
    }
}