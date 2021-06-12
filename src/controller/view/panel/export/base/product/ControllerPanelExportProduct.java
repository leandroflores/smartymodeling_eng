package controller.view.panel.export.base.product;

import controller.view.panel.export.ControllerPanelExport;
import model.structural.base.product.Product;
import view.panel.export.base.product.PanelExportProduct;

/**
 * <p>Class of Controller <b>ControllerPanelExportProduct</b>.</p>
 * <p>Class responsible for controlling the <b>PanelExportProduct</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-05
 * @see    controller.view.panel.export.ControllerPanelExport
 * @see    model.structural.base.product.Product
 * @see    view.panel.export.base.product.PanelExportProduct
 */
public class ControllerPanelExportProduct extends ControllerPanelExport {

    /**
     * Default constructor method of Class.
     * @param panel Panel Export Product.
     */
    public ControllerPanelExportProduct(PanelExportProduct panel) {
        super(panel);
    }
    
    @Override
    protected void update() {
        if (getPanel().getContextComboBox().getSelectedItem() != null) {
            getPanel().setProduct((Product) getPanel().getContextComboBox().getSelectedItem());
            getPanel().updateInstances();
        }
    }
    
    @Override
    public PanelExportProduct getPanel() {
        return (PanelExportProduct) panel;
    }
}