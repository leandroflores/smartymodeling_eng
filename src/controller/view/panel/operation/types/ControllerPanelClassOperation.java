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
            this.setAddPackage();
        else if (this.getPanelOperation().getClassButton().equals(event.getSource()))
            this.setAddClass();
        else if (this.getPanelOperation().getInterfaceButton().equals(event.getSource()))
            this.setAddInterface();
    }
    
    /**
     * Method responsible for setting the Add Package Operation.
     */
    public void setAddPackage() {
        this.getPanelOperation().resetBackground();
        this.getPanelOperation().getPackageButton().setBackground(this.getFocusColor());
        this.getPanelDiagram().setOperation("Package");
    }
    
    /**
     * Method responsible for setting the Add Class Operation.
     */
    public void setAddClass() {
        this.getPanelOperation().resetBackground();
        this.getPanelOperation().getClassButton().setBackground(this.getFocusColor());
        this.getPanelDiagram().setOperation("Class");
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
    protected PanelClassOperation getPanelOperation() {
        return (PanelClassOperation) this.panel;
    }
}