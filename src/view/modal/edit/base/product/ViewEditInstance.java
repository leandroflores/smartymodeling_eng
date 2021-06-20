package view.modal.edit.base.product;

import controller.view.modal.edit.base.product.ControllerViewEditInstance;
import java.awt.Dimension;
import model.structural.base.product.Instance;
import view.modal.edit.ViewEdit;
import view.panel.base.product.PanelBaseInstance;
import view.panel.edit.base.product.PanelEditInstance;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditInstance</b>.</p>
 * <p>Class responsible for defining the <b>Instance Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-18
 * @see    controller.view.modal.edit.base.product.ControllerViewEditInstance
 * @see    model.structural.base.product.Instance
 * @see    view.modal.edit.ViewEdit
 */
public final class ViewEditInstance extends ViewEdit {
    private final Instance instance;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param instance Instance.
     */
    public ViewEditInstance(PanelModeling panel, Instance instance) {
        super(panel.getViewMenu());
        this.instance    = instance;
        this.controller = new ControllerViewEditInstance(this);
        this.title      = "Edit Instance Data";
        initComponents();
    }
    
    @Override
    protected Dimension getViewDimension() {
        return new Dimension(600, 350);
    }
    
    @Override
    protected PanelEditInstance createPanelEdit() {
        return new PanelEditInstance(view, instance);
    }
    
    @Override
    protected Dimension getPanelDimension() {
        return new Dimension(500, 225);
    }
    
    @Override
    public PanelEditInstance getPanelEdit() {
        return (PanelEditInstance) super.getPanelEdit();
    }
    
    /**
     * Method responsible for returning the Panel Base Instance.
     * @return Panel Base Instance.
     */
    public PanelBaseInstance getPanelBaseInstance() {
        return getPanelEdit().getPanelBaseInstance();
    }
    
    /**
     * Method responsible for returning the Instance.
     * @return Instance.
     */
    public Instance getInstance() {
        return instance;
    }
}