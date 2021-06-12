package controller.view.panel.operation.types;

import controller.view.panel.operation.ControllerPanelOperation;
import java.awt.event.ActionEvent;
import view.panel.operation.types.PanelComponentOperation;

/**
 * <p>Class of Controller <b>ControllerPanelComponentOperation</b>.</p>
 * <p>Class responsible for controlling the <b>PanelComponentOperation</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-10
 * @see    controller.view.panel.operation.ControllerPanelOperation
 * @see    view.panel.operation.types.PanelComponentOperation
 */
public class ControllerPanelComponentOperation extends ControllerPanelOperation {

    /**
     * Default constructor method of Class.
     * @param panel Panel Component Operation.
     */
    public ControllerPanelComponentOperation(PanelComponentOperation panel) {
        super(panel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        if (getPanel().getComponentButton().equals(event.getSource()))
            setOperation(getPanel().getComponentButton(), "Component");
        else if (getPanel().getInterfaceButton().equals(event.getSource()))
            setOperation(getPanel().getInterfaceButton(), "Interface");
    }

    @Override
    public PanelComponentOperation getPanel() {
        return (PanelComponentOperation) panel;
    }
}