package controller.view.system;

import controller.view.ControllerViewModal;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.system.ViewSystemInformation;

/**
 * <p>Class of Controller <b>ControllerViewSystemInformation</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewSystemInformation</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  23/05/2019
 * @see    controller.view.ControllerViewModal
 * @see    view.system.ViewSystemInformation
 */
public class ControllerViewSystemInformation extends ControllerViewModal {
    private final ViewSystemInformation viewSistemaInformation;
    
    /**
     * Default constructor method of Class.
     * @param view View System Information.
     */
    public ControllerViewSystemInformation(ViewSystemInformation view) {
        super(view);
        this.viewSistemaInformation = view;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.viewSistemaInformation.getButtonOk().equals(event.getSource()))
            this.viewSistemaInformation.dispose();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        if (ENTER == event.getKeyCode()) {
            if (this.viewSistemaInformation.getFocusOwner().equals(this.viewSistemaInformation.getButtonOk()))
                this.viewSistemaInformation.dispose();
        }
    }
}