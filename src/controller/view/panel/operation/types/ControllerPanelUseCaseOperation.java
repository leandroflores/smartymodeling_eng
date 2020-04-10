package controller.view.panel.operation.types;

import controller.view.panel.operation.ControllerPanelOperation;
import java.awt.event.ActionEvent;
import view.panel.operation.types.PanelUseCaseOperation;

/**
 * <p>Class of Controller <b>ControllerPanelUseCaseOperation</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>Use Case Operation Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  10/04/2020
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
        if (this.getPanelOperation().getActorButton().equals(event.getSource()))
            this.setAddActor();
        else if (this.getPanelOperation().getUseCaseButton().equals(event.getSource()))
            this.setAddUseCase();
    }
    
    /**
     * Method responsible for setting the Add Actor Operation.
     */
    public void setAddActor() {
        this.getPanelOperation().resetBackground();
        this.getPanelOperation().getActorButton().setBackground(this.getFocusColor());
        this.getPanelDiagram().setOperation("Actor");
    }
    
    /**
     * Method responsible for setting the Add Use Case Operation.
     */
    public void setAddUseCase() {
        this.getPanelOperation().resetBackground();
        this.getPanelOperation().getUseCaseButton().setBackground(this.getFocusColor());
        this.getPanelDiagram().setOperation("UseCase");
    }

    @Override
    protected PanelUseCaseOperation getPanelOperation() {
        return (PanelUseCaseOperation) this.panel;
    }
}