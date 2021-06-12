package controller.view.panel.new_.base.product.instance;

import model.structural.base.Diagram;
import model.structural.base.product.Instance;
import model.structural.base.product.Product;
import view.panel.new_.base.product.instance.PanelBaseInstance;

/**
 * <p>Class of Controller <b>ControllerPanelBaseInstance</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseInstance</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-07
 * @see    controller.view.panel.new_.base.product.instance.ControllerPanelBase
 * @see    view.panel.new_.base.product.instance.PanelBaseInstance
 */
public class ControllerPanelBaseInstance extends ControllerPanelBase {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Instance.
     */
    public ControllerPanelBaseInstance(PanelBaseInstance panel) {
        super(panel);
    }

    @Override
    protected void return_() {
        getViewNew().dispose();
    }
    
    @Override
    protected boolean check() {
        return check(getPanel().getProductComboBox(), "Select a Product!")
            && check(getPanel().getDiagramComboBox(), "Select a Diagram!")
            && check(getPanel().getNameTextField(),   "Name is required!");
    }
    
    @Override
    public void next() {
        update();
        getPanel().getPanelNew().addPanelBaseOptional();
    }
    
    @Override
    protected void update() {
        getInstance().setProduct((Product) getPanel().getProductComboBox().getSelectedItem());
        getInstance().setDiagram((Diagram) getPanel().getDiagramComboBox().getSelectedItem());
        getInstance().setName(getString(getPanel().getNameTextField()));
        super.refresh();
    }
    
    @Override
    protected Instance getInstance() {
        return getPanel().getInstance();
    }
    
    @Override
    public PanelBaseInstance getPanel() {
        return (PanelBaseInstance) panel;
    }
}