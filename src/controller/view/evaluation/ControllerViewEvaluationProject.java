package controller.view.evaluation;

import view.evaluation.ViewEvaluationProject;

/**
 * <p>Class of Controller <b>ControllerViewEvaluationProject</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewEvaluationProject</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  28/03/2020
 * @see    controller.view.evaluation.ControllerViewEvaluation
 * @see    view.evaluation.ViewEvaluationProject
 */
public class ControllerViewEvaluationProject extends ControllerViewEvaluation {
    private final ViewEvaluationProject viewEvaluationProject;

    /**
     * Default constructor method of Class.
     * @param viewEvaluation View Evaluation Project.
     */
    public ControllerViewEvaluationProject(ViewEvaluationProject viewEvaluation) {
        super(viewEvaluation);
        this.viewEvaluationProject = viewEvaluation;
    }
    
    @Override
    public void refresh() {
        this.viewEvaluationProject.getPanelEvaluationProject().getController().update();
    }

    @Override
    public void clear() {
        this.viewEvaluationProject.getPanelEvaluationProject().clear();
    }
}