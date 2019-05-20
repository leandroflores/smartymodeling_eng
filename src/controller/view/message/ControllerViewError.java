package controller.view.message;

import controller.view.ControllerViewModal;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.message.ViewError;

/**
 * <p>Class of Controller <b>ControllerViewError</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewError</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  20/05/2019
 * @see    controller.view.ControllerViewModal
 * @see    view.message.ViewError
 */
public class ControllerViewError extends ControllerViewModal {
    private final ViewError viewError;
    
    /**
     * Default constructor method of Class.
     * @param view View Error.
     */
    public ControllerViewError(ViewError view) {
        super(view);
        this.viewError = view;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.viewError.getButtonOk().equals(event.getSource()))
            this.viewError.dispose();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        if (ENTER == event.getKeyCode())
            this.viewError.dispose();
    }
}