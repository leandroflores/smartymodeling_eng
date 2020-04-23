package view.panel.export.base.product;

import controller.view.panel.export.base.product.ControllerPanelExportProduct;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import model.controller.structural.base.product.ControllerProduct;
import model.structural.base.product.Instance;
import model.structural.base.product.Product;
import view.panel.export.PanelExport;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelExportProduct</b>.</p>
 * <p>Class responsible for defining a <b>Export Product Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-04
 * @see    controller.view.panel.export.base.product.ControllerPanelExportProduct
 * @see    model.structural.base.product.Product
 * @see    view.panel.export.PanelExport
 */
public final class PanelExportProduct extends PanelExport {
    private Product product;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public PanelExportProduct(ViewMenu view) {
        super(view);
        this.product    = null;
        this.controller = new ControllerPanelExportProduct(this);
        this.setDefaultProperties();
        this.addComponents();
        this.updateInstances();
    }
    
    @Override
    protected void addComponents() {
        super.addDirectoryField();
        
        this.add(this.createLabel("Product: "), this.createConstraints(1, 1, 0, 1));
        this.add(this.createComboBox("contextComboBox", new ControllerProduct(this.project).getProducts(), 250), this.createConstraints(4, 1, 1, 1));
        this.setProduct((Product) this.getContextComboBox().getSelectedItem());
        
        this.createList("instancesList");
        this.add(this.getScrollPane("instancesList"), this.createConstraints(5, 10, 0, 2));    
    }
    
    /**
     * Method responsible for updating the Instances.
     */
    public void updateInstances() {
        this.getInstancesList().removeAll();
        DefaultListModel model = new DefaultListModel();
        for (Instance instance : this.product.getInstancesList())
            model.addElement(instance.getAbstract());
        this.getInstancesList().setModel(model);
    }
    
    /**
     * Method responsible for return the Instances List.
     * @return Instances List.
     */
    public JList getInstancesList() {
        return this.getList("instancesList");
    }
    
    /**
     * Method responsible for returning the Product.
     * @return Product.
     */
    public Product getProduct() {
        return this.product;
    }
    
    /**
     * Method responsible for setting the Product.
     * @param product Product.
     */
    public void setProduct(Product product) {
        this.product = product;
    }
    
    @Override
    public ControllerPanelExportProduct getController() {
        return (ControllerPanelExportProduct) this.controller;
    }
}