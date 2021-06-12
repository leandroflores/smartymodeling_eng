package controller.view.modal;

import controller.Controller;
import funct.FunctDate;
import funct.FunctString;
import java.awt.event.KeyEvent;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import view.modal.ViewModal;
import view.modal.message.ViewError;

/**
 * <p>Class of Controller <b>ControllerViewModal</b>.</p>
 * <p>Class responsible for controlling the <b>Modal View</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-20
 * @see    controller.Controller
 * @see    view.modal.ViewModal
 */
public abstract class ControllerViewModal extends Controller {
    protected final ViewModal viewModal;
    
    /**
     * Default contructor method of Class.
     * @param view ViewModal.
     */
    public ControllerViewModal(ViewModal view) {
        viewModal = view;
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        if (ESC == event.getKeyCode())
            viewModal.dispose();
    }
    
    /**
     * Method responsible for checking a required JTextComponent.
     * @param  textComponent JTextComponent.
     * @param  message Error Message.
     * @return JTextComponent checked.
     */
    protected boolean check(JTextComponent textComponent, String message) {
        String text = textComponent.getText().trim();
        if (!check(text)) {
            new ViewError(viewModal, message).setVisible(true);
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
            new ViewError(viewModal, message).setVisible(true);
            comboBox.requestFocus();
            return false;
        }
        return true;
    }
    
    /**
     * Method responsible for checking a Year JTextComponent.
     * @param  textComponent JTextComponent.
     * @param  message Error Message.
     * @return JTextComponent checked.
     */
    protected boolean checkYear(JTextComponent textComponent, String message) {
        String text = textComponent.getText().trim();
        if (!checkYear(text)) {
            new ViewError(viewModal, message).setVisible(true);
            textComponent.requestFocus();
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
        if (!checkDate(text)) {
            new ViewError(viewModal, message).setVisible(true);
            textComponent.requestFocus();
            return false;
        }
        return true;
    }
    
    /**
     * Method responsible for checking a Number JTextComponent.
     * @param  textComponent JTextComponent.
     * @param  message Error Message.
     * @return JTextComponent checked.
     */
    protected boolean checkNumbers(JTextComponent textComponent, String message) {
        String text = textComponent.getText().trim();
        if (!checkNumbers(text)) {
            new ViewError(viewModal, message).setVisible(true);
            textComponent.requestFocus();
            return false;
        }
        return true;
    }

    /**
     * Method responsible for returning the Text of JTextComponent.
     * @param  textComponent JTextComponent.
     * @return String of JTextComponent.
     */
    protected String getString(JTextComponent textComponent) {
        return new FunctString().removeSpecialChars(textComponent.getText().toUpperCase().trim());
    }
    
    /**
     * Method responsible for returning the Date of JTextComponent.
     * @param  textComponent JTextComponent.
     * @return Date of JTextComponent.
     */
    protected Date getDate(JTextField textComponent) {
        return new FunctDate().createDate(textComponent.getText().trim());
    }
    
    /**
     * Method responsible for returning the Integer of JTextComponent.
     * @param  textComponent JTextComponent.
     * @return Integer of JTextComponent.
     */
    protected Integer getInteger(JTextComponent textComponent) {
        return Integer.parseInt(textComponent.getText().trim());
    }
    
    /**
     * Method responsible for returning the Double of JTextComponent.
     * @param  textComponent JTextComponent.
     * @return Double of JTextComponent.
     */
    protected Double getDouble(JTextComponent textComponent) {
        return Double.parseDouble(textComponent.getText().trim());
    }
    
    /**
     * Method responsible for returning the Value of JComboBox.
     * @param  comboBox JComboBox.
     * @return Value of JComboBox.
     */
    protected String getValue(JComboBox comboBox) {
        return comboBox.getSelectedItem().toString();
    }
    
    /**
     * Method responsible for returning the View.
     * @return View.
     */
    public ViewModal getView() {
        return viewModal;
    }
}