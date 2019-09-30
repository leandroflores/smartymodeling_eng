package controller.view.panel.diagram.types;

import controller.view.panel.diagram.ControllerPanelDiagram;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import model.structural.diagram.sequence.base.InstanceUML;
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
        if (this.panelDiagram.getLifelineButton().equals(event.getSource()))
            this.setAddLifeline();
        else if (this.panelDiagram.getInstanceButton().equals(event.getSource()))
            this.setAddInstance();
    }
    
    @Override
    public void mousePressed(MouseEvent evento) {
        if (this.panelDiagram.getOperation().equals("Lifeline"))
            this.addLifeline(evento);
        else if (this.panelDiagram.getOperation().equals("Instance"))
            this.addInstance(evento);
    }
    
    /**
     * Method responsible for defining Add Lifeline Operation.
     */
    public void setAddLifeline() {
        this.panelDiagram.resetBackground();
        this.panelDiagram.getLifelineButton().setBackground(this.getFocusColor());
        this.panelDiagram.setOperation("Lifeline");
    }
    
    /**
     * Method responsible for defining Add Instance Operation.
     */
    public void setAddInstance() {
        this.panelDiagram.resetBackground();
        this.panelDiagram.getInstanceButton().setBackground(this.getFocusColor());
        this.panelDiagram.setOperation("Instance");
    }

    /**
     * Method responsible for adding a New Lifeline.
     * @param event Mouse Event.
     */
    public void addLifeline(MouseEvent event) {
        LifelineUML lifeline = new LifelineUML();
                    lifeline.setPosition(event.getX(), 20);
        this.panelDiagram.getDiagram().addLifeline(lifeline);
//                    lifeline.setDefaultName();
        this.panelDiagram.updateDiagram();
        this.panelDiagram.getViewMenu().update();
    }
    
    /**
     * Method responsible for adding a new Instance.
     * @param event Mouse Event.
     */
    public void addInstance(MouseEvent event) {
        InstanceUML instance = new InstanceUML();
                    instance.setPosition(event.getX(), 20);
//                    lifeline.setActor(this.panelDiagram.getDiagram().getProject().getDefaultStereotype());
        this.panelDiagram.getDiagram().addInstance(instance);
//                   lifeline.setDefaultName();
        this.panelDiagram.updateDiagram();
        this.panelDiagram.getViewMenu().update();
    }
}