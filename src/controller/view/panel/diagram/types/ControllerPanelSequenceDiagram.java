package controller.view.panel.diagram.types;

import controller.view.panel.diagram.ControllerPanelDiagram;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import model.structural.diagram.sequence.base.LifelineUML;
import view.panel.diagram.types.PanelSequenceDiagram;

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
        if (this.panelDiagram.getOperation().equals("LifelineActor"))
            this.addLifelineActor(evento);
        else if (this.panelDiagram.getOperation().equals("LifelineClass"))
            this.addLifelineClass(evento);
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
                    lifeline.setPosition(event.getX(), 20);
        this.panelDiagram.getDiagram().addLifeline(lifeline);
//                    lifeline.setDefaultName();
        this.panelDiagram.updateDiagram();
        this.panelDiagram.getViewMenu().update();
    }
    
    /**
     * Method responsible for adding a new Lifeline Class.
     * @param event Mouse Event.
     */
    public void addLifelineClass(MouseEvent event) {
        LifelineUML lifeline = new LifelineUML();
                    lifeline.setPosition(event.getX(), 20);
//                    lifeline.setElement(this.panelDiagram.getDiagram().getProject().getDefaultStereotype());
        this.panelDiagram.getDiagram().addLifeline(lifeline);
//                   lifeline.setDefaultName();
        this.panelDiagram.updateDiagram();
        this.panelDiagram.getViewMenu().update();
    }
}