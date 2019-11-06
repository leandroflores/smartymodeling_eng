package controller.view.export;

import file.exportation.product.ExportProduct;
import model.structural.base.product.Product;
import view.export.ViewExportProduct;

/**
 * <p>Class of Controller <b>ControllerViewExportProduct</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewExportProduct</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  05/11/2019
 * @see    controller.view.export.ControllerViewExport
 * @see    view.export.ViewExportProduct
 */
public class ControllerViewExportProduct extends ControllerViewExport {
    private final ViewExportProduct viewExportProduct;

    /**
     * Default constructor method of Class.
     * @param viewExport View Export Product.
     */
    public ControllerViewExportProduct(ViewExportProduct viewExport) {
        super(viewExport);
        this.viewExportProduct = viewExport;
    }
    
    @Override
    public boolean check() {
        return this.check(this.viewExportProduct.getPanelExportProduct().getDirectoryTextField(), "Select a Directory!");
    }

    @Override
    public void export() {
        String  path    = this.viewExportProduct.getPanelExportProduct().getDirectoryTextField().getText().trim();
        Product product = this.viewExportProduct.getPanelExportProduct().getProduct();
        System.out.println("Path: " + path);
        System.out.println("Prod: " + product);
        System.out.println("");
        new ExportProduct(path, product).export();
        this.viewExportProduct.dispose();
    }
}