package view.modal.delete.base.product;

import controller.view.modal.delete.base.product.ControllerViewDeleteInstance;
import model.structural.base.product.Instance;
import model.structural.base.product.Product;
import view.modal.delete.ViewDelete;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewDeleteInstance</b>.</p>
 * <p>Class responsible for defining the <b>Instance Delete View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-10
 * @see    controller.view.modal.delete.base.product.ControllerViewDeleteInstance
 * @see    model.structural.base.product.Instance
 * @see    view.modal.delete.ViewDelete
 */
public final class ViewDeleteInstance extends ViewDelete {
    private final Product  product;
    private final Instance instance;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param instance Instance.
     */
    public ViewDeleteInstance(PanelModeling panel, Instance instance) {
        super(panel);
        this.product    = instance.getProduct();
        this.instance   = instance;
        this.controller = new ControllerViewDeleteInstance(this);
        this.title      = "Delete Instance";
        this.initComponents();
        this.addComponents();
    }

    @Override
    public void addComponents() {
        super.addComponents(this.instance.getName());
    }
    
    /**
     * Method responsible for returning the Instance.
     * @return Instance.
     */
    public Instance getInstance() {
        return this.instance;
    }
    
    /**
     * Method responsible for returning the Product.
     * @return Product.
     */
    public Product getProduct() {
        return this.product;
    }
}