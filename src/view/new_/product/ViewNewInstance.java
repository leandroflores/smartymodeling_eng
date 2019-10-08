package view.new_.product;

import java.awt.Dimension;
import java.util.HashMap;
import javax.swing.JTabbedPane;
import model.structural.base.product.Instance;
import view.edit.panel.base.product.PanelBaseInstance;
import view.new_.ViewNew;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewNewInstance</b>.</p>
 * <p>Class responsible for defining the <b>New Instance View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  07/10/2019
 * @see    controller.view.new_.product.
 * @see    model.structural.base.product.Instance
 * @see    view.new_.ViewNew
 */
public final class ViewNewInstance extends ViewNew {
    private final Instance instance;
    private PanelBaseInstance panelBaseInstance;
    private HashMap<String, Integer> components;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewNewInstance(ViewMenu view) {
        super(view);
//        this.controller = new ControllerViewNewProduct_Final(this);
        this.title      = "New Instance";
        this.instance   = new Instance();
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(650, 570);
        this.addHeader();
        this.addComponents();
        this.addFooter();
//        this.setValues();
    }

    @Override
    public void addComponents() {
        this.addTabbedPane();
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Tabbed Pane.
     */
    private void addTabbedPane() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(500, 400));
            this.addPanelBaseInstance();
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Instance.
     */
    public void addPanelBaseInstance() {
        this.panelBaseInstance = new PanelBaseInstance(this.view, this.instance);
        this.tabbedPane.removeAll();
        this.tabbedPane.add("Instance", this.panelBaseInstance);
    }

    /**
     * Method responsible for returning the Panel Base Instance.
     * @return Panel Base Instance.
     */
    public PanelBaseInstance getPanelBaseInstance() {
        return this.panelBaseInstance;
    }

    /**
     * Method responsible for returning the Components Map.
     * @return Components Map.
     */
    public HashMap<String, Integer> getComponentsMap() {
        return this.components;
    }
}