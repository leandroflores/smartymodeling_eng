package controller.view.panel.operation.types;

import controller.view.panel.operation.ControllerPanelOperation;
import java.awt.event.ActionEvent;
import view.panel.operation.types.PanelComponentOperation;

/**
 * <p>Class of Controller <b>ControllerPanelComponentOperation</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>Component Operation Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  10/04/2020
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
        if (this.getPanelOperation().getComponentButton().equals(event.getSource()))
            this.setAddComponent();
        else if (this.getPanelOperation().getInterfaceButton().equals(event.getSource()))
            this.setAddInterface();
    }
    
    /**
     * Method responsible for setting the Add Component Operation.
     */
    public void setAddComponent() {
        this.getPanelOperation().resetBackground();
        this.getPanelOperation().getComponentButton().setBackground(this.getFocusColor());
        this.getPanelDiagram().setOperation("Component");
    }
    
    /**
     * Method responsible for setting the Add Interface Operation.
     */
    public void setAddInterface() {
        this.getPanelOperation().resetBackground();
        this.getPanelOperation().getInterfaceButton().setBackground(this.getFocusColor());
        this.getPanelDiagram().setOperation("Interface");
    }

    @Override
    protected PanelComponentOperation getPanelOperation() {
        return (PanelComponentOperation) this.panel;
    }
}