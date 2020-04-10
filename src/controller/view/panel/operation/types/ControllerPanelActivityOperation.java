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
            this.setOperation(this.getPanelOperation().getInitialButton(),  "Initial");
        else if (this.getPanelOperation().getActivityButton().equals(event.getSource()))
            this.setOperation(this.getPanelOperation().getActivityButton(), "Activity");
        else if (this.getPanelOperation().getDecisionButton().equals(event.getSource()))
            this.setOperation(this.getPanelOperation().getDecisionButton(), "Decision");
        else if (this.getPanelOperation().getFinalButton().equals(event.getSource()))
            this.setOperation(this.getPanelOperation().getFinalButton(),    "Final");
    }

    @Override
    protected PanelActivityOperation getPanelOperation() {
        return (PanelActivityOperation) this.panel;
    }
}