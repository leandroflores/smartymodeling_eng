package controller.view.panel.operation.types;

import controller.view.panel.operation.ControllerPanelOperation;
import java.awt.event.ActionEvent;
import view.panel.operation.types.PanelActivityOperation;

/**
 * <p>Class of Controller <b>ControllerPanelActivityOperation</b>.</p>
 * <p>Class responsible for controlling the <b>PanelActivityOperation</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-10
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
        if (getPanel().getInitialButton().equals(event.getSource()))
            setOperation(getPanel().getInitialButton(), "Initial");
        else if (getPanel().getActivityButton().equals(event.getSource()))
            setOperation(getPanel().getActivityButton(), "Activity");
        else if (getPanel().getDecisionButton().equals(event.getSource()))
            setOperation(getPanel().getDecisionButton(), "Decision");
        else if (getPanel().getFinalButton().equals(event.getSource()))
            setOperation(getPanel().getFinalButton(), "Final");
    }

    @Override
    public PanelActivityOperation getPanel() {
        return (PanelActivityOperation) panel;
    }
}