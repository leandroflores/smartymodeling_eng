package controller.view.panel.diagram.types;

import controller.view.panel.diagram.ControllerPanelDiagram;
import java.awt.event.MouseEvent;
import model.structural.diagram.usecase.base.ActorUML;
import model.structural.diagram.usecase.base.UseCaseUML;
import view.panel.diagram.types.PanelUseCaseDiagram;

/**
 * <p>Class of Controller <b>ControllerPanelUseCaseDiagram</b>.</p>
 * <p>Class responsible for controlling the <b>PanelUseCaseDiagram</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-28
 * @see    controller.view.panel.diagram.ControllerPanelDiagram
 * @see    model.structural.diagram.UseCaseDiagram
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
        if (getPanel().getOperation().equals("Actor"))
            addActor(event);
        else if (getPanel().getOperation().equals("UseCase"))
            addUseCase(event);
    }
    
    /**
     * Method responsible for adding a New UML Actor.
     * @param event Mouse Event.
     */
    public void addActor(MouseEvent event) {
        ActorUML actor = new ActorUML(getPanel().getDiagram());
                 actor.setPosition(event.getX(), event.getY());
                 getPanel().getDiagram().addActor(actor);
                 actor.setDefaultName();
        getPanel().updateGraph();
        getPanel().getViewMenu().update();
    }
    
    /**
     * Method responsible for adding a New UML Use Case.
     * @param event Mouse Event.
     */
    public void addUseCase(MouseEvent event) {
        UseCaseUML useCase = new UseCaseUML(getPanel().getDiagram());
                   useCase.setPosition(event.getX(), event.getY());
                   getPanel().getDiagram().addUseCase(useCase);
                   useCase.setDefaultName();
        getPanel().updateGraph();
        getPanel().getViewMenu().update();
    }
    
    @Override
    public PanelUseCaseDiagram getPanel() {
        return (PanelUseCaseDiagram) panel;
    }
}