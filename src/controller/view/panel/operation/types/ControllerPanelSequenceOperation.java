package controller.view.panel.operation.types;

import controller.view.panel.operation.ControllerPanelOperation;
import java.awt.event.ActionEvent;
import view.panel.operation.types.PanelSequenceOperation;

/**
 * <p>Class of Controller <b>ControllerPanelSequenceOperation</b>.</p>
 * <p>Class responsible for controlling the <b>PanelSequenceOperation</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-10
 * @see    controller.view.panel.operation.ControllerPanelOperation
 * @see    view.panel.operation.types.PanelSequenceOperation
 */
public class ControllerPanelSequenceOperation extends ControllerPanelOperation {

    /**
     * Default constructor method of Class.
     * @param panel Panel Sequence Operation.
     */
    public ControllerPanelSequenceOperation(PanelSequenceOperation panel) {
        super(panel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        if (getPanel().getLifelineButton().equals(event.getSource()))
            setOperation(getPanel().getLifelineButton(), "Lifeline");
        else if (getPanel().getInstanceButton().equals(event.getSource()))
            setOperation(getPanel().getInstanceButton(), "Instance");
    }
    
    @Override
    public PanelSequenceOperation getPanel() {
        return (PanelSequenceOperation) panel;
    }
}