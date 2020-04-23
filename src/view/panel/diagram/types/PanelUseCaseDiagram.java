package view.panel.diagram.types;

import com.mxgraph.util.mxEvent;
import controller.view.panel.diagram.association.types.ControllerEventAssociationUseCase;
import controller.view.panel.diagram.event.ControllerEventChange;
import controller.view.panel.diagram.event.ControllerEventEdit;
import controller.view.panel.diagram.event.ControllerEventMove;
import controller.view.panel.diagram.event.ControllerEventFocus;
import controller.view.panel.diagram.event.ControllerEventPoints;
import controller.view.panel.diagram.event.ControllerEventResize;
import controller.view.panel.diagram.event.ControllerEventSelect;
import controller.view.panel.diagram.types.ControllerPanelUseCaseDiagram;
import model.structural.diagram.UseCaseDiagram;
import view.panel.diagram.PanelDiagram;
import view.panel.operation.types.PanelUseCaseOperation;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelUseCaseDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Use Case Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  28/05/2019
 * @see    controller.view.panel.diagram.association.types.ControllerEventAssociationUseCase
 * @see    controller.view.panel.diagram.types.ControllerPanelUseCaseDiagram
 * @see    model.structural.diagram.UseCaseDiagram
 * @see    view.panel.diagram.PanelDiagram
 */
public final class PanelUseCaseDiagram extends PanelDiagram {

    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Use Case Diagram.
     */
    public PanelUseCaseDiagram(ViewMenu view, UseCaseDiagram diagram) {
        super(view, diagram);
        this.controller = new ControllerPanelUseCaseDiagram(this);
        this.setDefaultProperties();
        this.addComponents();
        this.setClick();
    }
    
    @Override
    public void initPanelOperation() {
        this.panel = new PanelUseCaseOperation(this);
    }
    
    @Override
    public void setStyle() {
        switch (this.getType()) {
            case 0:
                this.setDefaultStyle();
                break;
            case 3:
                this.setGeneralizationStyle();
                break;
            default:
                this.setDependencyStyle();
                break;
        }
    }
    
    @Override
     public void addControllers() {
        this.component.getConnectionHandler().addListener(mxEvent.CONNECT, new ControllerEventAssociationUseCase(this));
        this.component.getGraph().addListener(mxEvent.CELLS_MOVED, new ControllerEventMove(this));
        this.component.getGraph().addListener(mxEvent.CELLS_RESIZED, new ControllerEventResize(this));
        this.component.addListener(mxEvent.START_EDITING, new ControllerEventEdit(this));
        this.component.addListener(mxEvent.LABEL_CHANGED, new ControllerEventChange(this));
        this.component.getGraph().getSelectionModel().addListener(mxEvent.CHANGE, new ControllerEventSelect(this));
        
        this.component.getGraphControl().addMouseListener(new ControllerEventFocus(this));
        this.component.getGraphControl().addMouseListener(new ControllerEventPoints(this));
     }
    
    @Override
    public UseCaseDiagram getDiagram() {
        return (UseCaseDiagram) this.diagram;
    }
    
    @Override
    public PanelUseCaseOperation getPanelOperation() {
        return (PanelUseCaseOperation) this.panel;
    }
}