package controller.view.delete.base.product;

import controller.view.delete.ControllerViewDelete;
import model.structural.base.product.Instance;
import model.structural.base.product.Product;
import view.delete.base.product.ViewDeleteProduct;

/**
 * <p>Class of Controller <b>ControllerViewDeleteProduct</b>.</p>
 * <p>Class responsible for controlling the <b>ViewDeleteProduct</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-10
 * @see    controller.view.delete.ControllerViewDelete
 * @see    model.structural.base.product.Product
 * @see    view.delete.base.product.ViewDeleteProduct
 */
public class ControllerViewDeleteProduct extends ControllerViewDelete {
    private final Product product;
    
    /**
     * Default constructor method of Class.
     * @param viewDelete View Delete Product.
     */
    public ControllerViewDeleteProduct(ViewDeleteProduct viewDelete) {
        super(viewDelete);
        this.product = viewDelete.getProduct();
    }
    
    @Override
    public void delete() {
        this.removeInstances();
        this.getView().getProject().removeProduct(this.product);
        this.close();
    }
    
    /**
     * Method responsible for removing the Instances.
     */
    private void removeInstances() {
        for (Instance instance : this.product.getInstancesList())
            this.getView().getPanelModeling().removeInstance(instance);
    }
    
    @Override
    public ViewDeleteProduct getView() {
        return (ViewDeleteProduct) this.viewModal;
    }
}