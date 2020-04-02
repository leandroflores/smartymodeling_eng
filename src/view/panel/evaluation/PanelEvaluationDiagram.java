package view.panel.evaluation;

import controller.view.panel.evaluation.ControllerPanelEvaluationDiagram;
import model.controller.structural.base.ControllerProject;
import view.evaluation.ViewEvaluationDiagram;

/**
 * <p>Class of View <b>PanelEvaluationDiagram</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Panel Evaluation Diagram</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  01/04/2020
 * @see    controller.view.panel.evaluation.ControllerPanelEvaluationDiagram
 * @see    view.panel.evaluation.PanelEvaluation
 * @see    view.evaluation.ViewEvaluationDiagram
 */
public final class PanelEvaluationDiagram extends PanelEvaluation {
    
    /**
     * Default constructor method of Class.
     * @param viewEvaluation View Evaluation Diagram.
     */
    public PanelEvaluationDiagram(ViewEvaluationDiagram viewEvaluation) {
        super(viewEvaluation);
        this.controller = new ControllerPanelEvaluationDiagram(this);
        this.setSettings();
        this.addComponents();
    }
    
    @Override
    protected Object[] getTargets() {
        return new ControllerProject(this.project).getDiagrams();
    }
}