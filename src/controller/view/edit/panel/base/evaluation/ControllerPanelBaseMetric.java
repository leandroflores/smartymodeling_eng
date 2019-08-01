package controller.view.edit.panel.base.evaluation;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.edit.panel.base.evaluation.PanelBaseMetric;

/**
 * <p>Class of Controller <b>ControllerPanelBaseMetric</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseMetric</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  23/07/2019
 * @see    controller.view.ControllerPanel
 * @see    view.edit.panel.base.evaluation.PanelBaseMetric
 */
public class ControllerPanelBaseMetric extends ControllerPanel {
    private final PanelBaseMetric panelBaseMetric;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Metric.
     */
    public ControllerPanelBaseMetric(PanelBaseMetric panel) {
        super(panel);
        this.panelBaseMetric = panel;
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
        return this.check(this.panelBaseMetric.getNameTextField().getText())
            && this.check(this.panelBaseMetric.getOperationTextField().getText());
    }
    
    /**
     * Method responsible for setting the Metric Values.
     */
    public void update() {
        this.panelBaseMetric.getMetric().setName(this.panelBaseMetric.getNameTextField().getText().trim());
        this.panelBaseMetric.getMetric().setTarget(this.panelBaseMetric.getTargetComboBox().getSelectedItem().toString());
        this.panelBaseMetric.getMetric().setOperation(this.panelBaseMetric.getOperationTextField().getText().trim());
        this.panelBaseMetric.getViewMenu().getPanelProject().getPanelTree().updateUI();
        this.panelBaseMetric.getViewMenu().setSave(false);
    }
}