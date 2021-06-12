package controller.view.panel.operation.types;

import controller.view.panel.operation.ControllerPanelOperation;
import java.awt.event.ActionEvent;
import view.panel.operation.types.PanelUseCaseOperation;

/**
 * <p>Class of Controller <b>ControllerPanelUseCaseOperation</b>.</p>
 * <p>Class responsible for controlling the <b>PanelUseCaseOperation</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-10
 * @see    controller.view.panel.operation.ControllerPanelOperation
 * @see    view.panel.operation.types.PanelUseCaseOperation
 */
public class ControllerPanelUseCaseOperation extends ControllerPanelOperation {

    /**
     * Default constructor method of Class.
     * @param panel Panel Use Case Operation.
     */
    public ControllerPanelUseCaseOperation(PanelUseCaseOperation panel) {
        super(panel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        if (getPanel().getActorButton().equals(event.getSource()))
            setOperation(getPanel().getActorButton(),   "Actor");
        else if (getPanel().getUseCaseButton().equals(event.getSource()))
            setOperation(getPanel().getUseCaseButton(), "UseCase");
    }

    @Override
    public PanelUseCaseOperation getPanel() {
        return (PanelUseCaseOperation) panel;
    }
}