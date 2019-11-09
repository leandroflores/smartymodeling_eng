package controller.view.panel.export;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import model.structural.base.product.Product;
import view.panel.export.PanelExportProduct;

/**
 * <p>Class of Controller <b>ControllerPanelExportProduct</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelExportProduct</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  05/11/2019
 * @see    controller.view.panel.export.ControllerPanelExport
 * @see    model.structural.base.product.Product
 * @see    view.panel.export.PanelExportProduct
 */
public class ControllerPanelExportProduct extends ControllerPanelExport {
    private final PanelExportProduct panelExportProduct;

    /**
     * Default constructor method of Class.
     * @param panel Panel Export Product.
     */
    public ControllerPanelExportProduct(PanelExportProduct panel) {
        super(panel);
        this.panelExportProduct = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        if (this.panelExportProduct.getProductComboBox().equals(event.getSource()))
            this.updateProduct();
    }

    @Override
    public void keyPressed(KeyEvent event) {}
    
    /**
     * Method responsible for updating the Product.
     */
    private void updateProduct() {
        if (this.panelExportProduct.getProductComboBox().getSelectedItem() != null) {
            this.panelExportProduct.setProduct((Product) this.panelExportProduct.getProductComboBox().getSelectedItem());
            this.panelExportProduct.updateInstances();
        }
    }
}