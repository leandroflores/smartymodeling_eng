package view.modal.new_.base.product;

import controller.view.modal.new_.base.product.ControllerViewNewInstance;
import java.awt.Dimension;
import java.util.HashMap;
import model.structural.base.product.Instance;
import view.panel.new_.base.product.instance.PanelBaseInstance;
import view.modal.new_.ViewNew;
import view.main.structural.ViewMenu;
import view.panel.new_.base.product.PanelNewInstance;

/**
 * <p>Class of View <b>ViewNewInstance</b>.</p>
 * <p>Class responsible for defining the <b>New Instance View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-07
 * @see    controller.view.modal.new_.base.product.ControllerViewNewInstance
 * @see    model.structural.base.product.Instance
 * @see    view.modal.new_.ViewNew
 */
public final class ViewNewInstance extends ViewNew {
    private final Instance instance;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewNewInstance(ViewMenu view) {
        super(view);
        this.instance   = new Instance();
        this.controller = new ControllerViewNewInstance(this);
        this.title      = "New Instance";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(585, 520);
        this.addHeader();
        this.addComponents();
        this.addFooter();
        this.getInsertButton().setEnabled(false);
    }

    @Override
    public void addComponents() {
        this.addPanelNewInstance();
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel New Instance.
     */
    private void addPanelNewInstance() {
        this.addPanel("panelNewInstance", new PanelNewInstance(this, this.instance));
        this.getPanelNewInstance().setPreferredSize(new Dimension(500, 400));
        this.add(this.getPanelNewInstance());
    }
    
    /**
     * Method responsible for returning the Panel New Instance.
     * @return Panel New Instance.
     */
    private PanelNewInstance getPanelNewInstance() {
        return (PanelNewInstance) this.getPanel("panelNewInstance");
    }
    
    /**
     * Method responsible for returning the Panel Base Instance.
     * @return Panel Base Instance.
     */
    public PanelBaseInstance getPanelBaseInstance() {
        return this.getPanelNewInstance().getPanelBaseInstance();
    }
    
    /**
     * Method responsible for returning the Elements HashMap.
     * @return Elements HashMap.
     */
    public HashMap<String, Integer> getElements() {
        return this.getPanelNewInstance().getElements();
    }
    
    /**
     * Method responsible for returning the Controller.
     * @return Controller.
     */
    public ControllerViewNewInstance getController() {
        return (ControllerViewNewInstance) this.controller;
    }
    
    /**
     * Method responsible for returning the Instance.
     * @return Instance.
     */
    public Instance getInstance() {
        return this.instance;
    }
}