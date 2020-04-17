package controller.view.panel.base.product;

import controller.view.panel.base.ControllerPanelBase;
import model.structural.base.product.Instance;
import view.panel.base.product.PanelBaseInstance;

/**
 * <p>Class of Controller <b>ControllerPanelBaseInstance</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseInstance</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-25
 * @see    controller.view.panel.base.ControllerPanelBase
 * @see    view.panel.base.product.PanelBaseInstance
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
    protected void refresh() {
        this.getPanelTree().updateNode(this.getInstance());
        this.getPanelModeling().updateTab(this.getInstance());
        super.refresh();
    }
    
    /**
     * Method responsible for checking the Product.
     * @return Product checked.
     */
    protected boolean check() {
        return this.check(this.getPanel().getNameTextField().getText());
    }
    
    @Override
    protected void update() {
        if (this.check()) {
            this.getInstance().setName(this.getString(this.getPanel().getNameTextField()));
            this.refresh();
        }
    }
    
    /**
     * Method responsible for returning the Instance.
     * @return Instance.
     */
    private Instance getInstance() {
        return this.getPanel().getInstance();
    }
    
    @Override
    public PanelBaseInstance getPanel() {
        return (PanelBaseInstance) this.panel;
    }
}