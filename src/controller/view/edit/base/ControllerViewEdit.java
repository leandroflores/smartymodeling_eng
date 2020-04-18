package controller.view.edit.base;

import controller.view.ControllerViewModal;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.edit.base.ViewEdit;

/**
 * <p>Class of Controller <b>ControllerViewEdit</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewEdit</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  28/05/2019
 * @see    controller.view.ControllerViewModal
 * @see    view.edit.base.ViewEdit
 */
public abstract class ControllerViewEdit extends ControllerViewModal {
    protected ViewEdit viewEdit;
    
    /**
     * Default constructor method of Class.
     * @param view View Edit.
     */
    public ControllerViewEdit(ViewEdit view) {
        super(view);
        this.viewEdit = view;
    }

    /**
     * Abstract method responsible for Checking Values.
     * @return Values checked.
     */
    public abstract boolean check();
    
    /**
     * Abstract Method responsible for Saving Values.
     */
    public abstract void save();
    
    /**
     * Method responsible for closing the View Edit.
     */
    protected void close() {
        this.viewEdit.getViewMenu().setSave(false);
        this.viewEdit.getViewMenu().update();
        this.viewEdit.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.viewEdit.getSaveButton().equals(event.getSource())) {
            if (this.check())
                this.save();
        }else if (this.viewEdit.getCancelButton().equals(event.getSource())) {
            this.viewEdit.dispose();
        }
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        if (F1 == event.getKeyCode()) {
            if (this.check())
                this.save();
        }else if (F2 == event.getKeyCode()) {
            this.viewEdit.dispose();
        }
    }
}