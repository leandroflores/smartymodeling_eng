package controller.view.panel.diagram.types;

import controller.view.panel.diagram.ControllerPanelDiagram;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
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
    private final PanelActivityDiagram panelDiagram;

    /**
     * Default constructor method of Class.
     * @param panel Panel Activity Diagram.
     */
    public ControllerPanelActivityDiagram(PanelActivityDiagram panel) {
        super(panel);
        this.panelDiagram = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        this.panelDiagram.resetBackground();
        if (this.panelDiagram.getInitialButton().equals(event.getSource()))
            this.setAddInitial();
        else if (this.panelDiagram.getActivityButton().equals(event.getSource()))
            this.setAddActivity();
        else if (this.panelDiagram.getDecisionButton().equals(event.getSource()))
            this.setAddDecision();
        else if (this.panelDiagram.getFinalButton().equals(event.getSource()))
            this.setAddFinal();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
    }
    
    @Override
    public void mousePressed(MouseEvent evento) {
        switch (this.panelDiagram.getOperation()) {
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
     * Method responsible for setting Add Initial Operation.
     */
    public void setAddInitial() {
        this.panelDiagram.resetBackground();
        this.panelDiagram.getInitialButton().setBackground(this.getFocusColor());
        this.panelDiagram.setOperation("Initial");
    }
    
    /**
     * Method responsible for setting Add Activity Operation.
     */
    public void setAddActivity() {
        this.panelDiagram.resetBackground();
        this.panelDiagram.getActivityButton().setBackground(this.getFocusColor());
        this.panelDiagram.setOperation("Activity");
    }
    
    /**
     * Method responsible for setting Add Decision Operation.
     */
    public void setAddDecision() {
        this.panelDiagram.resetBackground();
        this.panelDiagram.getDecisionButton().setBackground(this.getFocusColor());
        this.panelDiagram.setOperation("Decision");
    }
    
    /**
     * Method responsible for setting Add Final Operation.
     */
    public void setAddFinal() {
        this.panelDiagram.resetBackground();
        this.panelDiagram.getFinalButton().setBackground(this.getFocusColor());
        this.panelDiagram.setOperation("Final");
    }

    /**
     * Method responsible for adding a new Initial UML.
     * @param event Mouse Event.
     */
    public void addInitial(MouseEvent event) {
        InitialUML initialUML = new InitialUML();
                   initialUML.setPosition(event.getX(), event.getY());
        this.panelDiagram.getDiagram().addInitial(initialUML);
        this.panelDiagram.updateDiagram();
        this.panelDiagram.getViewMenu().update();
    }
    
    /**
     * Method responsible for adding a new Activity UML.
     * @param event Mouse Event.
     */
    public void addActivity(MouseEvent event) {
        ActivityUML activityUML = new ActivityUML();
                    activityUML.setPosition(event.getX(), event.getY());
        this.panelDiagram.getDiagram().addActivity(activityUML);
        this.panelDiagram.updateDiagram();
        this.panelDiagram.getViewMenu().update();
    }
    
    /**
     * Method responsible for adding a new Decision UML.
     * @param event Mouse Event.
     */
    public void addDecision(MouseEvent event) {
        DecisionUML decisionUML = new DecisionUML();
                    decisionUML.setPosition(event.getX(), event.getY());
        this.panelDiagram.getDiagram().addDecision(decisionUML);
        this.panelDiagram.updateDiagram();
        this.panelDiagram.getViewMenu().update();
    }
    
    /**
     * Method responsible for adding a new Final UML.
     * @param event Mouse Event.
     */
    public void addFim(MouseEvent event) {
        FinalUML finalUML = new FinalUML();
                 finalUML.setPosition(event.getX(), event.getY());
        this.panelDiagram.getDiagram().addFinal(finalUML);
        this.panelDiagram.updateDiagram();
        this.panelDiagram.getViewMenu().update();
    }
}