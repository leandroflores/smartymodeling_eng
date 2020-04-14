package controller.view.panel.diagram.types;

import controller.view.panel.diagram.ControllerPanelDiagram;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import model.structural.base.Element;
import model.structural.diagram.classes.base.ClassUML;
import model.structural.diagram.sequence.base.InstanceUML;
import model.structural.diagram.sequence.base.LifelineUML;
import model.structural.diagram.usecase.base.ActorUML;
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

    /**
     * Default constructor method of Class.
     * @param panel Panel Sequence Diagram.
     */
    public ControllerPanelSequenceDiagram(PanelSequenceDiagram panel) {
        super(panel);
    }
    
    @Override
    public void mousePressed(MouseEvent event) {
        if (this.getPanelDiagram().getOperation().equals("Lifeline"))
            this.addLifeline(event);
        else if (this.getPanelDiagram().getOperation().equals("Instance"))
            this.addInstance(event);
    }
    
    /**
     * Method responsible for adding a New Lifeline.
     * @param event Mouse Event.
     */
    public void addLifeline(MouseEvent event) {
        LifelineUML lifeline = new LifelineUML();
                    lifeline.setPosition(event.getX(), this.getPanelDiagram().getDiagram().getYDefault());
                    lifeline.setHeight(this.getPanelDiagram().getDiagram().getHeightDefault());
        this.getPanelDiagram().getDiagram().addLifeline(lifeline);
                    lifeline.setDefaultName();
                    lifeline.setActor(this.getDefaultActor());
        this.getPanelDiagram().updateGraph();
        this.getPanelDiagram().getViewMenu().update();
    }
    
    /**
     * Method responsible for returning the Default Actor.
     * @return Default Actor.
     */
    private ActorUML getDefaultActor() {
        return (ActorUML) this.getPanelDiagram().getViewMenu().getProject().getElements("actor").get(0);
    }
    
    /**
     * Method responsible for adding a New Instance.
     * @param event Mouse Event.
     */
    public void addInstance(MouseEvent event) {
        InstanceUML instance = new InstanceUML();
                    instance.setPosition(event.getX(), this.getPanelDiagram().getDiagram().getYDefault());
                    instance.setHeight(this.getPanelDiagram().getDiagram().getHeightDefault());
        this.getPanelDiagram().getDiagram().addInstance(instance);
                    instance.setDefaultName();
                    instance.setClassUML(this.getDefaultClass());
        this.getPanelDiagram().updateGraph();
        this.getPanelDiagram().getViewMenu().update();
    }
    
    /**
     * Method responsible for returning the Default Class.
     * @return Default Class.
     */
    private ClassUML getDefaultClass() {
        return (ClassUML) this.getPanelDiagram().getViewMenu().getProject().getElements("class").get(0);
    }
    
    @Override
    public void move(KeyEvent event) {
        Element element = this.getPanelDiagram().getSelectedElement();
        if (element != null) {
            super.move(element, event);
            this.getPanelDiagram().getDiagram().updateY(element.getY());
            this.getPanelDiagram().updateGraph();
            this.getPanelDiagram().getGraph().setSelectionCell(this.getPanelDiagram().getObjects().get(element.getId()));
        }
    }
    
    @Override
    protected PanelSequenceDiagram getPanelDiagram() {
        return (PanelSequenceDiagram) this.panel;
    }
}