package controller.view.panel.operation.types;

import controller.view.panel.operation.ControllerPanelOperation;
import java.awt.event.ActionEvent;
import view.panel.operation.types.PanelClassOperation;

/**
 * <p>Class of Controller <b>ControllerPanelClassOperation</b>.</p>
 * <p>Class responsible for controlling the <b>PanelClassOperation</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-10
 * @see    controller.view.panel.operation.ControllerPanelOperation
 * @see    view.panel.operation.types.PanelClassOperation
 */
public class ControllerPanelClassOperation extends ControllerPanelOperation {

    /**
     * Default constructor method of Class.
     * @param panel Panel Class Operation.
     */
    public ControllerPanelClassOperation(PanelClassOperation panel) {
        super(panel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        if (getPanel().getPackageButton().equals(event.getSource()))
            setOperation(getPanel().getPackageButton(), "Package");
        else if (getPanel().getClassButton().equals(event.getSource()))
            setOperation(getPanel().getClassButton(), "Class");
        else if (getPanel().getInterfaceButton().equals(event.getSource()))
            setOperation(getPanel().getInterfaceButton(), "Interface");
    }
    
    @Override
    public PanelClassOperation getPanel() {
        return (PanelClassOperation) panel;
    }
}