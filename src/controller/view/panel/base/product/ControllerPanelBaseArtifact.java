package controller.view.panel.base.product;

import controller.view.panel.base.ControllerPanelBase;
import view.panel.base.product.PanelBaseArtifact;

/**
 * <p>Class of Controller <b>ControllerPanelBaseArtifact</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseArtifact</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-25
 * @see    controller.view.panel.base.ControllerPanelBase
 * @see    model.structural.base.product.Artifact
 * @see    view.panel.base.product.PanelBaseArtifact
 */
public class ControllerPanelBaseArtifact extends ControllerPanelBase {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Artifact.
     */
    public ControllerPanelBaseArtifact(PanelBaseArtifact panel) {
        super(panel);
    }

    @Override
    protected void update() {}
}