package view.panel.diagram.types;

import com.mxgraph.util.mxEvent;
import controller.view.panel.diagram.association.types.ControllerEventAssociationFeature;
import controller.view.panel.diagram.event.ControllerEventChange;
import controller.view.panel.diagram.event.ControllerEventEdit;
import controller.view.panel.diagram.event.ControllerEventMove;
import controller.view.panel.diagram.event.ControllerEventFocus;
import controller.view.panel.diagram.event.ControllerEventPoints;
import controller.view.panel.diagram.event.ControllerEventSelect;
import controller.view.panel.diagram.event.feature.ControllerEventResize;
import controller.view.panel.diagram.types.ControllerPanelFeatureDiagram;
import model.structural.base.Element;
import model.structural.diagram.FeatureDiagram;
import model.structural.diagram.feature.base.Feature;
import style.association.types.StyleFeatureAssociation;
import view.panel.diagram.PanelDiagram;
import view.panel.operation.types.PanelFeatureOperation;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelFeatureDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Feature Diagram Panel</b> of SMartyModeling.</p>
 * @author Henrique
 * @since  2020-02-11
 * @see    controller.view.panel.diagram.types.ControllerPanelFeatureDiagram
 * @see    model.structural.diagram.FeatureDiagram
 * @see    view.panel.diagram.PanelDiagram
 */
public final class PanelFeatureDiagram extends PanelDiagram {

    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Feature Diagram.
     */
    public PanelFeatureDiagram(ViewMenu view, FeatureDiagram diagram) {
        super(view, diagram);
        controller = new ControllerPanelFeatureDiagram(this);
        setDefaultProperties();
        addComponents();
        setClick();
    }
    
    @Override
    public void addComponents() {
        super.addComponents();
        graph.setAllowLoops(false);
    }
    
    @Override
    public void initPanelOperation() {
        panel = new PanelFeatureOperation(this);
    }
    
    @Override
    public void initStyleAssociation() {
        style = new StyleFeatureAssociation();
    }
    
    @Override
    protected String getTitle(Element element) {
        if (element instanceof Feature)
            return element.getName();
        return element.getTitle();
    }
    
    @Override
    public void setStyle() {
        switch (getType()) {
            case 0:
                getStyle().setConnectionStyle(getEdgeStyle(), true, true);
                break;
            case 1:
                getStyle().setConnectionStyle(getEdgeStyle(), true, false);
                break;
            case 2:
                getStyle().setCombinationStyle(getEdgeStyle());
                break;
            default:
                getStyle().setConnectionStyle(getEdgeStyle(), true, true);
                break;
        }
    }
    
    @Override
     public void addControllers() {
        component.getConnectionHandler().addListener(mxEvent.CONNECT, new ControllerEventAssociationFeature(this));
        component.getGraph().addListener(mxEvent.CELLS_MOVED, new ControllerEventMove(this));
        component.getGraph().addListener(mxEvent.CELLS_RESIZED, new ControllerEventResize(this));
        component.addListener(mxEvent.START_EDITING, new ControllerEventEdit(this));
        component.addListener(mxEvent.LABEL_CHANGED, new ControllerEventChange(this));
        component.getGraph().getSelectionModel().addListener(mxEvent.CHANGE, new ControllerEventSelect(this));
        component.getGraphControl().addMouseListener(new ControllerEventFocus(this));
        component.getGraphControl().addMouseListener(new ControllerEventPoints(this));
     }
    
    @Override
    public FeatureDiagram getDiagram() {
        return (FeatureDiagram) diagram;
    }

    @Override
    public PanelFeatureOperation getPanelOperation() {
        return (PanelFeatureOperation)  panel;
    }
    
    @Override
    public StyleFeatureAssociation getStyle() {
        return (StyleFeatureAssociation) style;
    }
}