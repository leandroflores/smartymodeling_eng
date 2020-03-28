package controller.view.edit.panel.base.evaluation;

import controller.view.ControllerPanel;
import funct.evaluation.base.EvaluationDiagram;
import funct.evaluation.types.EvaluationUseCaseDiagram;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.script.ScriptException;
import model.structural.base.Diagram;
import model.structural.base.evaluation.Metric;
import model.structural.diagram.UseCaseDiagram;
import view.edit.panel.base.evaluation.PanelBaseEvaluation;

/**
 * <p>Class of Controller <b>ControllerPanelBaseEvaluation</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseEvaluation</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  23/10/2019
 * @see    controller.view.ControllerPanel
 * @see    view.edit.panel.base.evaluation.PanelBaseEvaluation
 */
public class ControllerPanelBaseEvaluation extends ControllerPanel {
    private final PanelBaseEvaluation panelBaseEvaluation;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Evaluation.
     */
    public ControllerPanelBaseEvaluation(PanelBaseEvaluation panel) {
        super(panel);
        this.panelBaseEvaluation = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.update();
        if (this.panelBaseEvaluation.getBackButton().equals(event.getSource()))
            this.panelBaseEvaluation.getViewNewMeasure().removePanelBaseEvaluation();
        else if (this.panelBaseEvaluation.getNextButton().equals(event.getSource()))
            this.panelBaseEvaluation.getViewNewMeasure().getController().insert();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        this.update();
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
        this.update();
    }
    
    private void evaluate() {
        Object target = this.panelBaseEvaluation.getTargetComboBox().getSelectedItem();
        Metric metric = this.panelBaseEvaluation.getMeasure().getMetric();
        if (target instanceof Diagram) {
            try {
                this.evaluateDiagram((Diagram) target, metric);
            } catch (ScriptException exception) {
                System.out.println(exception);
            }
        }
    }
    
    private void evaluateDiagram(Diagram diagram, Metric metric) throws ScriptException {
        EvaluationDiagram evaluation = diagram.getEvaluation();
        Double value = evaluation.getFinalValue(metric.getOperation());
        this.panelBaseEvaluation.getValueTextField().setText(Double.toString(value));
        this.panelBaseEvaluation.updateDetailsList(evaluation.getObjects());
    }
    
    /**
     * Method responsible for setting the Evaluation Values.
     */
    private void update() {
        this.evaluate();
//        this.panelBaseEvaluation.getViewMenu().getPanelModeling().updateDiagram(this.panelBaseEvaluation.getDiagram());
        this.panelBaseEvaluation.getViewMenu().getPanelProject().getPanelTree().updateUI(); 
        this.panelBaseEvaluation.getViewMenu().setSave(false);
    }
}