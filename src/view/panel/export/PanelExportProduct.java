package view.panel.export;

import controller.view.panel.export.ControllerPanelExportProduct;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import model.controller.structural.base.ControllerProject;
import model.structural.base.Project;
import model.structural.base.product.Instance;
import model.structural.base.product.Product;
import view.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelExportProduct</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Export Product Base</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  04/11/2019
 * @see    controller.view.panel.export.ControllerPanelExportProduct
 * @see    model.structural.base.product.Product
 * @see    view.Panel
 */
public final class PanelExportProduct extends Panel {
    private final ViewMenu viewMenu;
    private final Project project;
    private Product product;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     */
    public PanelExportProduct(ViewMenu viewMenu) {
        this.viewMenu   = viewMenu;
        this.project    = this.viewMenu.getProject();
        this.product    = null;
        this.controller = new ControllerPanelExportProduct(this);
        this.setSettings();
        this.addComponents();
        this.updateInstances();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(50, 50));
        this.setSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Directory*: "), this.getConstraints(1, 1, 0, 0));
        this.add(this.createTextFieldNoEditable("directoryTextField", "", 15), this.getConstraints(3, 1, 1, 0));
        this.add(this.createButton("searchDirectoryButton", "", "Search Directory", "search.png"), this.getConstraints(1, 1, 4, 0));
        this.createDirectoryChooser("searchDirectoryChooser");
        
        this.add(this.createLabel("Product: "), this.getConstraints(1, 1, 0, 1));
        this.add(this.createComboBox("productComboBox", new ControllerProject(this.project).getProducts(), 250), this.getConstraints(4, 1, 1, 1));
        this.setProduct((Product) this.getProductComboBox().getSelectedItem());
        
        this.createList("instancesList");
        this.add(this.getInstancesScrollPane(), this.getConstraints(5, 10, 0, 2));    
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
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
    
    /**
     * Method responsible for returning the Controller.
     * @return Controller.
     */
    public ControllerPanelExportProduct getController() {
        return (ControllerPanelExportProduct) this.controller;
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return this.project;
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
     * Method responsible for returning the Directory Text Field.
     * @return Directory Text Field.
     */
    public JTextField getDirectoryTextField() {
        return this.textFields.get("directoryTextField");
    }
    
    /**
     * Method responsible for returning the Search Directory Button.
     * @return Search Directory Button.
     */
    public JButton getSearchDirectoryButton() {
        return this.buttons.get("searchDirectoryButton");
    }
    
    /**
     * Method responsible for returning the Search Directory Chooser.
     * @return Search Directory Chooser.
     */
    public JFileChooser getSearchDirectoryChooser() {
        return this.fileChoosers.get("searchDirectoryChooser");
    }
    
    /**
     * Method responsible for returning the Product Combo Box.
     * @return Product Combo Box.
     */
    public JComboBox getProductComboBox() {
        return this.comboBoxes.get("productComboBox");
    }
    
    /**
     * Method responsible for return the Instances List.
     * @return Instances List.
     */
    public JList getInstancesList() {
        return this.lists.get("instancesList");
    }
    
    /**
     * Method responsible for return the Instances Scroll Pane.
     * @return Instances Scroll Pane.
     */
    public JScrollPane getInstancesScrollPane() {
        return this.scrollPanes.get("instancesList");
    }
}