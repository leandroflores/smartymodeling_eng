package controller.view.panel.diagram.types;

import com.mxgraph.model.mxCell;
import controller.view.panel.diagram.ControllerPanelDiagram;
import java.awt.event.ActionEvent;
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
                    lifeline.setPosition(event.getX(), this.panelDiagram.getDiagram().getYDefault());
                    lifeline.setHeight(this.panelDiagram.getDiagram().getHeightDefault());
        this.panelDiagram.getDiagram().addLifeline(lifeline);
                    lifeline.setDefaultName();
        this.panelDiagram.updateDiagram();
        this.panelDiagram.getViewMenu().update();
    }
    
    /**
     * Method responsible for adding a new Instance.
     * @param event Mouse Event.
     */
    public void addInstance(MouseEvent event) {
        InstanceUML instance = new InstanceUML();
                    instance.setPosition(event.getX(), this.panelDiagram.getDiagram().getYDefault());
                    instance.setHeight(this.panelDiagram.getDiagram().getHeightDefault());
        this.panelDiagram.getDiagram().addInstance(instance);
                    instance.setDefaultName();
        this.panelDiagram.updateDiagram();
        this.panelDiagram.getViewMenu().update();
    }
    
    @Override
    public void move(KeyEvent event) {
        mxCell  cell    = (mxCell) this.panelDiagram.getGraph().getSelectionCell();
        String  id      = this.panelDiagram.getIdentifiers().get(cell);
        Element element = this.panelDiagram.getDiagram().getElement(id);
        if (element != null) {
            if (event.getKeyCode() == KeyEvent.VK_UP)
                element.dy(-10);
            if (event.getKeyCode() == KeyEvent.VK_DOWN)
                element.dy(10);
            if (event.getKeyCode() == KeyEvent.VK_LEFT)
                element.dx(-10);
            if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                element.dx(10);
            this.panelDiagram.getDiagram().updateY(element.getY());
            this.panelDiagram.updateDiagram();
            this.panelDiagram.getGraph().setSelectionCell(this.panelDiagram.getObjects().get(element.getId()));
        }
    }
}