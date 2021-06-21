package view.modal.evaluation.base;

import controller.view.modal.evaluation.base.ControllerViewEvaluationDiagram;
import model.structural.base.Project;
import view.modal.evaluation.ViewEvaluation;
import view.panel.evaluation.base.PanelEvaluationDiagram;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewEvaluationDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Evaluation Diagram View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-01
 * @see    controller.view.modal.evaluation.base.ControllerViewEvaluationDiagram
 * @see    model.structural.base.Diagram
 * @see    view.modal.evaluation.ViewEvaluation
 */
public final class ViewEvaluationDiagram extends ViewEvaluation {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param project Project.
     */
    public ViewEvaluationDiagram(ViewMenu view, Project project) {
        super(view);
        controller = new ControllerViewEvaluationDiagram(this);
        title      = "Evaluation Diagram";
        initComponents();
    }
    
    @Override
    protected PanelEvaluationDiagram createPanelEvaluation() {
        return new PanelEvaluationDiagram(this);
    }
    
    @Override
    public PanelEvaluationDiagram getPanelEvaluation() {
        return (PanelEvaluationDiagram) super.getPanelEvaluation();
    }
}