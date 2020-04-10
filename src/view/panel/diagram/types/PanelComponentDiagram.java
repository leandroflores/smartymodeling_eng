package view.panel.diagram.types;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import controller.view.panel.diagram.association.types.ControllerEventAssociationComponent;
import controller.view.panel.diagram.event.ControllerEventChange;
import controller.view.panel.diagram.event.ControllerEventEdit;
import controller.view.panel.diagram.event.ControllerEventFocus;
import controller.view.panel.diagram.event.ControllerEventMove;
import controller.view.panel.diagram.event.ControllerEventPoints;
import controller.view.panel.diagram.event.ControllerEventResize;
import controller.view.panel.diagram.types.ControllerPanelComponentDiagram;
import java.awt.GridBagConstraints;
import java.util.HashMap;
import java.util.Map;
import model.structural.diagram.ComponentDiagram;
import model.structural.diagram.component.base.ComponentUML;
import model.structural.diagram.component.base.InterfaceUML;
import view.panel.diagram.PanelDiagram;
import view.panel.operation.types.PanelComponentOperation;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelComponentDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Component Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  21/07/2019
 * @see    controller.view.panel.diagram.types.ControllerPanelComponentDiagram
 * @see    model.structural.diagram.ComponentDiagram
 * @see    view.panel.diagram.PanelDiagram
 */
public final class PanelComponentDiagram extends PanelDiagram {

    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Component Diagram.
     */
    public PanelComponentDiagram(ViewMenu view, ComponentDiagram diagram) {
        super(view, diagram);
        this.controller = new ControllerPanelComponentDiagram(this);
        this.setDefaultProperties();
        this.addComponents();
        this.setClick();
    }
    
    @Override
    public void addOperationsPanel() {
        this.panel = new PanelComponentOperation(this);
        this.add(this.panel, this.setStartConstraint(new GridBagConstraints()));
    }
    
    @Override
    public void addElements() {
        this.addComponentsUML();
        this.addInterfacesUML();
    }
    
    /**
     * Method responsible for adding the Diagram Components.
     */
    private void addComponentsUML() {
        this.addStyle("styleImageComponent", this.getImageComponentStyle());
        for (ComponentUML component_ : this.getDiagram().getComponentsList()) {
            this.addStyle(component_.getStyleLabel(), component_.getStyle());
            String title  = this.getTitle(component_);
            mxCell cell = (mxCell) this.graph.insertVertex(this.parent, component_.getId(), title, component_.getPosition().x, component_.getPosition().y, component_.getSize().x, component_.getSize().y, component_.getStyleLabel());
                   cell.setConnectable(true);
            this.graph.insertVertex(cell, null, "", 10, 10, 20, 20, "styleImageComponent");
            this.addElement(component_, cell);
        }
    }
    
    /**
     * Method responsible for adding the Diagram Interfaces.
     */
    private void addInterfacesUML() {
        for (InterfaceUML interface_ : this.getDiagram().getInterfacesList()) {
            this.addStyle(interface_.getStyleLabel(), interface_.getStyle());
            String title = this.getTitle(interface_);
            mxCell cell  = (mxCell) this.graph.insertVertex(this.parent, interface_.getId(), title, interface_.getPosition().x, interface_.getPosition().y, interface_.getSize().x, interface_.getSize().y, interface_.getStyleLabel());
                   cell.setConnectable(true);
            this.addElement(interface_, cell);
        }
    }
    
    /**
     * Method responsible for returning the Image Component Style.
     * @return Image Component Style.
     */
    private Map getImageComponentStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_MOVABLE,   0);
               style.put(mxConstants.STYLE_EDITABLE,  0);
               style.put(mxConstants.STYLE_RESIZABLE, 0);
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_IMAGE);
               style.put(mxConstants.STYLE_IMAGE, "/images/diagram/component/component.png");
        return style;
    }
    
    @Override
    public void setStyle() {
        switch (this.getType()) {
            case 0:
                this.setProvideStyle();
                break;
            case 1:
                this.setRequireStyle();
                break;
            case 2:
            case 3:
            case 4:
                this.setDependencyStyle();
                break;
            default:
                this.setProvideStyle();    
                break;
        }
    }
    
    /**
     * Method responsible for setting the Provide Style.
     */
    private void setProvideStyle() {
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_DASHED, "0");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_FONTCOLOR,   "#000000");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STROKECOLOR, "#000000");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_ENDARROW,    mxConstants.ARROW_SPACING);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STARTARROW,  mxConstants.ARROW_SPACING);
    }
    
    /**
     * Method responsible for setting the Require Style.
     */
    private void setRequireStyle() {
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_DASHED,  "1");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_ENDSIZE, "15");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_FONTCOLOR,   "#000000");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STROKECOLOR, "#000000");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_ENDARROW,   mxConstants.ARROW_OPEN);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_SPACING);
    }
    
    @Override
     public void addControllers() {
        this.component.getConnectionHandler().addListener(mxEvent.CONNECT, new ControllerEventAssociationComponent(this));
        this.component.getGraph().addListener(mxEvent.CELLS_MOVED, new ControllerEventMove(this));
        this.component.getGraph().addListener(mxEvent.CELLS_RESIZED, new ControllerEventResize(this));
        this.component.addListener(mxEvent.START_EDITING, new ControllerEventEdit(this));
        this.component.addListener(mxEvent.LABEL_CHANGED, new ControllerEventChange(this));
        
        this.component.getGraphControl().addMouseListener(new ControllerEventFocus(this));
        this.component.getGraphControl().addMouseListener(new ControllerEventPoints(this));
     }
    
    @Override
    public ComponentDiagram getDiagram() {
        return (ComponentDiagram) this.diagram;
    }
    
    @Override
    public PanelComponentOperation getPanelOperation() {
        return (PanelComponentOperation) this.panel;
    }
}