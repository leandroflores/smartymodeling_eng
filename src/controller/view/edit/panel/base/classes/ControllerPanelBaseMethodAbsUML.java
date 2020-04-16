package controller.view.edit.panel.base.classes;

import controller.view.panel.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import model.structural.diagram.classes.base.TypeUML;
import view.edit.panel.base.classes.PanelBaseMethodAbsUML;

/**
 * <p>Class of Controller <b>ControllerPanelBaseMethodAbsUML</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseMethodAbsUML</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  27/01/2020
 * @see    controller.view.panel.ControllerPanel
 * @see    view.edit.panel.base.classes.PanelBaseMethodAbsUML
 */
public class ControllerPanelBaseMethodAbsUML extends ControllerPanel {
    private final PanelBaseMethodAbsUML panelBaseMethodAbsUML;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Base Method.
     */
    public ControllerPanelBaseMethodAbsUML(PanelBaseMethodAbsUML panel) {
        super(panel);
        this.panelBaseMethodAbsUML = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.update();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        if (this.panelBaseMethodAbsUML.getNameTextField().equals(event.getSource()))
            this.update();
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
        if (this.panelBaseMethodAbsUML.getNameTextField().equals(event.getSource()))
            this.update();
    }

    /**
     * Method responsible for setting the Abstract Method Values.
     */
    private void update() {
        this.panelBaseMethodAbsUML.getMethodUML().setVisibility(this.panelBaseMethodAbsUML.getVisibilityComboBox().getSelectedItem().toString());
        this.panelBaseMethodAbsUML.getMethodUML().setName(this.panelBaseMethodAbsUML.getNameTextField().getText().trim());
        this.panelBaseMethodAbsUML.getMethodUML().setReturn((TypeUML) this.panelBaseMethodAbsUML.getReturnComboBox().getSelectedItem());
        this.panelBaseMethodAbsUML.getViewMenu().getPanelProject().getPanelTree().getPanelTreeDiagram().updateNode(this.panelBaseMethodAbsUML.getMethodUML());
        this.panelBaseMethodAbsUML.getViewMenu().getPanelProject().getPanelTree().updateUI();
        this.panelBaseMethodAbsUML.getViewMenu().getProject().changeNames(this.panelBaseMethodAbsUML.getMethodUML());
        this.panelBaseMethodAbsUML.getViewMenu().getPanelModeling().updateDiagram(this.panelBaseMethodAbsUML.getDiagram());
        this.panelBaseMethodAbsUML.getViewMenu().setSave(false);
    }
}