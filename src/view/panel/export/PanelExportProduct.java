package view.panel.export;

import controller.view.panel.export.ControllerPanelExportProduct;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import model.controller.structural.base.product.ControllerProduct;
import model.structural.base.product.Instance;
import model.structural.base.product.Product;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelExportProduct</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Product Export</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  04/11/2019
 * @see    controller.view.panel.export.ControllerPanelExportProduct
 * @see    model.structural.base.product.Product
 * @see    view.panel.export.PanelExport
 */
public final class PanelExportProduct extends PanelExport {
    private Product product;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     */
    public PanelExportProduct(ViewMenu viewMenu) {
        super(viewMenu);
        this.product    = null;
        this.controller = new ControllerPanelExportProduct(this);
        this.setSettings();
        this.addComponents();
        this.updateInstances();
    }
    
    @Override
    protected void addComponents() {
        super.addDirectoryField();
        
        this.add(this.createLabel("Product: "), this.createConstraints(1, 1, 0, 1));
        this.add(this.createComboBox("productComboBox", new ControllerProduct(this.project).getProducts(), 250), this.createConstraints(4, 1, 1, 1));
        this.setProduct((Product) this.getProductComboBox().getSelectedItem());
        
        this.createList("instancesList");
        this.add(this.getInstancesScrollPane(), this.createConstraints(5, 10, 0, 2));    
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
     * Method responsible for returning the Controller.
     * @return Controller.
     */
    public ControllerPanelExportProduct getController() {
        return (ControllerPanelExportProduct) this.controller;
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
    
    /**
     * Method responsible for returning the Product Combo Box.
     * @return Product Combo Box.
     */
    public JComboBox getProductComboBox() {
        return this.getComboBox("productComboBox");
    }
    
    /**
     * Method responsible for return the Instances List.
     * @return Instances List.
     */
    public JList getInstancesList() {
        return this.getList("instancesList");
    }
    
    /**
     * Method responsible for return the Instances Scroll Pane.
     * @return Instances Scroll Pane.
     */
    public JScrollPane getInstancesScrollPane() {
        return this.getScrollPane("instancesList");
    }
}