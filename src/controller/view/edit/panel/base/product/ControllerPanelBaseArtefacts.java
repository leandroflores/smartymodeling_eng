package controller.view.edit.panel.base.product;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.edit.panel.base.product.PanelBaseArtefacts;

/**
 * <p>Class of Controller <b>ControllerPanelBaseArtefacts</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseArtefacts</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  09/10/2019
 * @see    controller.view.ControllerPanel
 * @see    model.structural.base.product.Instance
 * @see    view.edit.panel.base.product.PanelBaseArtefacts
 */
public class ControllerPanelBaseArtefacts extends ControllerPanel {
    private final PanelBaseArtefacts panelBaseArtefacts;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Artefacts.
     */
    public ControllerPanelBaseArtefacts(PanelBaseArtefacts panel) {
        super(panel);
        this.panelBaseArtefacts = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.panelBaseArtefacts.getBackButton().equals(event.getSource()))
            this.panelBaseArtefacts.getViewNewInstance().removePanelBaseArtefacts();
        else if (this.panelBaseArtefacts.getNextButton().equals(event.getSource()))
            this.newInstance();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {}
    
    /**
     * Method responsible for creating a New Instance.
     */
    public void newInstance() {
        this.panelBaseArtefacts.getInstance().setName(this.panelBaseArtefacts.getNameTextField().getText().trim());
        this.panelBaseArtefacts.getViewNewInstance().addNewInstance();
    }
}