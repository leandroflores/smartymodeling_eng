package controller.view.edit.panel.base.evaluation;

import controller.view.panel.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.panel.base.evaluation.PanelBaseMeasure;
import view.message.ViewError;

/**
 * <p>Class of Controller <b>ControllerPanelBaseMeasure</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseMeasure</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  04/09/2019
 * @see    controller.view.panel.ControllerPanel
 * @see    view.panel.base.evaluation.PanelBaseMeasure
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
        if (this.panelBaseMeasure.getBackButton().equals(event.getSource()))
            this.panelBaseMeasure.getViewNewMeasure().dispose();
        else if (this.panelBaseMeasure.getNextButton().equals(event.getSource()))
            this.next();
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
     * Method responsible for checking the Measure Name.
     * @return Measure Name is checked.
     */
    private boolean checkName() {
        if (!this.check(this.panelBaseMeasure.getNameTextField().getText())) {
            new ViewError(this.panelBaseMeasure.getViewNewMeasure(), "Name is required!").setVisible(true);
            this.panelBaseMeasure.getNameTextField().requestFocus();
            return false;
        }
        return true;
    }
    
    /**
     * Method responsible for checking the Measure Date.
     * @return Measure Date is checked.
     */
    private boolean checkDate() {
        if (!this.checkDate(this.panelBaseMeasure.getDateTextField().getText())) {
            new ViewError(this.panelBaseMeasure.getViewNewMeasure(), "Type a Date (DD/MM/YYYY)!").setVisible(true);
            this.panelBaseMeasure.getDateTextField().requestFocus();
            return false;
        }
        return true;
    }
    
    /**
     * Method responsible for checking the Measure.
     * @return Measure checked.
     */
    private boolean check() {
        return this.checkName()
            && this.checkDate();
    }
    
    /**
     * Method responsible for going to Next Panel.
     */
    public void next() {
        if (this.check()) {
            this.update();
            this.panelBaseMeasure.getViewNewMeasure().addPanelBaseTarget();
        }
    }
    
    /**
     * Method responsible for setting the Measure Values.
     */
    public void update() {
        this.panelBaseMeasure.getMeasure().setName(this.panelBaseMeasure.getNameTextField().getText().trim());
        this.panelBaseMeasure.getMeasure().setDate(this.panelBaseMeasure.getDateTextField().getText().trim());
        this.panelBaseMeasure.setMetric();
        this.panelBaseMeasure.getViewNewMeasure().getViewMenu().getPanelProject().getPanelTree().updateUI();
        this.panelBaseMeasure.getViewNewMeasure().getViewMenu().setSave(false);
    }
}