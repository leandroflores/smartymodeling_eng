package file.exportation.product;

import java.io.File;
import java.io.IOException;
import model.structural.base.product.Instance;
import model.structural.base.product.Product;

/**
 * <p>Class of File <b>ExportProduct</b>.</p>
 * <p>Class responsible for <b>Exporting</b> the <b>Product</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-06
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
     * @throws java.io.IOException
     */
    public void export() throws IOException {
        createDirectory();
        exportInstances();
    }
    
    /**
     * Method responsible for creating the Directory to Export.
     */
    private void createDirectory() {
        String folder = path + "\\" + product.getName();
               new File(folder).mkdir();
    }
    
    /**
     * Method responsible for exporting the Instances.
     */
    private void exportInstances() throws IOException {
        for (Instance instance : product.getInstancesList())
            new ExportInstance(path + "\\" + product.getName(), instance).export();
    }
}