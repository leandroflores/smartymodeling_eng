package controller.view.panel.diagram.types;

import controller.view.panel.diagram.ControllerPanelDiagram;
import java.awt.event.ActionEvent;
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
    private final PanelUseCaseDiagram panelDiagram;

    /**
     * Default constructor method of Class.
     * @param panel Panel Use Case Diagram.
     */
    public ControllerPanelUseCaseDiagram(PanelUseCaseDiagram panel) {
        super(panel);
        this.panelDiagram = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        if (this.panelDiagram.getActorButton().equals(event.getSource()))
            this.setAddActor();
        else if (this.panelDiagram.getUseCaseButton().equals(event.getSource()))
            this.setAddUseCase();
    }
    
    @Override
    public void mousePressed(MouseEvent event) {
        if (this.panelDiagram.getOperation().equals("Actor"))
            this.addActor(event);
        else if (this.panelDiagram.getOperation().equals("UseCase"))
            this.addUseCase(event);
    }
    
    /**
     * Method responsible for defining Add Actor Operation.
     */
    public void setAddActor() {
        this.panelDiagram.resetBackground();
        this.panelDiagram.getActorButton().setBackground(this.getFocusColor());
        this.panelDiagram.setOperation("Actor");
    }
    
    /**
     * Method responsible for defining Add Use Case Operation.
     */
    public void setAddUseCase() {
        this.panelDiagram.resetBackground();
        this.panelDiagram.getUseCaseButton().setBackground(this.getFocusColor());
        this.panelDiagram.setOperation("UseCase");
    }

    /**
     * Method responsible for adding a new Actor.
     * @param event Mouse Event.
     */
    public void addActor(MouseEvent event) {
        ActorUML actor = new ActorUML();
                 actor.setPosition(event.getX(), event.getY());
        this.panelDiagram.getDiagram().addActor(actor);
                 actor.setDefaultName();
        this.panelDiagram.updateDiagram();
        this.panelDiagram.getViewMenu().update();
    }
    
    /**
     * Method responsible for adding a new Use Case.
     * @param event Mouse Event.
     */
    public void addUseCase(MouseEvent event) {
        UseCaseUML useCase = new UseCaseUML();
                   useCase.setPosition(event.getX(), event.getY());
        this.panelDiagram.getDiagram().addUseCase(useCase);
                   useCase.setDefaultName();
        this.panelDiagram.updateDiagram();
        this.panelDiagram.getViewMenu().update();
    }
}