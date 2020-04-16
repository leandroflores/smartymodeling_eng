package controller.view.edit.panel.base.feature.association;

import controller.view.panel.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.edit.panel.base.feature.association.PanelBaseConnection;

/**
 * <p>Class of Controller <b>ControllerPanelBaseConnection</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseConnection</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  09/04/2020
 * @see    controller.view.panel.ControllerPanel
 * @see    model.structural.diagram.feature.base.association.Connection
 * @see    view.edit.panel.base.feature.association.PanelBaseConnection
 */
public class ControllerPanelBaseConnection extends ControllerPanel {
    private final PanelBaseConnection panelBaseConnection;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Connection.
     */
    public ControllerPanelBaseConnection(PanelBaseConnection panel) {
        super(panel);
        this.panelBaseConnection = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.update();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        this.update();
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
        this.update();
    }
    
    /**
     * Method responsible for setting the Association UML Values.
     */
    private void update() {
        this.panelBaseConnection.getConnection().setCategory(this.panelBaseConnection.getCategoryComboBox().getSelectedItem().toString());
        this.panelBaseConnection.getViewMenu().getPanelProject().getPanelTree().updateUI();
        this.panelBaseConnection.getViewMenu().getPanelModeling().updateModelingPanels();
        this.panelBaseConnection.getViewMenu().setSave(false);
    }
}