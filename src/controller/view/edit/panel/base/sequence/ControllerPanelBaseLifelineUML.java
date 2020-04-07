package controller.view.edit.panel.base.sequence;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import model.structural.diagram.usecase.base.ActorUML;
import view.edit.panel.base.sequence.PanelBaseLifelineUML;

/**
 * <p>Class of Controller <b>ControllerPanelBaseLifelineUML</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseLifelineUML</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  03/10/2019
 * @see    controller.view.ControllerPanel
 * @see    view.edit.panel.base.sequence.PanelBaseLifelineUML
 */
public class ControllerPanelBaseLifelineUML extends ControllerPanel {
    private final PanelBaseLifelineUML panelBaseLifelineUML;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Lifeline UML.
     */
    public ControllerPanelBaseLifelineUML(PanelBaseLifelineUML panel) {
        super(panel);
        this.panelBaseLifelineUML = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.update();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        if (this.panelBaseLifelineUML.getNameTextField().equals(event.getSource()))
            this.update();
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
        if (this.panelBaseLifelineUML.getNameTextField().equals(event.getSource()))
            this.update();
    }
    
    /**
     * Method responsible for setting the Lifeline Values.
     */
    private void update() {
        this.panelBaseLifelineUML.getLifelineUML().setName(this.panelBaseLifelineUML.getNameTextField().getText().trim());
        this.panelBaseLifelineUML.getLifelineUML().setActor((ActorUML) this.panelBaseLifelineUML.getActorComboBox().getSelectedItem());
        this.panelBaseLifelineUML.getLifelineUML().setMandatory(this.panelBaseLifelineUML.getMandatoryCheckBox().isSelected());
        this.panelBaseLifelineUML.getDiagram().updateStereotype(this.panelBaseLifelineUML.getLifelineUML());
        this.panelBaseLifelineUML.getViewMenu().getPanelProject().getPanelTree().getPanelTreeUML().updateNode(this.panelBaseLifelineUML.getLifelineUML());
        this.panelBaseLifelineUML.getViewMenu().setSave(false);
        this.panelBaseLifelineUML.getViewMenu().getPanelProject().getPanelTree().updateUI();
        this.panelBaseLifelineUML.getViewMenu().getPanelModeling().updateModelingPanel();
        this.panelBaseLifelineUML.getViewMenu().getPanelModeling().updateDiagram(this.panelBaseLifelineUML.getDiagram());
    }
}