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
        if (this.getView().getExportButton().equals(event.getSource()))
            this.action();
        else if (this.getView().getCancelButton().equals(event.getSource()))
            this.getView().dispose();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        if (F1 == event.getKeyCode())
            this.action();
        else if (F2 == event.getKeyCode())
            this.getView().dispose();
    }
    
    /**
     * Method responsible for Exporting.
     */
    private void action() {
        if (this.check())
            this.export();
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
        return (ViewExport) this.viewModal;
    }
}