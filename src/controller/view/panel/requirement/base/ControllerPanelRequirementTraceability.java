package controller.view.panel.requirement.base;

import controller.view.panel.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.panel.requirement.base.PanelRequirementTraceability;

/**
 * <p>Class of Controller <b>ControllerPanelRequirementTraceability</b>.</p>
 * <p>Class responsible for controlling the <b>PanelRequirementTraceability</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-27
 * @see    controller.view.panel.ControllerPanel
 * @see    view.panel.requirement.base.PanelRequirementTraceability
 */
public class ControllerPanelRequirementTraceability extends ControllerPanel {

    /**
     * Default constructor method of Class.
     * @param panel Panel Requirement Traceability.
     */
    public ControllerPanelRequirementTraceability(PanelRequirementTraceability panel) {
        super(panel);
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if (getPanel().getRequirementComboBox().equals(event.getSource())) {
            getPanel().updateElementsList();
        }else if (getPanel().getTypeComboBox().equals(event.getSource())) {
            getPanel().updateDiagramComboBox();
            getPanel().updateElementsList();
        }else if (getPanel().getDiagramComboBox().equals(event.getSource())) {
            getPanel().updateElementComboBox();
            getPanel().updateElementsList();
        }else if (getPanel().getAddElementButton().equals(event.getSource())) {
            getPanel().addElement();
        }else if (getPanel().getDelElementButton().equals(event.getSource())) {
            getPanel().delElement();
        }
    }
    
    @Override
    public void keyPressed(KeyEvent event) {}
    
    @Override
    public PanelRequirementTraceability getPanel() {
        return (PanelRequirementTraceability) panel;
    }
}