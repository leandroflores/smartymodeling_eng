package controller.view.panel.base.requirement;

import controller.view.panel.base.ControllerPanelBase;
import java.awt.event.ActionEvent;
import model.structural.base.requirement.Requirement;
import model.structural.base.traceability.Traceability;
import view.modal.traceability.ViewTraceabilityOptions;
import view.panel.base.requirement.PanelBaseRequirement;

/**
 * <p>Class of Controller <b>ControllerPanelBaseRequirement</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseRequirement</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-15
 * @see    controller.view.panel.base.ControllerPanelBase
 * @see    model.structural.base.requirement.Requirement
 * @see    view.panel.base.requirement.PanelBaseRequirement
 */
public class ControllerPanelBaseRequirement extends ControllerPanelBase {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Requirement.
     */
    public ControllerPanelBaseRequirement(PanelBaseRequirement panel) {
        super(panel);
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        if (ready && getPanel().getTraceabilityButton().equals(event.getSource()))
            showTraceabilityOptions();
    }
    
    /**
     * Method responsible for showing the Traceability Options View.
     */
    private void showTraceabilityOptions() {
        Requirement  owner = getRequirement();
        Traceability traceability = getTraceability(owner);
        new ViewTraceabilityOptions(getViewMenu(), traceability).setVisible(true);
    }
    
    /**
     * Method responsible for returning the Traceability by Requirement Owner.
     * @param  owner Requirement Owner.
     * @return Traceability by Requirement Owner.
     */
    private Traceability getTraceability(Requirement owner) {
        Traceability traceability = getProject().getTraceability(owner);
        if (traceability == null) {
            traceability = new Traceability(owner);
            getProject().addTraceability(traceability);
        }
        return traceability;
    }
    
    @Override
    protected void refresh() {
        getPanelTree().updateNode(getRequirement());
        super.refresh();
    }
    
    @Override
    protected void update() {
        getRequirement().setCode(getPanel().getCodeTextField().getText().trim());
        getRequirement().setType(getPanel().getTypeComboBox().getSelectedItem().toString());
        getRequirement().setName(getPanel().getNameTextField().getText().trim());
        getRequirement().setDescription(getPanel().getDescriptionTextField().getText().trim());
        getRequirement().setPriority(getPanel().getPriorityComboBox().getSelectedItem().toString());
        getRequirement().setHistory(getPanel().getHistoryTextField().getText().trim());
        refresh();
    }
    
    /**
     * Method responsible for returning the Requirement.
     * @return Requirement.
     */
    private Requirement getRequirement() {
        return getPanel().getRequirement();
    }
    
    @Override
    public PanelBaseRequirement getPanel() {
        return (PanelBaseRequirement) panel;
    }
}