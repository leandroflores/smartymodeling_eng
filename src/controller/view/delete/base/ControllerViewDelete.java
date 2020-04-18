package controller.view.delete.base;

import controller.view.ControllerViewModal;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.delete.base.ViewDelete;

/**
 * <p>Class of Controller <b>ControllerViewDelete</b>.</p>
 * <p>Class responsible for controlling the <b>ViewDelete</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-27
 * @see    controller.view.ControllerViewModal
 * @see    view.delete.base.ViewDelete
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
        if (this.getView().getYesButton().equals(event.getSource()))
            this.delete();
        else if (this.getView().getNotButton().equals(event.getSource()))
            this.getView().dispose();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        if (F1 == event.getKeyCode())
            this.delete();
        else if (F2 == event.getKeyCode())
            this.getView().dispose();
    }
    
    /**
     * Abstract Method responsible for Deleting the Object.
     */
    public abstract void delete();
    
    /**
     * Method responsible for closing the View Delete.
     */
    protected void close() {
        this.getView().getPanelModeling().getViewMenu().setSave(false);
        this.getView().getPanelModeling().getViewMenu().update();
        this.getView().dispose();
    }
    
    @Override
    public ViewDelete getView() {
        return (ViewDelete) this.viewModal;
    }
}