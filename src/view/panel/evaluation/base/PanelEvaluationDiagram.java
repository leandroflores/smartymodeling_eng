package view.panel.evaluation.base;

import controller.view.panel.evaluation.base.ControllerPanelEvaluationDiagram;
import model.controller.structural.base.ControllerProject;
import view.modal.evaluation.base.ViewEvaluationDiagram;
import view.panel.evaluation.PanelEvaluation;

/**
 * <p>Class of View <b>PanelEvaluationDiagram</b>.</p>
 * <p>Class responsible for defining a <b>Diagram Evaluation Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-01
 * @see    controller.view.panel.evaluation.base.ControllerPanelEvaluationDiagram
 * @see    view.panel.evaluation.PanelEvaluation
 * @see    view.modal.evaluation.base.ViewEvaluationDiagram
 */
public final class PanelEvaluationDiagram extends PanelEvaluation {
    
    /**
     * Default constructor method of Class.
     * @param view View Evaluation Diagram.
     */
    public PanelEvaluationDiagram(ViewEvaluationDiagram view) {
        super(view);
        this.controller = new ControllerPanelEvaluationDiagram(this);
        this.setDefaultProperties();
        this.addComponents();
    }
    
    @Override
    protected Object[] getTargets() {
        return new ControllerProject(this.project).getDiagrams();
    }
}