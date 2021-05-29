package controller.view.modal.message;

import controller.view.modal.ControllerViewModal;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.modal.message.ViewConfirmation;

/**
 * <p>Class of Controller <b>ControllerViewConfirmation</b>.</p>
 * <p>Class responsible for controlling the <b>ViewConfirmation</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-20
 * @see    controller.view.modal.ControllerViewModal
 * @see    view.modal.message.ViewConfirmation
 */
public class ControllerViewConfirmation extends ControllerViewModal {
    
    /**
     * Default constructor method of Class.
     * @param view View Confirmation.
     */
    public ControllerViewConfirmation(ViewConfirmation view) {
        super(view);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (getView().getButtonYes().equals(event.getSource()))
            getView().getView().operation();
        else if (getView().getButtonNo().equals(event.getSource()))
            getView().dispose();
        else if (getView().getButtonBack().equals(event.getSource()))
            getView().dispose();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        if (ENTER == event.getKeyCode())
            getView().dispose();
    }
    
    @Override
    public ViewConfirmation getView() {
        return (ViewConfirmation) super.getView();
    }
}