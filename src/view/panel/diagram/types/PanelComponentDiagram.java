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
import style.element.StyleComponent;
import model.structural.base.Element;
import model.structural.diagram.ComponentDiagram;
import model.structural.diagram.component.base.ComponentUML;
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
    public void initPanelOperation() {
        this.panel = new PanelComponentOperation(this);
    }
    
    @Override
    protected void addElement(Element element) {
        if (element instanceof ComponentUML)
            this.addComponent((ComponentUML) element);
        else 
            super.addElement(element);
    }
    
    /**
     *  Method responsible for adding the Component UML Cell.
     * @param component Component UML.
     */
    protected void addComponent(ComponentUML component) {
        this.addStyle(component.getStyleLabel(), component.getStyle());
            String title = this.getTitle(component);
            mxCell cell  = (mxCell) this.getGraph().insertVertex(this.parent, component.getId(), title, component.getPosition().x, component.getPosition().y, component.getSize().x, component.getSize().y, component.getStyleLabel());
                   cell.setConnectable(true);
            this.getGraph().insertVertex(cell, component.getId(), "", 10, 10, 20, 20, "styleImageComponent");
            this.addElementCell(component, cell);
    }
    
    @Override
    protected void loadDefaultStyles() {
        this.addStyle("styleImageComponent", new StyleComponent().getImageComponentStyle());
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