package view.new_.product;

import controller.view.new_.product.ControllerViewNewProduct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.product.test.Product_Final;
import model.structural.base.variability.Variability;
import view.edit.panel.base.product.PanelBaseOptional;
import view.edit.panel.base.product.PanelBaseProduct;
import view.edit.panel.base.product.PanelBaseVariationPoints;
import view.message.ViewMessage;
import view.new_.ViewNew;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewNewProduct</b>.</p>
 * <p>Class responsible for defining the <b>New Product_Final View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  21/08/2019
 * @see    controller.view.new_.product.ControllerViewNewProduct
 * @see    view.new_.ViewNew
 */
public final class ViewNewProduct extends ViewNew {
    private final Diagram diagram;
    private Product_Final product;
    private PanelBaseOptional panelBaseOptional;
    private PanelBaseVariationPoints panelBaseVariationPoints;
    private PanelBaseProduct panelBaseProduct;
    private HashMap<String, Integer> components;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Diagram.
     */
    public ViewNewProduct(ViewMenu view, Diagram diagram) {
        super(view);
        this.diagram    = diagram;
        this.controller = new ControllerViewNewProduct(this);
        this.title      = "New Product";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(650, 570);
        this.addHeader();
        this.addComponents();
        this.addFooter();
        this.setValues();
    }

    @Override
    public void addComponents() {
        this.add(this.createLabel("Diagram: ", 100));
        this.add(this.createTextFieldNoEditable("diagramTextField", "", 30));
        
        this.addLines(1);
        
        this.addTabbedPane();
        
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Tabbed Pane.
     */
    private void addTabbedPane() {
        this.tabbedPane = new JTabbedPane();
            this.addOptionalTabbedPane();
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Optional Tabbed Pane.
     */
    public void addOptionalTabbedPane() {
        this.updateComponents();
        
        this.panelBaseOptional = new PanelBaseOptional(this);
        this.tabbedPane.removeAll();
        this.tabbedPane.add("Optionals", this.panelBaseOptional);
    }
    
    /**
     * Method responsible for returning the Variabilities List.
     * @return Variabilities List.
     */
    public List<Variability> filterVariabilities() {
        List<Variability> filter = new ArrayList<>();
        for (Variability variability : this.diagram.getVariabilitiesList()) {
            if (this.components.get(variability.getVariationPoint().getId()) > 0)
                filter.add(variability);
        }
        return filter;
    }
    
    /**
     * Method responsible for updating the Components.
     */
    private void updateComponents() {
        this.components = new HashMap();
        for (Element element : this.diagram.getElementsList())
            this.components.put(element.getId(), 0);
    }
    
    /**
     * Method responsible for increment Element Component.
     * @param element Element.
     */
    public void increment(Element element) {
        this.components.put(element.getId(), this.components.get(element.getId()) + 1);
    }
    
    /**
     * Method responsible for creating a New Product_Final.
     * @return New Product_Final.
     */
    public Product_Final createNewProduct() {
        Product_Final newProduct = new Product_Final();
                newProduct.setName("NewProduct");
                newProduct.setIdentifiers(this.components);
                newProduct.setElements(this.diagram.getElements());
                newProduct.setAssociations(this.diagram.getAssociations());
                newProduct.update();
        return  newProduct;
    }
    
    /**
     * Method responsible for showing the New Product_Final.
     */
    public void showNewProduct() {
        this.product = this.panelBaseProduct.getProduct();
        if (this.product.isEmpty())
            new ViewMessage(this, "Empty Product!").setVisible(true);
        else
            this.view.getPanelModeling().addProduct(this.product);
        this.dispose();
    }
    
    /**
     * Method responsible for returning if a Element is Variation Point.
     * @param element Element.
     * @return Element is Variation Point.
     */
    public boolean isVariationPoint(Element element) {
        return this.components.get(element.getId()) > 0;
    }
    
    /**
     * Method responsible for adding the Variation Points Tabbed Pane.
     */
    public void addVariationPointsTabbedPane() {
        this.panelBaseVariationPoints = new PanelBaseVariationPoints(this);
        this.tabbedPane.add("Variation Points", this.panelBaseVariationPoints);
        this.tabbedPane.setSelectedComponent(this.tabbedPane.getComponentAt(1));
        this.tabbedPane.setEnabledAt(0, false);
    }
    
    /**
     * Method responsible for removing the Variation Point Tabbed Pane.
     */
    public void removeVariationPointTabbedPane() {
        this.tabbedPane.getComponent(0).setEnabled(true);
        this.tabbedPane.setSelectedComponent(this.tabbedPane.getComponent(0));
        this.tabbedPane.remove(1);
    }
    
    /**
     * Method responsible for adding the Product_Final Tabbed Pane.
     */
    public void addProductTabbedPane() {
        this.panelBaseProduct = new PanelBaseProduct(this, this.createNewProduct());
        this.tabbedPane.add("New Product", this.panelBaseProduct);
        this.tabbedPane.setSelectedComponent(this.tabbedPane.getComponentAt(2));
        this.tabbedPane.setEnabledAt(1, false);
    }
    
    /**
     * Method responsible for removing the Product_Final Tabbed Pane.
     */
    public void removeProductTabbedPane() {
        this.tabbedPane.getComponent(1).setEnabled(true);
        this.tabbedPane.setSelectedComponent(this.tabbedPane.getComponent(1));
        this.tabbedPane.remove(2);
    }
    
    /**
     * Method responsible for setting the Values.
     */
    private void setValues() {
        this.getDiagramTextField().setText(this.diagram.getName());
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return this.diagram;
    }

    /**
     * Method responsible for returning the Panel Base Optional.
     * @return Panel Base Optional.
     */
    public PanelBaseOptional getPanelBaseOptional() {
        return this.panelBaseOptional;
    }

    /**
     * Method responsible for returning the Panel Base Variation Points.
     * @return Panel Base Variation Points.
     */
    public PanelBaseVariationPoints getPanelBaseVariationPoints() {
        return this.panelBaseVariationPoints;
    }
    
    /**
     * Method responsible for returning the Components Map.
     * @return Components Map.
     */
    public HashMap<String, Integer> getComponentsMap() {
        return this.components;
    }
    
    /**
     * Method responsible for returning the Diagram Text Field.
     * @return Diagram Text Field.
     */
    public JTextField getDiagramTextField() {
        return this.textFields.get("diagramTextField");
    }
}