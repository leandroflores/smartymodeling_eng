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
        this.getPanelTree().updateNode(this.getProduct());
        super.refresh();
    }
    
    /**
     * Method responsible for checking the Product.
     * @return Product checked.
     */
    protected boolean check() {
        return    this.check(this.getPanel().getNameTextField().getText())
               && this.check(this.getPanel().getVersionTextField().getText());
    }
    
    /**
     * Method responsible for setting the Product Values.
     */
    @Override
    protected void update() {
        if (this.check()) {
            this.getProduct().setName(this.getString(this.getPanel().getNameTextField()));
            this.getProduct().setVersion(this.getString(this.getPanel().getVersionTextField()));
            this.getProduct().setDescription(this.getString(this.getPanel().getDescriptionTextArea()));
            this.refresh();
        }
    }
    
    /**
     * Method responsible for returning the Product.
     * @return Product.
     */
    private Product getProduct() {
        return this.getPanel().getProduct();
    }
    
    @Override
    public PanelBaseProduct getPanel() {
        return (PanelBaseProduct) this.panel;
    }
}