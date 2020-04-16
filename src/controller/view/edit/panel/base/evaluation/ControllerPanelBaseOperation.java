package controller.view.edit.panel.base.evaluation;

import controller.view.panel.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.edit.panel.base.evaluation.PanelBaseOperation;

/**
 * <p>Class of Controller <b>ControllerPanelBaseOperation</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseOperation</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  20/08/2019
 * @see    controller.view.panel.ControllerPanel
 * @see    view.edit.panel.base.evaluation.PanelBaseOperation
 */
public class ControllerPanelBaseOperation extends ControllerPanel {
    private final PanelBaseOperation panelBaseOperation;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Operation.
     */
    public ControllerPanelBaseOperation(PanelBaseOperation panel) {
        super(panel);
        this.panelBaseOperation = panel;
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
     * Method responsible for checking the Metric.
     * @return Metric checked.
     */
    private boolean check() {
        return this.check(this.panelBaseOperation.getOperationTextField().getText());
    }
    
    /**
     * Method responsible for setting the Metric Values.
     */
    public void update() {
        this.panelBaseOperation.setTarget();
        this.panelBaseOperation.getMetric().setOperation(this.panelBaseOperation.getOperationTextField().getText().trim());
        this.panelBaseOperation.getViewMenu().getPanelProject().getPanelTree().updateUI();
        this.panelBaseOperation.getViewMenu().setSave(false);
    }
}