package controller.view.panel.requirement.base;

import controller.view.panel.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.panel.requirement.base.PanelRequirementMatrix;

/**
 * <p>Class of Controller <b>ControllerPanelRequirementMatrix</b>.</p>
 * <p>Class responsible for controlling the <b>PanelRequirementMatrix</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-27
 * @see    controller.view.panel.ControllerPanel
 * @see    view.panel.requirement.base.PanelRequirementMatrix
 */
public class ControllerPanelRequirementMatrix extends ControllerPanel {

    /**
     * Default constructor method of Class.
     * @param panel Panel Requirement Matrix.
     */
    public ControllerPanelRequirementMatrix(PanelRequirementMatrix panel) {
        super(panel);
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        getPanel().updateMatrix();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {}
    
    @Override
    public PanelRequirementMatrix getPanel() {
        return (PanelRequirementMatrix) panel;
    }
}