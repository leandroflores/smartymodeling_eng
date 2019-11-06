package file.exportation.product;

import java.io.File;
import model.structural.base.product.Instance;
import model.structural.base.product.Product;

/**
 * <p>Class of Export <b>ExportProduct</b>.</p>
 * <p>Class responsible for <b>Exporting</b> the <b>Product</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  06/11/2019
 * @see    model.structural.base.product.Product
 */
public class ExportProduct {
    private final String  path;
    private final Product product;
    
    /**
     * Default constructor method of Class.
     * @param path Path to Export Product.
     * @param product Product.
     */
    public ExportProduct(String path, Product product) {
        this.path    = path;
        this.product = product;
    }
    
    /**
     * Method responsible for exporting the Product.
     */
    public void export() {
        this.createDirectory();
        this.exportInstances();
    }
    
    /**
     * Method responsible for creating the Directory to Export.
     */
    private void createDirectory() {
        String folder = this.path + "\\" + this.product.getName();
        System.out.println("Folder: " + folder);
        File   file   = new File(folder);
        boolean mkdir = file.mkdir();
        System.out.println(mkdir);
    }
    
    /**
     * Method responsible for exporting the Instances.
     */
    private void exportInstances() {
        for (Instance instance : this.product.getInstancesList())
            new ExportInstance(this.path, instance).export();
    }
}