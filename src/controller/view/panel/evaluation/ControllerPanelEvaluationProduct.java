package controller.view.panel.evaluation;

import javax.script.ScriptException;
import view.modal.message.ViewError;
import view.panel.evaluation.PanelEvaluationProduct;

/**
 * <p>Class of Controller <b>ControllerPanelEvaluationProduct</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelEvaluationProduct</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  01/04/2020
 * @see    controller.view.panel.evaluation.ControllerPanelEvaluation
 * @see    view.panel.evaluation.PanelEvaluationProduct
 */
public class ControllerPanelEvaluationProduct extends ControllerPanelEvaluation {

    /**
     * Default constructor method of Class.
     * @param panelEvaluation Panel Evaluation Product.
     */
    public ControllerPanelEvaluationProduct(PanelEvaluationProduct panelEvaluation) {
        super(panelEvaluation);
    }
    
    @Override
    public void update() {
        try {
            if (this.check())
                this.evaluate(this.getProduct(), this.panelEvaluation.getOperationTextField().getText().trim());
        }catch (ScriptException exception) {
            new ViewError(this.panelEvaluation.getViewEvaluation(), "Error to Apply Operation!").setVisible(true);
            this.panelEvaluation.getOperationTextField().requestFocus();
        }
    }
}