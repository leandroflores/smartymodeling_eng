package controller.view.panel.evaluation.base;

import controller.view.panel.evaluation.ControllerPanelEvaluation;
import javax.script.ScriptException;
import view.modal.message.ViewError;
import view.panel.evaluation.base.PanelEvaluationDiagram;

/**
 * <p>Class of Controller <b>ControllerPanelEvaluationDiagram</b>.</p>
 * <p>Class responsible for controlling the <b>PanelEvaliationDiagram</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-01
 * @see    controller.view.panel.evaluation.ControllerPanelEvaluation
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
            if (this.check())
                this.evaluate(this.getDiagram(), this.getPanel().getOperationTextField().getText().trim());
        }catch (ScriptException exception) {
            new ViewError(this.getPanel().getViewEvaluation(), "Error to Apply Operation!").setVisible(true);
            this.getPanel().getOperationTextField().requestFocus();
        }catch (Exception exception) {
            new ViewError(this.getPanel().getViewEvaluation(), "Error to Apply Operation!").setVisible(true);
            this.getPanel().getOperationTextField().requestFocus();
        }
    }
    
    @Override
    public PanelEvaluationDiagram getPanel() {
        return (PanelEvaluationDiagram) this.panel;
    }
}