package controller.view.edit.panel.base;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.edit.panel.base.PanelBaseElement;
import view.edit.panel.base.PanelBaseStereotype;

/**
 * <p>Class of Controller <b>ControllerPanelBaseStereotype</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseStereotype</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  17/01/2020
 * @see    controller.view.ControllerPanel
 * @see    view.edit.panel.base.PanelBaseStereotype
 */
public class ControllerPanelBaseStereotype extends ControllerPanel {
    private final PanelBaseStereotype panelBaseStereotype;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Stereotype.
     */
    public ControllerPanelBaseStereotype(PanelBaseStereotype panel) {
        super(panel);
        this.panelBaseStereotype = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.update();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        if (this.panelBaseStereotype.getNameTextField().equals(event.getSource()))
            this.update();
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
        if (this.panelBaseStereotype.getNameTextField().equals(event.getSource()))
            this.update();
    }

    /**
     * Method responsible for checking the Diagram.
     * @return Diagram checked.
     */
    private boolean check() {
        return this.check(this.panelBaseStereotype.getNameTextField().getText());
    }
    
    /**
     * Method responsible for setting the Project Values.
     */
    private void update() {
        this.panelBaseStereotype.getStereotype().setName(this.panelBaseStereotype.getNameTextField().getText().trim());
        this.panelBaseStereotype.getViewMenu().getPanelProject().getPanelTree().updateUI();
        this.panelBaseStereotype.getViewMenu().getPanelModeling().updateModelingPanels();
        this.panelBaseStereotype.getViewMenu().setSave(false);
    }
}