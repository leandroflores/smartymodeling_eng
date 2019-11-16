package controller.view.edit.panel.base;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.edit.panel.base.PanelBaseAssociation;

/**
 * <p>Class of Controller <b>ControllerPanelBaseAssociation</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseAssociation</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  07/11/2019
 * @see    controller.view.ControllerPanel
 * @see    view.edit.panel.base.PanelBaseAssociation
 */
public class ControllerPanelBaseAssociation extends ControllerPanel {
    private final PanelBaseAssociation panelBaseAssociation;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Association.
     */
    public ControllerPanelBaseAssociation(PanelBaseAssociation panel) {
        super(panel);
        this.panelBaseAssociation = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {}
    
    @Override
    public void keyPressed(KeyEvent event) {}
    
    @Override
    public void keyReleased(KeyEvent event) {}
}