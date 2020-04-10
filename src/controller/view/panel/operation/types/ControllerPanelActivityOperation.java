package controller.view.panel.operation.types;

import controller.view.panel.operation.ControllerPanelOperation;
import java.awt.event.ActionEvent;
import view.panel.operation.types.PanelActivityOperation;

/**
 * <p>Class of Controller <b>ControllerPanelFeatureOperation</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>Activity Operation Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  10/04/2020
 * @see    controller.view.panel.operation.ControllerPanelOperation
 * @see    view.panel.operation.types.PanelActivityOperation
 */
public class ControllerPanelActivityOperation extends ControllerPanelOperation {

    /**
     * Default constructor method of Class.
     * @param panel Panel Activity Operation.
     */
    public ControllerPanelActivityOperation(PanelActivityOperation panel) {
        super(panel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        if (this.getPanelOperation().getInitialButton().equals(event.getSource()))
            this.setAddInitial();
        else if (this.getPanelOperation().getActivityButton().equals(event.getSource()))
            this.setAddActivity();
        else if (this.getPanelOperation().getDecisionButton().equals(event.getSource()))
            this.setAddDecision();
        else if (this.getPanelOperation().getFinalButton().equals(event.getSource()))
            this.setAddFinal();
    }
    
    /**
     * Method responsible for setting the Add Initial Operation.
     */
    public void setAddInitial() {
        this.getPanelOperation().resetBackground();
        this.getPanelOperation().getInitialButton().setBackground(this.getFocusColor());
        this.getPanelDiagram().setOperation("Initial");
    }
    
    /**
     * Method responsible for setting the Add Activity Operation.
     */
    public void setAddActivity() {
        this.getPanelOperation().resetBackground();
        this.getPanelOperation().getActivityButton().setBackground(this.getFocusColor());
        this.getPanelDiagram().setOperation("Activity");
    }
    
    /**
     * Method responsible for setting the Add Decision Operation.
     */
    public void setAddDecision() {
        this.getPanelOperation().resetBackground();
        this.getPanelOperation().getDecisionButton().setBackground(this.getFocusColor());
        this.getPanelDiagram().setOperation("Decision");
    }
    
    /**
     * Method responsible for setting the Add Final Operation.
     */
    public void setAddFinal() {
        this.getPanelOperation().resetBackground();
        this.getPanelOperation().getFinalButton().setBackground(this.getFocusColor());
        this.getPanelDiagram().setOperation("Final");
    }
    
    @Override
    protected PanelActivityOperation getPanelOperation() {
        return (PanelActivityOperation) this.panel;
    }
}