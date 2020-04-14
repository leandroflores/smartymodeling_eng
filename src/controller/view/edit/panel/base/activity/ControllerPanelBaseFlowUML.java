package controller.view.edit.panel.base.activity;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.edit.panel.base.activity.PanelBaseFlowUML;

/**
 * <p>Class of Controller <b>ControllerPanelBaseFlowUML</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseFlowUML</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  04/11/2019
 * @see    controller.view.ControllerPanel
 * @see    view.edit.panel.base.activity.PanelBaseFlowUML
 */
public class ControllerPanelBaseFlowUML extends ControllerPanel {
    private final PanelBaseFlowUML panelBaseFlowUML;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Flow UML.
     */
    public ControllerPanelBaseFlowUML(PanelBaseFlowUML panel) {
        super(panel);
        this.panelBaseFlowUML = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.update();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
//        this.update();
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
        this.update();
    }
    
    /**
     * Method responsible for setting the Flow Values.
     */
    private void update() {
        this.panelBaseFlowUML.getFlowUML().setGuard(this.panelBaseFlowUML.getGuardTextField().getText().trim());
        this.panelBaseFlowUML.getFlowUML().setAction(this.panelBaseFlowUML.getActionTextField().getText().trim());
        this.panelBaseFlowUML.getFlowUML().setWeight(this.panelBaseFlowUML.getWeightTextField().getText().trim());
        this.panelBaseFlowUML.getViewMenu().setSave(false);
        this.panelBaseFlowUML.getViewMenu().getPanelProject().getPanelTree().updateUI();
        this.panelBaseFlowUML.getViewMenu().getPanelModeling().updateDiagram(this.panelBaseFlowUML.getDiagram());
        this.panelBaseFlowUML.getViewMenu().getPanelModeling().setSelected(this.panelBaseFlowUML.getDiagram(), this.panelBaseFlowUML.getFlowUML().getId());
        this.panelBaseFlowUML.getViewMenu().getPanelModeling().updateInstancePanels();
    }
}