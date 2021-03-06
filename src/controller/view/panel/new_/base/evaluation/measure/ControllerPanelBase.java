package controller.view.panel.new_.base.evaluation.measure;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.text.JTextComponent;
import model.structural.base.evaluation.Measure;
import view.modal.message.ViewError;
import view.modal.new_.base.evaluation.ViewNewMeasure;
import view.panel.new_.base.evaluation.measure.PanelBase;

/**
 * <p>Class of Controller <b>ControllerPanelBase</b>.</p>
 * <p>Class responsible for controlling the <b>Measure Panel Base</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-16
 * @see    controller.view.panel.base.ControllerPanelBase
 * @see    view.panel.new_.base.evaluation.measure.PanelBase
 */
public abstract class ControllerPanelBase extends controller.view.panel.base.ControllerPanelBase {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base.
     */
    public ControllerPanelBase(PanelBase panel) {
        super(panel);
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if (getPanel().getReturnButton().equals(event.getSource()))
            return_();
        else if (getPanel().getNextButton().equals(event.getSource()))
            advance();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {}
    
    @Override
    public void keyReleased(KeyEvent event) {
        update();
    }
    
    /**
     * Method responsible for Return the Panel Base Instance.
     */
    protected abstract void return_();
    
    /**
     * Method responsible for checking a required JTextComponent.
     * @param  textComponent JTextComponent.
     * @param  message Error Message.
     * @return JTextComponent checked.
     */
    protected boolean check(JTextComponent textComponent, String message) {
        String text = textComponent.getText().trim();
        if (!check(text)) {
            new ViewError(getViewNew(), message).setVisible(true);
            textComponent.requestFocus();
            return false;
        }
        return true;
    }
    
    /**
     * Method responsible for checking a required JComboBox.
     * @param  comboBox JComboBox.
     * @param  message Error Message.
     * @return JComboBox checked.
     */
    protected boolean check(JComboBox comboBox, String message) {
        Object selected = comboBox.getSelectedItem();
        if (selected == null) {
            new ViewError(getViewNew(), message).setVisible(true);
            comboBox.requestFocus();
            return false;
        }
        return true;
    }
    
    /**
     * Method responsible for checking a Date JTextComponent.
     * @param  textComponent JTextComponent.
     * @param  message Error Message.
     * @return JTextComponent checked.
     */
    protected boolean checkDate(JTextComponent textComponent, String message) {
        String text = textComponent.getText().trim();
        if (!checkUSDate(text)) {
            new ViewError(getViewNew(), message).setVisible(true);
            textComponent.requestFocus();
            return false;
        }
        return true;
    }
    
    /**
     * Method responsible for checking the Panel Base Instance to Next.
     * @return Panel Base Instance is checked.
     */
    protected abstract boolean check();
    
    /**
     * Method responsible for Next the Panel Base Instance.
     */
    protected abstract void next();
    
    /**
     * Method responsible for Advancing the Panel Base Instance.
     */
    protected void advance() {
        if (check())
            next();
    }
    
    /**
     * Method responsible for returning the Measure.
     * @return Measure.
     */
    protected Measure getMeasure() {
        return getViewNew().getMeasure();
    }
    
    /**
     * Method responsible for returning the View New Measure.
     * @return View New Measure.
     */
    protected ViewNewMeasure getViewNew() {
        return getPanel().getViewNew();
    }
    
    @Override
    public PanelBase getPanel() {
        return (PanelBase) panel;
    }
}