package view.panel.evaluation;

import controller.view.panel.evaluation.ControllerPanelEvaluationProject;
import model.controller.structural.base.ControllerProject;
import view.modal.evaluation.ViewEvaluationProject;

/**
 * <p>Class of View <b>PanelEvaluationProject</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Panel Evaluation Project</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  28/03/2020
 * @see    controller.view.panel.evaluation.ControllerPanelEvaluationProject
 * @see    view.panel.evaluation.PanelEvaluation
 * @see    view.modal.evaluation.ViewEvaluationProject
 */
public final class PanelEvaluationProject extends PanelEvaluation {
    
    /**
     * Default constructor method of Class.
     * @param viewEvaluation View Evaluation.
     */
    public PanelEvaluationProject(ViewEvaluationProject viewEvaluation) {
        super(viewEvaluation);
        this.controller = new ControllerPanelEvaluationProject(this);
        this.setSettings();
        this.addComponents();
    }
    
    @Override
    protected Object[] getTargets() {
        return new ControllerProject(this.project).getProjectTargets();
    }
}