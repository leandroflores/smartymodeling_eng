package controller.view.panel.diagram.types;

import controller.view.panel.diagram.ControllerPanelDiagram;
import java.awt.event.MouseEvent;
import model.structural.diagram.activity.base.ActivityUML;
import model.structural.diagram.activity.base.DecisionUML;
import model.structural.diagram.activity.base.FinalUML;
import model.structural.diagram.activity.base.InitialUML;
import view.panel.diagram.types.PanelActivityDiagram;

/**
 * <p>Class of Controller <b>ControllerPanelActivityDiagram</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>Activity Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  18/07/2019
 * @see    controller.view.panel.diagram.ControllerPanelDiagram
 * @see    view.panel.diagram.types.PanelActivityDiagram
 */
public class ControllerPanelActivityDiagram extends ControllerPanelDiagram {

    /**
     * Default constructor method of Class.
     * @param panel Panel Activity Diagram.
     */
    public ControllerPanelActivityDiagram(PanelActivityDiagram panel) {
        super(panel);
    }

    @Override
    public void mousePressed(MouseEvent evento) {
        switch (this.getPanelDiagram().getOperation()) {
            case "Initial":
                this.addInitial(evento);
                break;
            case "Activity":
                this.addActivity(evento);
                break;
            case "Decision":
                this.addDecision(evento);
                break;
            case "Final":
                this.addFim(evento);
                break;
            default:
                break;
        }
    }
    
    /**
     * Method responsible for adding a New Initial UML.
     * @param event Mouse Event.
     */
    public void addInitial(MouseEvent event) {
        InitialUML initial = new InitialUML();
                   initial.setPosition(event.getX(), event.getY());
        this.getPanelDiagram().getDiagram().addInitial(initial);
        this.getPanelDiagram().updateGraph();
        this.getPanelDiagram().getViewMenu().update();
    }
    
    /**
     * Method responsible for adding a New Activity UML.
     * @param event Mouse Event.
     */
    public void addActivity(MouseEvent event) {
        ActivityUML activity = new ActivityUML();
                    activity.setPosition(event.getX(), event.getY());
        this.getPanelDiagram().getDiagram().addActivity(activity);
        this.getPanelDiagram().updateGraph();
        this.getPanelDiagram().getViewMenu().update();
    }
    
    /**
     * Method responsible for adding a New Decision UML.
     * @param event Mouse Event.
     */
    public void addDecision(MouseEvent event) {
        DecisionUML decision = new DecisionUML();
                    decision.setPosition(event.getX(), event.getY());
        this.getPanelDiagram().getDiagram().addDecision(decision);
        this.getPanelDiagram().updateGraph();
        this.getPanelDiagram().getViewMenu().update();
    }
    
    /**
     * Method responsible for adding a New Final UML.
     * @param event Mouse Event.
     */
    public void addFim(MouseEvent event) {
        FinalUML final_ = new FinalUML();
                 final_.setPosition(event.getX(), event.getY());
        this.getPanelDiagram().getDiagram().addFinal(final_);
        this.getPanelDiagram().updateGraph();
        this.getPanelDiagram().getViewMenu().update();
    }
    
    @Override
    protected PanelActivityDiagram getPanelDiagram() {
        return (PanelActivityDiagram) this.panel;
    }
}