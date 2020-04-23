package controller.view.modal.export.base.product;

import controller.view.modal.export.ControllerViewExport;
import file.exportation.product.ExportProduct;
import java.io.IOException;
import model.structural.base.product.Product;
import view.modal.export.base.product.ViewExportProduct;
import view.modal.message.ViewError;

/**
 * <p>Class of Controller <b>ControllerViewExportProduct</b>.</p>
 * <p>Class responsible for controlling the <b>ViewExportProduct</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-05
 * @see    controller.view.modal.export.ControllerViewExport
 * @see    file.exportation.product.ExportProduct
 * @see    model.structural.base.product.Product
 * @see    view.modal.export.base.product.ViewExportProduct
 */
public class ControllerViewExportProduct extends ControllerViewExport {

    /**
     * Default constructor method of Class.
     * @param view View Export Product.
     */
    public ControllerViewExportProduct(ViewExportProduct view) {
        super(view);
    }
    
    @Override
    public boolean check() {
        return this.check(this.getView().getPanelExportProduct().getDirectoryTextField(), "Select a Directory!");
    }

    @Override
    public void export() {
        String  path    = this.getView().getPanelExportProduct().getDirectoryTextField().getText().trim();
        Product product = this.getView().getPanelExportProduct().getProduct();
        try {
            new ExportProduct(path, product).export();
        } catch (IOException ex) {
            new ViewError(this.getView(), "Error to Export the Product!").setVisible(true);
        }
        this.getView().dispose();
    }
    
    @Override
    public ViewExportProduct getView() {
        return (ViewExportProduct) this.viewModal;
    }
}