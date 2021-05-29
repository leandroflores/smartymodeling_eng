package controller.view.modal.evaluation.base;

import controller.view.modal.evaluation.ControllerViewEvaluation;
import view.modal.evaluation.base.ViewEvaluationDiagram;

/**
 * <p>Class of Controller <b>ControllerViewEvaluationDiagram</b>.</p>
 * <p>Class responsible for controlling the <b>ViewEvaluationDiagram</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-01
 * @see    controller.view.modal.evaluation.ControllerViewEvaluation
 * @see    model.structural.base.Diagram
 * @see    view.modal.evaluation.base.ViewEvaluationDiagram
 */
public class ControllerViewEvaluationDiagram extends ControllerViewEvaluation {

    /**
     * Default constructor method of Class.
     * @param view View Evaluation Diagram.
     */
    public ControllerViewEvaluationDiagram(ViewEvaluationDiagram view) {
        super(view);
    }
    
    @Override
    public void refresh() {
        getView().getPanelEvaluationDiagram().getController().update();
    }

    @Override
    public void clear() {
        getView().getPanelEvaluationDiagram().clear();
    }
    
    @Override
    public ViewEvaluationDiagram getView() {
        return (ViewEvaluationDiagram) super.getView();
    }
}