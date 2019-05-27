package controller.view.delete;

import controller.view.ControllerViewModal;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.delete.ViewDelete;

/**
 * <p>Class of Controller <b>ControllerViewDelete</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewDelete</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  27/05/2019
 * @see    controller.view.ControllerViewModal
 * @see    view.delete.ViewDelete
 */
public abstract class ControllerViewDelete extends ControllerViewModal {
    protected ViewDelete viewDelete;
    
    /**
     * Default constructor method of Class.
     * @param view View Delete.
     */
    public ControllerViewDelete(ViewDelete view) {
        super(view);
        this.viewDelete = view;
    }

    /**
     * Abstract Method responsible for deleting.
     */
    public abstract void delete();
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.viewDelete.getYesButton().equals(event.getSource()))
            this.delete();
        else if (this.viewDelete.getNotButton().equals(event.getSource()))
            this.viewDelete.dispose();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        if (F1 == event.getKeyCode())
            this.delete();
        else if (F2 == event.getKeyCode())
            this.viewDelete.dispose();
    }
}