package controller.view.modal.export;

import controller.view.modal.ControllerViewModal;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.modal.export.ViewExport;

/**
 * <p>Class of Controller <b>ControllerViewExport</b>.</p>
 * <p>Class responsible for controlling the <b>ViewExport</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-05
 * @see    controller.view.modal.ControllerViewModal
 * @see    view.modal.export.ViewExport
 */
public abstract class ControllerViewExport extends ControllerViewModal {
    
    /**
     * Default constructor method of Class.
     * @param view View Export.
     */
    public ControllerViewExport(ViewExport view) {
        super(view);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (getView().getExportButton().equals(event.getSource()))
            action();
        else if (getView().getCancelButton().equals(event.getSource()))
            getView().dispose();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        if (F1 == event.getKeyCode())
            action();
        else if (F2 == event.getKeyCode())
            getView().dispose();
    }
    
    /**
     * Method responsible for Exporting.
     */
    private void action() {
        if (check())
            export();
    }
    
    /**
     * Abstract method responsible for Checking the Values.
     * @return Values checked.
     */
    public abstract boolean check();
    
    /**
     * Abstract Method responsible for Exporting.
     */
    public abstract void export();
    
    
    @Override
    public ViewExport getView() {
        return (ViewExport) super.getView();
    }
}