package controller.view.evaluation;

import view.evaluation.ViewEvaluationDiagram;

/**
 * <p>Class of Controller <b>ControllerViewEvaluationDiagram</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewEvaluationDiagram</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  01/04/2020
 * @see    controller.view.evaluation.ControllerViewEvaluation
 * @see    view.evaluation.ViewEvaluationDiagram
 */
public class ControllerViewEvaluationDiagram extends ControllerViewEvaluation {
    private final ViewEvaluationDiagram viewEvaluationDiagram;

    /**
     * Default constructor method of Class.
     * @param viewEvaluation View Evaluation Diagram.
     */
    public ControllerViewEvaluationDiagram(ViewEvaluationDiagram viewEvaluation) {
        super(viewEvaluation);
        this.viewEvaluationDiagram = viewEvaluation;
    }
    
    @Override
    public void refresh() {
        this.viewEvaluationDiagram.getPanelEvaluationDiagram().getController().update();
    }

    @Override
    public void clear() {
        this.viewEvaluationDiagram.getPanelEvaluationDiagram().clear();
    }
}