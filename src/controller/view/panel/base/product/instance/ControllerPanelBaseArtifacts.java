package controller.view.panel.base.product.instance;

import model.structural.base.product.Instance;
import view.panel.base.product.instance.PanelBaseArtifacts;

/**
 * <p>Class of Controller <b>ControllerPanelBaseArtifacts</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseArtifacts</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-09
 * @see    controller.view.panel.base.product.instance.ControllerPanelBase
 * @see    view.panel.base.product.instance.PanelBaseArtifacts
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
    protected void return_() {
        this.getViewNew().removePanelBaseArtifacts();
    }
    
    @Override
    protected boolean check() {
        return this.check(this.getPanel().getNameTextField(), "Name is required!");
    }
    
    @Override
    public void next() {
        this.update();
        this.getViewNew().getController().insert();
    }
    
    @Override
    protected void update() {
        this.getInstance().setName(this.getString(this.getPanel().getNameTextField()));
    }
    
    @Override
    protected Instance getInstance() {
        return this.getPanel().getInstance();
    }
    
    @Override
    public PanelBaseArtifacts getPanel() {
        return (PanelBaseArtifacts) this.panel;
    }
}