package controller.view.panel.evaluation.base;

import controller.view.panel.evaluation.ControllerPanelEvaluation;
import javax.script.ScriptException;
import view.modal.message.ViewError;
import view.panel.evaluation.base.PanelEvaluationDiagram;

/**
 * <p>Class of Controller <b>ControllerPanelEvaluationDiagram</b>.</p>
 * <p>Class responsible for controlling the <b>PanelEvaluationDiagram</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-01
 * @see    controller.view.panel.evaluation.ControllerPanelEvaluation
 * @see    model.structural.base.Diagram
 * @see    view.panel.evaluation.base.PanelEvaluationDiagram
 */
public class ControllerPanelEvaluationDiagram extends ControllerPanelEvaluation {

    /**
     * Default constructor method of Class.
     * @param panel Panel Evaluation Diagram.
     */
    public ControllerPanelEvaluationDiagram(PanelEvaluationDiagram panel) {
        super(panel);
    }
    
    @Override
    public void update() {
        try {
            if (check())
                evaluate(getDiagram(), getPanel().getOperationTextField().getText().trim());
        }catch (ScriptException exception) {
            new ViewError(getPanel().getView(), "Error to Apply Operation!").setVisible(true);
            getPanel().getOperationTextField().requestFocus();
        }catch (Exception exception) {
            new ViewError(getPanel().getView(), "Invalid Operation Expression!").setVisible(true);
            getPanel().getOperationTextField().requestFocus();
        }
    }
    
    @Override
    public PanelEvaluationDiagram getPanel() {
        return (PanelEvaluationDiagram) panel;
    }
}