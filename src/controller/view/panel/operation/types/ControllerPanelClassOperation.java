package controller.view.panel.operation.types;

import controller.view.panel.operation.ControllerPanelOperation;
import java.awt.event.ActionEvent;
import view.panel.operation.types.PanelClassOperation;

/**
 * <p>Class of Controller <b>ControllerPanelClassOperation</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>Class Operation Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  10/04/2020
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
        if (this.getPanelOperation().getPackageButton().equals(event.getSource()))
            this.setOperation(this.getPanelOperation().getPackageButton(), "Package");
        else if (this.getPanelOperation().getClassButton().equals(event.getSource()))
            this.setOperation(this.getPanelOperation().getClassButton(), "Class");
        else if (this.getPanelOperation().getInterfaceButton().equals(event.getSource()))
            this.setOperation(this.getPanelOperation().getInterfaceButton(), "Interface");
    }
    
    @Override
    protected PanelClassOperation getPanelOperation() {
        return (PanelClassOperation) this.panel;
    }
}