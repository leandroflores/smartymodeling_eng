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
 * <p>Class responsible for controlling the <b>PanelActivityDiagram</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-18
 * @see    controller.view.panel.diagram.ControllerPanelDiagram
 * @see    model.structural.diagram.ActivityDiagram
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
    public void mousePressed(MouseEvent event) {
        switch (getPanel().getOperation()) {
            case "Initial":
                addInitial(event);
                break;
            case "Activity":
                addActivity(event);
                break;
            case "Decision":
                addDecision(event);
                break;
            case "Final":
                addFinal(event);
                break;
            default:
                break;
        }
    }
    
    /**
     * Method responsible for adding a New UML Initial.
     * @param event Mouse Event.
     */
    public void addInitial(MouseEvent event) {
        InitialUML initial = new InitialUML(getPanel().getDiagram());
                   initial.setPosition(event.getX(), event.getY());
        getPanel().getDiagram().addInitial(initial);
        getPanel().updateGraph();
        getPanel().getViewMenu().update();
    }
    
    /**
     * Method responsible for adding a New UML Activity.
     * @param event Mouse Event.
     */
    public void addActivity(MouseEvent event) {
        ActivityUML activity = new ActivityUML(getPanel().getDiagram());
                    activity.setPosition(event.getX(), event.getY());
        getPanel().getDiagram().addActivity(activity);
        getPanel().updateGraph();
        getPanel().getViewMenu().update();
    }
    
    /**
     * Method responsible for adding a New UML Decision.
     * @param event Mouse Event.
     */
    public void addDecision(MouseEvent event) {
        DecisionUML decision = new DecisionUML(getPanel().getDiagram());
                    decision.setPosition(event.getX(), event.getY());
        getPanel().getDiagram().addDecision(decision);
        getPanel().updateGraph();
        getPanel().getViewMenu().update();
    }
    
    /**
     * Method responsible for adding a New UML Final.
     * @param event Mouse Event.
     */
    public void addFinal(MouseEvent event) {
        FinalUML final_ = new FinalUML(getPanel().getDiagram());
                 final_.setPosition(event.getX(), event.getY());
        getPanel().getDiagram().addFinal(final_);
        getPanel().updateGraph();
        getPanel().getViewMenu().update();
    }
    
    @Override
    public PanelActivityDiagram getPanel() {
        return (PanelActivityDiagram) panel;
    }
}