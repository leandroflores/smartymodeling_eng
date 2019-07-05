package controller.view.new_;

import controller.view.ControllerViewModal;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.new_.ViewNew;

/**
 * <p>Class of Controller <b>ControllerViewNew</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewNew</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  04/07/2019
 * @see    controller.view.ControllerView
 * @see    view.new_.ViewNew
 */
public abstract class ControllerViewNew extends ControllerViewModal {
    protected ViewNew viewNew;
    
    /**
     * Default constructor method of Class.
     * @param view View New.
     */
    public ControllerViewNew(ViewNew view) {
        super(view);
        this.viewNew = view;
    }

    /**
     * Abstract method responsible for Checking Values.
     * @return Values checked.
     */
    public abstract boolean check();
    
    /**
     * Abstract Method responsible for inserting the new Object.
     */
    public abstract void insert();
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.viewNew.getInsertButton().equals(event.getSource())) {
            if (this.check())
                this.insert();
        }else if (this.viewNew.getBackButton().equals(event.getSource())) {
            this.viewNew.dispose();
        }
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        switch (event.getKeyCode()) {
            case F1:
                if (this.check())
                    this.insert();
                break;
            case F2:
                this.viewNew.dispose();
                break;
            default:
                break;
        }
    }
}