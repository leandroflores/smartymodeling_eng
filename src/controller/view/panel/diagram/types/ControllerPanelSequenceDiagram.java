package controller.view.panel.diagram.types;

import controller.view.panel.diagram.ControllerPanelDiagram;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import model.structural.base.Element;
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

    /**
     * Default constructor method of Class.
     * @param panel Panel Sequence Diagram.
     */
    public ControllerPanelSequenceDiagram(PanelSequenceDiagram panel) {
        super(panel);
    }
    
    @Override
    public void mousePressed(MouseEvent evento) {
        if (this.getPanelDiagram().getOperation().equals("Lifeline"))
            this.addLifeline(evento);
        else if (this.getPanelDiagram().getOperation().equals("Instance"))
            this.addInstance(evento);
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
        this.getPanelDiagram().updateDiagram();
        this.getPanelDiagram().getViewMenu().update();
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
        this.getPanelDiagram().updateDiagram();
        this.getPanelDiagram().getViewMenu().update();
    }
    
    @Override
    public void move(KeyEvent event) {
        Element element = this.getPanelDiagram().getSelectedElement();
        if (element != null) {
            super.move(element, event);
            this.getPanelDiagram().getDiagram().updateY(element.getY());
            this.getPanelDiagram().updateDiagram();
            this.getPanelDiagram().getGraph().setSelectionCell(this.getPanelDiagram().getObjects().get(element.getId()));
        }
    }
    
    @Override
    protected PanelSequenceDiagram getPanelDiagram() {
        return (PanelSequenceDiagram) this.panel;
    }
}