package controller.view.edit.panel.traceability;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.edit.panel.base.traceability.PanelBaseTraceability;

/**
 * <p>Class of Controller <b>ControllerPanelBaseTraceability</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseTraceability</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  22/07/2019
 * @see    controller.view.ControllerPanel
 * @see    view.edit.panel.base.traceability.PanelBaseTraceability
 */
public class ControllerPanelBaseTraceability extends ControllerPanel {
    private final PanelBaseTraceability panelBaseTraceability;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Traceability.
     */
    public ControllerPanelBaseTraceability(PanelBaseTraceability panel) {
        super(panel);
        this.panelBaseTraceability = panel;
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
     * Method responsible for checking the Traceability.
     * @return Traceability checked.
     */
    private boolean check() {
        return    this.check(this.panelBaseTraceability.getNameTextField().getText())
               && this.check(this.panelBaseTraceability.getDescriptionTextField().getText());
    }
    
    /**
     * Method responsible for setting the Project Values.
     */
    public void update() {
        this.panelBaseTraceability.getTraceability().setName(this.panelBaseTraceability.getNameTextField().getText().trim());
        this.panelBaseTraceability.getTraceability().setDescription(this.panelBaseTraceability.getDescriptionTextField().getText().trim());
        this.panelBaseTraceability.getViewMenu().getPanelProject().getPanelTree().updateUI();
        this.panelBaseTraceability.getViewMenu().setSave(false);
    }
}