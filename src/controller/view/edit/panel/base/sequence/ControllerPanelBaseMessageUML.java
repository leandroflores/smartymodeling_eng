package controller.view.edit.panel.base.sequence;

import controller.view.panel.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import model.structural.diagram.classes.base.MethodUML;
import view.edit.panel.base.sequence.PanelBaseMessageUML;

/**
 * <p>Class of Controller <b>ControllerPanelBaseMessageUML</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseMessageUML</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  04/10/2019
 * @see    controller.view.panel.ControllerPanel
 * @see    view.edit.panel.base.sequence.PanelBaseMessageUML
 */
public class ControllerPanelBaseMessageUML extends ControllerPanel {
    private final PanelBaseMessageUML panelBaseMessageUML;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Message UML.
     */
    public ControllerPanelBaseMessageUML(PanelBaseMessageUML panel) {
        super(panel);
        this.panelBaseMessageUML = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.update();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        if (this.panelBaseMessageUML.getNameTextField().equals(event.getSource()))
            this.update();
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
        if (this.panelBaseMessageUML.getNameTextField().equals(event.getSource()))
            this.update();
    }
    
    /**
     * Method responsible for setting the Message Values.
     */
    private void update() {
        this.panelBaseMessageUML.getMessageUML().setName(this.panelBaseMessageUML.getNameTextField().getText().trim());
        this.panelBaseMessageUML.getMessageUML().setMethod((MethodUML) this.panelBaseMessageUML.getMethodComboBox().getSelectedItem());
        this.panelBaseMessageUML.getViewMenu().setSave(false);
        this.panelBaseMessageUML.getViewMenu().getPanelProject().getPanelTree().updateUI();
        this.panelBaseMessageUML.getViewMenu().getPanelModeling().updateModelingPanels();
        this.panelBaseMessageUML.getViewMenu().getPanelModeling().updateDiagram(this.panelBaseMessageUML.getDiagram());
    }
}