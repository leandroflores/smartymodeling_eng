package controller.view.modal.evaluation;

import controller.view.modal.ControllerViewModal;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.modal.evaluation.ViewEvaluation;

/**
 * <p>Class of Controller <b>ControllerViewEvaluation</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewEvaluation</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  28/03/2020
 * @see    controller.view.modal.ControllerViewModal
 * @see    view.modal.evaluation.ViewEvaluation
 */
public abstract class ControllerViewEvaluation extends ControllerViewModal {
    protected ViewEvaluation viewEvaluation;
    
    /**
     * Default constructor method of Class.
     * @param view View Evaluation.
     */
    public ControllerViewEvaluation(ViewEvaluation view) {
        super(view);
        this.viewEvaluation = view;
    }
    
    /**
     * Abstract Method responsible for Refresh the View Evaluation.
     */
    public abstract void refresh();
    
    /**
     * Abstract Method responsible for Clear the View Evaluation.
     */
    public abstract void clear();
    
    /**
     * Method responsible for closing the View Evaluation.
     */
    protected void close() {
        this.viewEvaluation.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.viewEvaluation.getRefreshButton().equals(event.getSource()))
            this.refresh();
        else if (this.viewEvaluation.getClearButton().equals(event.getSource()))
            this.clear();
        else if (this.viewEvaluation.getCancelButton().equals(event.getSource()))
            this.viewEvaluation.dispose();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        switch (event.getKeyCode()) {
            case F1:
            case ENTER:
                this.refresh();
                break;
            case F2:
                this.clear();
                break;
            case F3:
                this.viewEvaluation.dispose();
                break;
            default:
                break;
        }
    }
}