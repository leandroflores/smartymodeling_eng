package controller.view.modal.message;

import controller.view.modal.ControllerViewModal;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.modal.message.ViewConfirmation;

/**
 * <p>Class of Controller <b>ControllerViewConfirmation</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewConfirmation</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  20/05/2019
 * @see    controller.view.modal.ControllerViewModal
 * @see    view.modal.message.ViewConfirmation
 */
public class ControllerViewConfirmation extends ControllerViewModal {
    private final ViewConfirmation viewConfirmation;
    
    /**
     * Default constructor method of Class.
     * @param view View Confirmation.
     */
    public ControllerViewConfirmation(ViewConfirmation view) {
        super(view);
        this.viewConfirmation = view;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.viewConfirmation.getButtonYes().equals(event.getSource()))
            this.viewConfirmation.getView().operation();
        else if (this.viewConfirmation.getButtonNo().equals(event.getSource()))
            this.viewConfirmation.dispose();
        else if (this.viewConfirmation.getButtonBack().equals(event.getSource()))
            this.viewConfirmation.dispose();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        if (ENTER == event.getKeyCode())
            this.viewConfirmation.dispose();
    }
}