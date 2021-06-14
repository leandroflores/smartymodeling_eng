package controller.view.panel.base.product;

import controller.view.panel.base.ControllerPanelBase;
import model.structural.base.product.Product;
import view.panel.base.product.PanelBaseProduct;

/**
 * <p>Class of Controller <b>ControllerPanelBaseProduct</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseProduct</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-07
 * @see    controller.view.panel.base.ControllerPanelBase
 * @see    model.structural.base.product.Product
 * @see    view.panel.base.product.PanelBaseProduct
 */
public class ControllerPanelBaseProduct extends ControllerPanelBase {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Product.
     */
    public ControllerPanelBaseProduct(PanelBaseProduct panel) {
        super(panel);
    }

    @Override
    protected void refresh() {
        getPanelTree().updateNode(getProduct());
        super.refresh();
    }
    
    /**
     * Method responsible for checking the Product.
     * @return Product checked.
     */
    protected boolean check() {
        return check(getPanel().getNameTextField().getText())
            && check(getPanel().getVersionTextField().getText());
    }
    
    /**
     * Method responsible for setting the Product Values.
     */
    @Override
    protected void update() {
        if (check()) {
            getProduct().setName(getString(getPanel().getNameTextField()));
            getProduct().setVersion(getString(getPanel().getVersionTextField()));
            getProduct().setDescription(getString(getPanel().getDescriptionTextArea()));
            refresh();
        }
    }
    
    /**
     * Method responsible for returning the Product.
     * @return Product.
     */
    private Product getProduct() {
        return getPanel().getProduct();
    }
    
    @Override
    public PanelBaseProduct getPanel() {
        return (PanelBaseProduct) panel;
    }
}