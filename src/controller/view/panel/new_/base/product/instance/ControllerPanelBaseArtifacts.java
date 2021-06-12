package controller.view.panel.new_.base.product.instance;

import java.awt.event.ActionEvent;
import model.structural.base.product.Instance;
import model.structural.base.product.Product;
import view.panel.new_.base.product.instance.PanelBaseArtifacts;

/**
 * <p>Class of Controller <b>ControllerPanelBaseArtifacts</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseArtifacts</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-09
 * @see    controller.view.panel.new_.base.product.instance.ControllerPanelBase
 * @see    view.panel.new_.base.product.instance.PanelBaseArtifacts
 */
public class ControllerPanelBaseArtifacts extends ControllerPanelBase {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Artifacts.
     */
    public ControllerPanelBaseArtifacts(PanelBaseArtifacts panel) {
        super(panel);
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        if (getPanel().getProductComboBox().equals(event.getSource()))
            getInstance().setProduct((Product) getPanel().getProductComboBox().getSelectedItem());
    }
    
    @Override
    protected void return_() {
        getPanel().getPanelNew().removePanelBaseArtifacts();
    }
    
    @Override
    protected boolean check() {
        return check(getPanel().getNameTextField(), "Name is required!");
    }
    
    @Override
    public void next() {}
    
    @Override
    protected void update() {
        getInstance().setName(getString(getPanel().getNameTextField()));
    }
    
    @Override
    protected Instance getInstance() {
        return getPanel().getInstance();
    }
    
    @Override
    public PanelBaseArtifacts getPanel() {
        return (PanelBaseArtifacts) panel;
    }
}