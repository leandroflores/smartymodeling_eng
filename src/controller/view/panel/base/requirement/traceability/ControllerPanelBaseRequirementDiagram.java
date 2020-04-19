package controller.view.panel.base.requirement.traceability;

import controller.view.panel.base.ControllerPanelBase;
import java.awt.event.ActionEvent;
import view.panel.base.requirement.traceability.PanelBaseRequirementDiagram;

/**
 * <p>Class of Controller <b>ControllerPanelBaseRequirementDiagram</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseRequirementDiagram</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-19
 * @see    controller.view.panel.base.ControllerPanelBase
 * @see    view.panel.base.requirement.traceability.PanelBaseRequirementDiagram
 */
public class ControllerPanelBaseRequirementDiagram extends ControllerPanelBase {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Requirement Feature.
     */
    public ControllerPanelBaseRequirementDiagram(PanelBaseRequirementDiagram panel) {
        super(panel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.ready) {
            if (this.getPanel().getDiagramComboBox().equals(event.getSource()))
                this.getPanel().updateElementComboBox();
            if (this.getPanel().getAddElementButton().equals(event.getSource()))
                this.getPanel().addElement();
            else if (this.getPanel().getDelElementButton().equals(event.getSource()))
                this.getPanel().delElement();
            this.update();
        }
    }
    
    @Override
    protected void update() {
        this.refresh();
    }
    
    @Override
    public PanelBaseRequirementDiagram getPanel() {
        return (PanelBaseRequirementDiagram) this.panel;
    }
}