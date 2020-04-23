package controller.view.modal.evaluation;

import controller.view.modal.ControllerViewModal;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.modal.evaluation.ViewEvaluation;

/**
 * <p>Class of Controller <b>ControllerViewEvaluation</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEvaluation</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-03-28
 * @see    controller.view.modal.ControllerViewModal
 * @see    view.modal.evaluation.ViewEvaluation
 */
public abstract class ControllerViewEvaluation extends ControllerViewModal {
    
    /**
     * Default constructor method of Class.
     * @param view View Evaluation.
     */
    public ControllerViewEvaluation(ViewEvaluation view) {
        super(view);
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.getView().getRefreshButton().equals(event.getSource()))
            this.refresh();
        else if (this.getView().getClearButton().equals(event.getSource()))
            this.clear();
        else if (this.getView().getCancelButton().equals(event.getSource()))
            this.getView().dispose();
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
                this.getView().dispose();
                break;
            default:
                break;
        }
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
        this.getView().dispose();
    }
    
    @Override
    public ViewEvaluation getView() {
        return (ViewEvaluation) this.viewModal;
    }
}