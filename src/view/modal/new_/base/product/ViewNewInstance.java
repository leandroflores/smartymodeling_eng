package view.modal.new_.base.product;

import controller.view.modal.new_.base.product.ControllerViewNewInstance;
import java.awt.Dimension;
import java.util.HashMap;
import model.structural.base.product.Instance;
import view.panel.new_.base.product.instance.PanelBaseInstance;
import view.modal.new_.ViewNew;
import view.main.structural.ViewMenu;
import view.panel.new_.base.product.PanelNewInstance;
import view.panel.new_.base.product.instance.PanelBaseArtifacts;

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
        instance   = new Instance();
        controller = new ControllerViewNewInstance(this);
        title      = "New Instance";
        initComponents();
    }
    
    @Override
    public void initComponents() {
        super.initComponents();
        getInsertButton().setEnabled(false);
    }

    @Override
    protected Dimension getViewDimension() {
        return new Dimension(585, 520);
    }
    
    @Override
    protected PanelNewInstance createPanelNew() {
        return new PanelNewInstance(this, instance);
    }
    
    @Override
    protected Dimension getPanelDimension() {
        return new Dimension(500, 400);
    }
    
    @Override
    protected PanelNewInstance getPanelNew() {
        return (PanelNewInstance) super.getPanelNew();
    }
    
    /**
     * Method responsible for returning the Panel Base Instance.
     * @return Panel Base Instance.
     */
    public PanelBaseInstance getPanelBaseInstance() {
        return getPanelNew().getPanelBaseInstance();
    }
    
    /**
     * Method responsible for returning the Panel Base Artifacts.
     * @return Panel Base Artifacts.
     */
    public PanelBaseArtifacts getPanelBaseArtifacts() {
        return getPanelNew().getPanelBaseArtifacts();
    }
    
    /**
     * Method responsible for returning the Elements HashMap.
     * @return Elements HashMap.
     */
    public HashMap<String, Integer> getElements() {
        return getPanelNew().getElements();
    }
    
    /**
     * Method responsible for returning the Controller.
     * @return Controller.
     */
    public ControllerViewNewInstance getController() {
        return (ControllerViewNewInstance) controller;
    }
    
    /**
     * Method responsible for returning the Instance.
     * @return Instance.
     */
    public Instance getInstance() {
        return instance;
    }
}