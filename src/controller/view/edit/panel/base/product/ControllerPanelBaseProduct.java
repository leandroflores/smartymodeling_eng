package controller.view.edit.panel.base.product;


import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.edit.panel.base.product.PanelBaseProduct;

/**
 * <p>Class of Controller <b>ControllerPanelBaseProduct</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseProduct</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  22/08/2019
 * @see    controller.view.ControllerPanel
 * @see    view.edit.panel.base.product.PanelBaseProduct
 */
public class ControllerPanelBaseProduct extends ControllerPanel {
    private final PanelBaseProduct panelBaseProduct;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Product_Final.
     */
    public ControllerPanelBaseProduct(PanelBaseProduct panel) {
        super(panel);
        this.panelBaseProduct = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.panelBaseProduct.getBackButton().equals(event.getSource()))
            this.panelBaseProduct.getViewNewProduct().removeProductTabbedPane();
        else if (this.panelBaseProduct.getNextButton().equals(event.getSource()))
            this.showNewProduct();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {}
    
    /**
     * Method responsible for going to Next Panel.
     */
    public void showNewProduct() {
        this.panelBaseProduct.getProduct().setName(this.panelBaseProduct.getNameTextField().getText().trim());
        this.panelBaseProduct.getViewNewProduct().getViewMenu().getProject().addProduct(this.panelBaseProduct.getProduct());
        this.panelBaseProduct.getViewNewProduct().showNewProduct();
    }
}