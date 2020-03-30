package controller.view.edit.panel.base.evaluation;

import controller.view.ControllerPanel;
import funct.evaluation.base.EvaluationProject;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.script.ScriptException;
import view.edit.panel.base.evaluation.PanelQueryEvaluation;
import view.message.ViewError;

/**
 * <p>Class of Controller <b>ControllerPanelQueryEvaluation</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelQueryEvaluation</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  30/03/2020
 * @see    controller.view.ControllerPanel
 * @see    view.edit.panel.base.evaluation.PanelQueryEvaluation
 */
public class ControllerPanelQueryEvaluation extends ControllerPanel {
    private final PanelQueryEvaluation panelQueryEvaluation;

    /**
     * Default constructor method of Class.
     * @param panel Panel Query Evaluation.
     */
    public ControllerPanelQueryEvaluation(PanelQueryEvaluation panel) {
        super(panel);
        this.panelQueryEvaluation = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.panelQueryEvaluation.getApplyButton().equals(event.getSource()))
            this.update();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {}
    
    /**
     * Method responsible for Updating the Panel.
     */
    public void update() {
        Object target    = this.panelQueryEvaluation.getTargetComboBox().getSelectedItem();
        String operation = this.panelQueryEvaluation.getOperationTextField().getText().trim();
        System.out.println("Target: " + target);
        System.out.println("Opert.: " + operation);
        System.out.println("");
        try {
            if (target.toString().equalsIgnoreCase("Project"))
                this.evaluate(this.panelQueryEvaluation.getProject().getEvaluation(), operation);
//            else if (target instanceof Diagram)
//                this.evaluate(((Diagram) target).getEvaluation(), metric);
        }catch (ScriptException exception) {
            new ViewError(this.panelQueryEvaluation.getViewQueryEvaluation(), "Error to Apply Metric!").setVisible(true);
            this.panelQueryEvaluation.getOperationTextField().requestFocus();
        }catch (Exception exception) {
            new ViewError(this.panelQueryEvaluation.getViewQueryEvaluation(), "Error to Apply Metric!").setVisible(true);
            this.panelQueryEvaluation.getOperationTextField().requestFocus();
        }
    }
    
    /**
     * Method responsible for evaluate the Project by a Metric.
     * @param  evaluation Evaluation Project.
     * @param  operation Operation.
     * @throws ScriptException 
     */
    private void evaluate(EvaluationProject evaluation, String operation) throws ScriptException {
        Double value = evaluation.getFinalValue(operation);
        this.panelQueryEvaluation.getValueTextField().setText(Double.toString(value));
        this.panelQueryEvaluation.updateDetails(evaluation.getObjects());
    }
}