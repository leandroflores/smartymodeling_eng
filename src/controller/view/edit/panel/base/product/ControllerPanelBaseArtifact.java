package controller.view.edit.panel.base.product;

import controller.view.panel.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.panel.base.product.PanelBaseArtifact;

/**
 * <p>Class of Controller <b>ControllerPanelBaseArtifact</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseArtifact</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  25/10/2019
 * @see    controller.view.panel.ControllerPanel
 * @see    model.structural.base.product.Artifact
 * @see    view.panel.base.product.PanelBaseArtifact
 */
public class ControllerPanelBaseArtifact extends ControllerPanel {
    private final PanelBaseArtifact panelBaseArtifact;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Artifact.
     */
    public ControllerPanelBaseArtifact(PanelBaseArtifact panel) {
        super(panel);
        this.panelBaseArtifact = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {}

    @Override
    public void keyPressed(KeyEvent event) {}
}