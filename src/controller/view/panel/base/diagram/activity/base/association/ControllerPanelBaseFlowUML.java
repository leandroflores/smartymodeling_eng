package controller.view.panel.base.diagram.activity.base.association;

import controller.view.panel.base.ControllerPanelBaseAssociation;
import model.structural.diagram.activity.base.association.FlowUML;
import view.panel.base.diagram.activity.base.association.PanelBaseFlowUML;

/**
 * <p>Class of Controller <b>ControllerPanelBaseFlowUML</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseFlowUML</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-04
 * @see    controller.view.panel.base.ControllerPanelBaseAssociation
 * @see    view.panel.base.diagram.activity.base.association.PanelBaseFlowUML
 */
public class ControllerPanelBaseFlowUML extends ControllerPanelBaseAssociation {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Flow UML.
     */
    public ControllerPanelBaseFlowUML(PanelBaseFlowUML panel) {
        super(panel);
    }
    
    @Override
    protected void update() {
        this.getFlow().setGuard(this.getString(this.getPanel().getGuardTextField()));
        this.getFlow().setAction(this.getString(this.getPanel().getActionTextField()));
        this.getFlow().setWeight(this.getString(this.getPanel().getWeightTextField()));
        super.refresh();
    }
    
    /**
     * Method responsible for returning the Flow UML.
     * @return Flow UML.
     */
    private FlowUML getFlow() {
        return this.getPanel().getAssociation();
    }
    
    @Override
    public PanelBaseFlowUML getPanel() {
        return (PanelBaseFlowUML) this.panel;
    }
}