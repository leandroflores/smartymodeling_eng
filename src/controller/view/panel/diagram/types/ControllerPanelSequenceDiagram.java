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
 * <p>Class responsible for controlling the <b>PanelSequenceDiagram</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-25
 * @see    controller.view.panel.diagram.ControllerPanelDiagram
 * @see    model.structural.diagram.SequenceDiagram
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
        if (getPanel().getOperation().equals("Lifeline"))
            addLifeline(event);
        else if (getPanel().getOperation().equals("Instance"))
            addInstance(event);
    }
    
    /**
     * Method responsible for adding a New UML Lifeline.
     * @param event Mouse Event.
     */
    public void addLifeline(MouseEvent event) {
        LifelineUML lifeline = new LifelineUML(getPanel().getDiagram());
                    lifeline.setPosition(event.getX(), getPanel().getDiagram().getYDefault());
                    lifeline.setHeight(getPanel().getDiagram().getHeightDefault());
                    getPanel().getDiagram().addLifeline(lifeline);
                    lifeline.setDefaultName();
                    lifeline.setActor(getDefaultActor());
        getPanel().updateGraph();
        getPanel().getViewMenu().update();
    }
    
    /**
     * Method responsible for returning the Default Actor.
     * @return Default Actor.
     */
    private ActorUML getDefaultActor() {
        return (ActorUML) getPanel().getViewMenu().getProject().getElements("actor").get(0);
    }
    
    /**
     * Method responsible for adding a New UML Instance.
     * @param event Mouse Event.
     */
    public void addInstance(MouseEvent event) {
        InstanceUML instance = new InstanceUML(getPanel().getDiagram());
                    instance.setPosition(event.getX(), getPanel().getDiagram().getYDefault());
                    instance.setHeight(getPanel().getDiagram().getHeightDefault());
                    getPanel().getDiagram().addInstance(instance);
                    instance.setDefaultName();
                    instance.setClassUML(getDefaultClass());
        getPanel().updateGraph();
        getPanel().getViewMenu().update();
    }
    
    /**
     * Method responsible for returning the Default Class.
     * @return Default Class.
     */
    private ClassUML getDefaultClass() {
        return (ClassUML) getPanel().getViewMenu().getProject().getElements("class").get(0);
    }
    
    @Override
    public void move(KeyEvent event) {
        Element element = getPanel().getSelectedElement();
        if (element != null) {
            super.move(element, event);
            getPanel().getDiagram().updateY(element.getY());
            getPanel().updateGraph();
            getPanel().getGraph().setSelectionCell(getPanel().getObjects().get(element.getId()));
        }
    }
    
    @Override
    public PanelSequenceDiagram getPanel() {
        return (PanelSequenceDiagram) panel;
    }
}