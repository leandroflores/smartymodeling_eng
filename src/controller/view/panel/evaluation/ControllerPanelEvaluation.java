package controller.view.panel.evaluation;

import controller.view.ControllerPanel;
import funct.evaluation.Evaluation;
import funct.evaluation.base.EvaluationDiagram;
import funct.evaluation.base.EvaluationProduct;
import funct.evaluation.base.EvaluationProject;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.script.ScriptException;
import model.structural.base.Diagram;
import model.structural.base.Project;
import model.structural.base.product.Product;
import view.message.ViewError;
import view.panel.evaluation.PanelEvaluation;

/**
 * <p>Class of Controller <b>ControllerPanelEvaluation</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelEvaluation</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  01/04/2020
 * @see    controller.view.ControllerPanel
 * @see    view.panel.evaluation.PanelEvaluation
 */
public abstract class ControllerPanelEvaluation extends ControllerPanel {
    protected final PanelEvaluation panelEvaluation;

    /**
     * Default constructor method of Class.
     * @param panel Panel Evaluation.
     */
    public ControllerPanelEvaluation(PanelEvaluation panel) {
        super(panel);
        this.panelEvaluation = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.panelEvaluation.getApplyButton().equals(event.getSource()))
            this.update();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case ENTER:
                this.update();
                break;
            default:
                break;
        }
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    protected Project getProject() {
        return this.panelEvaluation.getProject();
    }
    
    /**
     * Method responsible for returning the Diagram selected.
     * @return Diagram selected.
     */
    protected Diagram getDiagram() {
        return (Diagram) this.panelEvaluation.getTargetComboBox().getSelectedItem();
    }
    
    /**
     * Method responsible for returning the Product selected.
     * @return Product selected.
     */
    protected Product getProduct() {
        return (Product) this.panelEvaluation.getTargetComboBox().getSelectedItem();
    }
    
    /**
     * Method responsible for checking the Operation.
     * @return Operation is checked.
     */
    protected boolean check() {
        if (!this.check(this.panelEvaluation.getOperationTextField().getText())) {
            new ViewError(this.panelEvaluation.getViewEvaluation(), "Type a Operation!").setVisible(true);
            this.panelEvaluation.getOperationTextField().requestFocus();
            return false;
        }
        return true;
    }
    
    /**
     * Method responsible for Updating the Panel.
     */
    public abstract void update();
    
    /**
     * Method responsible for evaluate the Project by Operation.
     * @param  project Project.
     * @param  operation Operation.
     * @throws ScriptException Exception to Apply Operation on Project.
     */
    protected void evaluate(Project project, String operation) throws ScriptException {
        System.out.println("AAA");
        Evaluation evaluation = new EvaluationProject(project);
        Double     finalValue = evaluation.getFinalValue(operation);
        this.panelEvaluation.getValueTextField().setText(Double.toString(finalValue));
        System.out.println("List: " + evaluation.getObjects());
        this.panelEvaluation.updateDetails(evaluation.getObjects());
        System.out.println("");
    }
    
    /**
     * Method responsible for evaluate the Product by Operation.
     * @param  product Product.
     * @param  operation Operation.
     * @throws ScriptException Exception to Apply Operation on Product.
     */
    protected void evaluate(Product product, String operation) throws ScriptException {
        Evaluation evaluation = new EvaluationProduct(this.getProject(), product);
        Double     finalValue = evaluation.getFinalValue(operation);
        this.panelEvaluation.getValueTextField().setText(Double.toString(finalValue));
        this.panelEvaluation.updateDetails(evaluation.getObjects());
    }
    
    /**
     * Method responsible for evaluate the Diagram by Operation.
     * @param  diagram Diagram.
     * @param  operation Operation.
     * @throws ScriptException Exception to Apply Operation on Diagram.
     */
    protected void evaluate(Diagram diagram, String operation) throws ScriptException {
        Evaluation evaluation = new EvaluationDiagram(diagram);
        Double     finalValue = evaluation.getFinalValue(operation);
        this.panelEvaluation.getValueTextField().setText(Double.toString(finalValue));
        this.panelEvaluation.updateDetails(evaluation.getObjects());
    }
}