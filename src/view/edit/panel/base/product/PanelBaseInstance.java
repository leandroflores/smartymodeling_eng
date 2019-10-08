package view.edit.panel.base.product;

import controller.view.edit.panel.base.product.ControllerPanelBaseInstance;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.structural.base.product.Instance;
import view.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseInstance</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Instance Base</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  07/10/2019
 * @see    controller.view.edit.panel.base.product.
 * @see    model.structural.base.product.Instance
 * @see    view.Panel
 */
public final class PanelBaseInstance extends Panel {
    private final ViewMenu viewMenu;
    private final Instance instance;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param instance Instance.
     */
    public PanelBaseInstance(ViewMenu viewMenu, Instance instance) {
        this.viewMenu   = viewMenu;
        this.instance   = instance;
        this.controller = new ControllerPanelBaseInstance(this);
        this.setSettings();
        this.addComponents();
        this.addFooter();
        this.setValues();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridBagLayout());
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Product*: "), this.getConstraints(1, 1, 0, 0));
        this.add(this.createComboBox("productComboBox", this.viewMenu.getProject().getProductsList().toArray(), 15), this.getConstraints(3, 1, 1, 0));
        this.getProductComboBox().setPreferredSize(new Dimension(325, 30));
        
        this.add(this.createLabel("Diagram*: "), this.getConstraints(1, 1, 0, 1));
        this.add(this.createComboBox("diagramComboBox", this.viewMenu.getProject().getDiagramsList().toArray(), 15), this.getConstraints(3, 1, 1, 1));
        this.getDiagramComboBox().setPreferredSize(new Dimension(325, 30));
        
        this.add(this.createLabel("Name*: "), this.getConstraints(1, 1, 0, 2));
        this.add(this.createTextField("nameTextField", "", 15), this.getConstraints(3, 1, 1, 2));
    }
    
    /**
     * Method responsible for setting the Instance Values.
     */
    public void setValues() {
        this.setProduct();
        this.setDiagram();
        this.getNameTextField().setText(this.instance.getName());
    }
    
    /**
     * Method responsible for setting the Instance Product.
     */
    private void setProduct() {
        if (this.instance.getProduct() != null)
            this.getProductComboBox().setSelectedItem(this.instance.getProduct());
    }
    
    /**
     * Method responsible for setting the Instance Diagram.
     */
    private void setDiagram() {
        if (this.instance.getDiagram() != null)
            this.getDiagramComboBox().setSelectedItem(this.instance.getDiagram());
    }
    
    /**
     * Method responsible for adding the Panel Footer.
     */
    public void addFooter() {
        this.add(this.createButton("nextButton", "  Next  ", "Next", "next.png"), this.getConstraints(2, 1, 1, 3));
        
        this.getNextButton().setPreferredSize(new Dimension(150, 30));
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
    
    /**
     * Method responsible for returning the Instance.
     * @return Instance.
     */
    public Instance getInstance() {
        return this.instance;
    }
    
    /**
     * Method responsible for returning the Product Combo Box.
     * @return Product Combo Box.
     */
    public JComboBox getProductComboBox() {
        return this.comboBoxes.get("productComboBox");
    }
    
    /**
     * Method responsible for returning the Diagram Combo Box.
     * @return Diagram Combo Box.
     */
    public JComboBox getDiagramComboBox() {
        return this.comboBoxes.get("diagramComboBox");
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.textFields.get("nameTextField");
    }
    
    /**
     * Method responsible for returning the Next Button.
     * @return Next Button.
     */
    public JButton getNextButton() {
        return this.buttons.get("nextButton");
    }
}