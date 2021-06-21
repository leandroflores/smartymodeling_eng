package view.modal.export.base.product;

import controller.view.modal.export.base.product.ControllerViewExportProduct;
import model.structural.base.product.Product;
import view.modal.export.ViewExport;
import view.panel.export.base.product.PanelExportProduct;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewExportProduct</b>.</p>
 * <p>Class responsible for defining the <b>Export Product View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-05
 * @see    controller.view.modal.export.base.product.ControllerViewExportProduct
 * @see    model.structural.base.product.Product
 * @see    view.modal.export.ViewExport
 */
public final class ViewExportProduct extends ViewExport {
    private Product product;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     */
    public ViewExportProduct(ViewMenu viewMenu) {
        super(viewMenu);
        controller = new ControllerViewExportProduct(this);
        title      = "Export Product";
        initComponents();
    }
    
    @Override
    protected PanelExportProduct createPanelExport() {
        return new PanelExportProduct(getView());
    }
    
    @Override
    public PanelExportProduct getPanelExport() {
        return (PanelExportProduct) getPanel("export");
    }
    
    /**
     * Method responsible for returning the Product.
     * @return Product.
     */
    public Product getProduct() {
        return product;
    }
}