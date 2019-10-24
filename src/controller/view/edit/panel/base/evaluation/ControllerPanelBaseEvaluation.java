package controller.view.edit.panel.base.evaluation;

import controller.view.ControllerPanel;
import funct.evaluation.Evaluation;
import funct.evaluation.types.EvaluationUseCaseDiagram;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        System.out.println("");
        System.out.println("Target: " + target);
        System.out.println("Metric: " + metric);
        if (target instanceof Diagram) {
            try {
                this.evaluateDiagram((Diagram) target, metric);
            } catch (ScriptException exception) {
                System.out.println(exception);
            }
        }
        System.out.println("");
    }
    
    private void evaluateDiagram(Diagram diagram, Metric metric) throws ScriptException {
        System.out.println("Operation: " + metric.getOperation());
        Evaluation evaluation;
        if (diagram instanceof UseCaseDiagram) {
            evaluation = new EvaluationUseCaseDiagram((UseCaseDiagram) diagram);
            System.out.println("");
            System.out.println(evaluation.getExpression(metric.getOperation()));
        }
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