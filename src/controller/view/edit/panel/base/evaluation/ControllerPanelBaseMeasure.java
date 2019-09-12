package controller.view.edit.panel.base.evaluation;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.edit.panel.base.evaluation.PanelBaseMeasure;
import view.edit.panel.base.evaluation.PanelBaseMetric;

/**
 * <p>Class of Controller <b>ControllerPanelBaseMeasure</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseMeasure</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  04/09/2019
 * @see    controller.view.ControllerPanel
 * @see    view.edit.panel.base.evaluation.PanelBaseMeasure
 */
public class ControllerPanelBaseMeasure extends ControllerPanel {
    private final PanelBaseMeasure panelBaseMeasure;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Measure.
     */
    public ControllerPanelBaseMeasure(PanelBaseMeasure panel) {
        super(panel);
        this.panelBaseMeasure = panel;
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
     * Method responsible for checking the Measure.
     * @return Measure checked.
     */
    private boolean check() {
        return this.check(this.panelBaseMeasure.getNameTextField().getText())
            && this.checkDate(this.panelBaseMeasure.getDateTextField().getText());
    }
    
    /**
     * Method responsible for setting the Measure Values.
     */
    public void update() {
        this.panelBaseMeasure.getMeasure().setName(this.panelBaseMeasure.getNameTextField().getText().trim());
        this.panelBaseMeasure.getMeasure().setDate(this.panelBaseMeasure.getDateTextField().getText().trim());
        this.panelBaseMeasure.setMetric();
        this.panelBaseMeasure.getViewMenu().getPanelProject().getPanelTree().updateUI();
        this.panelBaseMeasure.getViewMenu().setSave(false);
    }
}