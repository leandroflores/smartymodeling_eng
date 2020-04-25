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
        this.getViewNew().dispose();
    }
    
    @Override
    protected boolean check() {
        return this.check(this.getPanel().getProductComboBox(), "Select a Product!")
            && this.check(this.getPanel().getDiagramComboBox(), "Select a Diagram!");
    }
    
    @Override
    public void next() {
        this.update();
        this.getPanel().getPanelNew().addPanelBaseOptional();
    }
    
    @Override
    protected void update() {
        this.getInstance().setProduct((Product) this.getPanel().getProductComboBox().getSelectedItem());
        this.getInstance().setDiagram((Diagram) this.getPanel().getDiagramComboBox().getSelectedItem());
        this.getInstance().setName(this.getString(this.getPanel().getNameTextField()));
        super.refresh();
    }
    
    @Override
    protected Instance getInstance() {
        return this.getPanel().getInstance();
    }
    
    @Override
    public PanelBaseInstance getPanel() {
        return (PanelBaseInstance) this.panel;
    }
}