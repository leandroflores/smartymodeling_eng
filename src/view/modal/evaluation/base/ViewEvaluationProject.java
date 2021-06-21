package view.modal.evaluation.base;

import controller.view.modal.evaluation.base.ControllerViewEvaluationProject;
import model.structural.base.Project;
import view.modal.evaluation.ViewEvaluation;
import view.panel.evaluation.base.PanelEvaluationProject;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewEvaluationProject</b>.</p>
 * <p>Class responsible for defining the <b>Evaluation Project View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-03-28
 * @see    controller.view.modal.evaluation.base.ControllerViewEvaluationProject
 * @see    model.structural.base.Project
 * @see    view.modal.evaluation.ViewEvaluation
 */
public final class ViewEvaluationProject extends ViewEvaluation {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param project Project.
     */
    public ViewEvaluationProject(ViewMenu view, Project project) {
        super(view);
        controller = new ControllerViewEvaluationProject(this);
        title      = "Evaluation Project";
        initComponents();
    }
    
    @Override
    protected PanelEvaluationProject createPanelEvaluation() {
        return new PanelEvaluationProject(this);
    }
    
    @Override
    public PanelEvaluationProject getPanelEvaluation() {
        return (PanelEvaluationProject) super.getPanelEvaluation();
    }
}