package controller.view.edit.panel.base.product;

import controller.view.panel.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.edit.panel.base.product.PanelBaseProduct;

/**
 * <p>Class of Controller <b>ControllerPanelBaseProduct</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseProduct</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  07/10/2019
 * @see    controller.view.panel.ControllerPanel
 * @see    model.structural.base.product.Product
 * @see    view.edit.panel.base.product.PanelBaseProduct
 */
public class ControllerPanelBaseProduct extends ControllerPanel {
    private final PanelBaseProduct panelBaseProduct;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Product.
     */
    public ControllerPanelBaseProduct(PanelBaseProduct panel) {
        super(panel);
        this.panelBaseProduct = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.update();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        this.update();
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
        this.update();
    }

    /**
     * Method responsible for checking the Product.
     * @return Product checked.
     */
    private boolean check() {
        return    this.check(this.panelBaseProduct.getNameTextField().getText())
               && this.check(this.panelBaseProduct.getVersionTextField().getText());
    }
    
    /**
     * Method responsible for setting the Product Values.
     */
    private void update() {
        this.panelBaseProduct.getProduct().setName(this.panelBaseProduct.getNameTextField().getText().trim());
        this.panelBaseProduct.getProduct().setVersion(this.panelBaseProduct.getVersionTextField().getText().trim());
        this.panelBaseProduct.getProduct().setDescription(this.panelBaseProduct.getDescriptionTextArea().getText());
        this.panelBaseProduct.getViewMenu().getPanelProject().getPanelTree().updateUI();
        this.panelBaseProduct.getViewMenu().setSave(false);
    }
}