package controller.view.panel.operation.types;

import controller.view.panel.operation.ControllerPanelOperation;
import java.awt.event.ActionEvent;
import view.panel.operation.types.PanelFeatureOperation;

/**
 * <p>Class of Controller <b>ControllerPanelFeatureOperation</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>Feature Operation Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  10/04/2020
 * @see    controller.view.panel.operation.ControllerPanelOperation
 * @see    view.panel.operation.types.PanelFeatureOperation
 */
public class ControllerPanelFeatureOperation extends ControllerPanelOperation {

    /**
     * Default constructor method of Class.
     * @param panel Panel Feature Operation.
     */
    public ControllerPanelFeatureOperation(PanelFeatureOperation panel) {
        super(panel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        if (this.getPanelOperation().getFeatureButton().equals(event.getSource()))
            this.setOperation(this.getPanelOperation().getFeatureButton(), "Feature");
    }
    
    @Override
    protected PanelFeatureOperation getPanelOperation() {
        return (PanelFeatureOperation) this.panel;
    }
}