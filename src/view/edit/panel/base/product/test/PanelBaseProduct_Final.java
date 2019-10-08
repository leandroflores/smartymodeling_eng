package view.edit.panel.base.product.test;

import controller.view.edit.panel.base.product.test.ControllerPanelBaseProduct_Final;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import model.structural.base.product.test.Product_Final;
import view.Panel;
import view.new_.product.test.ViewNewProduct_Final;

/**
 * <p>Class of View <b>PanelBaseProduct_Final</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Product_Final Base</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  22/08/2019
 * @see    controller.view.edit.panel.base.product.test.ControllerPanelBaseProduct_Final
 * @see    model.structural.base.product.Product
 * @see    view.Panel
 */
public class PanelBaseProduct_Final extends Panel {
    private final ViewNewProduct_Final view;
    private final Product_Final product;
    
    /**
     * Default constructor method of Class.
     * @param view View New Product_Final.
     * @param product Product_Final.
     */
    public PanelBaseProduct_Final(ViewNewProduct_Final view, Product_Final product) {
        this.view       = view;
        this.product    = product;
        this.controller = new ControllerPanelBaseProduct_Final(this);
        this.initComponents();
    }
    
    /**
     * Method responsible for initializing the Components.
     */
    private void initComponents() {
        this.setPreferredSize(new Dimension(600, 350));
        this.setLayout(new GridBagLayout());
        this.addHeader();
        this.addComponents();
        this.addFooter();
        this.setValues();
    }
    
    /**
     * Method responsible for adding the Header.
     */
    public void addHeader() {
        this.add(this.createLabel("Finishing the new Product:"), this.getConstraints(6, 1, 0, 0));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Name*: "), this.getConstraints(2, 1, 0, 1));
        this.add(this.createTextField("nameTextField", "", 10),     this.getConstraints(4, 1, 2, 1));
        
        this.add(this.createLabel("Elements: "), this.getConstraints(2, 1, 0, 2));
        this.add(this.createTextFieldNoEditable("elementsTextField", "", 10), this.getConstraints(4, 1, 2, 2));
        
        this.add(this.createLabel("Associations: "), this.getConstraints(2, 1, 0, 3));
        this.add(this.createTextFieldNoEditable("associationsTextField", "", 10), this.getConstraints(4, 1, 2, 3));
    }
    
    /**
     * Method responsible for adding the Panel Footer.
     */
    public void addFooter() {
        this.add(this.createButton("backButton", "  Back  ", "Back", "back.png"), this.getConstraints(3, 1, 0, 4));
        this.add(this.createButton("nextButton", "  Next  ", "Next", "next.png"), this.getConstraints(3, 1, 3, 4));
        
        this.getBackButton().setPreferredSize(new Dimension(150, 30));
        this.getNextButton().setPreferredSize(new Dimension(150, 30));
    }
    
    /**
     * Method responsible for setting the Values.
     */
    public void setValues() {
        this.getNameTextField().setText(this.product.getName());
        this.getElementsTextField().setText(Integer.toString(this.product.getElements().size()));
        this.getAssociationsTextField().setText(Integer.toString(this.product.getAssociations().size()));
        
        this.getNameTextField().requestFocus();
    }
    
    /**
     * Method responsible for returning the View New Product_Final.
     * @return View New Product_Final.
     */
    public ViewNewProduct_Final getViewNewProduct() {
        return this.view;
    }
    
    /**
     * Method responsible for returning the Product_Final.
     * @return Product_Final.
     */
    public Product_Final getProduct() {
        return this.product;
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.textFields.get("nameTextField");
    }
    
    /**
     * Method responsible for returning the Elements Field.
     * @return Elements Field.
     */
    public JTextField getElementsTextField() {
        return this.textFields.get("elementsTextField");
    }
    
    /**
     * Method responsible for returning the Associations Field.
     * @return Associations Field.
     */
    public JTextField getAssociationsTextField() {
        return this.textFields.get("associationsTextField");
    }
    
    /**
     * Method responsible for returning the Back Button.
     * @return Back Button.
     */
    public JButton getBackButton() {
        return this.buttons.get("backButton");
    }
    
    /**
     * Method responsible for returning the Next Button.
     * @return Next Button.
     */
    public JButton getNextButton() {
        return this.buttons.get("nextButton");
    }
}