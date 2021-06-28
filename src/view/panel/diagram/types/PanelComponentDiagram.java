package view.panel.diagram.types;

import com.mxgraph.model.mxCell;
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
import style.association.types.StyleComponentAssociation;
import view.panel.diagram.PanelDiagram;
import view.panel.operation.types.PanelComponentOperation;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelComponentDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Component Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-21
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
        controller = new ControllerPanelComponentDiagram(this);
        setDefaultProperties();
        addComponents();
        setClick();
    }
    
    @Override
    public void initPanelOperation() {
        panel = new PanelComponentOperation(this);
    }
    
    @Override
    public void initStyleAssociation() {
        style = new StyleComponentAssociation();
    }
    
    @Override
    protected void addElement(Element element) {
        if (element instanceof ComponentUML)
            addComponent((ComponentUML) element);
        else 
            super.addElement(element);
    }
    
    /**
     *  Method responsible for adding the Component UML Cell.
     * @param component Component UML.
     */
    protected void addComponent(ComponentUML component) {
        addStyle(component.getStyleLabel(), component.getStyle());
            String title = getTitle(component);
            mxCell cell  = (mxCell) getGraph().insertVertex(parent, component.getId(), title, component.getPosition().x, component.getPosition().y, component.getSize().x, component.getSize().y, component.getStyleLabel());
                   cell.setConnectable(true);
            getGraph().insertVertex(cell, component.getId(), "", 10, 10, 20, 20, "styleImageComponent");
            addElementCell(component, cell);
    }
    
    @Override
    protected void loadDefaultStyles() {
        addStyle("styleImageComponent", new StyleComponent().getImageComponentStyle());
    }
    
    @Override
    public void setStyle() {
        switch (getType()) {
            case 0:
                getStyle().setProvideStyle(getEdgeStyle());
                break;
            case 1:
                getStyle().setRequireStyle(getEdgeStyle());
                break;
            case 2:
            case 3:
            case 4:
                setDependencyStyle();
                break;
            default:
                getStyle().setProvideStyle(getEdgeStyle());
                break;
        }
    }
    
    @Override
     public void addControllers() {
        component.getConnectionHandler().addListener(mxEvent.CONNECT, new ControllerEventAssociationComponent(this));
        component.getGraph().addListener(mxEvent.CELLS_MOVED, new ControllerEventMove(this));
        component.getGraph().addListener(mxEvent.CELLS_RESIZED, new ControllerEventResize(this));
        component.addListener(mxEvent.START_EDITING, new ControllerEventEdit(this));
        component.addListener(mxEvent.LABEL_CHANGED, new ControllerEventChange(this));
        
        component.getGraphControl().addMouseListener(new ControllerEventFocus(this));
        component.getGraphControl().addMouseListener(new ControllerEventPoints(this));
     }
    
    @Override
    public ComponentDiagram getDiagram() {
        return (ComponentDiagram) diagram;
    }
    
    @Override
    public PanelComponentOperation getPanelOperation() {
        return (PanelComponentOperation) panel;
    }
    
    @Override
    public StyleComponentAssociation getStyle() {
        return (StyleComponentAssociation) style;
    }
}