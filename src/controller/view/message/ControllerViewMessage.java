package controller.view.message;

import controller.view.ControllerViewModal;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.message.ViewMessage;

/**
 * <p>Class of Controller <b>ControllerViewMessage</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewMessage</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  20/05/2019
 * @see    controller.view.ControllerViewModal
 * @see    view.message.ViewMessage
 */
public class ControllerViewMessage extends ControllerViewModal {
    private final ViewMessage viewMessage;
    
    /**
     * Default constructor method of Class.
     * @param view View Parent.
     */
    public ControllerViewMessage(ViewMessage view) {
        super(view);
        this.viewMessage = view;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.viewMessage.getButtonOk().equals(event.getSource()) == true)
            this.viewMessage.dispose();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        if (ENTER == event.getKeyCode())
            this.viewMessage.dispose();
    }
}