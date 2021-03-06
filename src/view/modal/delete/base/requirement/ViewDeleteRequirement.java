package view.modal.delete.base.requirement;

import controller.view.modal.delete.base.requirement.ControllerViewDeleteRequirement;
import model.structural.base.requirement.Requirement;
import view.modal.delete.ViewDelete;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewDeleteRequirement</b>.</p>
 * <p>Class responsible for defining the <b>Requirement Delete View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-17
 * @see    controller.view.modal.delete.base.requirement.ControllerViewDeleteRequirement
 * @see    model.structural.base.requirement.Requirement
 * @see    view.modal.delete.ViewDelete
 */
public final class ViewDeleteRequirement extends ViewDelete {
    private final Requirement requirement;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param requirement Requirement.
     */
    public ViewDeleteRequirement(PanelModeling panel, Requirement requirement) {
        super(panel);
        this.requirement = requirement;
        this.controller  = new ControllerViewDeleteRequirement(this);
        this.title       = "Delete Requirement";
        initComponents();
        addComponents();
    }

    @Override
    public void addComponents() {
        super.addComponents(requirement.toString());
    }
    
    /**
     * Method responsible for returning the Requirement.
     * @return Requirement.
     */
    public Requirement getRequirement() {
        return requirement;
    }
}