package controller.view.edit.panel.base.traceability;

import controller.view.panel.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.panel.base.traceability.PanelBaseElements;

/**
 * <p>Class of Controller <b>ControllerPanelBaseElements</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseElements</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  22/07/2019
 * @see    controller.view.panel.ControllerPanel
 * @see    view.panel.base.traceability.PanelBaseElements
 */
public class ControllerPanelBaseElements extends ControllerPanel {
    private final PanelBaseElements panelBaseElements;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Elements.
     */
    public ControllerPanelBaseElements(PanelBaseElements panel) {
        super(panel);
        this.panelBaseElements = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.panelBaseElements.getDiagramComboBox().equals(event.getSource()))
            this.panelBaseElements.updateValues();
        else if (this.panelBaseElements.getAddElementButton().equals(event.getSource()))
            this.panelBaseElements.addElement();
        else if (this.panelBaseElements.getDelElementButton().equals(event.getSource()))
            this.panelBaseElements.delElement();
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
     * Method responsible for setting the Project Values.
     */
    private void update() {
        this.panelBaseElements.getViewMenu().getPanelProject().getPanelTree().updateUI(); 
        this.panelBaseElements.getViewMenu().setSave(false);
    }
}