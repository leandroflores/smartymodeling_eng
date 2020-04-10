package controller.view.panel.operation.types;

import controller.view.panel.operation.ControllerPanelOperation;
import java.awt.event.ActionEvent;
import view.panel.operation.types.PanelSequenceOperation;

/**
 * <p>Class of Controller <b>ControllerPanelSequenceOperation</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>Sequence Operation Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  10/04/2020
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
        if (this.getPanelOperation().getLifelineButton().equals(event.getSource()))
            this.setOperation(this.getPanelOperation().getLifelineButton(), "Lifeline");
        else if (this.getPanelOperation().getInstanceButton().equals(event.getSource()))
            this.setOperation(this.getPanelOperation().getInstanceButton(), "Instance");
    }
    
    @Override
    protected PanelSequenceOperation getPanelOperation() {
        return (PanelSequenceOperation) this.panel;
    }
}