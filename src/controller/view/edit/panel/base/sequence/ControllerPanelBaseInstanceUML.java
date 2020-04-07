package controller.view.edit.panel.base.sequence;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import model.structural.diagram.classes.base.ClassUML;
import view.edit.panel.base.sequence.PanelBaseInstanceUML;

/**
 * <p>Class of Controller <b>ControllerPanelBaseInstanceUML</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseInstanceUML</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  03/10/2019
 * @see    controller.view.ControllerPanel
 * @see    view.edit.panel.base.sequence.PanelBaseInstanceUML
 */
public class ControllerPanelBaseInstanceUML extends ControllerPanel {
    private final PanelBaseInstanceUML panelBaseInstanceUML;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Instance UML.
     */
    public ControllerPanelBaseInstanceUML(PanelBaseInstanceUML panel) {
        super(panel);
        this.panelBaseInstanceUML = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.update();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        if (this.panelBaseInstanceUML.getNameTextField().equals(event.getSource()))
            this.update();
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
        if (this.panelBaseInstanceUML.getNameTextField().equals(event.getSource()))
            this.update();
    }
    
    /**
     * Method responsible for setting the Instance Values.
     */
    private void update() {
        this.panelBaseInstanceUML.getInstanceUML().setName(this.panelBaseInstanceUML.getNameTextField().getText().trim());
        this.panelBaseInstanceUML.getInstanceUML().setClassUML((ClassUML) this.panelBaseInstanceUML.getClassComboBox().getSelectedItem());
        this.panelBaseInstanceUML.getInstanceUML().setMandatory(this.panelBaseInstanceUML.getMandatoryCheckBox().isSelected());
        this.panelBaseInstanceUML.getDiagram().updateStereotype(this.panelBaseInstanceUML.getInstanceUML());
        this.panelBaseInstanceUML.getViewMenu().getPanelProject().getPanelTree().getPanelTreeUML().updateNode(this.panelBaseInstanceUML.getInstanceUML());
        this.panelBaseInstanceUML.getViewMenu().setSave(false);
        this.panelBaseInstanceUML.getViewMenu().getPanelProject().getPanelTree().updateUI();
        this.panelBaseInstanceUML.getViewMenu().getPanelModeling().updateModelingPanel();
        this.panelBaseInstanceUML.getViewMenu().getPanelModeling().updateDiagram(this.panelBaseInstanceUML.getDiagram());
    }
}