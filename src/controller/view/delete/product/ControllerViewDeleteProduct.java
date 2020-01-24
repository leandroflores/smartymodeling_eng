package controller.view.delete.product;

import controller.view.delete.ControllerViewDelete;
import model.structural.base.product.Instance;
import model.structural.base.product.Product;
import view.delete.product.ViewDeleteProduct;

/**
 * <p>Class of Controller <b>ControllerViewDeleteProduct</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewDeleteProduct</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  10/10/2019
 * @see    controller.view.delete.ControllerViewDelete
 * @see    model.structural.base.product.Product
 * @see    view.delete.product.ViewDeleteProduct
 */
public class ControllerViewDeleteProduct extends ControllerViewDelete {
    private final ViewDeleteProduct viewDeleteProduct;
    private final Product product;
    
    /**
     * Default constructor method of Class.
     * @param viewDelete View Delete Product.
     */
    public ControllerViewDeleteProduct(ViewDeleteProduct viewDelete) {
        super(viewDelete);
        this.viewDeleteProduct = viewDelete;
        this.product           = viewDelete.getProduct();
    }
    
    /**
     * Method responsible for removing the Instances.
     */
    private void removeInstances() {
        for (Instance instance : this.product.getInstancesList())
            this.viewDelete.getPanelModeling().removeInstance(instance);
    }
    
    @Override
    public void delete() {
        this.removeInstances();
        this.viewDeleteProduct.getProject().removeProduct(this.product);
        this.close();
    }
}