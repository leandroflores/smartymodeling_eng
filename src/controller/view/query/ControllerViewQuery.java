package controller.view.query;

import controller.view.ControllerViewModal;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.query.ViewQuery;

/**
 * <p>Class of Controller <b>ControllerViewQuery</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewQuery</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  28/03/2020
 * @see    controller.view.ControllerViewModal
 * @see    view.query.ViewQuery
 */
public abstract class ControllerViewQuery extends ControllerViewModal {
    protected ViewQuery viewQuery;
    
    /**
     * Default constructor method of Class.
     * @param view View Query.
     */
    public ControllerViewQuery(ViewQuery view) {
        super(view);
        this.viewQuery = view;
    }
    
    /**
     * Abstract Method responsible for Apply the Operation.
     */
    public abstract void apply();
    
    /**
     * Abstract Method responsible for Clear the View Query.
     */
    public abstract void clear();
    
    /**
     * Method responsible for closing the View Query.
     */
    protected void close() {
        this.viewQuery.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.viewQuery.getApplyButton().equals(event.getSource()))
            this.apply();
        else if (this.viewQuery.getClearButton().equals(event.getSource()))
            this.clear();
        else if (this.viewQuery.getCancelButton().equals(event.getSource()))
            this.viewQuery.dispose();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        switch (event.getKeyCode()) {
            case F1:
                this.apply();
                break;
            case F2:
                this.clear();
                break;
            case F3:
                this.viewQuery.dispose();
                break;
            default:
                break;
        }
    }
}