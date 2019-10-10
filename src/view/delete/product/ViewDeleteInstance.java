package view.delete.product;

import controller.view.delete.product.ControllerViewDeleteInstance;
import model.structural.base.Project;
import model.structural.base.product.Instance;
import model.structural.base.product.Product;
import view.ViewStyle;
import view.delete.ViewDelete;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewDeleteInstance</b>.</p>
 * <p>Class responsible for defining the <b>Instance Delete View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  10/10/2019
 * @see    controller.view.delete.product.ControllerViewDeleteInstance
 * @see    model.structural.base.product.Instance
 * @see    view.delete.ViewDelete
 */
public final class ViewDeleteInstance extends ViewDelete {
    private final Project  project;
    private final Product  product;
    private final Instance instance;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param instance Instance.
     */
    public ViewDeleteInstance(PanelModeling panel, Instance instance) {
        super(panel);
        this.project    = this.view.getProject();
        this.product    = instance.getProduct();
        this.instance   = instance;
        this.controller = new ControllerViewDeleteInstance(this);
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.setTitle(ViewStyle.SYSTEM + "Delete Instance");
        this.addComponents();
    }

    @Override
    public void addComponents() {
        super.addComponents(this.instance.getName());
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return this.project;
    }
    
    /**
     * Method responsible for returning the Product.
     * @return Product.
     */
    public Product getProduct() {
        return this.product;
    }
    
    /**
     * Method responsible for returning the Instance.
     * @return Instance.
     */
    public Instance getInstance() {
        return this.instance;
    }
}