package controller.view.modal.message;

import controller.view.modal.ControllerViewModal;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.modal.message.ViewMessage;

/**
 * <p>Class of Controller <b>ControllerViewMessage</b>.</p>
 * <p>Class responsible for controlling the <b>ViewMessage</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-20
 * @see    controller.view.modal.ControllerViewModal
 * @see    view.modal.message.ViewMessage
 */
public class ControllerViewMessage extends ControllerViewModal {
    
    /**
     * Default constructor method of Class.
     * @param view View Message.
     */
    public ControllerViewMessage(ViewMessage view) {
        super(view);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (getView().getButtonOk().equals(event.getSource()))
            getView().dispose();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        if (ENTER == event.getKeyCode())
            getView().dispose();
    }
    
    @Override
    public ViewMessage getView() {
        return (ViewMessage) super.getView();
    }
}