package controller.view.modal.delete;

import controller.view.modal.ControllerViewModal;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.modal.delete.ViewDelete;

/**
 * <p>Class of Controller <b>ControllerViewDelete</b>.</p>
 * <p>Class responsible for controlling the <b>ViewDelete</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-27
 * @see    controller.view.modal.ControllerViewModal
 * @see    view.modal.delete.ViewDelete
 */
public abstract class ControllerViewDelete extends ControllerViewModal {
    
    /**
     * Default constructor method of Class.
     * @param view View Delete.
     */
    public ControllerViewDelete(ViewDelete view) {
        super(view);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (getView().getYesButton().equals(event.getSource()))
            delete();
        else if (getView().getNotButton().equals(event.getSource()))
            getView().dispose();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        if (F1 == event.getKeyCode())
            delete();
        else if (F2 == event.getKeyCode())
            getView().dispose();
    }
    
    /**
     * Abstract Method responsible for Deleting the Object.
     */
    public abstract void delete();
    
    /**
     * Method responsible for closing the View Delete.
     */
    protected void close() {
        getView().getPanelModeling().getViewMenu().update();
        getView().getPanelModeling().getViewMenu().setSave(false);
        getView().dispose();
    }
    
    @Override
    public ViewDelete getView() {
        return (ViewDelete) super.getView();
    }
}