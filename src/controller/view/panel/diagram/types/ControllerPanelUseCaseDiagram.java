package controller.view.panel.diagram.types;

import controller.view.panel.diagram.ControllerPanelDiagram;
import java.awt.event.MouseEvent;
import model.structural.diagram.usecase.base.ActorUML;
import model.structural.diagram.usecase.base.UseCaseUML;
import view.panel.diagram.types.PanelUseCaseDiagram;

/**
 * <p>Class of Controller <b>ControllerPanelUseCaseDiagram</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>Use Case Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  28/05/2019
 * @see    controller.view.panel.diagram.ControllerPanelDiagram
 * @see    view.panel.diagram.types.PanelUseCaseDiagram
 */
public class ControllerPanelUseCaseDiagram extends ControllerPanelDiagram {

    /**
     * Default constructor method of Class.
     * @param panel Panel Use Case Diagram.
     */
    public ControllerPanelUseCaseDiagram(PanelUseCaseDiagram panel) {
        super(panel);
    }
    
    @Override
    public void mousePressed(MouseEvent event) {
        if (this.getPanelDiagram().getOperation().equals("Actor"))
            this.addActor(event);
        else if (this.getPanelDiagram().getOperation().equals("UseCase"))
            this.addUseCase(event);
    }
    
    /**
     * Method responsible for adding a New Actor.
     * @param event Mouse Event.
     */
    public void addActor(MouseEvent event) {
        ActorUML actor = new ActorUML();
                 actor.setPosition(event.getX(), event.getY());
        this.getPanelDiagram().getDiagram().addActor(actor);
                 actor.setDefaultName();
        this.getPanelDiagram().updateDiagram();
        this.getPanelDiagram().getViewMenu().update();
    }
    
    /**
     * Method responsible for adding a new Use Case.
     * @param event Mouse Event.
     */
    public void addUseCase(MouseEvent event) {
        UseCaseUML useCase = new UseCaseUML();
                   useCase.setPosition(event.getX(), event.getY());
        this.getPanelDiagram().getDiagram().addUseCase(useCase);
                   useCase.setDefaultName();
        this.getPanelDiagram().updateDiagram();
        this.getPanelDiagram().getViewMenu().update();
    }
    
    @Override
    protected PanelUseCaseDiagram getPanelDiagram() {
        return (PanelUseCaseDiagram) this.panel;
    }
}