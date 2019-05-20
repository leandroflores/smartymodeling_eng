package controller.view;

import controller.Controller;
import funct.FunctDate;
import funct.FunctString;
import java.awt.event.KeyEvent;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import view.ViewModal;
import view.message.ViewError;

/**
 * <p>Class of Controller <b>ControllerViewModal</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the ViewModals of SMartyModeling.</p>
 * @author Leandro
 * @since  20/05/2019
 * @see    controller.Controller
 * @see    view.ViewModal
 */
public abstract class ControllerViewModal extends Controller {
    private final ViewModal viewModal;
    
    /**
     * Default contructor method of Class.
     * @param viewModal ViewModal.
     */
    public ControllerViewModal(ViewModal viewModal) {
        this.viewModal = viewModal;
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        if (ESC == event.getKeyCode())
            this.viewModal.dispose();
    }
    
    /**
     * Method responsible for checking a required JTextComponent.
     * @param  textComponent JTextComponent.
     * @param  message Error Message.
     * @return JTextComponent checked.
     */
    protected boolean check(JTextComponent textComponent, String message) {
        String text = textComponent.getText().trim();
        if (this.check(text) == false) {
            new ViewError(this.viewModal, message).setVisible(true);
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
        if (comboBox.getSelectedIndex() == 0) {
            new ViewError(this.viewModal, message).setVisible(true);
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
        if (this.checkYear(text) == false) {
            new ViewError(this.viewModal, message).setVisible(true);
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
        if (this.checkDate(text) == false) {
            new ViewError(this.viewModal, message).setVisible(true);
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
        if (this.checkNumbers(text) == false) {
            new ViewError(this.viewModal, message).setVisible(true);
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
     * Method responsible for returning the Value of JComboBox.
     * @param  comboBox JComboBox.
     * @return Value of JComboBox.
     */
    protected String getValue(JComboBox comboBox) {
        return comboBox.getSelectedItem().toString();
    }
}