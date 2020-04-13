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
import style.association.types.StyleFeatureAssociation;
import view.panel.diagram.PanelDiagram;
import view.panel.operation.types.PanelFeatureOperation;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelFeatureDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Feature Diagram Panel</b> of SMartyModeling.</p>
 * @author Henrique
 * @since  11/02/2020
 * @see    controller.view.panel.diagram.association.types.ControllerEventAssociationFeature
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
        this.controller = new ControllerPanelFeatureDiagram(this);
        this.setDefaultProperties();
        this.addComponents();
        this.setClick();
    }
    
    @Override
    public void addComponents() {
        super.addComponents();
        this.graph.setAllowLoops(false);
    }
    
    @Override
    public void initPanelOperation() {
        this.panel = new PanelFeatureOperation(this);
    }
    
    @Override
    public void initStyleAssociation() {
        this.style = new StyleFeatureAssociation();
    }
    
    @Override
    protected String getTitle(Element element) {
        return element.getName();
    }
    
    @Override
    public void setStyle() {
        switch (this.getType()) {
            case 0:
                this.getStyle().setConnectionStyle(this.getDefaultEdgeStyle(), true, true);
                break;
            case 1:
                this.getStyle().setConnectionStyle(this.getDefaultEdgeStyle(), true, false);
                break;
            case 2:
                this.getStyle().setConnectionStyle(this.getDefaultEdgeStyle(), false, false);
                break;
            default:
                this.getStyle().setConnectionStyle(this.getDefaultEdgeStyle(), false, true);
                break;
        }
    }
    
    @Override
     public void addControllers() {
        this.component.getConnectionHandler().addListener(mxEvent.CONNECT, new ControllerEventAssociationFeature(this));
        this.component.getGraph().addListener(mxEvent.CELLS_MOVED, new ControllerEventMove(this));
        this.component.getGraph().addListener(mxEvent.CELLS_RESIZED, new ControllerEventResize(this));
        this.component.addListener(mxEvent.START_EDITING, new ControllerEventEdit(this));
        this.component.addListener(mxEvent.LABEL_CHANGED, new ControllerEventChange(this));
        this.component.getGraph().getSelectionModel().addListener(mxEvent.CHANGE, new ControllerEventSelect(this));
        this.component.getGraphControl().addMouseListener(new ControllerEventFocus(this));
        this.component.getGraphControl().addMouseListener(new ControllerEventPoints(this));
     }
    
    @Override
    public FeatureDiagram getDiagram() {
        return (FeatureDiagram) this.diagram;
    }

    @Override
    public PanelFeatureOperation getPanelOperation() {
        return (PanelFeatureOperation)  this.panel;
    }
    
    @Override
    public StyleFeatureAssociation getStyle() {
        return (StyleFeatureAssociation) this.style;
    }
}