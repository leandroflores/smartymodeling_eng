package controller.view.modal.evaluation.base;

import controller.view.modal.evaluation.ControllerViewEvaluation;
import view.modal.evaluation.base.ViewEvaluationProject;

/**
 * <p>Class of Controller <b>ControllerViewEvaluationProject</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEvaluationProject</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-03-28
 * @see    controller.view.modal.evaluation.ControllerViewEvaluation
 * @see    model.structural.base.Project
 * @see    view.modal.evaluation.base.ViewEvaluationProject
 */
public class ControllerViewEvaluationProject extends ControllerViewEvaluation {

    /**
     * Default constructor method of Class.
     * @param view View Evaluation Project.
     */
    public ControllerViewEvaluationProject(ViewEvaluationProject view) {
        super(view);
    }
    
    @Override
    public void refresh() {
        this.getView().getPanelEvaluationProject().getController().update();
    }

    @Override
    public void clear() {
        this.getView().getPanelEvaluationProject().clear();
    }
    
    @Override
    public ViewEvaluationProject getView() {
        return (ViewEvaluationProject) this.viewModal;
    }
}