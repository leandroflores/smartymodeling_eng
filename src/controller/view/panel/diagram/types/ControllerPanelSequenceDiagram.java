package controller.view.panel.diagram.types;

import controller.view.panel.diagram.ControllerPanelDiagram;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import model.structural.diagram.sequence.base.LifelineUML;
import model.structural.diagram.usecase.base.ActorUML;
import model.structural.diagram.usecase.base.UseCaseUML;
import view.panel.diagram.types.PanelSequenceDiagram;
import view.panel.diagram.types.PanelUseCaseDiagram;

/**
 * <p>Class of Controller <b>ControllerPanelSequenceDiagram</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>Sequence Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  25/07/2019
 * @see    controller.view.panel.diagram.ControllerPanelDiagram
 * @see    view.panel.diagram.types.PanelSequenceDiagram
 */
public class ControllerPanelSequenceDiagram extends ControllerPanelDiagram {
    private final PanelSequenceDiagram panelDiagram;

    /**
     * Default constructor method of Class.
     * @param panel Panel Sequence Diagram.
     */
    public ControllerPanelSequenceDiagram(PanelSequenceDiagram panel) {
        super(panel);
        this.panelDiagram = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        if (this.panelDiagram.getLifelineActorButton().equals(event.getSource()))
            this.setAddLifelineActor();
        else if (this.panelDiagram.getLifelineClassButton().equals(event.getSource()))
            this.setAddLifelineClass();
    }
    
    @Override
    public void mousePressed(MouseEvent evento) {
        if (this.panelDiagram.getOperation().equals("Actor"))
            this.addLifelineActor(evento);
        else if (this.panelDiagram.getOperation().equals("UseCase"))
            this.addUseCase(evento);
    }
    
    /**
     * Method responsible for defining Add Lifeline Actor Operation.
     */
    public void setAddLifelineActor() {
        this.panelDiagram.resetBackground();
        this.panelDiagram.getLifelineActorButton().setBackground(this.getFocusColor());
        this.panelDiagram.setOperation("LifelineActor");
    }
    
    /**
     * Method responsible for defining Add Lifeline Class Operation.
     */
    public void setAddLifelineClass() {
        this.panelDiagram.resetBackground();
        this.panelDiagram.getLifelineClassButton().setBackground(this.getFocusColor());
        this.panelDiagram.setOperation("LifelineClass");
    }

    /**
     * Method responsible for adding a new Lifeline Actor.
     * @param event Mouse Event.
     */
    public void addLifelineActor(MouseEvent event) {
        LifelineUML lifeline = new LifelineUML();
                    lifeline.setPosition(event.getX(), event.getY());
        this.panelDiagram.getDiagram().addLifeline(lifeline);
//                    lifeline.setDefaultName();
        this.panelDiagram.updateDiagram();
        this.panelDiagram.getViewMenu().update();
    }
    
    /**
     * Method responsible for adding a new Lifeline Class.
     * @param event Mouse Event.
     */
    public void addUseCase(MouseEvent event) {
        LifelineUML lifeline = new LifelineUML();
                    lifeline.setPosition(event.getX(), event.getY());
        this.panelDiagram.getDiagram().addLifeline(lifeline);
//                   lifeline.setDefaultName();
        this.panelDiagram.updateDiagram();
        this.panelDiagram.getViewMenu().update();
    }
}