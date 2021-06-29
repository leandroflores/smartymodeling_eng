package view.panel.evaluation.base;

import controller.view.panel.evaluation.base.ControllerPanelEvaluationProject;
import model.controller.structural.base.ControllerProject;
import view.modal.evaluation.base.ViewEvaluationProject;
import view.panel.evaluation.PanelEvaluation;

/**
 * <p>Class of View <b>PanelEvaluationProject</b>.</p>
 * <p>Class responsible for defining a <b>Project Evaluation Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-03-28
 * @see    controller.view.panel.evaluation.base.ControllerPanelEvaluationProject
 * @see    view.panel.evaluation.PanelEvaluation
 * @see    view.modal.evaluation.base.ViewEvaluationProject
 */
public final class PanelEvaluationProject extends PanelEvaluation {
    
    /**
     * Default constructor method of Class.
     * @param view View Evaluation Project.
     */
    public PanelEvaluationProject(ViewEvaluationProject view) {
        super(view);
        controller = new ControllerPanelEvaluationProject(this);
        setDefaultProperties();
        addComponents();
    }
    
    @Override
    protected Object[] getTargets() {
        return new ControllerProject(project).getProjectTargets();
    }
}