package controller.view.edit.panel.base.product.instance;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.edit.panel.base.product.instance.PanelBaseArtifacts;

/**
 * <p>Class of Controller <b>ControllerPanelBaseArtifacts</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseArtifacts</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  09/10/2019
 * @see    controller.view.ControllerPanel
 * @see    model.structural.base.product.Instance
 * @see    view.edit.panel.base.product.instance.PanelBaseArtifacts
 */
public class ControllerPanelBaseArtifacts extends ControllerPanel {
    private final PanelBaseArtifacts panelBaseArtifacts;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Artifacts.
     */
    public ControllerPanelBaseArtifacts(PanelBaseArtifacts panel) {
        super(panel);
        this.panelBaseArtifacts = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.panelBaseArtifacts.getBackButton().equals(event.getSource()))
            this.panelBaseArtifacts.getViewNewInstance().removePanelBaseArtifacts();
        else if (this.panelBaseArtifacts.getNextButton().equals(event.getSource()))
            this.newInstance();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {}
    
    /**
     * Method responsible for creating a New Instance.
     */
    public void newInstance() {
        this.panelBaseArtifacts.getInstance().setName(this.panelBaseArtifacts.getNameTextField().getText().trim());
        this.panelBaseArtifacts.getViewNewInstance().getController().insert();
    }
}