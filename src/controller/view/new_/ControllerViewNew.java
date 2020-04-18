package controller.view.new_;

import controller.view.ControllerViewModal;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.new_.ViewNew;

/**
 * <p>Class of Controller <b>ControllerViewNew</b>.</p>
 * <p>Class responsible for controlling the <b>ViewNew</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-04
 * @see    controller.view.ControllerViewModal
 * @see    view.new_.ViewNew
 */
public abstract class ControllerViewNew extends ControllerViewModal {
    
    /**
     * Default constructor method of Class.
     * @param view View New.
     */
    public ControllerViewNew(ViewNew view) {
        super(view);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.getView().getInsertButton().equals(event.getSource()))
            this.new_();
        else if (this.getView().getBackButton().equals(event.getSource()))
            this.getView().dispose();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        switch (event.getKeyCode()) {
            case F1:
                this.new_();
                break;
            case F2:
                this.getView().dispose();
                break;
            default:
                break;
        }
    }
    
    /**
     * Method responsible for Insert a New Object.
     */
    private void new_() {
        if (this.check()) {
            this.insert();
            this.close();
        }
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
    
    /**
     * Method responsible for closing the View New.
     */
    protected void close() {
        this.getView().getViewMenu().setSave(false);
        this.getView().getViewMenu().update();
        this.getView().dispose();
    }
    
    @Override
    public ViewNew getView() {
        return (ViewNew) this.viewModal;
    }
}