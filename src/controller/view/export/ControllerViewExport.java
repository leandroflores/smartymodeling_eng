package controller.view.export;

import controller.view.ControllerViewModal;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.export.ViewExport;

/**
 * <p>Class of Controller <b>ControllerViewExport</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewExport</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  05/11/2019
 * @see    controller.view.ControllerViewModal
 * @see    view.export.ViewExport
 */
public abstract class ControllerViewExport extends ControllerViewModal {
    protected ViewExport viewExport;
    
    /**
     * Default constructor method of Class.
     * @param view View Export.
     */
    public ControllerViewExport(ViewExport view) {
        super(view);
        this.viewExport = view;
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
    public void actionPerformed(ActionEvent event) {
        if (this.viewExport.getExportButton().equals(event.getSource())) {
            if (this.check())
                this.export();
        }else if (this.viewExport.getCancelButton().equals(event.getSource())) {
            this.viewExport.dispose();
        }
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        if (F1 == event.getKeyCode()) {
            if (this.check())
                this.export();
        }else if (F2 == event.getKeyCode()) {
            this.viewExport.dispose();
        }
    }
}