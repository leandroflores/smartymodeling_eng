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
 * @since  2019-07-18
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
        controller = new ControllerPanelActivityDiagram(this);
        setDefaultProperties();
        addComponents();
        setClick();
    }
    
    @Override
    public void initPanelOperation() {
        panel = new PanelActivityOperation(this);
    }
    
    @Override
    public void initStyleAssociation() {
        style = new StyleActivityAssociation();
    }
    
    @Override
    public void setStyle() {
        switch (getType()) {
            case 0:
                getStyle().setFlowStyle(getEdgeStyle());
                break;
            default:
                setDependencyStyle();
                break;
        }
    }
    
    @Override
    public void addControllers() {
        component.getConnectionHandler().addListener(mxEvent.CONNECT, new ControllerEventAssociationActivity(this));
        component.getGraph().addListener(mxEvent.CELLS_MOVED, new ControllerEventMove(this));
        component.getGraph().addListener(mxEvent.CELLS_RESIZED, new ControllerEventResize(this));
        component.addListener(mxEvent.START_EDITING, new ControllerEventEdit(this));
        component.addListener(mxEvent.LABEL_CHANGED, new ControllerEventChange(this));
        
        component.getGraphControl().addMouseListener(new ControllerEventFocus(this));
        component.getGraphControl().addMouseListener(new ControllerEventPoints(this));
    }
    
    @Override
    public ActivityDiagram getDiagram() {
        return (ActivityDiagram) diagram;
    }
    
    @Override
    public PanelActivityOperation getPanelOperation() {
        return (PanelActivityOperation) panel;
    }
    
    @Override
    public StyleActivityAssociation getStyle() {
        return (StyleActivityAssociation) style;
    }
}