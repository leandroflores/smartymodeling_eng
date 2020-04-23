package view.panel.diagram.types;

import com.mxgraph.util.mxEvent;
import controller.view.panel.diagram.association.types.ControllerEventAssociationActivity;
import controller.view.panel.diagram.event.ControllerEventEdit;
import controller.view.panel.diagram.event.ControllerEventFocus;
import controller.view.panel.diagram.event.ControllerEventMove;
import controller.view.panel.diagram.event.ControllerEventPoints;
import controller.view.panel.diagram.event.ControllerEventResize;
import controller.view.panel.diagram.event.activity.ControllerEventChange;
import controller.view.panel.diagram.types.ControllerPanelActivityDiagram;
import model.structural.diagram.ActivityDiagram;
import style.association.types.StyleActivityAssociation;
import view.panel.diagram.PanelDiagram;
import view.panel.operation.types.PanelActivityOperation;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelActivityDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Activity Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  18/07/2019
 * @see    controller.view.panel.diagram.types.ControllerPanelActivityDiagram
 * @see    model.structural.diagram.ActivityDiagram
 * @see    view.panel.diagram.PanelDiagram
 */
public final class PanelActivityDiagram extends PanelDiagram {

    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Activity Diagram.
     */
    public PanelActivityDiagram(ViewMenu view, ActivityDiagram diagram) {
        super(view, diagram);
        this.controller = new ControllerPanelActivityDiagram(this);
        this.setDefaultProperties();
        this.addComponents();
        this.setClick();
    }
    
    @Override
    public void initPanelOperation() {
        this.panel = new PanelActivityOperation(this);
    }
    
    @Override
    public void initStyleAssociation() {
        this.style = new StyleActivityAssociation();
    }
    
    @Override
    public void setStyle() {
        switch (this.getType()) {
            case 0:
                this.getStyle().setFlowStyle(this.getEdgeStyle());
                break;
            default:
                this.setDependencyStyle();
                break;
        }
    }
    
    @Override
    public void addControllers() {
        this.component.getConnectionHandler().addListener(mxEvent.CONNECT, new ControllerEventAssociationActivity(this));
        this.component.getGraph().addListener(mxEvent.CELLS_MOVED, new ControllerEventMove(this));
        this.component.getGraph().addListener(mxEvent.CELLS_RESIZED, new ControllerEventResize(this));
        this.component.addListener(mxEvent.START_EDITING, new ControllerEventEdit(this));
        this.component.addListener(mxEvent.LABEL_CHANGED, new ControllerEventChange(this));
        
        this.component.getGraphControl().addMouseListener(new ControllerEventFocus(this));
        this.component.getGraphControl().addMouseListener(new ControllerEventPoints(this));
    }
    
    @Override
    public ActivityDiagram getDiagram() {
        return (ActivityDiagram) this.diagram;
    }
    
    @Override
    public PanelActivityOperation getPanelOperation() {
        return (PanelActivityOperation) this.panel;
    }
    
    @Override
    public StyleActivityAssociation getStyle() {
        return (StyleActivityAssociation) this.style;
    }
}