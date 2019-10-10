package controller.view.edit.panel.base.classes;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.edit.panel.base.classs.PanelBaseClassUML;

/**
 * <p>Class of Controller <b>ControllerPanelBaseClassUML</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseClassUML</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  17/06/2019
 * @see    controller.view.ControllerPanel
 * @see    view.edit.panel.base.classs.PanelBaseClassUML
 */
public class ControllerPanelBaseClassUML extends ControllerPanel {
    private final PanelBaseClassUML panelBaseClassUML;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Base Class.
     */
    public ControllerPanelBaseClassUML(PanelBaseClassUML panel) {
        super(panel);
        this.panelBaseClassUML = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.panelBaseClassUML.getAbstractCheckBox().equals(event.getSource()))
            this.actionAbstract();
        else if (this.panelBaseClassUML.getFinalCheckBox().equals(event.getSource()))
            this.actionFinal();
        this.update();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        if (this.panelBaseClassUML.getNameTextField().equals(event.getSource()))
            this.update();
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
        if (this.panelBaseClassUML.getNameTextField().equals(event.getSource()))
            this.update();
    }

    /**
     * Method responsible for updating the Abstract.
     */
    private void actionAbstract() {
        this.panelBaseClassUML.getFinalCheckBox().setEnabled(!this.panelBaseClassUML.getAbstractCheckBox().isSelected());
        this.panelBaseClassUML.getFinalCheckBox().setSelected(this.panelBaseClassUML.getAbstractCheckBox().isSelected() ? false : this.panelBaseClassUML.getFinalCheckBox().isSelected());
    }
    
    /**
     * Method responsible for updating the Final.
     */
    private void actionFinal() {
        this.panelBaseClassUML.getAbstractCheckBox().setEnabled(!this.panelBaseClassUML.getFinalCheckBox().isSelected());
        this.panelBaseClassUML.getAbstractCheckBox().setSelected(this.panelBaseClassUML.getFinalCheckBox().isSelected() ? false : this.panelBaseClassUML.getAbstractCheckBox().isSelected());
    }
    
    /**
     * Method responsible for setting the Class Values.
     */
    private void update() {
        this.panelBaseClassUML.getClassUML().setName(this.panelBaseClassUML.getNameTextField().getText().trim());
        this.panelBaseClassUML.getClassUML().setAbstract(this.panelBaseClassUML.getAbstractCheckBox().isSelected());
        this.panelBaseClassUML.getClassUML().setFinal(this.panelBaseClassUML.getFinalCheckBox().isSelected());
        this.panelBaseClassUML.getClassUML().setMandatory(this.panelBaseClassUML.getMandatoryCheckBox().isSelected());
        this.panelBaseClassUML.getDiagram().updateStereotype(this.panelBaseClassUML.getClassUML());
        this.panelBaseClassUML.getViewMenu().setSave(false);
        this.panelBaseClassUML.getViewMenu().getPanelProject().getPanelTree().updateUI();
        this.panelBaseClassUML.getViewMenu().getPanelModeling().updateModelingPanel();
        this.panelBaseClassUML.getViewMenu().getPanelModeling().updateDiagram(this.panelBaseClassUML.getDiagram());
        this.panelBaseClassUML.getViewMenu().getPanelModeling().updateUI();
    }
}