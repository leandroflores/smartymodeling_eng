package controller.view.panel.base.product;

import controller.view.panel.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.panel.base.product.PanelBaseRelationship;

/**
 * <p>Class of Controller <b>ControllerPanelBaseRelationship</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseRelationship</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  14/11/2019
 * @see    controller.view.panel.ControllerPanel
 * @see    model.structural.base.product.Relationship
 * @see    view.panel.base.product.PanelBaseRelationship
 */
public class ControllerPanelBaseRelationship extends ControllerPanel {
    private final PanelBaseRelationship panelBaseRelationship;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Relationship.
     */
    public ControllerPanelBaseRelationship(PanelBaseRelationship panel) {
        super(panel);
        this.panelBaseRelationship = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {}

    @Override
    public void keyPressed(KeyEvent event) {}
}