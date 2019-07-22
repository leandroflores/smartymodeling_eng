package controller.view.panel.diagram.association.types;

import com.mxgraph.model.mxCell;
import controller.view.panel.diagram.association.ControllerEventAssociation;
import model.structural.base.Element;
import model.structural.diagram.ActivityDiagram;
import model.structural.diagram.activity.base.FinalUML;
import model.structural.diagram.activity.base.InitialUML;
import model.structural.diagram.activity.base.association.FlowUML;
import view.panel.diagram.types.PanelActivityDiagram;

/**
 * <p>Class of Controller <b>ControllerEventAssociationActivity</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Activity Diagram Association</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  18/07/2019
 * @see    controller.view.panel.diagram.association.ControllerEventAssociation
 * @see    model.structural.diagram.ActivityDiagram
 * @see    view.panel.diagram.types.PanelActivityDiagram
 */
public class ControllerEventAssociationActivity extends ControllerEventAssociation {
    private final PanelActivityDiagram panelDiagram;
    private final ActivityDiagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Activity Diagram.
     */
    public ControllerEventAssociationActivity(PanelActivityDiagram panel) {
        super(panel);
        this.panelDiagram = panel;
        this.diagram      = this.panelDiagram.getDiagram();
    }
    
    @Override
    public void addAssociation(mxCell association) {
        Element source = this.getSource(association);
        Element target = this.getTarget(association);
        if (this.check(source, target))
            this.createAssociation(association);
    }
    
    @Override
    public void createAssociation(mxCell association) {
        switch (this.panelDiagram.getType()) {
            case 0:
                this.addFlowUML(association);
                break;
            case 1:
                this.addDependency(association);
                break;
            case 2:
                this.addRequires(association);
                break;
            case 3:
                this.addMutex(association);
                break;
            default:
                break;
        }
    }
    
    /**
     * Method responsible for adding the Flow UML.
     * @param association Association mxCell.
     */
    private void addFlowUML(mxCell association) {
        FlowUML flowUML = this.createFlowUML(association);
        if (flowUML != null)
            this.diagram.addFlow(flowUML);
    }
    
    /**
     * Method responsible for returning a new Flow UML.
     * @param  association Association.
     * @return Flow UML.
     */
    private FlowUML createFlowUML(mxCell association) {
        Element source = this.getSource(association);
        Element target = this.getTarget(association);
        if   ((source instanceof FinalUML == false) 
           && (target instanceof InitialUML == false))
            return new FlowUML(source, target);
        return null;
    }
}