package controller.view.panel.evaluation;

import controller.view.panel.ControllerPanel;
import funct.evaluation.base.EvaluationBase;
import funct.evaluation.base.EvaluationDiagram;
import funct.evaluation.base.product.EvaluationProduct;
import funct.evaluation.base.EvaluationProject;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.script.ScriptException;
import model.structural.base.Diagram;
import model.structural.base.Project;
import model.structural.base.product.Product;
import view.modal.message.ViewError;
import view.panel.evaluation.PanelEvaluation;

/**
 * <p>Class of Controller <b>ControllerPanelEvaluation</b>.</p>
 * <p>Class responsible for controlling the <b>Evaluation Panel</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-01
 * @see    controller.view.panel.ControllerPanel
 * @see    view.panel.evaluation.PanelEvaluation
 */
public abstract class ControllerPanelEvaluation extends ControllerPanel {
    private final List result;

    /**
     * Default constructor method of Class.
     * @param panel Panel Evaluation.
     */
    public ControllerPanelEvaluation(PanelEvaluation panel) {
        super(panel);
        result = new ArrayList();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (getPanel().getApplyButton().equals(event.getSource()))
            update();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case ENTER:
                update();
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
        return getPanel().getProject();
    }
    
    /**
     * Method responsible for returning the Diagram selected.
     * @return Diagram selected.
     */
    protected Diagram getDiagram() {
        return (Diagram) getPanel().getTargetComboBox().getSelectedItem();
    }
    
    /**
     * Method responsible for returning the Product selected.
     * @return Product selected.
     */
    protected Product getProduct() {
        return (Product) getPanel().getTargetComboBox().getSelectedItem();
    }
    
    /**
     * Method responsible for checking the Operation.
     * @return Operation is checked.
     */
    protected boolean check() {
        if (!check(getPanel().getOperationTextField().getText())) {
            new ViewError(getPanel().getView(), "Type a Operation!").setVisible(true);
            getPanel().getOperationTextField().requestFocus();
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
        EvaluationBase evaluation = new EvaluationProject(project);
        getPanel().getValueTextField().setText(Double.toString(evaluation.getFinalValue(operation)));
        getPanel().updateDetails(evaluation.getResult());
    }
    
    /**
     * Method responsible for evaluate the Product by Operation.
     * @param  product Product.
     * @param  operation Operation.
     * @throws ScriptException Exception to Apply Operation on Product.
     */
    protected void evaluate(Product product, String operation) throws ScriptException {
        EvaluationBase evaluation = new EvaluationProduct(getProject(), product);
        getPanel().getValueTextField().setText(Double.toString(evaluation.getFinalValue(operation)));
        getPanel().updateDetails(evaluation.getResult());
    }
    
    /**
     * Method responsible for evaluate the Diagram by Operation.
     * @param  diagram Diagram.
     * @param  operation Operation.
     * @throws ScriptException Exception to Apply Operation on Diagram.
     */
    protected void evaluate(Diagram diagram, String operation) throws ScriptException {
        EvaluationBase evaluation = new EvaluationDiagram(diagram);
        getPanel().getValueTextField().setText(Double.toString(evaluation.getFinalValue(operation)));
        getPanel().updateDetails(evaluation.getResult());
    }
    
    @Override
    public PanelEvaluation getPanel() {
        return (PanelEvaluation) panel;
    }
}