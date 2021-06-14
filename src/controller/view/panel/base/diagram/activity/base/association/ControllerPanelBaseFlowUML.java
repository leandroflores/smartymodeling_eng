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
 * @see    model.structural.diagram.activity.base.association.FlowUML
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
        getFlow().setGuard(getString(getPanel().getGuardTextField()));
        getFlow().setAction(getString(getPanel().getActionTextField()));
        getFlow().setWeight(getString(getPanel().getWeightTextField()));
        refresh();
    }
    
    /**
     * Method responsible for returning the Flow UML.
     * @return Flow UML.
     */
    private FlowUML getFlow() {
        return getPanel().getAssociation();
    }
    
    @Override
    public PanelBaseFlowUML getPanel() {
        return (PanelBaseFlowUML) panel;
    }
}