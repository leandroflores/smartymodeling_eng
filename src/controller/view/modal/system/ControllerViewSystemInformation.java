package controller.view.modal.system;

import controller.view.modal.ControllerViewModal;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.modal.system.ViewSystemInformation;

/**
 * <p>Class of Controller <b>ControllerViewSystemInformation</b>.</p>
 * <p>Class responsible for controlling the <b>ViewSystemInformation</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-23
 * @see    controller.view.modal.ControllerViewModal
 * @see    view.modal.system.ViewSystemInformation
 */
public class ControllerViewSystemInformation extends ControllerViewModal {
    
    /**
     * Default constructor method of Class.
     * @param view View System Information.
     */
    public ControllerViewSystemInformation(ViewSystemInformation view) {
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
        if (ENTER == event.getKeyCode()) {
            if (getView().getFocusOwner().equals(getView().getButtonOk()))
                getView().dispose();
        }
    }
    
    @Override
    public ViewSystemInformation getView() {
        return (ViewSystemInformation) super.getView();
    }
}