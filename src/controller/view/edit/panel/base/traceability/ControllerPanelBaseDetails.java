package controller.view.edit.panel.base.traceability;

import controller.view.panel.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.edit.panel.base.traceability.PanelBaseDetails;

/**
 * <p>Class of Controller <b>ControllerPanelBaseDetails</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseDetails</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  15/01/2020
 * @see    controller.view.panel.ControllerPanel
 * @see    view.edit.panel.base.traceability.PanelBaseDetails
 */
public class ControllerPanelBaseDetails extends ControllerPanel {
    private final PanelBaseDetails panelBaseDetails;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Details.
     */
    public ControllerPanelBaseDetails(PanelBaseDetails panel) {
        super(panel);
        this.panelBaseDetails = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {}
    
    @Override
    public void keyPressed(KeyEvent event) {}
    
    @Override
    public void keyReleased(KeyEvent event) {}
}