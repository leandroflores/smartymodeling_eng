package controller.view.modal.delete.base.product;

import controller.view.modal.delete.ControllerViewDelete;
import model.structural.base.product.Instance;
import model.structural.base.product.Product;
import view.modal.delete.base.product.ViewDeleteProduct;

/**
 * <p>Class of Controller <b>ControllerViewDeleteProduct</b>.</p>
 * <p>Class responsible for controlling the <b>ViewDeleteProduct</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-10
 * @see    controller.view.modal.delete.ControllerViewDelete
 * @see    model.structural.base.product.Product
 * @see    view.modal.delete.base.product.ViewDeleteProduct
 */
public final class ControllerViewDeleteProduct extends ControllerViewDelete {
    private final Product product;
    
    /**
     * Default constructor method of Class.
     * @param view View Delete Product.
     */
    public ControllerViewDeleteProduct(ViewDeleteProduct view) {
        super(view);
        product = getView().getProduct();
    }
    
    @Override
    public void delete() {
        removeInstances();
        getView().getProject().removeProduct(product);
        close();
    }
    
    /**
     * Method responsible for removing the Instances.
     */
    private void removeInstances() {
        for (Instance instance : product.getInstancesList())
            getView().getPanelModeling().removeInstance(instance);
    }
    
    @Override
    public ViewDeleteProduct getView() {
        return (ViewDeleteProduct) super.getView();
    }
}