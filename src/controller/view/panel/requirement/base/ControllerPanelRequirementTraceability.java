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
        if (this.getPanel().getRequirementComboBox().equals(event.getSource())) {
            this.getPanel().updateElementsList();
        }else if (this.getPanel().getTypeComboBox().equals(event.getSource())) {
            this.getPanel().updateDiagramComboBox();
            this.getPanel().updateElementsList();
        }else if (this.getPanel().getDiagramComboBox().equals(event.getSource())) {
            this.getPanel().updateElementComboBox();
            this.getPanel().updateElementsList();
        }else if (this.getPanel().getAddElementButton().equals(event.getSource())) {
            this.getPanel().addElement();
        }else if (this.getPanel().getDelElementButton().equals(event.getSource())) {
            this.getPanel().delElement();
        }
    }
    
    @Override
    public void keyPressed(KeyEvent event) {}
    
    @Override
    public PanelRequirementTraceability getPanel() {
        return (PanelRequirementTraceability) this.panel;
    }
}